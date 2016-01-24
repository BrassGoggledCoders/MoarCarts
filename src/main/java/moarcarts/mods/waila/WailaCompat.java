package moarcarts.mods.waila;

import xyz.brassgoggledcoders.boilerplate.common.modcompat.ModCompat;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;

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
