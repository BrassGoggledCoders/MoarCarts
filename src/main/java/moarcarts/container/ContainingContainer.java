package moarcarts.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import moarcarts.entities.EntityMinecartTileEntityBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * @author SkySom
 */
public class ContainingContainer extends Container
{
	protected EntityMinecartTileEntityBase entityMinecartTileEntityBase;
	protected Container containedContainer;
	protected IInventory playerInventory;

	public ContainingContainer(IInventory playerInventory, EntityMinecartTileEntityBase entityMinecartTileEntityBase,
			Container containedContainer)
	{
		super();
		this.entityMinecartTileEntityBase = entityMinecartTileEntityBase;
		this.containedContainer = containedContainer;
		this.playerInventory = playerInventory;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer)
	{
		return this.entityMinecartTileEntityBase.isUseableByPlayer(entityPlayer);
	}

	@Override
	public void addCraftingToCrafters(ICrafting iCrafting) {
		this.containedContainer.addCraftingToCrafters(iCrafting);
	}

	@Override
	public List getInventory() {
		return this.containedContainer.getInventory();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void removeCraftingFromCrafters(ICrafting iCrafting) {
		this.containedContainer.removeCraftingFromCrafters(iCrafting);
	}

	@Override
	public void detectAndSendChanges() {
		this.containedContainer.detectAndSendChanges();
	}

	@Override
	public boolean enchantItem(EntityPlayer entityPlayer, int slotIndex) {
		return this.containedContainer.enchantItem(entityPlayer, slotIndex);
	}

	@Override
	public Slot getSlotFromInventory(IInventory iInventory, int slotIndex) {
		return this.containedContainer.getSlotFromInventory(iInventory, slotIndex);
	}

	@Override
	public Slot getSlot(int slotIndex) {
		return this.containedContainer.getSlot(slotIndex);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex) {
		return this.containedContainer.transferStackInSlot(entityPlayer, slotIndex);
	}

	@Override
	public ItemStack slotClick(int slotIndex, int p_75144_2_, int p_75144_3_, EntityPlayer entityPlayer) {
		return this.containedContainer.slotClick(slotIndex, p_75144_2_, p_75144_3_, entityPlayer);
	}

	@Override
	public boolean func_94530_a(ItemStack p_94530_1_, Slot p_94530_2_) {
		return this.containedContainer.func_94530_a(p_94530_1_, p_94530_2_);
	}

	@Override
	protected void retrySlotClick(int p_75133_1_, int p_75133_2_, boolean p_75133_3_, EntityPlayer p_75133_4_) {
		this.containedContainer.slotClick(p_75133_1_, p_75133_2_, 1, p_75133_4_);
	}

	@Override
	public void onContainerClosed(EntityPlayer entityPlayer) {
		this.containedContainer.onContainerClosed(entityPlayer);
	}

	@Override
	public void onCraftMatrixChanged(IInventory iInventory) {
		this.containedContainer.onCraftMatrixChanged(iInventory);
	}

	@Override
	public void putStackInSlot(int slotIndex, ItemStack itemStack) {
		this.containedContainer.putStackInSlot(slotIndex, itemStack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void putStacksInSlots(ItemStack[] itemStacks) {
		this.containedContainer.putStacksInSlots(itemStacks);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int p_75137_1_, int p_75137_2_) {
		this.containedContainer.updateProgressBar(p_75137_1_, p_75137_2_);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public short getNextTransactionID(InventoryPlayer inventoryPlayer) {
		return this.containedContainer.getNextTransactionID(inventoryPlayer);
	}

	@Override
	public boolean isPlayerNotUsingContainer(EntityPlayer entityPlayer) {
		return this.containedContainer.isPlayerNotUsingContainer(entityPlayer);
	}

	@Override
	public void setPlayerIsPresent(EntityPlayer entityPlayer, boolean isPresent) {
		this.containedContainer.setPlayerIsPresent(entityPlayer, isPresent);
	}

	@Override
	public boolean canDragIntoSlot(Slot slotIndex)
	{
		return this.containedContainer.canDragIntoSlot(slotIndex);
	}
}
