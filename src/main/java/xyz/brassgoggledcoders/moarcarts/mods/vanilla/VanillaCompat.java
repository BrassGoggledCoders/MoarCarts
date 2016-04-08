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

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.lib.common.modules.Module;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.BlockRegistry;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.EntityRegistry;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.ItemRegistry;
import xyz.brassgoggledcoders.moarcarts.mods.vanilla.blocks.BlockComparatorTrack;
import xyz.brassgoggledcoders.moarcarts.mods.vanilla.entities.EntityMinecartEnderChest;
import xyz.brassgoggledcoders.moarcarts.mods.vanilla.items.ItemMinecartEnderChest;


/**
 * @author SkySom
 */
public class VanillaCompat extends Module
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
		BlockRegistry.registerBlock(BLOCK_COMPARATOR_TRACK);

		ITEM_MINECART_ENDERCHEST = new ItemMinecartEnderChest();
		ItemRegistry.registerItem(ITEM_MINECART_ENDERCHEST);
		EntityRegistry.registerEntity(EntityMinecartEnderChest.class);
	}
}
