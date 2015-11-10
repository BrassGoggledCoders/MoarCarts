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

	private static int INVENTORY_NAME_DW = 30;
	private static int METADATA_DW = 31;

	private static String INVENTORY_NAME = "INVENTORYNAME";
	private static String METADATA = "METADATA";


	public EntityMinecartBase(World world, Block block, int metadata, int inventorySize, String inventoryName)
	{
		super(world);
		iInventoryImpl = new IInventoryImpl(inventorySize, inventoryName);
		this.setMetadata(metadata);
		this.setCartBlock(block);
	}

	public EntityMinecartBase(World world, Block block, int inventorySize, String inventoryName)
	{
		this(world, block, 0, inventorySize, inventoryName);
	}

	public abstract ItemStack getCartItem();

	@Override
	public void entityInit(){
		super.entityInit();
		dataWatcher.addObject(METADATA_DW, 0);
		dataWatcher.addObject(INVENTORY_NAME_DW, "");
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbtTagCompound)
	{
		super.readEntityFromNBT(nbtTagCompound);
		this.setMetadata(nbtTagCompound.getInteger(METADATA));
		this.setInventoryName(nbtTagCompound.getString(INVENTORY_NAME));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbtTagCompound)
	{
		super.writeEntityToNBT(nbtTagCompound);
		nbtTagCompound.setInteger(METADATA, this.getMetadata());
		nbtTagCompound.setString(INVENTORY_NAME, this.getInventoryName());
	}

	@Override
	public Block func_145820_n()
	{
		return this.getCartBlock();
	}

	@Override
	public Block func_145817_o()
	{
		return this.getCartBlock();
	}

	@Override
	public int getDisplayTileData()
	{
		return this.getMetadata();
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
		return false; //this.getInventoryName() != "";
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

	public int getMetadata()
	{
		return this.dataWatcher.getWatchableObjectInt(METADATA_DW);
	}

	public void setMetadata(int metadata)
	{
		this.dataWatcher.updateObject(METADATA_DW, metadata);
	}

	public void setInventoryName(String inventoryName)
	{
		this.dataWatcher.updateObject(INVENTORY_NAME_DW, inventoryName);
	}

	@Override
	public String getInventoryName()
	{
		return this.dataWatcher.getWatchableObjectString(INVENTORY_NAME_DW);
	}
}
