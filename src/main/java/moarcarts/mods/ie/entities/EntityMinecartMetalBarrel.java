package moarcarts.mods.ie.entities;

import blusunrize.immersiveengineering.common.IEContent;
import moarcarts.entities.EntityMinecartFluidTEBase;
import moarcarts.mods.ie.items.ItemMinecartMetalBarrel;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class EntityMinecartMetalBarrel extends EntityMinecartFluidTEBase
{
	public EntityMinecartMetalBarrel(World world)
	{
		super(world, IEContent.blockMetalDevice2, 7);
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
