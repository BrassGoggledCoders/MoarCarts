package moarcarts.mods.ironchest.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.ironchest.IronChestType;
import moarcarts.MoarCarts;
import moarcarts.entities.EntityMinecartBase;
import moarcarts.items.ItemMinecartBase;
import moarcarts.mods.ironchest.entities.*;
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
		this.setUnlocalizedName("minecartironchests");
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
	@SuppressWarnings("unchecked")
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
		EntityMinecartIronChest entityMinecartIronChest;
		switch(itemStack.getItemDamage()) {
			case 1:
				entityMinecartIronChest = new EntityMinecartGoldChest(world);
				break;
			case 2:
				entityMinecartIronChest = new EntityMinecartDiamondChest(world);
				break;
			case 3:
				entityMinecartIronChest = new EntityMinecartCopperChest(world);
				break;
			case 4:
				entityMinecartIronChest = new EntityMinecartSilverChest(world);
				break;
			case 5:
				entityMinecartIronChest = new EntityMinecartCrystalChest(world);
				break;
			case 6:
				entityMinecartIronChest = new EntityMinecartObsidianChest(world);
				break;
			case 7:
				entityMinecartIronChest = new EntityMinecartDirtChest(world);
				break;
			default:
				entityMinecartIronChest = new EntityMinecartIronChest(world, itemStack.getItemDamage());
		}
		return entityMinecartIronChest;
	}
}
