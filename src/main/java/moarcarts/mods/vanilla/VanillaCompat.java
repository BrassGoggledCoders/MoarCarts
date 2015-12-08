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
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import moarcarts.MoarCarts;
import moarcarts.mods.vanilla.entities.EntityMinecartEnderChest;
import moarcarts.mods.vanilla.items.ItemMinecartEnderChest;
import moarcarts.recipes.NBTCartRecipe;
import moarcarts.renderers.RenderItemMinecartTEBase;
import net.minecraft.init.Blocks;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * @author SkySom
 */
public class VanillaCompat extends ModCompat
{
	public static ItemMinecartEnderChest ITEM_MINECART_ENDERCHEST;

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
		GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_ENDERCHEST, Blocks.ender_chest));
	}

	@Override
	public void clientInit(FMLInitializationEvent event)
	{
		MinecraftForgeClient.registerItemRenderer(ITEM_MINECART_ENDERCHEST, new RenderItemMinecartTEBase());
	}
}
