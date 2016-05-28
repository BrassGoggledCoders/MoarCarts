package xyz.brassgoggledcoders.moarcarts.mods.tinkers.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.tinkers.entities.EntityMinecartSearedTank;
import xyz.brassgoggledcoders.moarcarts.renderers.IRenderBlock.RenderMethod;

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
	public RenderMethod getCartBlockRenderMethod(ItemStack itemStack)
	{
		return RenderMethod.COMBO;
	}

	@Override
	public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack)
	{
		return new EntityMinecartSearedTank(world);
	}
}
