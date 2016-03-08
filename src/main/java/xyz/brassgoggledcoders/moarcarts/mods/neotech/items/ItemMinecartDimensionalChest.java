package xyz.brassgoggledcoders.moarcarts.mods.neotech.items;

import com.dyonovan.neotech.managers.BlockManager;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.entities.EntityMinecartDimensionalChest;

public class ItemMinecartDimensionalChest extends ItemMinecartBase
{
	public ItemMinecartDimensionalChest()
	{
		super("neotech", "dimensionalchest");
	}

	@Override
	public Block getCartBlock(ItemStack itemStack)
	{
		return BlockManager.dimStorage();
	}

	@Override
	public int getCartBlockMetadata(ItemStack itemStack)
	{
		return 2;
	}

	@Override
	public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack)
	{
		return new EntityMinecartDimensionalChest(world);
	}
}
