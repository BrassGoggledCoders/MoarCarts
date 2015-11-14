package moarcarts.entities;

import mods.railcraft.api.carts.IFluidCart;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * @author SkySom
 */
public abstract class EntityMinecartFluidInventoryTEBase extends EntityMinecartInventoryTEBase
		implements IFluidCart, IFluidHandler
{
	private static int IS_FILLING = 29;

	public EntityMinecartFluidInventoryTEBase(World world, Block cartBlock, int metadata)
	{
		super(world, cartBlock, metadata);
	}
	
	public IFluidHandler getFluidTileEntity() 
	{
		return (IFluidHandler)this.getTileEntity();	
	}

	@Override
	public void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(IS_FILLING, Byte.valueOf((byte) 0));
	}

	@Override
	public boolean canPassFluidRequests(Fluid fluid)
	{
		return true;
	}

	@Override
	public boolean canAcceptPushedFluid(EntityMinecart entityMinecart, Fluid fluid)
	{
		return this.getFluidTileEntity().canFill(ForgeDirection.UNKNOWN, fluid);
	}

	@Override
	public boolean canProvidePulledFluid(EntityMinecart entityMinecart, Fluid fluid)
	{
		return this.getFluidTileEntity().canDrain(ForgeDirection.UNKNOWN, fluid);
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		return this.getFluidTileEntity().fill(from, resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	{
		return this.getFluidTileEntity().drain(from, resource, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	{
		return this.getFluidTileEntity().drain(from, maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid)
	{
		return this.getFluidTileEntity().canFill(from, fluid);
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid)
	{
		return this.getFluidTileEntity().canDrain(from, fluid);
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from)
	{
		return this.getFluidTileEntity().getTankInfo(from);
	}

	public boolean isFilling()
	{
		return dataWatcher.getWatchableObjectByte(IS_FILLING) != 0;
	}

	@Override
	public void setFilling(boolean isFilling)
	{
		dataWatcher.updateObject(IS_FILLING, Byte.valueOf(isFilling ? 1 : (byte) 0));
	}
}
