package moarcarts.mods.avaritia;

import boilerplate.common.modcompat.ModCompat;
import boilerplate.common.utils.helpers.RegistryHelper;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import moarcarts.MoarCarts;
import moarcarts.mods.avaritia.entities.EntityMinecartInfinitato;
import moarcarts.mods.avaritia.items.ItemMinecartInfinitato;
import moarcarts.mods.avaritia.renderers.RenderMinecartInfinatato;
import moarcarts.recipes.NBTCartRecipe;
import moarcarts.renderers.RenderItemMinecartBase;
import net.minecraft.block.Block;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * @author SkySom
 */
public class AvaritiaCompat extends ModCompat
{
	public static ItemMinecartInfinitato ITEM_MINECART_INFINITATO;
	public static Block INFINITATO;

	public boolean isBotaniaModuleLoaded = false;

	@Override
	public String getName()
	{
		return "Avaritia";
	}

	@Override
	public boolean areRequirementsMet()
	{
		return Loader.isModLoaded("Avaritia");
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		isBotaniaModuleLoaded = Loader.isModLoaded("Botania");
		if(isBotaniaModuleLoaded)
		{
			ITEM_MINECART_INFINITATO = new ItemMinecartInfinitato();
			RegistryHelper.registerItem(ITEM_MINECART_INFINITATO, MoarCarts.MODID);
			RegistryHelper.registerEntity(MoarCarts.instance, EntityMinecartInfinitato.class, "entityminecartinfinitato");
		}
	}

	@Override
	public void clientInit(FMLInitializationEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityMinecartInfinitato.class, new RenderMinecartInfinatato());
	}

	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
		INFINITATO = GameRegistry.findBlock("Avaritia", "infinitato");
		if(isBotaniaModuleLoaded)
		{
			GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_INFINITATO, INFINITATO));
			MinecraftForgeClient.registerItemRenderer(ITEM_MINECART_INFINITATO, new RenderItemMinecartBase());
		}
	}
}
