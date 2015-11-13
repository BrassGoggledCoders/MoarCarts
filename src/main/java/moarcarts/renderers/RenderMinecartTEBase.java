package moarcarts.renderers;

import cpw.mods.fml.client.registry.RenderingRegistry;
import moarcarts.entities.EntityMinecartTileEntityBase;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderMinecart;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

/**
 * @author SkySom
 */
public class RenderMinecartTEBase extends RenderMinecart
{
	protected EntityMinecartTileEntityBase entityMinecartTEBase;
	protected RenderingRegistry renderingRegistry;

	public RenderMinecartTEBase()
	{
		renderingRegistry = new RenderingRegistry();
	}

	public void doRender(EntityMinecart entityMinecart, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
	{
		if(entityMinecart instanceof EntityMinecartTileEntityBase)
		{
			entityMinecartTEBase = (EntityMinecartTileEntityBase) entityMinecart;
			this.field_94145_f.blockAccess = entityMinecartTEBase.getFakeWorld();
			GL11.glPushMatrix();
			this.bindEntityTexture(entityMinecartTEBase);
			long i = (long) entityMinecartTEBase.getEntityId() * 493286711L;
			i = i * i * 4392167121L + i * 98761L;
			float f2 = (((float) (i >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
			float f3 = (((float) (i >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
			float f4 = (((float) (i >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
			GL11.glTranslatef(f2, f3, f4);
			double d3 = entityMinecartTEBase.lastTickPosX
					+ (entityMinecartTEBase.posX - entityMinecartTEBase.lastTickPosX) * (double) p_76986_9_;
			double d4 = entityMinecartTEBase.lastTickPosY
					+ (entityMinecartTEBase.posY - entityMinecartTEBase.lastTickPosY) * (double) p_76986_9_;
			double d5 = entityMinecartTEBase.lastTickPosZ
					+ (entityMinecartTEBase.posZ - entityMinecartTEBase.lastTickPosZ) * (double) p_76986_9_;
			double d6 = 0.30000001192092896D;
			Vec3 vec3 = entityMinecartTEBase.func_70489_a(d3, d4, d5);
			float f5 = entityMinecartTEBase.prevRotationPitch
					+ (entityMinecartTEBase.rotationPitch - entityMinecartTEBase.prevRotationPitch) * p_76986_9_;

			if(vec3 != null)
			{
				Vec3 vec31 = entityMinecartTEBase.func_70495_a(d3, d4, d5, d6);
				Vec3 vec32 = entityMinecartTEBase.func_70495_a(d3, d4, d5, -d6);

				if(vec31 == null)
				{
					vec31 = vec3;
				}

				if(vec32 == null)
				{
					vec32 = vec3;
				}

				p_76986_2_ += vec3.xCoord - d3;
				p_76986_4_ += (vec31.yCoord + vec32.yCoord) / 2.0D - d4;
				p_76986_6_ += vec3.zCoord - d5;
				Vec3 vec33 = vec32.addVector(-vec31.xCoord, -vec31.yCoord, -vec31.zCoord);

				if(vec33.lengthVector() != 0.0D)
				{
					vec33 = vec33.normalize();
					p_76986_8_ = (float) (Math.atan2(vec33.zCoord, vec33.xCoord) * 180.0D / Math.PI);
					f5 = (float) (Math.atan(vec33.yCoord) * 73.0D);
				}
			}

			GL11.glTranslatef((float) p_76986_2_, (float) p_76986_4_, (float) p_76986_6_);
			GL11.glRotatef(180.0F - p_76986_8_, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-f5, 0.0F, 0.0F, 1.0F);
			float f7 = (float) entityMinecartTEBase.getRollingAmplitude() - p_76986_9_;
			float f8 = entityMinecartTEBase.getDamage() - p_76986_9_;

			if(f8 < 0.0F)
			{
				f8 = 0.0F;
			}

			if(f7 > 0.0F)
			{
				GL11.glRotatef(MathHelper.sin(f7) * f7 * f8 / 10.0F * (float) entityMinecartTEBase.getRollingDirection(), 1.0F, 0.0F, 0.0F);
			}

			int offset = entityMinecartTEBase.getDisplayTileOffset();
			Block block = entityMinecartTEBase.getCartBlock();
			int metadata = entityMinecartTEBase.getDisplayTileData();
			GL11.glPushMatrix();
			this.bindTexture(TextureMap.locationBlocksTexture);
			float f6 = 0.75F;
			GL11.glScalef(f6, f6, f6);
			GL11.glTranslatef(0.0F, (float) offset / 16.0F, 0.0F);
			switch(entityMinecartTEBase.getRenderMethod()) {
				case VMC:
					renderVMCMethod(entityMinecartTEBase, block, offset, metadata, p_76986_9_);
					break;
				case TESR:
					renderTESRMethod(entityMinecartTEBase, offset);
					break;
				case ISBRH:
					renderISBRH(entityMinecartTEBase, block, offset);
					break;
				default:
					break;
			}
			GL11.glPopMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.bindEntityTexture(entityMinecartTEBase);
			GL11.glScalef(-1.0F, -1.0F, 1.0F);
			this.modelMinecart.render(entityMinecartTEBase, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
		} else
		{
			super.doRender(entityMinecart, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
		}
	}

	private void renderISBRH(EntityMinecartTileEntityBase entityMinecartTEBase, Block block, int offset)
	{
		renderSidesFromTile(block);
	}

	private void renderTESRMethod(EntityMinecartTileEntityBase entityMinecartTEBase, int offset)
	{
		TileEntityRendererDispatcher.instance.renderTileEntityAt(entityMinecartTEBase.getTileEntity(),
				entityMinecartTEBase.posX, entityMinecartTEBase.posY, entityMinecartTEBase.posZ, 1.0F);
	}

	private void renderVMCMethod(EntityMinecartTileEntityBase entityMinecartTEBase, Block block, int offset,
			int data, float p_76986_9_)
	{
		if(block.getRenderType() != -1)
		{
			this.func_147910_a(entityMinecartTEBase, p_76986_9_, block, data);
		}
	}

	private IIcon getBlockIcon(Block block, int side)
	{
		return this.field_94145_f.getBlockIcon(block, entityMinecartTEBase.getFakeWorld(), 0, 0, 0, side);
	}

	private void renderSidesFromTile(Block block)
	{
		GL11.glPushMatrix();
		Tessellator tessellator = Tessellator.instance;
		block.setBlockBoundsForItemRender();
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		float f1 = 0.00F;
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1.0F, 0.0F);

		this.field_94145_f.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, this.getBlockIcon(block, 0));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		this.field_94145_f.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, this.getBlockIcon(block, 1));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1.0F);
		tessellator.addTranslation(0.0F, 0.0F, f1);
		this.field_94145_f.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, this.getBlockIcon(block, 2));
		tessellator.addTranslation(0.0F, 0.0F, -f1);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		tessellator.addTranslation(0.0F, 0.0F, -f1);
		this.field_94145_f.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, this.getBlockIcon(block, 3));
		tessellator.addTranslation(0.0F, 0.0F, f1);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1.0F, 0.0F, 0.0F);
		tessellator.addTranslation(f1, 0.0F, 0.0F);
		this.field_94145_f.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, this.getBlockIcon(block, 4));
		tessellator.addTranslation(-f1, 0.0F, 0.0F);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		tessellator.addTranslation(-f1, 0.0F, 0.0F);
		this.field_94145_f.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, this.getBlockIcon(block, 5));
		tessellator.addTranslation(f1, 0.0F, 0.0F);
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		GL11.glPopMatrix();
	}
}
