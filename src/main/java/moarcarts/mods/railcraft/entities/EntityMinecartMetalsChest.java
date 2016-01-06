package moarcarts.mods.railcraft.entities;

import moarcarts.entities.EntityMinecartInventoryTEBase;
import moarcarts.items.ItemMinecartBase;
import moarcarts.mods.railcraft.RailcraftCompat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class EntityMinecartMetalsChest extends EntityMinecartInventoryTEBase
{

	public EntityMinecartMetalsChest(World world)
	{
		super(world, 12);
	}

	@Override
	public boolean interactFirst(EntityPlayer player)
	{
		this.sendUpdateToAllAround();
		if(!player.isSneaking())
		{
			player.displayGUIChest(this);
		}
		return true;
	}

	@Override
	public ItemStack getCartItem() {
		return new ItemStack(this.getItem(), 1, 1);
	}

	@Override
	public ItemMinecartBase getItem()
	{
		return RailcraftCompat.ITEM_MINECART_MACHINEBETA;
	}

	@Override
	public boolean shouldTileUpdate()
	{
		return true;
	}
}
