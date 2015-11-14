package moarcarts.entities;

import moarcarts.renderers.IRenderBlock;
import mods.railcraft.api.carts.IItemCart;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public abstract class EntityMinecartInventoryTEBase extends EntityMinecartTEBase implements IRenderBlock, IInventory,
		IItemCart
{
	public EntityMinecartInventoryTEBase(World world, Block cartBlock, int metadata)
	{
		super(world, cartBlock, metadata);
	}

	public IInventory getIInventoryTileEntity()
	{
		if(this.getTileEntity() instanceof IInventory)
		{
			return (IInventory)this.getTileEntity();
		}
		return null;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityPlayer)
	{
		return entityPlayer.getDistance(this.posX, this.posY, this.posZ) <= 64D;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return this.getIInventoryTileEntity().getInventoryStackLimit();
	}

	@Override
	public void openInventory()
	{
		this.getIInventoryTileEntity().openInventory();
	}

	@Override
	public void closeInventory()
	{
		this.getIInventoryTileEntity().closeInventory();
	}

	@Override
	public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack)
	{
		return this.getIInventoryTileEntity().isItemValidForSlot(slotIndex, itemStack);
	}

	@Override
	public int getSizeInventory()
	{
		return this.getIInventoryTileEntity().getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int slotIndex)
	{
		return this.getIInventoryTileEntity().getStackInSlot(slotIndex);
	}

	@Override
	public ItemStack decrStackSize(int slotIndex, int decreaseAmount)
	{
		return this.getIInventoryTileEntity().decrStackSize(slotIndex, decreaseAmount);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slotIndex)
	{
		return this.getIInventoryTileEntity().getStackInSlotOnClosing(slotIndex);
	}

	@Override
	public void setInventorySlotContents(int slotIndex, ItemStack itemStack)
	{
		this.getIInventoryTileEntity().setInventorySlotContents(slotIndex, itemStack);
	}

	@Override
	public String getInventoryName()
	{
		return this.getIInventoryTileEntity().getInventoryName();
	}

	@Override
	public boolean canPassItemRequests()
	{
		return true;
	}

	@Override
	public boolean canAcceptPushedItem(EntityMinecart entityMinecart, ItemStack itemStack)
	{
		return true;
	}

	@Override
	public boolean canProvidePulledItem(EntityMinecart entityMinecart, ItemStack itemStack)
	{
		return true;
	}
}
