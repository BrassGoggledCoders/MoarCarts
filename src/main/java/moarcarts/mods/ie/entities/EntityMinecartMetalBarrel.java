package moarcarts.mods.ie.entities;

import moarcarts.items.ItemMinecartBase;
import moarcarts.mods.ie.IEModCompat;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class EntityMinecartMetalBarrel extends EntityMinecartWoodenBarrel
{
	public EntityMinecartMetalBarrel(World world)
	{
		super(world, 7);
	}

	@Override
	public ItemMinecartBase getItem()
	{
		return IEModCompat.ITEM_MINECART_METALBARREL;
	}

}
