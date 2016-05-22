package xyz.brassgoggledcoders.moarcarts.mods.coupling.events;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.minecart.MinecartInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.ItemStackUtils;
import xyz.brassgoggledcoders.moarcarts.api.capabilities.couplable.CouplingCapability;
import xyz.brassgoggledcoders.moarcarts.api.capabilities.couplable.CouplingHandler;
import xyz.brassgoggledcoders.moarcarts.api.capabilities.couplable.ICoupling;
import xyz.brassgoggledcoders.moarcarts.api.capabilities.couplingdevice.CouplingDeviceCapability;
import xyz.brassgoggledcoders.moarcarts.api.capabilities.couplingdevice.CouplingDeviceHandler;
import xyz.brassgoggledcoders.moarcarts.api.capabilities.couplingdevice.ICouplingDevice;

public class CouplingCapAttachHandler
{
	@SubscribeEvent
	public void couplingAttach(AttachCapabilitiesEvent.Entity event)
	{
		if(event.getEntity() instanceof EntityMinecart)
		{
			EntityMinecart entityMinecart = (EntityMinecart) event.getEntity();
			event.addCapability(new ResourceLocation("moarcarts:coupling"), new CouplingHandler(entityMinecart));
		}
	}

	@SubscribeEvent
	public void couplingDeviceAttach(AttachCapabilitiesEvent.Item event)
	{
		if(event.getItem() == Items.stick)
		{
			event.addCapability(new ResourceLocation("moarcarts:couplingdevice"), new CouplingDeviceHandler());
		}
	}

	@SubscribeEvent
	public void interactEvent(MinecartInteractEvent event)
	{
		ItemStack heldItem = event.player.getHeldItem();
		if(ItemStackUtils.isItemNonNull(heldItem) && heldItem.hasCapability(
				CouplingDeviceCapability.COUPLING_DEVICE_CAPABILITY, null))
		{
			if(event.minecart.hasCapability(CouplingCapability.COUPLING_CAPABILITY, null))
			{
				ICoupling coupling = event.minecart.getCapability(CouplingCapability.COUPLING_CAPABILITY, null);
				ICouplingDevice couplingDevice = heldItem.getCapability(
						CouplingDeviceCapability.COUPLING_DEVICE_CAPABILITY, null);

				couplingDevice.tryCouplingCart(coupling);
			}
		}
	}
}
