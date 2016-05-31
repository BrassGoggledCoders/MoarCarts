package xyz.brassgoggledcoders.moarcarts.mods.rails.events;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.event.entity.minecart.MinecartInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.brassgoggledcoders.moarcarts.MoarCarts;

public class CheckCartDirection
{
	@SubscribeEvent
	public void checkCartFacing(MinecartInteractEvent event)
	{
		MoarCarts.logger.devInfo(event.minecart.getHorizontalFacing().getName());
		MoarCarts.logger.devInfo(EnumFacing.fromAngle(event.minecart.prevRotationYaw) + "");
		MoarCarts.logger.devInfo(event.minecart.getRollingDirection() + "");
	}
}
