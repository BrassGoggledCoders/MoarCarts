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

import boilerplate.common.IBoilerplateMod;
import boilerplate.common.modcompat.CompatibilityHandler;
import boilerplate.common.utils.ModLogger;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import moarcarts.config.ConfigHandler;
import moarcarts.mods.avaritia.AvaritiaCompat;
import moarcarts.mods.botania.BotaniaCompat;
import moarcarts.mods.ie.IEModCompat;
import moarcarts.events.CartUpdateEvents;
import moarcarts.mods.ironchest.IronChestCompat;
import moarcarts.mods.mfr.MFRCompat;
import moarcarts.mods.minechem.MinechemCompat;
import moarcarts.mods.railcraft.RailcraftCompat;
import moarcarts.mods.rf.RFCompat;
import moarcarts.mods.vanilla.VanillaCompat;
import moarcarts.mods.waila.WailaCompat;
import moarcarts.network.PacketHandler;
import moarcarts.proxies.CommonProxy;
import moarcarts.recipes.NBTCartRecipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.RecipeSorter;

/*
 * @author SkySom
 */
@Mod(modid = MoarCarts.MODID, name = MoarCarts.MODNAME, version = MoarCarts.MODVERSION, dependencies = MoarCarts.DEPENDENCIES)
public class MoarCarts implements IBoilerplateMod
{
	@Instance("moarcarts")
	public static MoarCarts instance;

	public static final String MODID = "moarcarts";
	public static final String MODNAME = "MoarCarts";
	public static final String MODVERSION = "@VERSION@";
	public static final String DEPENDENCIES = "after:boilerplate;after:railcraft;after:Avaritia;";

	public static CompatibilityHandler compatibilityHandler;
	public static GuiHandler guiHandler;
	public static ModLogger logger;
	public static PacketHandler packetHandler;

	@SidedProxy(clientSide = "moarcarts.proxies.ClientProxy", serverSide = "moarcarts.proxies.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = new ModLogger(MODID);
		packetHandler = new PacketHandler();

		initModCompatHandler();
		ConfigHandler.setConfigFile(event.getSuggestedConfigurationFile());
		ConfigHandler.init();

		compatibilityHandler.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		guiHandler = new GuiHandler();
		proxy.init(event);

		MinecraftForge.EVENT_BUS.register(new CartUpdateEvents());

		RecipeSorter.register("moarcarts:nbtcartrecipe", NBTCartRecipe.class, RecipeSorter.Category.SHAPELESS,
				"after:minecraft:shapeless");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		compatibilityHandler.postInit(event);
	}

	public void initModCompatHandler()
	{
		compatibilityHandler = new CompatibilityHandler(MoarCarts.logger);
		compatibilityHandler.addModCompat(new VanillaCompat());
		compatibilityHandler.addModCompat(new RailcraftCompat());
		compatibilityHandler.addModCompat(new RFCompat());
		compatibilityHandler.addModCompat(new IronChestCompat());
		compatibilityHandler.addModCompat(new IEModCompat());
		compatibilityHandler.addModCompat(new MFRCompat());
		compatibilityHandler.addModCompat(new MinechemCompat());
		compatibilityHandler.addModCompat(new BotaniaCompat());
		compatibilityHandler.addModCompat(new AvaritiaCompat());
		compatibilityHandler.addModCompat(new WailaCompat());
	}

	@Override
	public String getModID()
	{
		return MODID;
	}
}