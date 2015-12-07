package moarcarts.mods.ie.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import moarcarts.entities.EntityMinecartBase;
import moarcarts.items.ItemMinecartBase;
import moarcarts.mods.ie.entities.EntityMinecartCapacitorCreative;
import moarcarts.mods.ie.entities.EntityMinecartCapacitorHV;
import moarcarts.mods.ie.entities.EntityMinecartCapacitorLV;
import moarcarts.mods.ie.entities.EntityMinecartCapacitorMV;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author SkySom
 */
public class ItemMinecartCapacitor extends ItemMinecartBase
{
	public ItemMinecartCapacitor()
	{
		super("ie", "minecartcapacitor");
		this.setHasSubtypes(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings("unchecked")
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		for (int i = 0; i < 4; i++)
		{
			ItemStack stack = new ItemStack(item, 1, i);
			list.add(stack);
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack)
	{
		String unlocalizedName = "item.minecartcapacitor";
		switch(itemstack.getItemDamage())
		{
			case 0:
				unlocalizedName += "lv";
				break;
			case 1:
				unlocalizedName += "mv";
				break;
			case 2:
				unlocalizedName += "hv";
				break;
			case 3:
				unlocalizedName += "creative";
				break;
			default:
				break;
		}
		return unlocalizedName;
	}

	@Override
	public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack)
	{
		EntityMinecartCapacitorLV entityMinecartCapacitorLV;
		switch(itemStack.getItemDamage())
		{
			case 1:
				entityMinecartCapacitorLV = new EntityMinecartCapacitorMV(world);
				break;
			case 2:
				entityMinecartCapacitorLV = new EntityMinecartCapacitorHV(world);
				break;
			case 3:
				entityMinecartCapacitorLV = new EntityMinecartCapacitorCreative(world);
				break;
			default:
				entityMinecartCapacitorLV = new EntityMinecartCapacitorLV(world);
				break;
		}
		return entityMinecartCapacitorLV;
	}
}
