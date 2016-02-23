package moarcarts.mods.ironchest.items;

import cpw.mods.ironchest.IronChest;
import cpw.mods.ironchest.IronChestType;
import moarcarts.entities.EntityMinecartBase;
import moarcarts.items.ItemMinecartBase;
import moarcarts.mods.ironchest.entities.*;
import moarcarts.renderers.IRenderBlock.RenderMethod;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * @author SkySom
 */
public class ItemMinecartIronChest extends ItemMinecartBase
{
	public ItemMinecartIronChest()
	{
		super("ironchest", "minecartironchest");
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
	public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> list)
	{
		for (int i = 0; i < 8; i++)
		{
			ItemStack stack = new ItemStack(item, 1, i);
			list.add(stack);
		}
	}

	@Override
	public Block getCartBlock(ItemStack itemStack)
	{
		return IronChest.ironChestBlock;
	}

	@Override
	public int getCartBlockMetadata(ItemStack itemStack)
	{
		return itemStack.getItemDamage();
	}

	@Override
	public RenderMethod getCartBlockRenderMethod(ItemStack itemStack)
	{
		return RenderMethod.TESR;
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
