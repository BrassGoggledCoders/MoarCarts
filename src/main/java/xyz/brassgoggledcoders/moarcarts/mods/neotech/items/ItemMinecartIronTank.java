package xyz.brassgoggledcoders.moarcarts.mods.neotech.items;

import com.dyonovan.neotech.managers.BlockManager;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemSubMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.entities.EntityMinecartIronTank;
import xyz.brassgoggledcoders.moarcarts.renderers.IRenderBlock.RenderMethod;

public class ItemMinecartIronTank extends ItemSubMinecartBase
{
	public ItemMinecartIronTank()
	{
		super("neotech", "minecartirontank");
	}

	@Override
	public int getNumberOfSubItems()
	{
		return 1;
	}

	@Override
	public Block getCartBlock(ItemStack itemStack)
	{
		return BlockManager.ironTank();
	}

	@Override
	public RenderMethod getCartBlockRenderMethod(ItemStack itemStack)
	{
		return RenderMethod.COMBO;
	}

	@Override
	public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack)
	{
		return new EntityMinecartIronTank(world);
	}
}
