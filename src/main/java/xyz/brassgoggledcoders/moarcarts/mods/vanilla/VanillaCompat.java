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

import xyz.brassgoggledcoders.moarcarts.mods.vanilla.blocks.BlockComparatorTrack;
import xyz.brassgoggledcoders.moarcarts.mods.vanilla.entities.EntityMinecartEnderChest;
import xyz.brassgoggledcoders.moarcarts.mods.vanilla.items.ItemMinecartEnderChest;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.brassgoggledcoders.boilerplate.lib.client.models.SafeModelLoader;
import xyz.brassgoggledcoders.boilerplate.lib.common.modcompat.ModCompat;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.helpers.RegistryHelper;

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
	}
}
