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
package xyz.brassgoggledcoders.moarcarts;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.RecipeSorter;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.lib.common.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.lib.common.config.ConfigEntry;
import xyz.brassgoggledcoders.boilerplate.lib.common.config.Type;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.ConfigRegistry;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.ModLogger;
import xyz.brassgoggledcoders.moarcarts.items.MoarCartsCreativeTab;
import xyz.brassgoggledcoders.moarcarts.mods.extras.ExtrasModule;
import xyz.brassgoggledcoders.moarcarts.mods.hydraulicraft.HydraulicraftModule;
import xyz.brassgoggledcoders.moarcarts.mods.ie.IEModule;
import xyz.brassgoggledcoders.moarcarts.mods.ironchest.IronChestCompat;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.NeotechModule;
import xyz.brassgoggledcoders.moarcarts.mods.rf.RFModule;
import xyz.brassgoggledcoders.moarcarts.mods.tinkers.TinkersModule;
import xyz.brassgoggledcoders.moarcarts.mods.rails.RailsModule;
import xyz.brassgoggledcoders.moarcarts.mods.vanilla.VanillaModule;
import xyz.brassgoggledcoders.moarcarts.mods.waila.WailaModule;
import xyz.brassgoggledcoders.moarcarts.network.EntityTileEntityUpdateMessage;
import xyz.brassgoggledcoders.moarcarts.proxies.CommonProxy;
import xyz.brassgoggledcoders.moarcarts.recipes.NBTCartRecipe;

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
	public static final String DEPENDENCIES = "after:IronChest;";

	public static ModLogger logger;

	public static Configuration config;
	public static MoarCartsCreativeTab moarcartsTab = new MoarCartsCreativeTab();

	@SidedProxy(clientSide = "xyz.brassgoggledcoders.moarcarts.proxies.ClientProxy", serverSide = "xyz.brassgoggledcoders.moarcarts.proxies.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = new ModLogger(MODID);
		initModuleHandler();

		BoilerplateLib.getInstance().preInitStart(event);
		BoilerplateLib.getPacketHandler().registerPacket(EntityTileEntityUpdateMessage.Handler.class,
				EntityTileEntityUpdateMessage.class, Side.CLIENT);

		ConfigRegistry.addCategoryComment("Tweaks",
				"Most these values will be overwritten by Railcraft values, if it is installed");
		ConfigRegistry.addEntry(new ConfigEntry("Tweaks", "breakOnDrop", Type.BOOLEAN, "false",
				"change to 'true' to restore vanilla behavior"));
		ConfigRegistry.addEntry(new ConfigEntry("Tweaks", "maxStackSize", Type.INTEGER, "3",
				"change the value to your desired minecart stack size, vanilla=1, default=3, max=64"));

		BoilerplateLib.getInstance().preInitEnd(event);
		MoarCarts.proxy.preInit();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		BoilerplateLib.getInstance().init(event);

		RecipeSorter.register("moarcarts:nbtcartrecipe", NBTCartRecipe.class, RecipeSorter.Category.SHAPELESS,
				"after:minecraft:shapeless");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		BoilerplateLib.getInstance().postInit(event);
	}

	public void initModuleHandler()
	{
		BoilerplateLib.getModuleHandler().addModule(new VanillaModule());
		BoilerplateLib.getModuleHandler().addModule(new IronChestCompat());
		BoilerplateLib.getModuleHandler().addModule(new WailaModule());
		BoilerplateLib.getModuleHandler().addModule(new HydraulicraftModule());
		BoilerplateLib.getModuleHandler().addModule(new NeotechModule());
		BoilerplateLib.getModuleHandler().addModule(new ExtrasModule());
		BoilerplateLib.getModuleHandler().addModule(new RFModule());
		BoilerplateLib.getModuleHandler().addModule(new IEModule());
		BoilerplateLib.getModuleHandler().addModule(new TinkersModule());
		BoilerplateLib.getModuleHandler().addModule(new RailsModule());
	}

	@Override
	public Object getInstance()
	{
		return MoarCarts.instance;
	}

	@Override
	public CreativeTabs getCreativeTab()
	{
		return moarcartsTab;
	}

	@Override
	public String getID()
	{
		return MODID;
	}

	@Override
	public String getName()
	{
		return MODNAME;
	}

	@Override
	public String getVersion()
	{
		return MODVERSION;
	}

	@Override
	public String getPrefix()
	{
		return MODID + ":";
	}
}