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

import moarcarts.MoarCarts;
import moarcarts.mods.vanilla.blocks.BlockComparatorTrack;
import moarcarts.mods.vanilla.entities.EntityMinecartEnderChest;
import moarcarts.mods.vanilla.items.ItemMinecartEnderChest;
import moarcarts.recipes.NBTCartRecipe;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xyz.brassgoggledcoders.boilerplate.common.modcompat.ModCompat;
import xyz.brassgoggledcoders.boilerplate.common.utils.helpers.RegistryHelper;

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
		RegistryHelper.registerBlockWithDesc(BLOCK_COMPARATOR_TRACK, "Comparator Track");

		ITEM_MINECART_ENDERCHEST = new ItemMinecartEnderChest();
		RegistryHelper.registerItem(ITEM_MINECART_ENDERCHEST);
		RegistryHelper.registerEntity(MoarCarts.instance, EntityMinecartEnderChest.class, "entityminecartenderchest");
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_ENDERCHEST, Blocks.ender_chest));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void clientInit(FMLInitializationEvent event)
	{
		//MinecraftForgeClient.registerItemRenderer(ITEM_MINECART_ENDERCHEST, new RenderItemMinecartBase());
	}
}
