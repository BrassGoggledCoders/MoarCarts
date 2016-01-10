package moarcarts.mods.avaritia.renderers;

import fox.spiteful.avaritia.compat.botania.RenderTileInfinitato;
import moarcarts.entities.EntityMinecartTEBase;
import moarcarts.mods.botania.renderers.RenderMinecartTinyPotato;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import org.lwjgl.opengl.GL11;

/**
 * @author SkySom
 */
public class RenderMinecartInfinatato extends RenderMinecartTinyPotato
{
	@Override
	protected void renderCustom(EntityMinecartTEBase entityMinecartTEBase, Block block, float partial)
	{
		boolean renderHalo = RenderTileInfinitato.drawHalo;
		RenderTileInfinitato.drawHalo = false;
		GL11.glRotated(90D, 0D, 1D, 0D);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		TileEntityRendererDispatcher.instance.renderTileEntityAt(entityMinecartTEBase.getTileEntity(), 0, 0, 0, 0.0F);
		RenderTileInfinitato.drawHalo = renderHalo;
	}
}
