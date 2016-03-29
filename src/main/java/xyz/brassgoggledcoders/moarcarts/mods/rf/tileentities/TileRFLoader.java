package xyz.brassgoggledcoders.moarcarts.mods.rf.tileentities;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.boilerplate.lib.common.blocks.SideType;
import xyz.brassgoggledcoders.boilerplate.lib.common.tileentities.TileEntitySided;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.BlockUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SkySom
 */
public class TileRFLoader extends TileEntitySided implements IEnergyProvider, IEnergyReceiver, ITickable
{
	protected EnergyStorage energyStorage = new EnergyStorage(100000, 1000, 1000);

	@Override
	public void update()
	{
		for(EnumFacing facing: EnumFacing.VALUES)
		{
			if(this.getSideValue(facing.ordinal()) != SideType.NONE)
			{
				if(this.getSideValue(facing.ordinal()) == SideType.INPUT)
				{
					IEnergyProvider energyProvider = null;
					if(BlockUtils.isRailBlock(this.worldObj.getBlockState(pos)))
					{
						EntityMinecart entityMinecart = getMinecartsAt(worldObj, pos.offset(facing), 1f).get(0);
						if(entityMinecart instanceof IEnergyProvider)
						{
							energyProvider = (IEnergyProvider)entityMinecart;
						}
					} else if(this.worldObj.getTileEntity(pos) instanceof IEnergyProvider)
					{
						energyProvider = (IEnergyProvider)this.worldObj.getTileEntity(pos);
					}

					if(energyProvider != null)
					{
						this.unLoadCart(facing, energyProvider);
					}
				}

				if(this.getSideValue(facing.ordinal()) == SideType.OUTPUT)
				{
					IEnergyReceiver energyReceiver = null;
					if(BlockUtils.isRailBlock(this.worldObj.getBlockState(pos)))
					{
						EntityMinecart entityMinecart = getMinecartsAt(worldObj, pos.offset(facing), 1f).get(0);
						if(entityMinecart instanceof IEnergyReceiver)
						{
							energyReceiver = (IEnergyReceiver)entityMinecart;
						}
					} else if(this.worldObj.getTileEntity(pos) instanceof IEnergyReceiver)
					{
						energyReceiver = (IEnergyReceiver)this.worldObj.getTileEntity(pos);
					}

					if(energyReceiver != null)
					{
						this.loadCart(facing, energyReceiver);
					}
				}
			}
		}
	}

	public void unLoadCart(EnumFacing facing, IEnergyProvider provider)
	{
		int amountToUnload = this.energyStorage.receiveEnergy(this.energyStorage.getMaxReceive(), true);
		int amountRemovedFromCart = provider.extractEnergy(facing.getOpposite(), amountToUnload, false);
		this.energyStorage.receiveEnergy(amountRemovedFromCart, false);
	}

	public void loadCart(EnumFacing facing, IEnergyReceiver receiver)
	{
		int amountToLoad = this.energyStorage.extractEnergy(this.energyStorage.getMaxExtract(), true);
		int amountLoadedIntoCart = receiver.receiveEnergy(facing.getOpposite(), amountToLoad, false);
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
	public int receiveEnergy(EnumFacing facing, int amount, boolean simulate)
	{
		int received = (!worldObj.isRemote && this.canConnectEnergy(facing)) ?
				this.energyStorage.receiveEnergy(amount, simulate) : 0;
		this.sendBlockUpdate();
		return received;
	}

	@Override
	public int extractEnergy(EnumFacing facing, int amount, boolean simulate)
	{
		int extracted = (this.canConnectEnergy(facing)) ? this.energyStorage.extractEnergy(amount, simulate) : 0;
		this.sendBlockUpdate();
		return extracted;
	}

	@Override
	public int getEnergyStored(EnumFacing facing)
	{
		return this.energyStorage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(EnumFacing facing)
	{
		return this.energyStorage.getMaxEnergyStored();
	}

	@Override
	public boolean canConnectEnergy(EnumFacing from)
	{
		return this.getSideValue(from.ordinal()) != SideType.NONE;
	}

	public static List<EntityMinecart> getMinecartsAt(World world, BlockPos pos, float sensitivity) {
		sensitivity = Math.min(sensitivity, 0.49f);
		List entities = world.getEntitiesWithinAABB(EntityMinecart.class, AxisAlignedBB
				.fromBounds(pos.getX() + sensitivity, pos.getY() + sensitivity, pos.getZ() + sensitivity,
				pos.getX() + 1 - sensitivity, pos.getY() + 1 - sensitivity, pos.getZ() + 1 - sensitivity));
		List<EntityMinecart> carts = new ArrayList<EntityMinecart>();
		for (Object o : entities) {
			EntityMinecart cart = (EntityMinecart) o;
			if (!cart.isDead)
				carts.add((EntityMinecart) o);
		}
		return carts;
	}
}
