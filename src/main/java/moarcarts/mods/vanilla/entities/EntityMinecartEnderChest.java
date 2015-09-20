package moarcarts.mods.vanilla.entities;

import moarcarts.entities.EntityMinecartBase;
import moarcarts.mods.vanilla.items.ItemMinecartEnderChest;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author SkySom
 * @date 9/19/2015
 */
public class EntityMinecartEnderChest extends EntityMinecartBase
{
	public EntityMinecartEnderChest(World world)
	{
		super(world, 27, "moarcarts.vanilla.enderchest");
	}

	@Override
	public boolean interactFirst(EntityPlayer player)
	{
		InventoryEnderChest inventoryenderchest = player.getInventoryEnderChest();

		if (!this.worldObj.isRemote && !player.isSneaking())
		{
			player.displayGUIChest(inventoryenderchest);
		}
		return true;
	}

	@Override
	public ItemStack getCartItem()
	{
		return new ItemStack(new ItemMinecartEnderChest());
	}

	@Override
	public Block getCartBlock()
	{
		return Blocks.ender_chest;
	}
}
