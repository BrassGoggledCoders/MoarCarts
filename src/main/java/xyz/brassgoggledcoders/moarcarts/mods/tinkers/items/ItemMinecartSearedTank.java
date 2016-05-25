package xyz.brassgoggledcoders.moarcarts.mods.tinkers.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.tinkers.entities.EntityMinecartSearedTank;

public class ItemMinecartSearedTank extends ItemMinecartBase
{
	public ItemMinecartSearedTank()
	{
		super("tinkers", "searedtank");
	}

	@Override
	public Block getCartBlock(ItemStack itemStack)
	{
		return TinkerSmeltery.searedTank;
	}

	@Override
	public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack)
	{
		return new EntityMinecartSearedTank(world);
	}
}
