package moarcarts.mods.ironchest.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.ironchest.IronChestType;
import moarcarts.MoarCarts;
import moarcarts.entities.EntityMinecartBase;
import moarcarts.items.ItemMinecartBase;
import moarcarts.mods.ironchest.entities.EntityMinecartIronChest;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author SkySom
 */
public class ItemMinecartIronChest extends ItemMinecartBase
{
	private IIcon[] ironChestIcons;

	public ItemMinecartIronChest()
	{
		super();
		this.setHasSubtypes(true);
	}


	public IronChestType getIronChestType(ItemStack itemStack)
	{
		if(itemStack != null && itemStack.getItemDamage() < IronChestType.values().length)
		{
			return IronChestType.values()[itemStack.getItemDamage()];
		}
		return null;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack)
	{
		return "item.minecartironchest." + IronChestType.values()[itemstack.getItemDamage()].name().toLowerCase();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register)
	{
		ironChestIcons = new IIcon[IronChestType.values().length];
		for(int x = 0; x < ironChestIcons.length; x++)
		{
			ironChestIcons[x] = register.registerIcon(MoarCarts.MODID + ":ironchest/minecartironchest" +
					IronChestType.values()[x].name().toLowerCase());
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int damage)
	{
		return this.ironChestIcons[damage];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		for (int i = 0; i < 8; i++)
		{
			ItemStack stack = new ItemStack(item, 1, i);
			list.add(stack);
		}
	}

	@Override
	public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack)
	{
		return new EntityMinecartIronChest(world, this.getIronChestType(itemStack));
	}
}
