package xyz.brassgoggledcoders.moarcarts.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xyz.brassgoggledcoders.boilerplate.lib.client.ClientHelper;

import java.util.List;

public abstract class ItemSubMinecartBase extends ItemMinecartBase
{
	private TileEntity[] renderTileEntities;

	public ItemSubMinecartBase(String mod, String name)
	{
		super(mod, name);
		this.setHasSubtypes(true);
		renderTileEntities = new TileEntity[getNumberOfSubItems()];
	}

	@Override
	public int getCartBlockMetadata(ItemStack itemStack)
	{
		return itemStack.getItemDamage();
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack)
	{
		return getUnlocalizedName() + "." + getUnlocalizedNameMetaExtension(itemstack);
	}

	public String getUnlocalizedNameMetaExtension(ItemStack itemStack)
	{
		return itemStack.getItemDamage() + "";
	}

	public abstract int getNumberOfSubItems();

	public TileEntity getRenderTileEntity(ItemStack itemStack)
	{
		int damage = itemStack.getItemDamage();
		if(renderTileEntities[damage] == null)
		{
			renderTileEntities[damage] = getCartBlock(itemStack).createTileEntity(ClientHelper.world(),
					getCartBlockState(itemStack));
		}
		return renderTileEntities[damage];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> list)
	{
		for (int i = 0; i < getNumberOfSubItems(); i++)
		{
			ItemStack stack = new ItemStack(item, 1, i);
			list.add(stack);
		}
	}
}
