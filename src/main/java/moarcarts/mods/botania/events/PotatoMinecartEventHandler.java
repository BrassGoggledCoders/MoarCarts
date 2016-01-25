package moarcarts.mods.botania.events;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityMinecartEmpty;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.item.TinyPotatoRenderEvent;
import xyz.brassgoggledcoders.boilerplate.client.ClientHelper;

/**
 * @author SkySom
 */
public class PotatoMinecartEventHandler
{
	EntityMinecartEmpty entityMinecartEmpty;
	RenderManager renderManager;

	@SubscribeEvent
	public void onPotatoRender(TinyPotatoRenderEvent event)
	{
		if(event.name.equalsIgnoreCase("Sky_Som") || event.name.equalsIgnoreCase("SkySom"))
		{
			entityMinecartEmpty = new EntityMinecartEmpty(event.tile.getWorld(), 0, 0, 0);
			GL11.glPushMatrix();
			GL11.glRotatef(180F, 1F, 0F, 0F);
			GL11.glRotatef(90F, 0F, 1F, 0F);
			GL11.glTranslatef(0.035F, -1.175F, 0F);
			GL11.glRotatef(155F, 0F, 0F, 1F);
			GL11.glScalef(0.275F,0.275F,0.275F);
			ClientHelper.mc().getRenderManager().renderEntityWithPosYaw(entityMinecartEmpty, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);

			GL11.glPopMatrix();
		}
	}
}
