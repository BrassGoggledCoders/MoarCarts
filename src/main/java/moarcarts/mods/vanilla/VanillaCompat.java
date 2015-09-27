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
package moarcarts.mods.vanilla;

import boilerplate.common.modcompat.ModCompat;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import moarcarts.MoarCarts;
import moarcarts.mods.vanilla.entities.EntityMinecartEnderChest;
import moarcarts.mods.vanilla.items.ItemMinecartEnderChest;
import moarcarts.utils.EntityUtils;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * @author SkySom
 */
public class VanillaCompat extends ModCompat
{
	public static Item ITEM_MINECART_ENDERCHEST;

	@Override
	public String getName()
	{
		return "Vanilla";
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		ITEM_MINECART_ENDERCHEST = new ItemMinecartEnderChest();
		GameRegistry.registerItem(ITEM_MINECART_ENDERCHEST, MoarCarts.MODID + "_" + ITEM_MINECART_ENDERCHEST.getUnlocalizedName());
		EntityUtils.registerEntity(EntityMinecartEnderChest.class, "EntityMinecartEnderChest");
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		GameRegistry.addShapelessRecipe(new ItemStack(ITEM_MINECART_ENDERCHEST), Blocks.ender_chest, Items.minecart);
	}
}
