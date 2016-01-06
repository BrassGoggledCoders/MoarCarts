package moarcarts.mods.railcraft.renderers;

import moarcarts.entities.EntityMinecartTEBase;
import moarcarts.renderers.RenderMinecartTEBase;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import org.lwjgl.opengl.GL11;

/**
 * @author SkySom
 */
public class RenderMinecartMachineBeta extends RenderMinecartTEBase
{
	@Override
	protected void renderCustom(EntityMinecartTEBase entityMinecart, Block block)
	{
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		TileEntityRendererDispatcher.instance.renderTileEntityAt(entityMinecartTEBase.getTileEntity(), 0, 0, 0, 0.0F);
	}
}
