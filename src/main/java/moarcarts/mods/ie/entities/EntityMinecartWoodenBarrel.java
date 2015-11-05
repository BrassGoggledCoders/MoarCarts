package moarcarts.mods.ie.entities;

import blusunrize.immersiveengineering.common.IEContent;
import blusunrize.immersiveengineering.common.blocks.wooden.TileEntityWoodenBarrel;
import moarcarts.MoarCarts;
import moarcarts.entities.EntityMinecartTileEntityBase;
import moarcarts.mods.ie.items.ItemMinecartWoodenBarrel;
import mods.railcraft.api.carts.IFluidCart;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * @author SkySom
 */
public class EntityMinecartWoodenBarrel extends EntityMinecartTileEntityBase implements IFluidCart, IFluidHandler
{
	public EntityMinecartWoodenBarrel(World world)
	{
		super(world, IEContent.blockWoodenDevice, 6, 0, "Wooden Barrel Cart");
	}

	@Override
	public boolean interactFirst(EntityPlayer entityPlayer)
	{
		MoarCarts.logger.devInfo(((TileEntityWoodenBarrel)this.getTileEntity())
				.getTankInfo(ForgeDirection.NORTH)[0].fluid.amount + "");
		return super.interactFirst(entityPlayer);
	}

	@Override
	public ItemStack getCartItem()
	{
		return new ItemStack(new ItemMinecartWoodenBarrel());
	}

	public TileEntityWoodenBarrel getTileEntityWoodenBarrel()
	{
		return (TileEntityWoodenBarrel)this.getTileEntity();
	}

	@Override
	public boolean canPassFluidRequests(Fluid fluid)
	{
		return true;
	}

	@Override
	public boolean canAcceptPushedFluid(EntityMinecart entityMinecart, Fluid fluid)
	{
		return this.getTileEntityWoodenBarrel().canFill(ForgeDirection.NORTH, fluid);
	}

	@Override
	public boolean canProvidePulledFluid(EntityMinecart entityMinecart, Fluid fluid)
	{
		return this.getTileEntityWoodenBarrel().canDrain(ForgeDirection.NORTH, fluid);
	}

	@Override
	public void setFilling(boolean b)
	{
		//NO-OP
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		return this.getTileEntityWoodenBarrel().fill(from, resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	{
		return this.getTileEntityWoodenBarrel().drain(from, resource, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	{
		return this.getTileEntityWoodenBarrel().drain(from, maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid)
	{
		return this.getTileEntityWoodenBarrel().canFill(from, fluid);
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid)
	{
		return this.getTileEntityWoodenBarrel().canDrain(from, fluid);
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from)
	{
		return this.getTileEntityWoodenBarrel().getTankInfo(from);
	}
}
