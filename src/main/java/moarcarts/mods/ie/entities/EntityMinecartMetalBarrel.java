package moarcarts.mods.ie.entities;

import blusunrize.immersiveengineering.common.IEContent;
import moarcarts.entities.EntityMinecartFluidBase;
import moarcarts.mods.ie.items.ItemMinecartMetalBarrel;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class EntityMinecartMetalBarrel extends EntityMinecartFluidBase
{
	public EntityMinecartMetalBarrel(World world)
	{
		super(world, IEContent.blockMetalDevice2, 7, 0, "Metal Barrel Cart");
	}

	@Override
	public ItemStack getCartItem()
	{
		return new ItemStack(new ItemMinecartMetalBarrel());
	}

	@Override
	public RenderMethod getRenderMethod()
	{
		return RenderMethod.ISBRH;
	}
}
