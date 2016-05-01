package moarcarts.mods.minechem;

import boilerplate.common.modcompat.ModCompat;
import boilerplate.common.utils.helpers.RegistryHelper;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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
		RegistryHelper.registerItem(ITEM_MINECART_LEADEDCHEST);
		RegistryHelper.registerEntity(EntityMinecartLeadedChest.class, "entityminecartleadedchest");
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
