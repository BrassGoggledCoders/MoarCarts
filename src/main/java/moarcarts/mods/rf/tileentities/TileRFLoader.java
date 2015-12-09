package moarcarts.mods.rf.tileentities;

import boilerplate.common.utils.BlockUtils;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import moarcarts.MoarCarts;
import mods.railcraft.api.carts.CartTools;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author SkySom
 */
public class TileRFLoader extends TileEntity implements IEnergyHandler
{
	protected int[] sideConfig={0,0,0,0,0,0};
	protected EnergyStorage energyStorage;

	public TileRFLoader()
	{
		this.energyStorage = new EnergyStorage(100000, 1000, 1000);
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		for(ForgeDirection direction: ForgeDirection.VALID_DIRECTIONS)
		{
			if(this.getSideValue(direction.ordinal()) != 0)
			{
				int blockPosX = this.xCoord + direction.offsetX;
				int blockPosY = this.yCoord + direction.offsetY;
				int blockPosZ = this.zCoord + direction.offsetZ;

				if(BlockUtils.isRailBlock(this.worldObj.getBlock(blockPosX, blockPosY, blockPosZ)))
				{
					EntityMinecart entityMinecart = CartTools.getMinecartOnSide(this.worldObj, xCoord, yCoord, zCoord,
							1F, direction);
					if(entityMinecart instanceof IEnergyHandler)
					{
						IEnergyHandler energyCart = (IEnergyHandler)entityMinecart;
						if(this.sideConfig[direction.ordinal()] == -1)
						{
							if(energyCart.canConnectEnergy(direction.getOpposite()))
							{
								MoarCarts.logger.devInfo(direction.getOpposite().toString());
								this.unLoadCart(direction, energyCart);
							}
						} else if(this.sideConfig[direction.ordinal()] == 1)
						{
							if(energyCart.canConnectEnergy(direction.getOpposite()))
							{
								this.loadCart(direction, energyCart);
							}
						}
					}
				}
			}
		}
	}

	public void unLoadCart(ForgeDirection direction, IEnergyHandler energyCart)
	{
		int amountToUnload = this.energyStorage.receiveEnergy(this.energyStorage.getMaxReceive(), true);
		int amountRemovedFromCart = energyCart.extractEnergy(direction.getOpposite(), amountToUnload, false);
		this.energyStorage.receiveEnergy(amountRemovedFromCart, true);
	}

	public void loadCart(ForgeDirection direction, IEnergyHandler energyCart)
	{
		int amountToLoad = this.energyStorage.extractEnergy(this.energyStorage.getMaxExtract(), true);
		int amountLoadedIntoCart = energyCart.receiveEnergy(direction.getOpposite(), amountToLoad, false);
		this.energyStorage.extractEnergy(amountLoadedIntoCart, true);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound)
	{
		this.sideConfig = nbtTagCompound.getIntArray("sideConfig");
		this.energyStorage.readFromNBT(nbtTagCompound);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound)
	{
		nbtTagCompound.setIntArray("sideConfig", this.sideConfig);
		this.energyStorage.writeToNBT(nbtTagCompound);
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		this.writeToNBT(nbttagcompound);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 3, nbttagcompound);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		this.readFromNBT(pkt.func_148857_g());
	}

	public void toggleSide(int side)
	{
		this.sideConfig[side]++;
		if(this.sideConfig[side]>1)
		{
			this.sideConfig[side]=-1;
		}
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType(), 0, 0);
	}

	public int getSideValue(int side)
	{
		return sideConfig[side];
	}

	@Override
	public boolean receiveClientEvent(int id, int arg)
	{
		if(id==0)
		{
			this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			return true;
		}
		return false;
	}

	@Override
	public int receiveEnergy(ForgeDirection forgeDirection, int amount, boolean simulate)
	{
		return (this.canConnectEnergy(forgeDirection)) ? this.energyStorage.receiveEnergy(amount, simulate) : 0;
	}

	@Override
	public int extractEnergy(ForgeDirection forgeDirection, int amount, boolean simulate)
	{
		return (this.canConnectEnergy(forgeDirection)) ? this.energyStorage.extractEnergy(amount, simulate) : 0;
	}

	@Override
	public int getEnergyStored(ForgeDirection forgeDirection)
	{
		return this.energyStorage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection forgeDirection)
	{
		return this.energyStorage.getMaxEnergyStored();
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection forgeDirection)
	{
		MoarCarts.logger.devInfo(forgeDirection.name());
		return this.sideConfig[forgeDirection.ordinal()] == 0;
	}
}
