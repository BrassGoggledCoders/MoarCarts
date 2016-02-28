package xyz.brassgoggledcoders.moarcarts.renderers;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface ISpecialRenderedItem extends IItemWithModel {
	@SideOnly(Side.CLIENT)
	ItemSpecialRenderer getSpecialRenderer();
}