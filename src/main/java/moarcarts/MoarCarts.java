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

import moarcarts.blocks.BlockMinecart;
import moarcarts.config.ConfigSettings;
import moarcarts.items.MoarCartsCreativeTab;
import moarcarts.mods.ironchest.IronChestCompat;
import moarcarts.mods.vanilla.VanillaCompat;
import moarcarts.mods.waila.WailaCompat;
import moarcarts.network.EntityTileEntityUpdateMessage;
import moarcarts.proxies.CommonProxy;
import moarcarts.recipes.NBTCartRecipe;
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
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.ModLogger;

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
	public static final String DEPENDENCIES = "after:railcraft;after:Avaritia;after:ImmersiveEngineering@[0.6.5,);";

	public static ModLogger logger;

	public static MoarCartsCreativeTab moarcartsTab = new MoarCartsCreativeTab();

	@SidedProxy(clientSide = "moarcarts.proxies.ClientProxy", serverSide = "moarcarts.proxies.CommonProxy")
	public static CommonProxy proxy;

	public static BlockMinecart blockMinecart;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = new ModLogger(MODID);
		initModCompatHandler();
		BoilerplateLib.getInstance().packetHandler.registerPacket(EntityTileEntityUpdateMessage.Handler.class,
				EntityTileEntityUpdateMessage.class, Side.CLIENT);

		Configuration configuration = BoilerplateLib.getInstance().config(event);
		ConfigSettings.init(configuration);
		configuration.save();

		BoilerplateLib.getInstance().preInit(event);

		//blockMinecart = new BlockMinecart();
		//RegistryHelper.registerBlockWithDesc(blockMinecart, "minecart");
		//SafeModelLoader.loadBlockModel(blockMinecart);
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		BoilerplateLib.getInstance().init(event);
		MoarCarts.proxy.init();
		RecipeSorter.register("moarcarts:nbtcartrecipe", NBTCartRecipe.class, RecipeSorter.Category.SHAPELESS,
				"after:minecraft:shapeless");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		BoilerplateLib.getInstance().postInit(event);
	}

	public void initModCompatHandler()
	{
		BoilerplateLib.getInstance().compatibilityHandler.addModCompat(new VanillaCompat());
		//TODO: Railcraft Compat
		//compatibilityHandler.addModCompat(new RailcraftCompat());
		//TODO: RF Compat (IE Flux)
		//compatibilityHandler.addModCompat(new RFCompat());
		BoilerplateLib.getInstance().compatibilityHandler.addModCompat(new IronChestCompat());
		//TODO: IE Compat
		//compatibilityHandler.addModCompat(new IEModCompat());
		//TODO: MFR Compat
		//compatibilityHandler.addModCompat(new MFRCompat());
		//TODO: Minechem Compat
		//compatibilityHandler.addModCompat(new MinechemCompat());
		//TODO: Botania Compat
		//compatibilityHandler.addModCompat(new BotaniaCompat());
		//TODO: Avaritia Compat
		//compatibilityHandler.addModCompat(new AvaritiaCompat());
		BoilerplateLib.getInstance().compatibilityHandler.addModCompat(new WailaCompat());
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

	@Override
	public ModLogger getLogger()
	{
		return logger;
	}

	@Override
	public String getClientProxyPath()
	{
		return "xyz.brassgoggledcoders.moarcarts.proxy.ClientProxy";
	}

	@Override
	public String getCommonProxyPath()
	{
		return "xyz.brassgoggledcoders.moarcarts.proxy.CommonProxy";
	}
}