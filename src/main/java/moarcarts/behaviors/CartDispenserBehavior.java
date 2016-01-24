package moarcarts.behaviors;

import xyz.brassgoggledcoders.boilerplate.common.utils.ItemStackUtils;
import moarcarts.entities.EntityMinecartBase;
import moarcarts.items.ItemMinecartBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class CartDispenserBehavior extends BehaviorDefaultDispenseItem
{
	public ItemStack dispenseStack(IBlockSource dispenser, ItemStack cartItemStack)
	{
		EnumFacing enumfacing = BlockDispenser.func_149937_b(dispenser.getBlockMetadata());
		World world = dispenser.getWorld();
		double d0 = dispenser.getX() + (double)((float)enumfacing.getFrontOffsetX() * 1.125F);
		double d1 = dispenser.getY() + (double)((float)enumfacing.getFrontOffsetY() * 1.125F);
		double d2 = dispenser.getZ() + (double)((float)enumfacing.getFrontOffsetZ() * 1.125F);
		int i = dispenser.getXInt() + enumfacing.getFrontOffsetX();
		int j = dispenser.getYInt() + enumfacing.getFrontOffsetY();
		int k = dispenser.getZInt() + enumfacing.getFrontOffsetZ();

		Block block = world.getBlock(i, j, k);

		if(ItemStackUtils.isItemInstanceOf(cartItemStack, ItemMinecartBase.class))
		{
			ItemMinecartBase itemMinecartBase = (ItemMinecartBase)cartItemStack.getItem();
			EntityMinecartBase entityMinecartBase = itemMinecartBase.getEntityFromItem(world, cartItemStack);
			itemMinecartBase.placeCart(cartItemStack, world, i, j, k, entityMinecartBase);
		}

		return cartItemStack;
	}

	protected void playDispenseSound(IBlockSource block)
	{
		block.getWorld().playAuxSFX(1000, block.getXInt(), block.getYInt(), block.getZInt(), 0);
	}
}
