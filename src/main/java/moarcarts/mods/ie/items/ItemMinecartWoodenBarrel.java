package moarcarts.mods.ie.items;

import moarcarts.MoarCarts;
import moarcarts.entities.EntityMinecartBase;
import moarcarts.items.ItemMinecartBase;
import moarcarts.mods.ie.entities.EntityMinecartWoodenBarrel;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class ItemMinecartWoodenBarrel extends ItemMinecartBase
{
	public ItemMinecartWoodenBarrel()
	{
		super();
		this.setUnlocalizedName("minecartwoodenbarrel");
		this.setTextureName(MoarCarts.MODID + ":defaultcart");
	}

	@Override
	public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack)
	{
		return new EntityMinecartWoodenBarrel(world);
	}
}
