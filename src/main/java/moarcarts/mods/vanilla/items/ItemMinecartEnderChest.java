package moarcarts.mods.vanilla.items;

import moarcarts.MoarCarts;
import moarcarts.items.ItemMinecartBase;
import moarcarts.mods.vanilla.entities.EntityMinecartEnderChest;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class ItemMinecartEnderChest extends ItemMinecartBase
{
	public ItemMinecartEnderChest()
	{
		super();
		this.setUnlocalizedName("MinecartEnderChest");
		this.setTextureName(MoarCarts.MODID + ":base/EnderChestCart");
	}

	@Override
	public EntityMinecart getEntityFromItem(World world)
	{
		return new EntityMinecartEnderChest(world);
	}
}
