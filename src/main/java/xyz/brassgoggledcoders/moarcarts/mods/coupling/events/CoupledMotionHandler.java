package xyz.brassgoggledcoders.moarcarts.mods.coupling.events;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraftforge.event.entity.minecart.MinecartCollisionEvent;
import net.minecraftforge.event.entity.minecart.MinecartUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.brassgoggledcoders.moarcarts.api.capabilities.couplable.CouplingCapability;
import xyz.brassgoggledcoders.moarcarts.api.capabilities.couplable.ICoupling;

public class CoupledMotionHandler
{
	@SubscribeEvent
	public void onCollision(MinecartCollisionEvent event)
	{

	}

	@SubscribeEvent
	public void onUpdate(MinecartUpdateEvent event)
	{
		ICoupling currentCoupling = event.minecart.getCapability(CouplingCapability.COUPLING_CAPABILITY, null);
		if(currentCoupling != null)
		{
			if(currentCoupling.getTrainSize() > 1)
			{
				if(currentCoupling.getCartPositionInTrain() != 0)
				{
					ICoupling frontCoupling = currentCoupling.getFrontCart();
					EntityMinecart frontCart = frontCoupling.getCartEntity();
					EntityMinecart currentCart = currentCoupling.getCartEntity();
				}
			}
		}
	}
}
