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
import moarcarts.MoarCarts;
import moarcarts.behaviors.CartDispenserBehavior;
import moarcarts.config.ConfigSettings;
import moarcarts.entities.EntityMinecartBase;
import moarcarts.entities.EntityMinecartTEBase;
import mods.railcraft.api.carts.CartTools;
import mods.railcraft.api.core.items.IMinecartItem;
import net.minecraft.block.BlockDispenser;
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
	public ItemMinecartBase(String mod, String name)
	{
		super(0);
		this.setUnlocalizedName(name);
		this.setTextureName(MoarCarts.MODID + ":" + mod + "/" + name);
		this.setCreativeTab(CreativeTabs.tabTransport);
		this.setMaxStackSize(ConfigSettings.getMinecartStackSize());
		BlockDispenser.dispenseBehaviorRegistry.putObject(this, new CartDispenserBehavior());
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int i, int j, int k, int l, float par8, float par9, float par10) {
		return placeCart(itemStack, world, i, j, k, this.getEntityFromItem(world, itemStack));
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
		EntityMinecartBase entityMinecart = getEntityFromItem(world, itemStack);
		CartTools.setCartOwner(entityMinecart, gameProfile);
		if (placeCart(itemStack, world, posX, posY, posZ, entityMinecart))
		{
			return entityMinecart;
		}
		return null;
	}

	public boolean placeCart(ItemStack itemStack, World world, int posX, int posY, int posZ, EntityMinecartBase entityMinecart)
	{
		if (itemStack != null && BlockUtils.isRailBlock(world.getBlock(posX, posY, posZ)))
		{
			if(itemStack.hasDisplayName())
			{
				entityMinecart.setMinecartName(itemStack.getDisplayName());
			}

			if (!world.isRemote)
			{
				entityMinecart.posX = (float)posX + 0.5F;
				entityMinecart.posY = (float)posY + 0.5F;
				entityMinecart.posZ = (float)posZ + 0.5F;
				if(entityMinecart instanceof EntityMinecartTEBase)
				{
					EntityMinecartTEBase entityMinecartTEBase = (EntityMinecartTEBase)entityMinecart;
					if(itemStack.hasTagCompound() && entityMinecartTEBase.shouldSaveDataToItem())
					{
						entityMinecartTEBase.getTileEntity().readFromNBT(itemStack.getTagCompound());
						world.spawnEntityInWorld(entityMinecartTEBase);
					}
				} else
				{
					world.spawnEntityInWorld(entityMinecart);
				}

			}
			--itemStack.stackSize;
			return true;
		}
		return false;
	}

	public abstract EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack);
}
