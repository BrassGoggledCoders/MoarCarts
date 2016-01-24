package moarcarts.mods.botania;

import xyz.brassgoggledcoders.boilerplate.common.modcompat.ModCompat;
import xyz.brassgoggledcoders.boilerplate.common.utils.helpers.RegistryHelper;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import moarcarts.MoarCarts;
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
		RegistryHelper.registerItem(ITEM_MINECART_TINYPOTATO, MoarCarts.MODID);
		RegistryHelper.registerEntity(MoarCarts.instance, EntityMinecartTinyPotato.class, "entityminecarttinypotato");
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
