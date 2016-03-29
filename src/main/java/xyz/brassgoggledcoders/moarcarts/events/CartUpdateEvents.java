package xyz.brassgoggledcoders.moarcarts.events;

import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartTEBase;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

/**
 * @author SkySom
 */
public class CartUpdateEvents
{
	@SubscribeEvent
	public void renderOverLay(RenderGameOverlayEvent.Pre event)
	{
		RayTraceResult movingObjectPosition = Minecraft.getMinecraft().objectMouseOver;
		if(movingObjectPosition != null && movingObjectPosition.typeOfHit == Type.ENTITY)
		{
			if(movingObjectPosition.entityHit instanceof EntityMinecartTEBase)
			{
				EntityMinecartTEBase minecartTEBase = (EntityMinecartTEBase)movingObjectPosition.entityHit;
				minecartTEBase.setClientNeedy(true);
				minecartTEBase.markDirty();
			}
		}
	}

	@SubscribeEvent
	public void cartJoinWorld(EntityJoinWorldEvent entityJoinWorldEvent)
	{
		if(!entityJoinWorldEvent.world.isRemote && entityJoinWorldEvent.entity instanceof EntityMinecartTEBase)
		{
			((EntityMinecartTEBase) entityJoinWorldEvent.entity).sendUpdateToAllAround();
		}
	}
}
