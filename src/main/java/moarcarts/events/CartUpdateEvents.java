package moarcarts.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import moarcarts.entities.EntityMinecartTEBase;
import net.minecraft.client.Minecraft;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import java.util.Random;

/**
 * @author SkySom
 */
public class CartUpdateEvents
{
	Random random = new Random();

	@SubscribeEvent
	public void renderOverLay(RenderGameOverlayEvent.Pre event)
	{
		MovingObjectPosition movingObjectPosition = Minecraft.getMinecraft().objectMouseOver;
		if(movingObjectPosition.typeOfHit == MovingObjectType.ENTITY)
		{
			if(movingObjectPosition.entityHit instanceof EntityMinecartTEBase)
			{
				EntityMinecartTEBase minecartTEBase = (EntityMinecartTEBase)movingObjectPosition.entityHit;
				if(random.nextInt(20) == 0 || minecartTEBase.isDirty())
				{
					minecartTEBase.requestClientUpdate();
				}
			}
		}
	}
}
