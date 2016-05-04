package xyz.brassgoggledcoders.moarcarts.mods.extras.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import xyz.brassgoggledcoders.boilerplate.lib.common.tileentities.TileEntityFluidBase;
import xyz.brassgoggledcoders.moarcarts.mods.extras.block.BlockFluidHopper;

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
					flag = this.pushFluidTo(BlockFluidHopper.getFacing(this.getBlockMetadata()));
				}

				if (getTank().getFluidAmount() < getTank().getCapacity())
				{
					flag = this.pullFluidFrom(EnumFacing.UP)|| flag;
				}

				if (flag)
				{
					this.markDirty();
				}

				this.setTransferCoolDown(20);
			}
		}
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

	@Override
	public int getTransferRate()
	{
		return 500;
	}
}
