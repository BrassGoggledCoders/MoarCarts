package moarcarts.fakeworld;

import moarcarts.MoarCarts;
import net.minecraft.item.Item;

/**
 * @author SkySom
 */
public class FakeNBTItem extends Item
{
	public FakeNBTItem()
	{
		super();
		this.setUnlocalizedName("fakenbtitem");
		this.setTextureName(MoarCarts.MODID + ":vanilla/minecartenderchest");
	}
}
