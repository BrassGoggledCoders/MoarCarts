package xyz.brassgoggledcoders.moarcarts.mods.neotech.items;

import com.dyonovan.neotech.managers.BlockManager;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemSubMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.entities.*;
import xyz.brassgoggledcoders.moarcarts.renderers.IRenderBlock.RenderMethod;

public class ItemMinecartTank extends ItemSubMinecartBase
{
	public ItemMinecartTank()
	{
		super("neotech", "tank");
	}

	@Override
	public int getNumberOfSubItems()
	{
		return 5;
	}

	@Override
	public Block getCartBlock(ItemStack itemStack)
	{
		return TankType.values()[itemStack.getItemDamage()].getBlock();
	}

	@Override
	public RenderMethod getCartBlockRenderMethod(ItemStack itemStack)
	{
		return RenderMethod.COMBO;
	}

	@Override
	public String getUnlocalizedNameMetaExtension(int meta)
	{
		return TankType.values()[meta].toString().toLowerCase();
	}

	@Override
	public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack)
	{
		EntityMinecartBase minecartBase;
		switch(itemStack.getItemDamage())
		{
			case 0:
				minecartBase = new EntityMinecartIronTank(world);
				break;
			case 1:
				minecartBase = new EntityMinecartGoldTank(world);
				break;
			case 2:
				minecartBase = new EntityMinecartDiamondTank(world);
				break;
			case 3:
				minecartBase = new EntityMinecartCreativeTank(world);
				break;
			case 4:
				minecartBase = new EntityMinecartVoidTank(world);
				break;
			default:
				minecartBase = new EntityMinecartIronTank(world);
				break;
		}
		return minecartBase;
	}

	public enum TankType {
		IRON(BlockManager.ironTank()),
		GOLD(BlockManager.goldTank()),
		DIAMOND(BlockManager.diamondTank()),
		EMERALD(BlockManager.creativeTank()),
		VOID(BlockManager.voidTank());

		private Block cartBlock;
		TankType(Block block)
		{
			cartBlock = block;
		}

		public Block getBlock()
		{
			return cartBlock;
		}
	}
}
