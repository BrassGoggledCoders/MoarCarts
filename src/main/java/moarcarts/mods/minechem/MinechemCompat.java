package moarcarts.mods.minechem;

import boilerplate.common.modcompat.ModCompat;
import boilerplate.common.utils.helpers.RegistryHelper;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import moarcarts.MoarCarts;
import moarcarts.mods.minechem.entities.EntityMinecartLeadedChest;
import moarcarts.mods.minechem.items.ItemMinecartLeadedChest;
import moarcarts.recipes.NBTCartRecipe;
import net.minecraft.block.Block;

/**
 * @author SkySom
 */
public class MinechemCompat extends ModCompat
{
	public static ItemMinecartLeadedChest ITEM_MINECART_LEADEDCHEST;

	public static Block leadedChest = GameRegistry.findBlock("minechem", "leadchest");

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
		GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_LEADEDCHEST, leadedChest));
	}
}
