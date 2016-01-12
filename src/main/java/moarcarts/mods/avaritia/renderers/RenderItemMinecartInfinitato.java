package moarcarts.mods.avaritia.renderers;

import fox.spiteful.avaritia.compat.botania.RenderTileInfinitato;
import moarcarts.items.ItemMinecartBase;
import moarcarts.renderers.RenderItemMinecartBase;
import net.minecraft.item.ItemStack;

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
		this.renderTESRMethod(itemStack, itemMinecartBase);
		RenderTileInfinitato.drawHalo = renderHalo;
	}

	@Override
	protected void rotateTESR() {}
}
