package moarcarts.mods.waila;

import boilerplate.common.modcompat.ModCompat;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;

/**
 * @author SkySom
 */
public class WailaCompat extends ModCompat
{
	@Override
	public String getName()
	{
		return "Waila";
	}

	@Override
	public boolean areRequirementsMet()
	{
		return Loader.isModLoaded("Waila");
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		FMLInterModComms.sendMessage("Waila", "register", "moarcarts.mods.waila.Register.callback");
	}
}
