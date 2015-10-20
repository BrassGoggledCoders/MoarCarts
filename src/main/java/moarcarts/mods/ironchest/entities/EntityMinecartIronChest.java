package moarcarts.mods.ironchest.entities;

import cpw.mods.ironchest.IronChest;
import cpw.mods.ironchest.IronChestType;
import moarcarts.entities.EntityMinecartTileEntityBase;
import moarcarts.mods.ironchest.items.ItemMinecartIronChest;
import moarcarts.utils.LoggerMoarCarts;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class EntityMinecartIronChest extends EntityMinecartTileEntityBase
{
	private static final int IRON_CHEST_TYPE_DW = 25;
	private static final String IRON_CHEST_TYPE = "IRONCHESTTYPE";

	public EntityMinecartIronChest(World world)
	{
		this(world, IronChestType.IRON);
	}

	public EntityMinecartIronChest(World world, IronChestType ironChestType)
	{
		super(world, IronChest.ironChestBlock, ironChestType.ordinal(), ironChestType.getRowCount() *
						ironChestType.getRowLength(), "Iron chest cart");
		this.setIronChestType(ironChestType);
	}

	@Override
	public void entityInit(){
		super.entityInit();
		dataWatcher.addObject(IRON_CHEST_TYPE_DW, 0);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbtTagCompound)
	{
		super.readEntityFromNBT(nbtTagCompound);
		this.setIronChestType(nbtTagCompound.getInteger(IRON_CHEST_TYPE));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbtTagCompound)
	{
		super.writeEntityToNBT(nbtTagCompound);
		nbtTagCompound.setInteger(IRON_CHEST_TYPE, this.getIronChestType().ordinal());
	}

	@Override
	public ItemStack getCartItem()
	{
		return new ItemStack(new ItemMinecartIronChest());
	}

	@Override
	public int getSizeInventory()
	{
		return this.getIronChestType().getRowCount() * this.getIronChestType().getRowLength();
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	public IronChestType getIronChestType()
	{
		return IronChestType.values()[this.getDataWatcher().getWatchableObjectInt(IRON_CHEST_TYPE_DW)];
	}

	public void setIronChestType(IronChestType ironChestType)
	{
		this.getDataWatcher().updateObject(IRON_CHEST_TYPE_DW, ironChestType.ordinal());
		try {
			this.setTileEntity(ironChestType.clazz.newInstance());
		} catch(Exception exception)
		{
			LoggerMoarCarts.error("Couldn't set TileEntity");
		}
	}

	public void setIronChestType(int ironChestType)
	{
		this.setIronChestType(IronChestType.values()[ironChestType]);
	}
}
