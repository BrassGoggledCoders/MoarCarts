package moarcarts.renderers;

import boilerplate.common.utils.ItemStackUtils;
import cpw.mods.fml.client.FMLClientHandler;
import moarcarts.items.ItemMinecartBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelMinecart;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

/**
 * @author SkySom
 */
public class RenderItemMinecraftTEBase implements IItemRenderer
{
	TileEntity renderTileEntity;

	public static ResourceLocation TEXTURE = new ResourceLocation("textures/entity/minecart.png");
	public ModelBase model = new ModelMinecart();

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return type != ItemRenderType.FIRST_PERSON_MAP;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack stack, Object... data)
	{
		Object renderer = data[0];
		if (renderer instanceof RenderBlocks)
		{
			switch(type)
			{
				case ENTITY:
					GL11.glScaled(0.5, 0.5, 0.5);
					render(stack, 0.0F, -0.25F, 0.0F, (RenderBlocks) renderer);
					break;
				case EQUIPPED:
					render(stack, 0.5F, 0.75F, 0.5F, (RenderBlocks) renderer);
					break;
				case EQUIPPED_FIRST_PERSON:
					render(stack, -0.5F, 1.0F, 0.7F, (RenderBlocks) renderer);
					break;
				case INVENTORY:
					GL11.glScaled(1.2, 1.2, 1.2);
					render(stack, -0.5F, -0.45F, -0.5F, (RenderBlocks) renderer);
					break;
				default:
					break;
			}
		}
	}

	private void render(ItemStack stack, float x, float y, float z, RenderBlocks renderer) {
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glEnable(GL11.GL_ALPHA_TEST);

		FMLClientHandler.instance().getClient().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		GL11.glPushMatrix();
		GL11.glScaled(0.75 * 0.75, 0.75 * 0.75, 0.75 * 0.75);
		GL11.glRotated(270, 0, 1, 0);
		GL11.glTranslated(0.0F, 6 / 16.0F, 0.0F);

		if(ItemStackUtils.isItemInstanceOf(stack, ItemMinecartBase.class))
		{
			ItemMinecartBase itemMinecartBase = (ItemMinecartBase)stack.getItem();
			switch(itemMinecartBase.getCartBlockRenderMethod(stack)) {
				case VMC:
					this.renderVMCMethod(renderer, stack, itemMinecartBase);
					break;
				case ISBRH:
					this.renderVMCMethod(renderer, stack, itemMinecartBase);
					break;
				case TESR:
					this.renderTESRMethod(stack, itemMinecartBase);
					break;
				default:
					break;
			}
		}

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();

		GL11.glRotated(90, 0, 1, 0);
		GL11.glScaled(0.75, 0.75, 0.75);
		GL11.glScaled(-1, -1, 1);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(TEXTURE);

		model.render(null, 0, 0, -0.1F, 0, 0, 1F / 16F);

		GL11.glPopMatrix();
	}

	protected void renderVMCMethod(RenderBlocks renderer, ItemStack itemStack, ItemMinecartBase itemMinecartBase)
	{
		renderer.renderBlockAsItem(itemMinecartBase.getCartBlock(itemStack),
				itemMinecartBase.getCartBlockMetadata(itemStack), 1);
	}

	protected void renderTESRMethod(ItemStack itemStack, ItemMinecartBase itemMinecartBase)
	{
		if(renderTileEntity == null)
		{
			renderTileEntity = itemMinecartBase.getCartBlock(itemStack).createTileEntity(
					Minecraft.getMinecraft().theWorld, itemMinecartBase.getCartBlockMetadata(itemStack));
		}
		if(renderTileEntity != null)
		{
			GL11.glRotated(90D, 0D, 1D, 0D);
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			TileEntityRendererDispatcher.instance.renderTileEntityAt(renderTileEntity, 0, 0, 0, 0.0F);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		}
	}
}
