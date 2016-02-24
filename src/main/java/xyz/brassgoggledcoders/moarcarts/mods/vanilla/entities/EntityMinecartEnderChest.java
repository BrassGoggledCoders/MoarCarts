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
package xyz.brassgoggledcoders.moarcarts.mods.vanilla.entities;

import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.vanilla.VanillaCompat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class EntityMinecartEnderChest extends EntityMinecartBase
{
	public EntityMinecartEnderChest(World world)
	{
		super(world, 0);
	}

	@Override
	public ItemMinecartBase getItem()
	{
		return VanillaCompat.ITEM_MINECART_ENDERCHEST;
	}

	@Override
	public boolean interactFirst(EntityPlayer player) {
		InventoryEnderChest inventoryenderchest = player.getInventoryEnderChest();

		if (!this.worldObj.isRemote && !player.isSneaking()) {
			player.displayGUIChest(inventoryenderchest);
		}
		return true;
	}

	@Override
	public boolean shouldDoDisplayTick()
	{
		return true;
	}
}
