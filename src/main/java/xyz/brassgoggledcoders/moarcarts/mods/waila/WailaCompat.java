package xyz.brassgoggledcoders.moarcarts.mods.waila;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import xyz.brassgoggledcoders.boilerplate.lib.common.modules.Module;

/**
 * @author SkySom
 */
public class WailaCompat extends Module
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
		FMLInterModComms.sendMessage("Waila", "register", "xyz.brassgoggledcoders.moarcarts.mods.waila.Register.callback");
	}
}
