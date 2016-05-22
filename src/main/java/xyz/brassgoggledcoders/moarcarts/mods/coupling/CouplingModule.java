package xyz.brassgoggledcoders.moarcarts.mods.coupling;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.lib.common.modules.Module;
import xyz.brassgoggledcoders.moarcarts.api.capabilities.couplable.CouplingCapability;
import xyz.brassgoggledcoders.moarcarts.mods.coupling.events.CouplingCapAttachHandler;

public class CouplingModule extends Module
{
	@Override
	public String getName()
	{
		return "Coupling";
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		CouplingCapability.init();
		CouplingData.init();
		MinecraftForge.EVENT_BUS.register(new CouplingCapAttachHandler());
	}
}
