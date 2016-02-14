package moarcarts.behaviors;

import moarcarts.entities.EntityMinecartBase;
import moarcarts.items.ItemMinecartBase;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.ItemStackUtils;

/**
 * @author SkySom
 */
public class CartDispenserBehavior extends BehaviorDefaultDispenseItem
{
	public ItemStack dispenseStack(IBlockSource dispenser, ItemStack cartItemStack)
	{
		EntityMinecartBase entityMinecartBase;
		World world = dispenser.getWorld();
		boolean cartSpawned = false;
		if(ItemStackUtils.isItemInstanceOf(cartItemStack, ItemMinecartBase.class))
		{
			ItemMinecartBase itemMinecartBase = (ItemMinecartBase)cartItemStack.getItem();
			entityMinecartBase = itemMinecartBase.getEntityFromItem(world, cartItemStack);

			if(entityMinecartBase != null)
			{
				EnumFacing enumfacing = BlockDispenser.getFacing(dispenser.getBlockMetadata());
				BlockPos blockpos = dispenser.getBlockPos().offset(enumfacing);
				cartSpawned = itemMinecartBase.placeCart(cartItemStack, world, blockpos, entityMinecartBase);
			}
		}

		if(!cartSpawned)
		{
			cartItemStack = super.dispenseStack(dispenser, cartItemStack);
		}

		return cartItemStack;
	}

	protected void playDispenseSound(IBlockSource block)
	{
		block.getWorld().playAuxSFX(1000, block.getBlockPos(), 0);
	}
}
