package moarcarts.mods.railcraft.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import moarcarts.entities.EntityMinecartBase;
import moarcarts.items.ItemMinecartBase;
import moarcarts.mods.railcraft.RailcraftCompat;
import moarcarts.mods.railcraft.entities.EntityMinecartMetalsChest;
import moarcarts.mods.railcraft.entities.EntityMinecartVoidChest;
import moarcarts.renderers.IRenderBlock.RenderMethod;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author SkySom
 */
public class ItemMinecartMachineBeta extends ItemMinecartBase
{
	public ItemMinecartMachineBeta()
	{
		super("railcraft", "minecraftmachinebeta");
		this.setHasSubtypes(true);
	}

	@Override
	public Block getCartBlock(ItemStack itemStack)
	{
		return RailcraftCompat.MACHINE_BETA;
	}

	@Override
	public RenderMethod getCartBlockRenderMethod(ItemStack itemStack)
	{
		return RenderMethod.CUSTOM;
	}

	public int getCartBlockMetadata(ItemStack itemStack)
	{
		int meta = 11;
		if(itemStack != null)
		{
			meta += itemStack.getItemDamage();
		}
		return meta;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		String extraBit = "";
		if(itemStack != null)
		{
			switch(itemStack.getItemDamage())
			{
				case 0:
					extraBit = "void";
					break;
				case 1:
					extraBit = "metals";
					break;
				default:
			}
		}
		return "item.minecartmachinebeta." + extraBit;
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings("unchecked")
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		for (int i = 0; i < 2; i++)
		{
			ItemStack stack = new ItemStack(item, 1, i);
			list.add(stack);
		}
	}

	@Override
	public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack)
	{
		EntityMinecartBase entityMinecart = null;
		if(itemStack != null)
		{
			switch(itemStack.getItemDamage())
			{
				case 0:
					entityMinecart = new EntityMinecartVoidChest(world);
					break;
				case 1:
					entityMinecart = new EntityMinecartMetalsChest(world);
					break;
				default:
			}
		}
		return entityMinecart;
	}
}
