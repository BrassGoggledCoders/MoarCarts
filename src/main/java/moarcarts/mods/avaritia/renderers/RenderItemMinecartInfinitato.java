package moarcarts.mods.avaritia.renderers;

import fox.spiteful.avaritia.compat.botania.RenderTileInfinitato;
import moarcarts.items.ItemMinecartBase;
import moarcarts.renderers.RenderItemMinecartBase;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

/**
 * @author SkySom
 */
public class RenderItemMinecartInfinitato extends RenderItemMinecartBase
{
	@Override
	protected void renderCustom(ItemStack itemStack, ItemMinecartBase itemMinecartBase)
	{
		boolean renderHalo = RenderTileInfinitato.drawHalo;
		RenderTileInfinitato.drawHalo = false;
		GL11.glRotated(90D, 0D, 1D, 0D);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		TileEntityRendererDispatcher.instance.renderTileEntityAt(renderTileEntity, 0, 0, 0, 0.0F);
		RenderTileInfinitato.drawHalo = renderHalo;
	}
}
