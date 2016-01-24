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

import moarcarts.config.ConfigHandler;
import moarcarts.events.CartUpdateEvents;
import moarcarts.items.MoarCartsCreativeTab;
import moarcarts.mods.botania.BotaniaCompat;
import moarcarts.mods.ironchest.IronChestCompat;
import moarcarts.mods.vanilla.VanillaCompat;
import moarcarts.mods.waila.WailaCompat;
import moarcarts.network.PacketHandler;
import moarcarts.proxies.CommonProxy;
import moarcarts.recipes.NBTCartRecipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.RecipeSorter;
import xyz.brassgoggledcoders.boilerplate.client.guis.GuiHandler;
import xyz.brassgoggledcoders.boilerplate.common.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.common.modcompat.CompatibilityHandler;
import xyz.brassgoggledcoders.boilerplate.common.utils.ModLogger;

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
	public static final String DEPENDENCIES = "required-after:boilerplate@[1.7.10-6.2.0.0,);after:railcraft;after:Avaritia;"
			+ "after:ImmersiveEngineering@[0.6.5,);";

	public static CompatibilityHandler compatibilityHandler;
	public static GuiHandler guiHandler;
	public static ModLogger logger;
	public static PacketHandler packetHandler;

	public static MoarCartsCreativeTab moarcartsTab = new MoarCartsCreativeTab();

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
		guiHandler = new GuiHandler(this);
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
		//TODO: Railcraft Compat
		//compatibilityHandler.addModCompat(new RailcraftCompat());
		//TODO: RF Compat (IE Flux)
		//compatibilityHandler.addModCompat(new RFCompat());
		compatibilityHandler.addModCompat(new IronChestCompat());
		//TODO: IE Compat
		//compatibilityHandler.addModCompat(new IEModCompat());
		//TODO: MFR Compat
		//compatibilityHandler.addModCompat(new MFRCompat());
		//TODO: Minechem Compat
		//compatibilityHandler.addModCompat(new MinechemCompat());
		compatibilityHandler.addModCompat(new BotaniaCompat());
		//TODO: Avaritia Compat
		//compatibilityHandler.addModCompat(new AvaritiaCompat());
		compatibilityHandler.addModCompat(new WailaCompat());
	}

	@Override
	public String getModID()
	{
		return MODID;
	}

	@Override
	public Object getInstance()
	{
		return MoarCarts.instance;
	}
}