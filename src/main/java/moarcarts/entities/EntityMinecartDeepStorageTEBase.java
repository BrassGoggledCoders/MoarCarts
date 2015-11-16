package moarcarts.entities;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.IDeepStorageUnit;

/**
 * @author SkySom
 */
public abstract class EntityMinecartDeepStorageTEBase extends EntityMinecartTEBase implements IDeepStorageUnit
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
}
