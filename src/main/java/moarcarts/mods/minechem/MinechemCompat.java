package moarcarts.mods.minechem;

import xyz.brassgoggledcoders.boilerplate.common.modcompat.ModCompat;
import xyz.brassgoggledcoders.boilerplate.common.utils.helpers.RegistryHelper;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import moarcarts.MoarCarts;
import moarcarts.mods.minechem.entities.EntityMinecartLeadedChest;
import moarcarts.mods.minechem.items.ItemMinecartLeadedChest;
import moarcarts.recipes.NBTCartRecipe;
import moarcarts.renderers.RenderItemMinecartBase;
import net.minecraft.block.Block;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * @author SkySom
 */
public class MinechemCompat extends ModCompat
{
	public static ItemMinecartLeadedChest ITEM_MINECART_LEADEDCHEST;

	public static Block LEADED_CHEST;

	@Override
	public String getName()
	{
		return "Minechem";
	}

	@Override
	public boolean areRequirementsMet()
	{
		return Loader.isModLoaded("minechem");
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		ITEM_MINECART_LEADEDCHEST = new ItemMinecartLeadedChest();
		RegistryHelper.registerItem(ITEM_MINECART_LEADEDCHEST, MoarCarts.MODID);
		RegistryHelper.registerEntity(MoarCarts.instance, EntityMinecartLeadedChest.class, "entityminecartleadedchest");
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		LEADED_CHEST = GameRegistry.findBlock("minechem", "tile.leadchest");
		GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_LEADEDCHEST, LEADED_CHEST));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void clientInit(FMLInitializationEvent event)
	{
		MinecraftForgeClient.registerItemRenderer(ITEM_MINECART_LEADEDCHEST, new RenderItemMinecartBase());
	}
}
