package moarcarts.mods.mfr;

import boilerplate.common.modcompat.ModCompat;
import boilerplate.common.utils.helpers.RegistryHelper;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import moarcarts.MoarCarts;
import moarcarts.mods.mfr.entities.EntityMinecartDSU;
import moarcarts.mods.mfr.items.ItemMinecartDSU;
import moarcarts.recipes.NBTCartRecipe;

/**
 * @author SkySom
 */
public class MFRCompat extends ModCompat
{
	public static ItemMinecartDSU ITEM_MINECART_DSU;
	@Override
	public String getName()
	{
		return "MineFactory Reload";
	}

	@Override
	public boolean areRequirementsMet()
	{
		return Loader.isModLoaded("MineFactoryReloaded");
	}

	@Override
	public String dependencies()
	{
		return "after:MineFactoryReloaded;";
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		ITEM_MINECART_DSU = new ItemMinecartDSU();
		RegistryHelper.registerItem(ITEM_MINECART_DSU, MoarCarts.MODID);
		RegistryHelper.registerEntity(MoarCarts.instance, EntityMinecartDSU.class, "entityminecartdsu");
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_DSU, EntityMinecartDSU.dsu, 3));
	}
}
