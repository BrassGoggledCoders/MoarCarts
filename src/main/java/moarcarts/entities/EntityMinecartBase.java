/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the MoarCarts Mod for Minecraft.
 *
 * MoarCarts is open-source and is distributed under the MIT License.
 *
 * MoarCarts is based on the original ExtraCarts Mod created by ScottDTA and SkySom.
 * ExtraCarts (c) ScottDTA 2014
 * (http://forum.feed-the-beast.com/threads/1-7-10-b0-7-0-extra-carts.47925/)
 *
 */
package moarcarts.entities;

import boilerplate.common.utils.interfaceimpl.IInventoryImpl;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import moarcarts.MoarCarts;
import mods.railcraft.api.carts.IItemCart;
import mods.railcraft.api.carts.IMinecart;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.minecart.MinecartInteractEvent;

/**
 * @author SkySom
 */
@Optional.InterfaceList({
		@Optional.Interface(iface = "mods.railcraft.api.carts.IItemCart", modid = "RailcraftAPI|carts"),
		@Optional.Interface(iface = "mods.railcraft.api.carts.IMinecart", modid = "RailcraftAPI|carts")
})
public abstract class EntityMinecartBase extends EntityMinecart implements IMinecart, IItemCart, IInventory
{
	protected IInventoryImpl iInventoryImpl;
	protected Block cartBlock;

	public EntityMinecartBase(World world, Block block, int inventorySize, String inventoryName)
	{
		super(world);
		iInventoryImpl = new IInventoryImpl(inventorySize, inventoryName);
		this.setCartBlock(block);
	}

	public abstract ItemStack getCartItem();

	@Override
	public Block func_145820_n()
	{
		return getCartBlock();
	}

	@Override
	public Block func_145817_o()
	{
		return getCartBlock();
	}

	@Override
	public void killMinecart(DamageSource damageSource)
	{
		super.killMinecart(damageSource);
	}

	@Override
	public int getMinecartType()
	{
		return 1;
	}

	@Override
	public boolean interactFirst(EntityPlayer player)
	{
		if(net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new MinecartInteractEvent(this, player)))
		{
			return true;
		}

		if (!this.worldObj.isRemote && !player.isSneaking())
		{
			FMLNetworkHandler.openGui(player, MoarCarts.instance, 2, player.worldObj, this.getEntityId(), 0, 0);
		}

		return true;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		iInventoryImpl.readFromNBT(nbtTagCompound);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound)
	{
		super.writeToNBT(nbtTagCompound);
		iInventoryImpl.writeToNBT(nbtTagCompound);
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return !this.getInventoryName().equals(this.getCartItem().getDisplayName());
	}

	@Override
	public String getInventoryName()
	{
		return iInventoryImpl.getInventoryName();
	}

	@Override
	public int getInventoryStackLimit()
	{
		return iInventoryImpl.getInventoryStackLimit();
	}

	@Override
	public void markDirty()
	{
		iInventoryImpl.markDirty();
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityPlayer)
	{
		return !this.isDead && entityPlayer.getDistanceSqToEntity(this) <= 64.0D;
	}

	@Override
	public void openInventory()
	{
		iInventoryImpl.openInventory();
	}

	@Override
	public void closeInventory()
	{
		iInventoryImpl.closeInventory();
	}

	@Override
	public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack)
	{
		return iInventoryImpl.isItemValidForSlot(slotIndex, itemStack);
	}

	@Override
	public int getSizeInventory()
	{
		return iInventoryImpl.getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int slotIndex)
	{
		return iInventoryImpl.getStackInSlot(slotIndex);
	}

	@Override
	public ItemStack decrStackSize(int slotIndex, int decreaseAmount)
	{
		return iInventoryImpl.decrStackSize(slotIndex, decreaseAmount);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slotIndex)
	{
		return iInventoryImpl.getStackInSlotOnClosing(slotIndex);
	}

	@Override
	public void setInventorySlotContents(int slotIndex, ItemStack itemStack)
	{
		iInventoryImpl.setInventorySlotContents(slotIndex, itemStack);
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

	@Override
	public boolean doesCartMatchFilter(ItemStack itemStack, EntityMinecart entityMinecart)
	{
		if(itemStack != null && entityMinecart instanceof EntityMinecartBase)
		{
			return itemStack.isItemEqual(entityMinecart.getCartItem());
		}
		return false;
	}

	public Block getCartBlock()
	{
		return cartBlock;
	}

	public void setCartBlock(Block cartBlock)
	{
		this.cartBlock = cartBlock;
	}
}
