package moarcarts.mods.ie.items;

import blusunrize.immersiveengineering.common.IEContent;
import moarcarts.entities.EntityMinecartBase;
import moarcarts.items.ItemMinecartBase;
import moarcarts.mods.ie.entities.EntityMinecartWoodenCrate;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class ItemMinecartWoodenCrate extends ItemMinecartBase
{
	public ItemMinecartWoodenCrate()
	{
		super("ie", "minecartwoodencrate");
	}

	@Override
	public Block getCartBlock(ItemStack itemStack)
	{
		return IEContent.blockWoodenDevice;
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
