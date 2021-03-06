package xyz.brassgoggledcoders.moarcarts.mods.ie.items;

import blusunrize.immersiveengineering.common.IEContent;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.ie.entities.EntityMinecartWoodenBarrel;
import xyz.brassgoggledcoders.moarcarts.renderers.IRenderBlock.RenderMethod;

/**
 * @author SkySom
 */
public class ItemMinecartWoodenBarrel extends ItemMinecartBase
{
	public ItemMinecartWoodenBarrel()
	{
		super("ie", "woodenbarrel");
	}

	@Override
	public Block getCartBlock(ItemStack itemStack)
	{
		return IEContent.blockWoodenDevice0;
	}

	@Override
	public int getCartBlockMetadata(ItemStack itemStack)
	{
		return 1;
	}

	@Override
	public RenderMethod getCartBlockRenderMethod(ItemStack itemStack)
	{
		return RenderMethod.ISBRH;
	}

	@Override
	public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack)
	{
		return new EntityMinecartWoodenBarrel(world);
	}
}
