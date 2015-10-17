package moarcarts.mods.ironchests.items;

import cpw.mods.ironchest.IronChestType;
import moarcarts.entities.EntityMinecartBase;
import moarcarts.items.ItemMinecartBase;
import moarcarts.mods.ironchests.entities.EntityMinecartIronChest;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class ItemMinecartIronChest extends ItemMinecartBase
{


	public IronChestType getIronChestType(ItemStack itemStack)
	{
		if(itemStack != null && itemStack.getItemDamage() < IronChestType.values().length) {
			return IronChestType.values()[itemStack.getItemDamage()];
		}
		return null;
	}

	@Override
	public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack)
	{
		return new EntityMinecartIronChest(world, this.getIronChestType(itemStack));
	}
}
