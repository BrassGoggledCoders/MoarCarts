package moarcarts.mods.rf.tileentities;

import xyz.brassgoggledcoders.boilerplate.common.blocks.SideType;
import xyz.brassgoggledcoders.boilerplate.common.tiles.TileEntitySided;
import xyz.brassgoggledcoders.boilerplate.common.utils.BlockUtils;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import mods.railcraft.api.carts.CartTools;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.EnumFacing;

/**
 * @author SkySom
 */
public class TileRFLoader extends TileEntitySided implements IEnergyHandler
{
	protected EnergyStorage energyStorage = new EnergyStorage(100000, 1000, 1000);

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		for(EnumFacing direction: EnumFacing.VALID_DIRECTIONS)
		{
			if(this.getSideValue(direction.ordinal()) != SideType.NONE)
			{
				int blockPosX = this.xCoord + direction.offsetX;
				int blockPosY = this.yCoord + direction.offsetY;
				int blockPosZ = this.zCoord + direction.offsetZ;

				if(this.getSideValue(direction.ordinal()) == SideType.INPUT)
				{
					IEnergyProvider energyProvider = null;
					if(BlockUtils.isRailBlock(this.worldObj.getBlock(blockPosX, blockPosY, blockPosZ)))
					{
						EntityMinecart entityMinecart = CartTools.getMinecartOnSide(this.worldObj, xCoord, yCoord,
								zCoord, 1F, direction);
						if(entityMinecart instanceof IEnergyProvider)
						{
							energyProvider = (IEnergyProvider)entityMinecart;
						}
					} else if(this.worldObj.getTileEntity(blockPosX, blockPosY, blockPosZ) instanceof IEnergyProvider)
					{
						energyProvider = (IEnergyProvider)this.worldObj.getTileEntity(blockPosX, blockPosY, blockPosZ);
					}

					if(energyProvider != null)
					{
						this.unLoadCart(direction, energyProvider);
					}
				}

				if(this.getSideValue(direction.ordinal()) == SideType.OUTPUT)
				{
					IEnergyReceiver energyReceiver = null;
					if(BlockUtils.isRailBlock(this.worldObj.getBlock(blockPosX, blockPosY, blockPosZ)))
					{
						EntityMinecart entityMinecart = CartTools.getMinecartOnSide(this.worldObj, xCoord, yCoord,
								zCoord, 1F, direction);
						if(entityMinecart instanceof IEnergyReceiver)
						{
							energyReceiver = (IEnergyReceiver)entityMinecart;
						}
					} else if(this.worldObj.getTileEntity(blockPosX, blockPosY, blockPosZ) instanceof IEnergyReceiver)
					{
						energyReceiver = (IEnergyReceiver)this.worldObj.getTileEntity(blockPosX, blockPosY, blockPosZ);
					}

					if(energyReceiver != null)
					{
						this.loadCart(direction, energyReceiver);
					}
				}
			}
		}
	}

	public void unLoadCart(EnumFacing direction, IEnergyProvider provider)
	{
		int amountToUnload = this.energyStorage.receiveEnergy(this.energyStorage.getMaxReceive(), true);
		int amountRemovedFromCart = provider.extractEnergy(direction.getOpposite(), amountToUnload, false);
		this.energyStorage.receiveEnergy(amountRemovedFromCart, false);
	}

	public void loadCart(EnumFacing direction, IEnergyReceiver receiver)
	{
		int amountToLoad = this.energyStorage.extractEnergy(this.energyStorage.getMaxExtract(), true);
		int amountLoadedIntoCart = receiver.receiveEnergy(direction.getOpposite(), amountToLoad, false);
		this.energyStorage.extractEnergy(amountLoadedIntoCart, false);
	}

	@Override
	public void readFromNBTCustom(NBTTagCompound nbtTagCompound)
	{
		super.readFromNBTCustom(nbtTagCompound);
		this.energyStorage.readFromNBT(nbtTagCompound);
	}

	@Override
	public void writeToNBTCustom(NBTTagCompound nbtTagCompound)
	{
		super.writeToNBTCustom(nbtTagCompound);
		this.energyStorage.writeToNBT(nbtTagCompound);
	}

	@Override
	public int receiveEnergy(EnumFacing EnumFacing, int amount, boolean simulate)
	{
		int received = (!worldObj.isRemote && this.canConnectEnergy(EnumFacing)) ?
				this.energyStorage.receiveEnergy(amount, simulate) : 0;
		this.sendBlockUpdate();
		return received;
	}

	@Override
	public int extractEnergy(EnumFacing EnumFacing, int amount, boolean simulate)
	{
		int extracted = (this.canConnectEnergy(EnumFacing)) ? this.energyStorage.extractEnergy(amount, simulate) : 0;
		this.sendBlockUpdate();
		return extracted;
	}

	@Override
	public int getEnergyStored(EnumFacing EnumFacing)
	{
		return this.energyStorage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(EnumFacing EnumFacing)
	{
		return this.energyStorage.getMaxEnergyStored();
	}

	@Override
	public boolean canConnectEnergy(EnumFacing EnumFacing)
	{
		return this.getSideValue(EnumFacing.ordinal()) != SideType.NONE;
	}
}
