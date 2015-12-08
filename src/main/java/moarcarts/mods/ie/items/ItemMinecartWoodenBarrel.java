package moarcarts.mods.ie.items;

import blusunrize.immersiveengineering.common.IEContent;
import moarcarts.entities.EntityMinecartBase;
import moarcarts.items.ItemMinecartBase;
import moarcarts.mods.ie.entities.EntityMinecartWoodenBarrel;
import moarcarts.renderers.IRenderBlock.RenderMethod;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class ItemMinecartWoodenBarrel extends ItemMinecartBase
{
	public ItemMinecartWoodenBarrel()
	{
		super("ie", "minecartwoodenbarrel");
	}

	@Override
	public Block getCartBlock(ItemStack itemStack)
	{
		return IEContent.blockWoodenDevice;
	}

	@Override
	public int getCartBlockMetadata(ItemStack itemStack)
	{
		return 6;
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
