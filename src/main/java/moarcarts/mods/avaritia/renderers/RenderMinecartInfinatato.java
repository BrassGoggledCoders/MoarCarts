package moarcarts.mods.avaritia.renderers;

import fox.spiteful.avaritia.compat.botania.ModelInfinitato;
import fox.spiteful.avaritia.compat.botania.TileInfinitato;
import fox.spiteful.avaritia.compat.botania.Tsundere;
import fox.spiteful.avaritia.compat.tails.InfiniteFoxes;
import moarcarts.entities.EntityMinecartTEBase;
import moarcarts.mods.botania.renderers.RenderMinecartTinyPotato;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

/**
 * @author SkySom
 */
public class RenderMinecartInfinatato extends RenderMinecartTinyPotato
{
	private static final ResourceLocation texture = new ResourceLocation("avaritia","textures/blocks/infinitato.png");
	public static final ResourceLocation halo = new ResourceLocation("avaritia", "textures/items/halo128.png");
	private static final ModelInfinitato model = new ModelInfinitato();
	public static boolean drawHalo = true;

	@Override
	protected void renderCustom(EntityMinecartTEBase entityMinecartTEBase, Block block, float partial)
	{
		GL11.glRotated(90D, 0D, 1D, 0D);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

		float partialTicks = 0;
		TileInfinitato potato = (TileInfinitato)entityMinecartTEBase.getTileEntity();
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1F, 1F, 1F, 1F);

		Minecraft mc = Minecraft.getMinecraft();

		GL11.glTranslatef(0.5F, 1.5F, 0.5F);
		GL11.glScalef(1F, -1F, -1F);
		int meta = entityMinecartTEBase.getMetadata();

		float rotY = meta * 90F - 180F;
		GL11.glRotatef(rotY, 0F, 1F, 0F);

		float jump = potato.jumpTicks*0.5f;
		if(jump > 0)
			jump += partialTicks*0.5f;

		float up = (float) -Math.abs(Math.sin(jump / 10 * Math.PI)) * 0.2F;
		float rotZ = (float) Math.sin(jump / 10 * Math.PI) * 2;



		GL11.glTranslatef(0F, up, 0F);
		GL11.glRotatef(rotZ, 0F, 0F, 1F);

		mc.renderEngine.bindTexture(texture);
		model.render();

		GL11.glPushMatrix();
		String name = potato.name.toLowerCase();
		mc.renderEngine.bindTexture(TextureMap.locationItemsTexture);
		float scale = 1F / 4F;
		GL11.glTranslatef(0F, 1F, 0F);
		GL11.glScalef(scale, scale, scale);
		if(name.equals("armstrong")) {
			GL11.glScalef(1.75F, 1.75F, 1.25F);
			GL11.glRotatef(180F, 0F, 0F, 1F);
			GL11.glTranslatef(-0.5F, -0.55F, -0.8F);
			renderIcon(Tsundere.costumes.getIconFromDamage(0));
		}
		else if(name.startsWith("moo") && name.endsWith("oon")) {
			GL11.glScalef(2.5F, 2.5F, 1.25F);
			GL11.glRotatef(180F, 0F, 0F, 1F);
			GL11.glTranslatef(-0.5F, -0.6F, -0.8F);
			renderIcon(Tsundere.costumes.getIconFromDamage(1));
		}
		else if(name.equals("egbert")) {
			GL11.glScalef(1.25F, 1.25F, 1.25F);
			GL11.glRotatef(180F, 0F, 0F, 1F);
			GL11.glTranslatef(-0.5F, -1.4F, -0.8F);
			renderIcon(Tsundere.costumes.getIconFromDamage(2));
		}
		else if(name.equals("popetato")) {
			GL11.glScalef(1.75F, 1.75F, 1.25F);
			GL11.glRotatef(180F, 0F, 0F, 1F);
			GL11.glTranslatef(-0.5F, -0, -0.8F);
			renderIcon(Tsundere.costumes.getIconFromDamage(3));
		}
		else if(name.startsWith("foxtato")) {
			InfiniteFoxes.renderInfinitatoFluff(partialTicks);
		}

		GL11.glPopMatrix();

		GL11.glRotatef(-rotZ, 0F, 0F, 1F);
		GL11.glRotatef(-rotY, 0F, 1F, 0F);
		GL11.glColor3f(1F, 1F, 1F);
		GL11.glScalef(1F, -1F, -1F);

		GL11.glPopMatrix();
	}

	protected void preRenderEffects(EntityMinecartTEBase entityMinecartTEBase)
	{
		if (drawHalo){
			Tessellator tessellator = Tessellator.instance;
			Minecraft mc = Minecraft.getMinecraft();

			mc.renderEngine.bindTexture(halo);
			GL11.glPushMatrix();

			double xdiff = (entityMinecartTEBase.posX + 0.5) - RenderManager.instance.viewerPosX;
			double ydiff = (entityMinecartTEBase.posY + 0.4) - RenderManager.instance.viewerPosY;
			double zdiff = (entityMinecartTEBase.posZ + 0.5) - RenderManager.instance.viewerPosZ;

			double len = Math.sqrt(xdiff*xdiff + ydiff*ydiff + zdiff*zdiff);

			xdiff /= len;
			ydiff /= len;
			zdiff /= len;

			GL11.glTranslated(-xdiff, ydiff, zdiff);

			GL11.glScalef(1F, -1F, -1F);

			GL11.glTranslatef(0F, -1.15F, 0F);
			GL11.glRotatef(-RenderManager.instance.playerViewY, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(RenderManager.instance.playerViewX, 1.0F, 0.0F, 0.0F);

			float f = 1.6F;
			float f1 = 0.016666668F * f;
			GL11.glScalef(f1, f1, f1);
			GL11.glDisable(GL11.GL_LIGHTING);

			GL11.glDepthMask(false);

			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			tessellator.startDrawingQuads();
			int i=60;
			tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 1.0F);
			tessellator.addVertexWithUV(-i, -i, 0.0D, 0,0);
			tessellator.addVertexWithUV(-i, i, 0.0D, 1,0);
			tessellator.addVertexWithUV(i, i, 0.0D, 1,1);
			tessellator.addVertexWithUV(i, -i, 0.0D, 0,1);
			tessellator.draw();
			GL11.glDepthMask(true);

			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			GL11.glColor4f(1F, 1F, 1F, 1F);

			GL11.glPopMatrix();
		}
	}
}
