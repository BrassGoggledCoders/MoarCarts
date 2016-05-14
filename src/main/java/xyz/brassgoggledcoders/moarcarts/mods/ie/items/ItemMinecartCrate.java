package xyz.brassgoggledcoders.moarcarts.mods.ie.items;

import blusunrize.immersiveengineering.common.IEContent;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemSubMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.ie.entities.EntityMinecartReinforcedCrate;
import xyz.brassgoggledcoders.moarcarts.mods.ie.entities.EntityMinecartWoodenCrate;

public class ItemMinecartCrate extends ItemSubMinecartBase
{
	public ItemMinecartCrate()
	{
		super("ie", "crate");
	}

	@Override
	public Block getCartBlock(ItemStack itemStack)
	{
		return IEContent.blockWoodenDevice0;
	}

	@Override
	public int getCartBlockMetadata(ItemStack itemStack)
	{
		return itemStack.getItemDamage() == 0 ? 0 : 5;
	}

	@Override
	public String getUnlocalizedNameMetaExtension(int meta)
	{
		if(meta == 0)
		{
			return "wooden";
		}
		return "reinforced";
	}

	@Override
	public int getNumberOfSubItems()
	{
		return 2;
	}

	@Override
	public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack)
	{
		return itemStack.getItemDamage() == 0 ? new EntityMinecartWoodenCrate(world):
				new EntityMinecartReinforcedCrate(world);
	}
}
