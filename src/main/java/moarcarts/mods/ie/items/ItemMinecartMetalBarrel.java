package moarcarts.mods.ie.items;

import blusunrize.immersiveengineering.common.IEContent;
import moarcarts.entities.EntityMinecartBase;
import moarcarts.items.ItemMinecartBase;
import moarcarts.mods.ie.entities.EntityMinecartMetalBarrel;
import net.minecraft.block.Block;
import moarcarts.renderers.IRenderBlock.RenderMethod;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class ItemMinecartMetalBarrel extends ItemMinecartBase
{
	public ItemMinecartMetalBarrel()
	{
		super("ie", "minecartmetalbarrel");
	}

	@Override
	public Block getCartBlock(ItemStack itemStack)
	{
		return IEContent.blockMetalDevice2;
	}

	@Override
	public int getCartBlockMetadata(ItemStack itemStack)
	{
		return 7;
	}

	@Override
	public RenderMethod getCartBlockRenderMethod(ItemStack itemStack)
	{
		return RenderMethod.ISBRH;
	}

	@Override
	public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack)
	{
		return new EntityMinecartMetalBarrel(world);
	}
}
