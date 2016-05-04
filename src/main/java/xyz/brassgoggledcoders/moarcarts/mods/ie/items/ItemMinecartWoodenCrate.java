package xyz.brassgoggledcoders.moarcarts.mods.ie.items;

import blusunrize.immersiveengineering.common.IEContent;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.ie.entities.EntityMinecartWoodenCrate;

public class ItemMinecartWoodenCrate extends ItemMinecartBase
{
	public ItemMinecartWoodenCrate()
	{
		super("ie", "woodencrate");
	}

	@Override
	public Block getCartBlock(ItemStack itemStack)
	{
		return IEContent.blockWoodenDevice0;
	}

	@Override
	public int getCartBlockMetadata(ItemStack itemStack)
	{
		return 4;
	}

	@Override
	public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack)
	{
		return new EntityMinecartWoodenCrate(world);
	}
}
