/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the MoarCarts Mod for Minecraft.
 *
 * MoarCarts is open-source and is distributed under the MIT License.
 *
 * MoarCarts is based on the original ExtraCarts Mod created by ScottDTA and SkySom.
 * ExtraCarts (c) ScottDTA 2014
 * (http://forum.feed-the-beast.com/threads/1-7-10-b0-7-0-extra-carts.47925/)
 *
 */
package moarcarts.mods.vanilla.entities;

import moarcarts.entities.EntityMinecartBase;
import moarcarts.mods.vanilla.items.ItemMinecartEnderChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class EntityMinecartEnderChest extends EntityMinecartBase
{
	public EntityMinecartEnderChest(World world)
	{
		super(world, Blocks.ender_chest, 0);
	}

	@Override
	public ItemStack getCartItem()
	{
		return new ItemStack(new ItemMinecartEnderChest());
	}

	@Override
	public boolean interactFirst(EntityPlayer player) {
		InventoryEnderChest inventoryenderchest = player.getInventoryEnderChest();

		if (!this.worldObj.isRemote && !player.isSneaking()) {
			player.displayGUIChest(inventoryenderchest);
		}
		return true;
	}
}
