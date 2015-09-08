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
package moarcarts;

import boilerplate.common.modcompat.CompatibilityHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import moarcarts.config.ConfigHandler;
import moarcarts.mods.railcraft.RailcraftCompat;
import moarcarts.mods.vanilla.VanillaCompat;
import moarcarts.utils.LoggerMoarCarts;
/*
 * @author SkySom
 */
@Mod(modid = ModInfo.MODID, name = ModInfo.MODNAME, version = ModInfo.MODVERSION, dependencies = ModInfo.DEPENDENCIES)
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
		LoggerMoarCarts.info("PreInit is complete");
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
		compatibilityHandler.addModCompat(new RailcraftCompat());
	}
}