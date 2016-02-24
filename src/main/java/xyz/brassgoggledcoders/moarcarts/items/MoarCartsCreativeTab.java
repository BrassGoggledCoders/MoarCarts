package xyz.brassgoggledcoders.moarcarts.items;

import xyz.brassgoggledcoders.boilerplate.lib.common.BaseCreativeTab;
import xyz.brassgoggledcoders.moarcarts.mods.vanilla.VanillaCompat;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * @author SkySom
 */
public class MoarCartsCreativeTab extends BaseCreativeTab
{
	public MoarCartsCreativeTab()
	{
		super("xyz/brassgoggledcoders/moarcarts");
	}

	public Item getTabIconItem() {
		if(VanillaCompat.ITEM_MINECART_ENDERCHEST != null) {
			return VanillaCompat.ITEM_MINECART_ENDERCHEST;
		}
		return Items.minecart;
	}
}
