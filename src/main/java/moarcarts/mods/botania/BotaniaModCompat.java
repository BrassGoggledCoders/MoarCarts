package moarcarts.mods.botania;

import boilerplate.common.modcompat.ModCompat;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import moarcarts.mods.botania.events.ManaCartComparatorEvent;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author SkySom
 */
public class BotaniaModCompat extends ModCompat
{
	@Override
	public String getName()
	{
		return "Botania";
	}

	@Override
	public boolean areRequirementsMet()
	{
		return Loader.isModLoaded("botania");
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new ManaCartComparatorEvent());
	}
}
