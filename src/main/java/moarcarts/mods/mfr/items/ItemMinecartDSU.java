package moarcarts.mods.mfr.items;

import moarcarts.entities.EntityMinecartBase;
import moarcarts.items.ItemMinecartBase;
import moarcarts.mods.mfr.entities.EntityMinecartDSU;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class ItemMinecartDSU extends ItemMinecartBase
{
	public ItemMinecartDSU()
	{
		super("mfr", "minecartdsu");
	}

	@Override
	public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack)
	{
		return new EntityMinecartDSU(world);
	}
}