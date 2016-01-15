package moarcarts.items;

import boilerplate.common.baseclasses.BaseCreativeTab;
import moarcarts.mods.vanilla.VanillaCompat;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * @author SkySom
 */
public class MoarCartsCreativeTab extends BaseCreativeTab
{
	public MoarCartsCreativeTab()
	{
		super("MoarCarts");
	}

	public Item getTabIconItem() {
		if(VanillaCompat.ITEM_MINECART_ENDERCHEST != null) {
			return VanillaCompat.ITEM_MINECART_ENDERCHEST;
		}
		return Items.minecart;
	}
}
