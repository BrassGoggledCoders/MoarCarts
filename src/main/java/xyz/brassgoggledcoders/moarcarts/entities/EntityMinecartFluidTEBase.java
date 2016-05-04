package xyz.brassgoggledcoders.moarcarts.entities;

import mods.railcraft.api.carts.IFluidCart;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fml.common.Optional;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.ComparatorUtils;

/**
 * @author SkySom
 * TODO: Railcraft IFluidcart
 */
@Optional.Interface(iface = "mods.railcraft.api.carts.IFluidCart", modid = "RailcraftAPI|carts")
public abstract class EntityMinecartFluidTEBase extends EntityMinecartTEBase implements IFluidHandler, IFluidCart
{
	private static int IS_FILLING = 29;

	public EntityMinecartFluidTEBase(World world, int metadata)
	{
		super(world, metadata);
	}

	public IFluidHandler getFluidTileEntity()
	{
		return (IFluidHandler)this.getTileEntity();
	}

	@Override
	public void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(IS_FILLING, (byte) 0);
	}

	@Override
	public int fill(EnumFacing from, FluidStack resource, boolean doFill)
	{
		return this.getFluidTileEntity().fill(from, resource, doFill);
	}

	@Override
	public FluidStack drain(EnumFacing from, FluidStack resource, boolean doDrain)
	{
		return this.getFluidTileEntity().drain(from, resource, doDrain);
	}

	@Override
	public FluidStack drain(EnumFacing from, int maxDrain, boolean doDrain)
	{
		return this.getFluidTileEntity().drain(from, maxDrain, doDrain);
	}

	@Override
	public boolean canFill(EnumFacing from, Fluid fluid)
	{
		return this.getFluidTileEntity().canFill(from, fluid);
	}

	@Override
	public boolean canDrain(EnumFacing from, Fluid fluid)
	{
		return this.getFluidTileEntity().canDrain(from, fluid);
	}

	@Override
	public FluidTankInfo[] getTankInfo(EnumFacing from)
	{
		return this.getFluidTileEntity().getTankInfo(from);
	}

	public boolean isFilling()
	{
		return dataWatcher.getWatchableObjectByte(IS_FILLING) != 0;
	}

	@Override
	@Optional.Method(modid = "RailcraftAPI|carts")
	public boolean canPassFluidRequests(Fluid fluid)
	{
		return true;
	}

	@Override
	@Optional.Method(modid = "RailcraftAPI|carts")
	public boolean canAcceptPushedFluid(EntityMinecart entityMinecart, Fluid fluid)
	{
		return this.getFluidTileEntity().canFill(EnumFacing.UP, fluid);
	}

	@Override
	@Optional.Method(modid = "RailcraftAPI|carts")
	public boolean canProvidePulledFluid(EntityMinecart entityMinecart, Fluid fluid)
	{
		return this.getFluidTileEntity().canDrain(EnumFacing.DOWN, fluid);
	}

	@Override
	@Optional.Method(modid = "RailcraftAPI|carts")
	public void setFilling(boolean isFilling)
	{
		dataWatcher.updateObject(IS_FILLING, isFilling ? 1 : (byte) 0);
	}

	@Override
	public int getComparatorInputOverride()
	{
		if(!this.getCartBlock().hasComparatorInputOverride())
		{
			return ComparatorUtils.scaleSingleFluidLevelTo(15, this);
		}
		return super.getComparatorInputOverride();
	}
}
