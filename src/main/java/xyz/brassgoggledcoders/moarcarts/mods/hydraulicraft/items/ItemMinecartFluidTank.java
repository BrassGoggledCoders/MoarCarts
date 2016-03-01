package xyz.brassgoggledcoders.moarcarts.mods.hydraulicraft.items;

import k4unl.minecraft.Hydraulicraft.blocks.HCBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.hydraulicraft.entites.EntityMinecartFluidTank;
import xyz.brassgoggledcoders.moarcarts.renderers.IRenderBlock.RenderMethod;

public class ItemMinecartFluidTank extends ItemMinecartBase
{
	public ItemMinecartFluidTank()
	{
		super("hydraulicraft", "minecartfluidtank");
	}

	@Override
	public Block getCartBlock(ItemStack itemStack)
	{
		return HCBlocks.blockFluidTank;
	}

	@Override
	public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack)
	{
		return new EntityMinecartFluidTank(world);
	}

	@Override
	public RenderMethod getCartBlockRenderMethod(ItemStack itemStack)
	{
		return RenderMethod.COMBO;
	}
}
