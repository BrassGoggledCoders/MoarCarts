package xyz.brassgoggledcoders.moarcarts.mods.extras.tiles;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import xyz.brassgoggledcoders.boilerplate.lib.common.tileentities.TileEntityFluidBase;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.Selectors;
import xyz.brassgoggledcoders.moarcarts.mods.extras.block.BlockFluidHopper;

import java.util.List;

public class TileFluidHopper extends TileEntityFluidBase implements ITickable
{
	private int transferCoolDown = -1;

	@Override
	public void update()
	{
		if (this.worldObj != null && !this.worldObj.isRemote)
		{
			--this.transferCoolDown;

			if (!this.isOnTransferCoolDown())
			{
				this.setTransferCoolDown(0);
				this.updateHopper();
			}
		}
	}

	private void updateHopper()
	{
		if (this.worldObj != null && !this.worldObj.isRemote)
		{
			if (!this.isOnTransferCoolDown() && BlockFluidHopper.isEnabled(this.getBlockMetadata()))
			{
				boolean flag = false;

				if (getTank().getFluidAmount() != 0)
				{
					flag = this.transferFluidOut();
				}

				if (getTank().getFluidAmount() < getTank().getCapacity())
				{
					flag = this.transferFluidIn() || flag;
				}

				if (flag)
				{
					this.markDirty();
				}

				this.setTransferCoolDown(20);
			}
		}
	}

	private boolean transferFluidIn()
	{
		IFluidHandler fluidHandler = this.getFluidHandlerForTransfer(EnumFacing.UP);
		boolean dirty = false;
		if(getTank().getFluidAmount() < getTank().getCapacity())
		{
			if(fluidHandler != null)
			{
				if(getTank().getFluidAmount() > 0)
				{
					FluidStack canPull = getTank().getFluid().copy();
					canPull.amount = getTank().getCapacity() - getTank().getFluidAmount();
					canPull.amount = Math.min(canPull.amount, getTransferRate());
					FluidStack drained = fluidHandler.drain(EnumFacing.DOWN, canPull, true);
					if(drained != null && drained.amount > 0)
					{
						getTank().fill(drained, true);
						dirty = true;
					}
				} else
				{
					FluidTankInfo[] infos = fluidHandler.getTankInfo(EnumFacing.DOWN);
					if(infos != null)
					{
						for (FluidTankInfo info : infos) {
							if(info.fluid != null && info.fluid.amount > 0)
							{
								if(canFill(EnumFacing.UP, info.fluid.getFluid()))
								{
									FluidStack canPull = info.fluid.copy();
									canPull.amount = Math.min(getTransferRate(), canPull.amount);
									FluidStack drained = fluidHandler.drain(EnumFacing.DOWN, canPull, true);
									if(drained != null && drained.amount > 0)
									{
										getTank().fill(drained, true);
										dirty = true;
									}
								}
							}
						}
					}
				}
			}
		}

		return dirty;
	}

	private boolean transferFluidOut()
	{
		IFluidHandler fluidHandler = this.getFluidHandlerForTransfer(BlockFluidHopper.getFacing(this.getBlockMetadata()));
		boolean dirty = false;
		EnumFacing dir = BlockFluidHopper.getFacing(this.getBlockMetadata());
		if(getTank().getFluidAmount() > 0) {
			if(fluidHandler != null) {
				if(fluidHandler.canFill(dir.getOpposite(), getTank().getFluid().getFluid())) {
					FluidStack push = getTank().getFluid().copy();
					push.amount = Math.min(push.amount, getTransferRate());
					int filled = fluidHandler.fill(dir.getOpposite(), push, true);
					if(filled > 0) {
						getTank().drain(filled, true);
						dirty = true;
					}
				}
			}
		}
		return dirty;
	}

	public IFluidHandler getFluidHandlerForTransfer(EnumFacing enumfacing)
	{
		return getFluidHandlerAtPosition(this.getWorld(), getPos().offset(enumfacing));
	}

	public IFluidHandler getFluidHandlerAtPosition(World world, BlockPos blockPos)
	{
		IFluidHandler fluidHandler = null;
		TileEntity tileEntity = world.getTileEntity(blockPos);

		if (tileEntity != null)
		{
			if (tileEntity instanceof IFluidHandler)
			{
				fluidHandler = (IFluidHandler)tileEntity;
			}
		}

		if (fluidHandler == null)
		{
			float sensitivity = 0.49f;
			List<Entity> list = world.getEntitiesInAABBexcluding(null, AxisAlignedBB
					.fromBounds(blockPos.getX() + sensitivity, blockPos.getY() + sensitivity, blockPos.getZ() + sensitivity,
							blockPos.getX() + 1 - sensitivity, blockPos.getY() + 1 - sensitivity, blockPos.getZ() + 1 - sensitivity),
					Selectors.IFLUID_HANDLER_ENTITIES);

			if (list.size() > 0)
			{
				fluidHandler = (IFluidHandler)list.get(world.rand.nextInt(list.size()));
			}
		}

		return fluidHandler;
	}


	@Override
	public void readFromNBTCustom(NBTTagCompound nbtTagCompound)
	{
		setTransferCoolDown(nbtTagCompound.getInteger("COOLDOWN"));
	}

	@Override
	public void writeToNBTCustom(NBTTagCompound nbtTagCompound)
	{
		nbtTagCompound.setInteger("COOLDOWN", this.transferCoolDown);
	}

	public boolean isOnTransferCoolDown()
	{
		return this.transferCoolDown > 0;
	}

	public void setTransferCoolDown(int transferCoolDown)
	{
		this.transferCoolDown = transferCoolDown;
	}

	@Override
	public int getCapacity()
	{
		return 16000;
	}

	public int getTransferRate()
	{
		return 500;
	}
}
