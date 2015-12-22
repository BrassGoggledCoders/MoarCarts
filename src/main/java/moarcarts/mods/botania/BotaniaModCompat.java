package moarcarts.mods.botania;

import boilerplate.common.modcompat.ModCompat;
import boilerplate.common.utils.helpers.RegistryHelper;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import moarcarts.MoarCarts;
import moarcarts.mods.botania.entities.EntityMinecartTinyPotato;
import moarcarts.mods.botania.events.ManaCartComparatorEvent;
import moarcarts.mods.botania.items.ItemTinyPotatoMinecart;
import moarcarts.recipes.NBTCartRecipe;
import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author SkySom
 */
public class BotaniaModCompat extends ModCompat
{
	public static ItemTinyPotatoMinecart ITEM_MINECART_TINYPOTATO;


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
		Block tinyPotato = GameRegistry.findBlock("Botania", "tinyPotato");
		GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_TINYPOTATO, tinyPotato));
	}
}
