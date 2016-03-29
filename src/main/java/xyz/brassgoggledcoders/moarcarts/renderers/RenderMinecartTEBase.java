package xyz.brassgoggledcoders.moarcarts.renderers;

import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartTEBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderMinecart;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraftforge.fml.client.registry.IRenderFactory;

/**
 * @author SkySom
 */
public class RenderMinecartTEBase<T extends EntityMinecartTEBase> extends RenderMinecart<EntityMinecartTEBase>
{
	public enum Factory implements IRenderFactory<EntityMinecartTEBase>
	{
		INSTANCE;

		@Override
		@SuppressWarnings("unchecked")
		public Render<? super EntityMinecartTEBase> createRenderFor(RenderManager manager) {
			return new RenderMinecartTEBase(manager);
		}
	}

	public RenderMinecartTEBase(RenderManager renderManagerIn)
	{
		super(renderManagerIn);
	}

	public void doRender(EntityMinecartTEBase entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		GlStateManager.pushMatrix();
		this.bindEntityTexture(entity);
		long i = (long)entity.getEntityId() * 493286711L;
		i = i * i * 4392167121L + i * 98761L;
		float f = (((float)(i >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
		float f1 = (((float)(i >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
		float f2 = (((float)(i >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
		GlStateManager.translate(f, f1, f2);
		double d0 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)partialTicks;
		double d1 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)partialTicks;
		double d2 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)partialTicks;
		double d3 = 0.30000001192092896D;
		Vec3d vec3 = entity.func_70489_a(d0, d1, d2);
		float f3 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;

		if (vec3 != null)
		{
			Vec3d vec31 = entity.func_70495_a(d0, d1, d2, d3);
			Vec3d vec32 = entity.func_70495_a(d0, d1, d2, -d3);

			if (vec31 == null)
			{
				vec31 = vec3;
			}

			if (vec32 == null)
			{
				vec32 = vec3;
			}

			x += vec3.xCoord - d0;
			y += (vec31.yCoord + vec32.yCoord) / 2.0D - d1;
			z += vec3.zCoord - d2;
			Vec3d vec33 = vec32.addVector(-vec31.xCoord, -vec31.yCoord, -vec31.zCoord);

			if (vec33.lengthVector() != 0.0D)
			{
				vec33 = vec33.normalize();
				entityYaw = (float)(Math.atan2(vec33.zCoord, vec33.xCoord) * 180.0D / Math.PI);
				f3 = (float)(Math.atan(vec33.yCoord) * 73.0D);
			}
		}

		GlStateManager.translate((float)x, (float)y + 0.375F, (float)z);
		GlStateManager.rotate(180.0F - entityYaw, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(-f3, 0.0F, 0.0F, 1.0F);
		float f5 = (float)entity.getRollingAmplitude() - partialTicks;
		float f6 = entity.getDamage() - partialTicks;

		if (f6 < 0.0F)
		{
			f6 = 0.0F;
		}

		if (f5 > 0.0F)
		{
			GlStateManager.rotate(MathHelper.sin(f5) * f5 * f6 / 10.0F * (float)entity.getRollingDirection(), 1.0F, 0.0F, 0.0F);
		}
		int j = entity.getDisplayTileOffset();

		GlStateManager.pushMatrix();
		this.bindTexture(TextureMap.locationBlocksTexture);
		float f4 = 0.75F;
		GlStateManager.scale(f4, f4, f4);
		GlStateManager.translate(-0.5F, (float)(j - 8) / 16.0F, 0.5F);

		switch(entity.getRenderMethod())
		{
			case TESR:
				renderTESRModel(entity);
				break;
			case ISBRH:
			case VMC:
				renderBlockModel(entity, partialTicks);
				break;
			case CUSTOM:
				break;
			case COMBO:
				renderTESRModel(entity);
				renderBlockModel(entity, partialTicks);
				break;
		}

		GlStateManager.popMatrix();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.bindEntityTexture(entity);

		GlStateManager.scale(-1.0F, -1.0F, 1.0F);
		this.modelMinecart.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GlStateManager.popMatrix();
	}

	protected void renderTESRModel(EntityMinecartTEBase entity)
	{
		GlStateManager.pushMatrix();
		GlStateManager.rotate(90F, 0F, 1F, 0F);
		TileEntityRendererDispatcher.instance.renderTileEntityAt(entity.getTileEntity(), 0, 0, 0, 0.0F);
		GlStateManager.enableRescaleNormal();
		GlStateManager.popMatrix();
	}

	protected void renderBlockModel(EntityMinecartTEBase entity, float partialTicks)
	{
		GlStateManager.pushMatrix();
		IBlockState iblockstate = entity.getDisplayTile();

		if (iblockstate.getRenderType() != EnumBlockRenderType.INVISIBLE)
		{
			this.func_188319_a(entity, partialTicks, iblockstate);
		}
		GlStateManager.popMatrix();
	}
}

	/*
	protected String haloString;
	private Minecraft mc = Minecraft.getMinecraft();

	public RenderMinecartTEBase(RenderManager renderManagerIn)
	{
		super(renderManagerIn);
	}

	public void doRender(EntityMinecartTEBase entityMinecart, double posX, double posY, double posZ, float p_76986_8_, float partial)
	{
		if(entityMinecart != null)
		{
			this.field_94145_f.blockAccess = entityMinecart.getFakeWorld();
			GL11.glPushMatrix();
			this.bindEntityTexture(entityMinecartTEBase);
			long i = (long) entityMinecartTEBase.getEntityId() * 493286711L;
			i = i * i * 4392167121L + i * 98761L;
			float f2 = (((float) (i >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
			float f3 = (((float) (i >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
			float f4 = (((float) (i >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
			GL11.glTranslatef(f2, f3, f4);
			double d3 = entityMinecartTEBase.lastTickPosX
					+ (entityMinecartTEBase.posX - entityMinecartTEBase.lastTickPosX) * (double) partial;
			double d4 = entityMinecartTEBase.lastTickPosY
					+ (entityMinecartTEBase.posY - entityMinecartTEBase.lastTickPosY) * (double) partial;
			double d5 = entityMinecartTEBase.lastTickPosZ
					+ (entityMinecartTEBase.posZ - entityMinecartTEBase.lastTickPosZ) * (double) partial;
			double d6 = 0.30000001192092896D;
			Vec3 vec3 = entityMinecartTEBase.func_70489_a(d3, d4, d5);
			float f5 = entityMinecartTEBase.prevRotationPitch
					+ (entityMinecartTEBase.rotationPitch - entityMinecartTEBase.prevRotationPitch) * partial;

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

				posX += vec3.xCoord - d3;
				posY += (vec31.yCoord + vec32.yCoord) / 2.0D - d4;
				posZ += vec3.zCoord - d5;
				Vec3 vec33 = vec32.addVector(-vec31.xCoord, -vec31.yCoord, -vec31.zCoord);

				if(vec33.lengthVector() != 0.0D)
				{
					vec33 = vec33.normalize();
					p_76986_8_ = (float) (Math.atan2(vec33.zCoord, vec33.xCoord) * 180.0D / Math.PI);
					f5 = (float) (Math.atan(vec33.yCoord) * 73.0D);
				}
			}


			GL11.glTranslatef((float) posX, (float) posY, (float) posZ);
			if(entityMinecartTEBase.showHalo() && !entityMinecartTEBase.getHaloString().isEmpty()) {
				this.renderHalo(entityMinecartTEBase, entityMinecartTEBase.getHaloString());
			}
			preRenderEffects(entityMinecartTEBase);
			GL11.glRotatef(180.0F - p_76986_8_, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-f5, 0.0F, 0.0F, 1.0F);
			float f7 = (float) entityMinecartTEBase.getRollingAmplitude() - partial;
			float f8 = entityMinecartTEBase.getDamage() - partial;


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
					this.renderVMCMethod(entityMinecartTEBase, block, metadata, partial);
					break;
				case TESR:
					this.renderTESRMethod(entityMinecartTEBase);
					break;
				case ISBRH:
					this.renderISBRH(entityMinecartTEBase, block, offset);
					break;
				case CUSTOM:
					this.renderCustom(entityMinecartTEBase, block, partial);
					GL11.glEnable(GL12.GL_RESCALE_NORMAL);
					break;
				default:
					break;
			}

			GL11.glPopMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.bindEntityTexture(entityMinecart);
			GL11.glScalef(-1.0F, -1.0F, 1.0F);
			this.modelMinecart.render(entityMinecart, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

			GL11.glPopMatrix();
		} else
		{
			super.doRender(entityMinecart, posX, posY, posZ, p_76986_8_, partial);
		}
	}

	protected void preRenderEffects(EntityMinecartTEBase entityMinecartTEBase)
	{
	}

	protected void renderCustom(EntityMinecartTEBase entityMinecart, Block block, float partial)
	{
	}

	protected void renderISBRH(EntityMinecartTEBase entityMinecartTEBase, Block block, int offset)
	{
		renderSidesFromTile(block);
	}

	protected void renderTESRMethod(EntityMinecartTEBase entityMinecartTEBase)
	{
		GL11.glRotated(90D, 0D, 1D, 0D);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		TileEntityRendererDispatcher.instance.renderTileEntityAt(entityMinecartTEBase.getTileEntity(), 0, 0, 0, 0.0F);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	}

	protected void renderVMCMethod(EntityMinecartTEBase entityMinecartTEBase, Block block, int data, float p_76986_9_)
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
		this.field_94145_f.setRenderBounds(0,0,0,1,1,1);

		Tessellator tessellator = Tessellator.instance;
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
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
		this.field_94145_f.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, this.getBlockIcon(block, 2));

		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		this.field_94145_f.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, this.getBlockIcon(block, 3));
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(-1.0F, 0.0F, 0.0F);
		this.field_94145_f.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, this.getBlockIcon(block, 4));
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		this.field_94145_f.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, this.getBlockIcon(block, 5));
		tessellator.draw();

		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

	public void renderHalo(EntityMinecartTEBase entityMinecartTEBase, String text)
	{
		func_147906_a(entityMinecartTEBase, text, 0, 0, 0, 64);
	} */

