package moarcarts.mods.ie.items;

import moarcarts.MoarCarts;
import moarcarts.entities.EntityMinecartBase;
import moarcarts.items.ItemMinecartBase;
import moarcarts.mods.ie.entities.EntityMinecartMetalBarrel;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class ItemMinecartMetalBarrel extends ItemMinecartBase
{
	public ItemMinecartMetalBarrel()
	{
		super();
		this.setUnlocalizedName("minecartmetalbarrel");
		this.setTextureName(MoarCarts.MODID + ":defaultcart");
	}

	@Override
	public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack)
	{
		return new EntityMinecartMetalBarrel(world);
	}
}
