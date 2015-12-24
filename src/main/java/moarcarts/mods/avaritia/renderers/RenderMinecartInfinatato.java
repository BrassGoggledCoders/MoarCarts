package moarcarts.mods.avaritia.renderers;

import moarcarts.entities.EntityMinecartTEBase;
import moarcarts.mods.botania.renderers.RenderMinecartTinyPotato;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import vazkii.botania.client.lib.LibResources;
import vazkii.botania.client.model.ModelTinyPotato;

/**
 * @author SkySom
 */
public class RenderMinecartInfinatato extends RenderMinecartTinyPotato
{
	private static final ResourceLocation texture = new ResourceLocation(LibResources.MODEL_TINY_POTATO);
	private static final ResourceLocation textureGrayscale = new ResourceLocation(LibResources.MODEL_TINY_POTATO_GS);
	private static final ModelTinyPotato model = new ModelTinyPotato();

	@Override
	protected void renderCustom(EntityMinecartTEBase entityMinecartTEBase, Block block)
	{

	}
}
