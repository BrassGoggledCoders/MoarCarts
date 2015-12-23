package moarcarts.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * @author SkySom
 */
public class FluidUtils
{
	public static boolean fillFluidHandlerWithPlayerItem(World world, IFluidHandler handler, EntityPlayer player)
	{
		ItemStack equipped = player.getCurrentEquippedItem();
		if(equipped==null)
			return false;
		FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(equipped);
		if(fluid != null)
		{
			if(handler.fill(ForgeDirection.UNKNOWN, fluid, false) == fluid.amount || player.capabilities.isCreativeMode)
			{
				if(world.isRemote)
					return true;

				ItemStack filledStack = FluidContainerRegistry.drainFluidContainer(equipped);
				if (!player.capabilities.isCreativeMode)
				{
					if(equipped.stackSize==1)
					{
						player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
						player.inventory.addItemStackToInventory(filledStack);
					}
					else
					{
						equipped.stackSize -= 1;
						if(filledStack!=null && !player.inventory.addItemStackToInventory(filledStack))
							player.func_146097_a(filledStack, false, true);
					}
					player.openContainer.detectAndSendChanges();
					((EntityPlayerMP) player).sendContainerAndContentsToPlayer(player.openContainer, player.openContainer.getInventory());
				}
				handler.fill(ForgeDirection.UNKNOWN, fluid, true);
				return true;
			}
		}
		else if(equipped.getItem() instanceof IFluidContainerItem)
		{
			IFluidContainerItem container = (IFluidContainerItem)equipped.getItem();
			fluid = container.getFluid(equipped);
			if(handler.fill(ForgeDirection.UNKNOWN, fluid, false)>0)
			{
				if(world.isRemote)
					return true;

				int fill = handler.fill(ForgeDirection.UNKNOWN, fluid, true);
				if(equipped.stackSize > 1)
				{
					ItemStack emptied = copyStackWithAmount(equipped, 1);
					equipped.stackSize -= 1;
					container.drain(emptied, fill, true);
					if(!player.inventory.addItemStackToInventory(emptied))
						player.func_146097_a(emptied, false, true);
				}
				else
				{
					container.drain(equipped, fill, true);
				}
				player.openContainer.detectAndSendChanges();
				((EntityPlayerMP) player).sendContainerAndContentsToPlayer(player.openContainer, player.openContainer.getInventory());
				return true;
			}
		}
		return false;
	}

	public static ItemStack copyStackWithAmount(ItemStack stack, int amount)
	{
		if(stack==null)
			return null;
		ItemStack s2 = stack.copy();
		s2.stackSize=amount;
		return s2;
	}
}
