package moarcarts.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import moarcarts.entities.EntityMinecartTEBase;
import net.minecraft.client.Minecraft;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
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
		MovingObjectPosition movingObjectPosition = Minecraft.getMinecraft().objectMouseOver;
		if(movingObjectPosition != null && movingObjectPosition.typeOfHit == MovingObjectType.ENTITY)
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
