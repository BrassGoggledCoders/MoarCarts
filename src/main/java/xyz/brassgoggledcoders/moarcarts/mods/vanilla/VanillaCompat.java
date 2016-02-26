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

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.brassgoggledcoders.boilerplate.lib.client.models.SafeModelLoader;
import xyz.brassgoggledcoders.boilerplate.lib.common.modcompat.ModCompat;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.helpers.RegistryHelper;
import xyz.brassgoggledcoders.moarcarts.mods.vanilla.blocks.BlockComparatorTrack;
import xyz.brassgoggledcoders.moarcarts.mods.vanilla.entities.EntityMinecartEnderChest;
import xyz.brassgoggledcoders.moarcarts.mods.vanilla.items.ItemMinecartEnderChest;
import xyz.brassgoggledcoders.moarcarts.recipes.NBTCartRecipe;

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

	@Override
	public void init(FMLInitializationEvent event)
	{
		GameRegistry.addShapelessRecipe(new ItemStack(BLOCK_COMPARATOR_TRACK), new ItemStack(Blocks.detector_rail),
				new ItemStack(Items.comparator));
		GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_ENDERCHEST, Blocks.ender_chest));
	}
}
