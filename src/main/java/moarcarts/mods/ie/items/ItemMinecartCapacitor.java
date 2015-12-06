package moarcarts.mods.ie.items;

import moarcarts.entities.EntityMinecartBase;
import moarcarts.items.ItemMinecartBase;
import moarcarts.mods.ie.entities.EntityMinecartLVCapacitor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class ItemMinecartCapacitor extends ItemMinecartBase
{
	public ItemMinecartCapacitor()
	{
		super("ie", "minecartlvcapacitor");
	}

	@Override
	public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack)
	{
		return new EntityMinecartLVCapacitor(world);
	}
}
