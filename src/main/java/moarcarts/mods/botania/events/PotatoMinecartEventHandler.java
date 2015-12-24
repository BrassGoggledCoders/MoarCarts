package moarcarts.mods.botania.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityMinecartEmpty;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.item.TinyPotatoRenderEvent;

/**
 * @author SkySom
 */
public class PotatoMinecartEventHandler
{
	EntityMinecartEmpty entityMinecartEmpty;

	@SubscribeEvent
	public void onPotatoRender(TinyPotatoRenderEvent event)
	{
		if(event.name.equalsIgnoreCase("Sky_Som"))
		{
			entityMinecartEmpty = new EntityMinecartEmpty(event.tile.getWorldObj(), 0, 0, 0);
			GL11.glPushMatrix();
			GL11.glRotated(180, 1, 0, 0);
			GL11.glRotatef(90, 0, 1, 0);
			GL11.glTranslated(0,-1.5,0);
			GL11.glScalef(.5f,.5f,.5f);
			RenderManager.instance.renderEntityWithPosYaw(entityMinecartEmpty, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
			GL11.glPopMatrix();
		}
	}
}
