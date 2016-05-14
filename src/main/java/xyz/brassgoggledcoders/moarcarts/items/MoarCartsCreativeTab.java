package xyz.brassgoggledcoders.moarcarts.items;

import xyz.brassgoggledcoders.boilerplate.lib.common.BaseCreativeTab;
import xyz.brassgoggledcoders.moarcarts.mods.vanilla.VanillaModule;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * @author SkySom
 */
public class MoarCartsCreativeTab extends BaseCreativeTab
{
	public MoarCartsCreativeTab()
	{
		super("moarcarts");
	}

	public Item getTabIconItem() {
		if(VanillaModule.ITEM_MINECART_ENDERCHEST != null) {
			return VanillaModule.ITEM_MINECART_ENDERCHEST;
		}
		return Items.minecart;
	}
}
