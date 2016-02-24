package xyz.brassgoggledcoders.moarcarts.renderers;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelMinecart;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.ItemStackUtils;
import xyz.brassgoggledcoders.moarcarts.items.ItemMinecartBase;

public abstract class RenderItemMinecartBase extends ItemSpecialRenderer
{
	private static final ResourceLocation minecartTextures = new ResourceLocation("textures/entity/minecart.png");
	protected ModelBase modelMinecart = new ModelMinecart();
	protected TileEntity renderTileEntity;

	@Override
	public void renderItem(ItemStack stack, float partialTicks)
	{
		GlStateManager.pushMatrix();
		GlStateManager.enableAlpha();

		FMLClientHandler.instance().getClient().renderEngine.bindTexture(TextureMap.locationBlocksTexture);

		GlStateManager.pushMatrix();
		GlStateManager.scale(0.75 * 0.75, 0.75 * 0.75, 0.75 * 0.75);
		GlStateManager.rotate(270, 0, 1, 0);
		GlStateManager.translate(0.0F, 6 / 16.0F, 0.0F);

		if(ItemStackUtils.isItemInstanceOf(stack, ItemMinecartBase.class))
		{
			ItemMinecartBase itemMinecartBase = (ItemMinecartBase)stack.getItem();
			IBlockState blockState = itemMinecartBase.getCartBlockState(stack);
			switch(itemMinecartBase.getCartBlockRenderMethod(stack)) {
				case VMC:
				case ISBRH:
					this.renderVMCMethod(stack, itemMinecartBase, blockState);
					break;
				case TESR:
					this.renderTESRMethod(stack, itemMinecartBase, blockState);
					break;
				case CUSTOM:
					this.renderCustom(stack, itemMinecartBase);
					break;
				default:
					break;
			}
		}

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.popMatrix();

		GlStateManager.rotate(90, 0, 1, 0);
		GlStateManager.scale(0.75, 0.75, 0.75);
		GlStateManager.scale(-1, -1, 1);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(minecartTextures);

		modelMinecart.render(null, 0, 0, -0.1F, 0, 0, 1F / 16F);

		GlStateManager.popMatrix();
	}

	protected void renderVMCMethod(ItemStack itemStack, ItemMinecartBase itemMinecartBase, IBlockState blockState)
	{
		if (blockState.getBlock().getRenderType() != -1)
		{
			GlStateManager.pushMatrix();
			Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlockBrightness(blockState, 15);
			GlStateManager.popMatrix();
		}
	}

	protected void renderTESRMethod(ItemStack itemStack, ItemMinecartBase itemMinecartBase, IBlockState blockState)
	{
		if(renderTileEntity == null)
		{
			renderTileEntity = itemMinecartBase.getCartBlock(itemStack).createTileEntity(
					Minecraft.getMinecraft().theWorld, itemMinecartBase.getCartBlockState(itemStack));
		}
		if(renderTileEntity != null)
		{
			rotateTESR();
			GlStateManager.translate(-0.5F, -0.5F, -0.5F);
			TileEntityRendererDispatcher.instance.renderTileEntityAt(renderTileEntity, 0, 0, 0, 0.0F);
			GlStateManager.enableRescaleNormal();
		}
	}

	protected void renderCustom(ItemStack itemStack, ItemMinecartBase itemMinecartBase)
	{

	}

	protected void rotateTESR()
	{
		GlStateManager.rotate(90, 0, 1, 0);
	}


	@Override
	public TransformationMatrix getTransformMatrixForPerspective(ItemCameraTransforms.TransformType cameraTransformsType) {
		switch(cameraTransformsType) {
			case FIRST_PERSON: return transformationMatrixFirstPerson();
			case THIRD_PERSON: return transformationMatrixThirdPerson();
			case GUI: return transformationMatrixGui();
			case GROUND: return transformationMatrixGround();
			default: return transformationMatrixDefault();
		}
	}

	private TransformationMatrix transformationMatrixFirstPerson() {
		return new TransformationMatrix(0.25, -0.275, 0.35)
				.multiplyRightWith(new TransformationMatrix(90, 1, 0, 0))
				.multiplyRightWith(new TransformationMatrix(225, 0, 0, 1))
				.multiplyRightWith(new TransformationMatrix(180, 0, 1, 0))
				.scale(0.075, 0.075, 0.075);
	}

	private TransformationMatrix transformationMatrixThirdPerson() {
		return new TransformationMatrix(0.08, -0.1, -0.07)
				.multiplyRightWith(new TransformationMatrix(180, 0, 1, 0))
				.scale(0.03, 0.03, 0.03);
	}

	private TransformationMatrix transformationMatrixGui() {
		return new TransformationMatrix(0, -0.1, 0)
				.multiplyRightWith(new TransformationMatrix(90, -1, 0, 1))
				.multiplyRightWith(new TransformationMatrix(-0, 1, 0, 0))
				.multiplyRightWith(new TransformationMatrix(90, 0, 1, 0))
				.scale(0.035, 0.035, 0.035);
	}

	private TransformationMatrix transformationMatrixGround() {
		return new TransformationMatrix(-0.2, 0, 0.05)
				.multiplyRightWith(new TransformationMatrix(90, 1, 0, 0))
				.multiplyRightWith(new TransformationMatrix(225, 0, 0, 1))
				.multiplyRightWith(new TransformationMatrix(180, 0, 1, 0))
				.scale(0.03, 0.03, 0.03);
	}

	private TransformationMatrix transformationMatrixDefault() {
		return new TransformationMatrix(0.25, -0.275, 0.35)
				.multiplyRightWith(new TransformationMatrix(90, 1, 0, 0))
				.multiplyRightWith(new TransformationMatrix(225, 0, 0, 1))
				.multiplyRightWith(new TransformationMatrix(180, 0, 1, 0))
				.scale(0.075, 0.075, 0.075);
	}
}