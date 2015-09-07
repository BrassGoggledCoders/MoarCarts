package moarcarts;

import boilerplate.common.modcompat.CompatibilityHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import moarcarts.mods.vanilla.VanillaCompat;

@Mod(modid = ModInfo.MODID, name = ModInfo.MODNAME, version = ModInfo.MODVERSION)
public class MoarCarts
{
	@Instance("moarcarts")
	public static MoarCarts instance;
	public static CompatibilityHandler compatibilityHandler;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		initModCompatHandler();
		ConfigHandler.setConfigFile(event.getSuggestedConfigurationFile());
		ConfigHandler.init();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

	}

	public void initModCompatHandler()
	{
		compatibilityHandler = new CompatibilityHandler();
		compatibilityHandler.addModCompat(new VanillaCompat());
	}
}