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
import boilerplate.common.utils.helpers.RegistryHelper;
import boilerplate.common.utils.recipe.RecipeUtils;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import moarcarts.MoarCarts;
import moarcarts.mods.vanilla.entities.EntityMinecartEnderChest;
import moarcarts.mods.vanilla.items.ItemMinecartEnderChest;
import net.minecraft.init.Blocks;
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
		RegistryHelper.registerItem(ITEM_MINECART_ENDERCHEST, MoarCarts.MODID);
		RegistryHelper.registerEntity(MoarCarts.instance, EntityMinecartEnderChest.class, "entityminecartenderchest");
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		RecipeUtils.addMinecartRecipe(new ItemStack(ITEM_MINECART_ENDERCHEST), new ItemStack(Blocks.ender_chest));
	}
}
