package moarcarts.mods.ie.entities;

import blusunrize.immersiveengineering.common.IEContent;
import moarcarts.entities.EntityMinecartFluidTEBase;
import moarcarts.mods.ie.items.ItemMinecartWoodenBarrel;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class EntityMinecartWoodenBarrel extends EntityMinecartFluidTEBase
{
	public EntityMinecartWoodenBarrel(World world)
	{
		super(world, IEContent.blockWoodenDevice, 6);
	}

	@Override
	public ItemStack getCartItem()
	{
		return new ItemStack(new ItemMinecartWoodenBarrel());
	}

	@Override
	public RenderMethod getRenderMethod()
	{
		return RenderMethod.ISBRH;
	}
}
