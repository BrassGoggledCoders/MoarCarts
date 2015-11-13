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
public abstract class EntityMinecartFluidBase extends EntityMinecartTileEntityBase implements IFluidCart, IFluidHandler
{
	public EntityMinecartFluidBase(World world, Block cartBlock, int metadata, int inventorySize, String inventoryName)
	{
		super(world, cartBlock, metadata, inventorySize, inventoryName);
	}
	
	public IFluidHandler getFluidTileEntity() 
	{
		return (IFluidHandler)this.getTileEntity();	
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
	public void setFilling(boolean b)
	{
		//NO-OP
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

	@Override
	public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Side: " + worldObj.isRemote + "\n");

		if(this.getTankInfo(ForgeDirection.NORTH)[0].fluid != null)
		{
			stringBuilder.append("Fluid: ");
			stringBuilder.append(this.getTankInfo(ForgeDirection.NORTH)[0].fluid.getLocalizedName() + "\n");
			stringBuilder.append("Amount: ");
			stringBuilder.append(this.getTankInfo(ForgeDirection.NORTH)[0].fluid.amount + "\n");
		}
		return stringBuilder.toString();
	}
}
