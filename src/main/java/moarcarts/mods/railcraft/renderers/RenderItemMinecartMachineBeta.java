package moarcarts.mods.railcraft.renderers;

import moarcarts.items.ItemMinecartBase;
import moarcarts.renderers.RenderItemMinecartBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

/**
 * @author SkySom
 */
public class RenderItemMinecartMachineBeta extends RenderItemMinecartBase
{
	protected void renderCustom(ItemStack itemStack, ItemMinecartBase itemMinecartBase)
	{
		if(renderTileEntity == null)
		{
			renderTileEntity = itemMinecartBase.getCartBlock(itemStack).createTileEntity(
					Minecraft.getMinecraft().theWorld, itemMinecartBase.getCartBlockMetadata(itemStack));
		}
		if(renderTileEntity != null)
		{
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			TileEntityRendererDispatcher.instance.renderTileEntityAt(renderTileEntity, 0, 0, 0, 0.0F);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		}
	}
}
