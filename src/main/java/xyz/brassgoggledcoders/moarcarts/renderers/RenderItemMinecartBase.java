package xyz.brassgoggledcoders.moarcarts.renderers;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelMinecart;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;
import xyz.brassgoggledcoders.boilerplate.lib.client.ClientHelper;
import xyz.brassgoggledcoders.boilerplate.lib.client.renderers.ItemSpecialRenderer;
import xyz.brassgoggledcoders.boilerplate.lib.client.renderers.math.DefaultTransformationMatrices;
import xyz.brassgoggledcoders.boilerplate.lib.client.renderers.math.TransformationMatrix;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.ItemStackUtils;
import xyz.brassgoggledcoders.moarcarts.items.ItemMinecartBase;

import java.util.List;

@SuppressWarnings("deprecation")
public class RenderItemMinecartBase extends ItemSpecialRenderer
{
	private static final ResourceLocation minecartTextures = new ResourceLocation("textures/entity/minecart.png");
	protected ModelBase modelMinecart = new ModelMinecart();

	@Override
	public void renderItem(ItemStack stack, float partialTicks)
	{
		GlStateManager.pushMatrix();
		GlStateManager.translate(-0.5,0.5,-0.5);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(TextureMap.locationBlocksTexture);

		GlStateManager.pushMatrix();
		GlStateManager.rotate(270, 0, 1, 0);
		GlStateManager.rotate(90, 0, 0, 1);
		GlStateManager.scale(12, 12, 12);

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
				case COMBO:
					this.renderVMCMethod(stack, itemMinecartBase, blockState);
					break;
				default:
					break;
			}
		}

		GlStateManager.popMatrix();
		GlStateManager.rotate(-90, 0, 0, 1);
		GlStateManager.rotate(-90, 1, 0, 0);
		GlStateManager.scale(-1, -1, 1);

		Minecraft.getMinecraft().getTextureManager().bindTexture(minecartTextures);
		modelMinecart.render(ClientHelper.player(), 0, 0, 0, 0, 0, 1);
		GlStateManager.popMatrix();
	}

	protected void renderVMCMethod(ItemStack itemStack, ItemMinecartBase itemMinecartBase, IBlockState blockState)
	{
		if (blockState.getRenderType() != EnumBlockRenderType.INVISIBLE  )
		{
			GlStateManager.pushMatrix();
			GlStateManager.translate(-0.5F, -0.5F, 0.5F);
			Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlockBrightness(blockState, 15);
			GlStateManager.popMatrix();
		}
	}

	protected void renderTESRMethod(ItemStack itemStack, ItemMinecartBase itemMinecartBase, IBlockState blockState)
	{
		rotateTESR();
		GlStateManager.pushMatrix();
		GlStateManager.translate(-0.5F, -0.5F, -0.5F);
		TileEntityRendererDispatcher.instance.renderTileEntityAt(
					itemMinecartBase.getRenderTileEntity(itemStack, ClientHelper.world()), 0, 0, 0, 0.0F);
		GlStateManager.popMatrix();
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
		return DefaultTransformationMatrices.getTransformMatrixForPerspective(cameraTransformsType);
	}

	@Override
	public Class getTileClass()
	{
		return DummyTile.class;
	}

	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand)
	{
		return null;
	}

	@Override
	public ItemOverrideList getOverrides()
	{
		return null;
	}

	static class DummyTile extends TileEntity {}
}