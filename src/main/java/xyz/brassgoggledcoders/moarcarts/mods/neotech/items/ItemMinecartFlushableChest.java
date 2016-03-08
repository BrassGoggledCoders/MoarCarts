package xyz.brassgoggledcoders.moarcarts.mods.neotech.items;

import com.dyonovan.neotech.managers.BlockManager;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.entities.EntityMinecartFlushableChest;
import xyz.brassgoggledcoders.moarcarts.renderers.IRenderBlock.RenderMethod;

public class ItemMinecartFlushableChest extends ItemMinecartBase
{
	public ItemMinecartFlushableChest()
	{
		super("neotech", "flushablechest");
	}

	@Override
	public Block getCartBlock(ItemStack itemStack)
	{
		return BlockManager.flushableChest();
	}

	@Override
	public int getCartBlockMetadata(ItemStack itemStack)
	{
		return 2;
	}

	@Override
	public RenderMethod getCartBlockRenderMethod(ItemStack itemStack)
	{
		return RenderMethod.TESR;
	}

	@Override
	public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack)
	{
		return new EntityMinecartFlushableChest(world);
	}
}
