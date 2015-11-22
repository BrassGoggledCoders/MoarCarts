package moarcarts.mods.minechem.items;

import moarcarts.entities.EntityMinecartBase;
import moarcarts.items.ItemMinecartBase;
import moarcarts.mods.minechem.entities.EntityMinecartLeadedChest;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class ItemMinecartLeadedChest extends ItemMinecartBase
{
	public ItemMinecartLeadedChest()
	{
		super("minechem", "minecartleadedchest");
	}

	@Override
	public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack)
	{
		return new EntityMinecartLeadedChest(world);
	}
}
