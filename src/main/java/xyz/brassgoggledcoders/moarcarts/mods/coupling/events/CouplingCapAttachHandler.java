package xyz.brassgoggledcoders.moarcarts.mods.coupling.events;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.brassgoggledcoders.moarcarts.api.capabilities.couplable.CouplingHandler;

public class CouplingCapAttachHandler
{
	@SubscribeEvent
	public void CouplingAttach(AttachCapabilitiesEvent.Entity event)
	{
		if(event.getEntity() instanceof EntityMinecart)
		{
			EntityMinecart entityMinecart = (EntityMinecart) event.getEntity();
			event.addCapability(new ResourceLocation("moarcarts:coupling"), new CouplingHandler(entityMinecart));
		}
	}
}
