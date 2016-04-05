package xyz.brassgoggledcoders.moarcarts.mods.extras.tiles;

import net.minecraft.block.BlockHopper;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
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

		if (fluidHandler != null)
		{
			FluidStack attempt = fluidHandler.drain(EnumFacing.DOWN, getTank().getCapacity() - getTank().getFluidAmount(), false);
			FluidStack actual = drain(EnumFacing.UP, attempt, true);
			fluidHandler.drain(EnumFacing.DOWN, actual.amount, true);
		}

		return false;
	}

	private boolean transferFluidOut()
	{
		IFluidHandler fluidHandler = this.getFluidHandlerForTransfer(BlockFluidHopper.getFacing(this.getBlockMetadata()));

		if (fluidHandler != null && getTank().getFluid() != null && getTank().getFluid().getFluid() != null)
		{
			EnumFacing enumfacing = BlockHopper.getFacing(this.getBlockMetadata()).getOpposite();

			if (fluidHandler.canFill(enumfacing, getTank().getFluid().getFluid()))
			{
				int attempt = fluidHandler.fill(enumfacing, getTank().getFluid(), false);
				FluidStack actual = drain(enumfacing.getOpposite(), attempt, true);
				fluidHandler.fill(enumfacing, actual, true);
			}
		}
		return false;
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
			List<Entity> list = world.getEntitiesInAABBexcluding(null, new AxisAlignedBB(blockPos, blockPos),
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
}
