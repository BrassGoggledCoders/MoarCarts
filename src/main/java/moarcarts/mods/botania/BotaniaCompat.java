package moarcarts.mods.botania;

import boilerplate.common.modcompat.ModCompat;
import boilerplate.common.utils.helpers.RegistryHelper;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import moarcarts.mods.botania.entities.EntityMinecartTinyPotato;
import moarcarts.mods.botania.events.ManaCartComparatorEvent;
import moarcarts.mods.botania.events.PotatoMinecartEventHandler;
import moarcarts.mods.botania.items.ItemTinyPotatoMinecart;
import moarcarts.mods.botania.renderers.RenderMinecartTinyPotato;
import moarcarts.recipes.NBTCartRecipe;
import moarcarts.renderers.RenderItemMinecartBase;
import net.minecraft.block.Block;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author SkySom
 */
public class BotaniaCompat extends ModCompat
{
	public static ItemTinyPotatoMinecart ITEM_MINECART_TINYPOTATO;
	public static Block TINYPOTATO;

	@Override
	public String getName()
	{
		return "Botania";
	}

	@Override
	public boolean areRequirementsMet()
	{
		return Loader.isModLoaded("Botania");
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new ManaCartComparatorEvent());
		ITEM_MINECART_TINYPOTATO = new ItemTinyPotatoMinecart();
		RegistryHelper.registerItem(ITEM_MINECART_TINYPOTATO);
		RegistryHelper.registerEntity(EntityMinecartTinyPotato.class, "entityminecarttinypotato");
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		TINYPOTATO = GameRegistry.findBlock("Botania", "tinyPotato");
		GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_TINYPOTATO, TINYPOTATO));

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void clientInit(FMLInitializationEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityMinecartTinyPotato.class, new RenderMinecartTinyPotato());
		MinecraftForgeClient.registerItemRenderer(ITEM_MINECART_TINYPOTATO, new RenderItemMinecartBase());
		MinecraftForge.EVENT_BUS.register(new PotatoMinecartEventHandler());
	}
}
