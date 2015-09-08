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
package moarcarts.items;

import boilerplate.common.utils.BlockUtils;
import com.mojang.authlib.GameProfile;
import cpw.mods.fml.common.Optional;
import moarcarts.config.ConfigSettings;
import mods.railcraft.api.carts.CartTools;
import mods.railcraft.api.core.items.IMinecartItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
@Optional.Interface(modid = "RailcraftAPI|items", iface = "mods.railcraft.api.core.items.IMinecartItem")
public abstract class ItemMinecartBase extends ItemMinecart implements IMinecartItem
{
	public ItemMinecartBase(int type)
	{
		super(type);
		this.setCreativeTab(CreativeTabs.tabTransport);
		this.setMaxStackSize(ConfigSettings.getMinecartStackSize());
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int i, int j, int k, int l, float par8, float par9, float par10) {
		if (!world.isRemote)
			return false;
		return placeCart(stack, world, i, j, k, getEntityFromItem());
	}

	@Override
	@Optional.Method(modid = "RailcraftAPI|items")
	public boolean canBePlacedByNonPlayer(ItemStack itemStack)
	{
		return itemStack != null;
	}

	@Override
	@Optional.Method(modid = "RailcraftAPI|items")
	public EntityMinecart placeCart(GameProfile gameProfile, ItemStack itemStack, World world, int posX, int posY, int posZ)
	{
		EntityMinecart entityMinecart = getEntityFromItem();
		CartTools.setCartOwner(entityMinecart, gameProfile);
		if (placeCart(itemStack, world, posX, posY, posZ, entityMinecart))
		{
			return entityMinecart;
		}
		return null;
	}

	public boolean placeCart(ItemStack itemStack, World world, int posX, int posY, int posZ, EntityMinecart entityMinecart)
	{
		if (itemStack != null && BlockUtils.isRailBlock(world.getBlock(posX, posY, posZ)))
		{
			if (!world.isRemote)
			{
				entityMinecart.posX = (float)posX + 0.5F;
				entityMinecart.posY = (float)posY + 0.5F;
				entityMinecart.posZ = (float)posZ + 0.5F;
				world.spawnEntityInWorld(entityMinecart);

			}
			--itemStack.stackSize;
			return true;
		}
		return false;
	}

	public abstract EntityMinecart getEntityFromItem();
}
