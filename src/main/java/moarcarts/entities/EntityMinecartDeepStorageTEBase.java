package moarcarts.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.IDeepStorageUnit;

/**
 * @author SkySom
 */
public abstract class EntityMinecartDeepStorageTEBase extends EntityMinecartInventoryTEBase implements IDeepStorageUnit
{
	public EntityMinecartDeepStorageTEBase(World world, Block block, int metadata)
	{
		super(world, block, metadata);
	}

	@Override
	public ItemStack getStoredItemType()
	{
		return this.getIDeepStorageUnitEntity().getStoredItemType();
	}

	@Override
	public void setStoredItemCount(int amount)
	{
		this.getIDeepStorageUnitEntity().setStoredItemCount(amount);
	}

	@Override
	public void setStoredItemType(ItemStack itemStack, int amount)
	{
		this.getIDeepStorageUnitEntity().setStoredItemType(itemStack, amount);
	}

	@Override
	public int getMaxStoredCount()
	{
		return this.getIDeepStorageUnitEntity().getMaxStoredCount();
	}

	public IDeepStorageUnit getIDeepStorageUnitEntity()
	{
		return (IDeepStorageUnit)this.getTileEntity();
	}

	@Override
	public boolean canAcceptPushedItem(EntityMinecart entityMinecart, ItemStack itemStack)
	{
		return itemStack != null && (this.getStoredItemType() == null ||
				itemStack.getItem() == this.getStoredItemType().getItem());
	}
}
