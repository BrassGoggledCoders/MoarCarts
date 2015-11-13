package moarcarts.mods.ie.items;

import moarcarts.MoarCarts;
import moarcarts.entities.EntityMinecartBase;
import moarcarts.items.ItemMinecartBase;
import moarcarts.mods.ie.entities.EntityMinecartWoodenCrate;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class ItemMinecartWoodenCrate extends ItemMinecartBase
{
	public ItemMinecartWoodenCrate()
	{
		super();
		this.setUnlocalizedName("minecartwoodencrate");
		this.setTextureName(MoarCarts.MODID + ":defaultcart");
	}

	@Override
	public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack)
	{
		return new EntityMinecartWoodenCrate(world);
	}
}
