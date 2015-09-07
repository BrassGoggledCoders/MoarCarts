package moarcarts.items;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.common.Optional;
import moarcarts.mods.railcraft.RailcraftConfigValues;
import mods.railcraft.api.core.items.IMinecartItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Skylar on 9/7/2015.
 */
@Optional.Interface(modid = "RailcraftAPI|items", iface = "mods.railcraft.api.core.items.IMinecartItem")
public abstract class ItemMinecartBase extends ItemMinecart implements IMinecartItem
{
	public ItemMinecartBase(int type)
	{
		super(type);
		this.setCreativeTab(CreativeTabs.tabTransport);
		this.setMaxStackSize(RailcraftConfigValues.getMinecraftItemStackSize());
	}

	@Override
	public boolean canBePlacedByNonPlayer(ItemStack itemStack)
	{
		return itemStack != null;
	}

	@Override
	public EntityMinecart placeCart(GameProfile gameProfile, ItemStack itemStack, World world, int i, int i1, int i2)
	{
		return null;
	}
}
