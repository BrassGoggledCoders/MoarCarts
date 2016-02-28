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
package xyz.brassgoggledcoders.moarcarts.mods.vanilla;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import xyz.brassgoggledcoders.moarcarts.mods.vanilla.blocks.BlockComparatorTrack;
import xyz.brassgoggledcoders.moarcarts.mods.vanilla.entities.EntityMinecartEnderChest;
import xyz.brassgoggledcoders.moarcarts.mods.vanilla.items.ItemMinecartEnderChest;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.brassgoggledcoders.boilerplate.lib.client.models.SafeModelLoader;
import xyz.brassgoggledcoders.boilerplate.lib.common.modcompat.ModCompat;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.helpers.RegistryHelper;
import xyz.brassgoggledcoders.moarcarts.renderers.ModelBakeHandler;
import xyz.brassgoggledcoders.moarcarts.renderers.RenderItemMinecartBase;

/**
 * @author SkySom
 */
public class VanillaCompat extends ModCompat
{
	public static ItemMinecartEnderChest ITEM_MINECART_ENDERCHEST;
	public static BlockComparatorTrack BLOCK_COMPARATOR_TRACK;

	@Override
	public String getName()
	{
		return "Vanilla";
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		BLOCK_COMPARATOR_TRACK = new BlockComparatorTrack();
		RegistryHelper.registerBlockWithDesc(BLOCK_COMPARATOR_TRACK, "comparatortrack");
		SafeModelLoader.loadBlockModel(BLOCK_COMPARATOR_TRACK);

		ITEM_MINECART_ENDERCHEST = new ItemMinecartEnderChest();
		GameRegistry.registerItem(ITEM_MINECART_ENDERCHEST, ITEM_MINECART_ENDERCHEST.getUnlocalizedName().substring(5));
		RegistryHelper.registerEntity(EntityMinecartEnderChest.class, "entityminecartenderchest");
		SafeModelLoader.loadItemModel(ITEM_MINECART_ENDERCHEST);

		RenderItemMinecartBase renderer = RenderItemMinecartBase.getInstance();
		ClientRegistry.bindTileEntitySpecialRenderer(renderer.getTileClass(), renderer);
		ModelResourceLocation[] variants = ITEM_MINECART_ENDERCHEST.getModelDefinitions();
		ModelLoader.setCustomModelResourceLocation(ITEM_MINECART_ENDERCHEST, 0, variants[0]);
		ModelBakeHandler.getInstance().registerModelToSwap(variants[0], renderer);
		//noinspection deprecation
		ForgeHooksClient.registerTESRItemStack(ITEM_MINECART_ENDERCHEST, 0, renderer.getTileClass());
	}
}
