package moarcarts.entities;

import cpw.mods.fml.common.Optional;
import moarcarts.renderers.IRenderBlock;
import mods.railcraft.api.carts.IItemCart;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
@Optional.Interface(iface = "mods.railcraft.api.carts.IItemCart", modid = "RailcraftAPI|carts")
public abstract class EntityMinecartInventoryTEBase extends EntityMinecartTEBase implements IRenderBlock, IInventory,
		IItemCart
{
	private boolean dropContentsWhenDead = true;

	public EntityMinecartInventoryTEBase(World world, int metadata)
	{
		super(world, metadata);
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
	public void setDead()
	{
		if (this.dropContentsWhenDead() && !this.shouldSaveDataToItem())
		{
			for (int i = 0; i < this.getSizeInventory(); ++i)
			{
				ItemStack itemstack = this.getStackInSlot(i);

				if (itemstack != null)
				{
					float f = this.rand.nextFloat() * 0.8F + 0.1F;
					float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
					float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

					while (itemstack.stackSize > 0)
					{
						int j = this.rand.nextInt(21) + 10;

						if (j > itemstack.stackSize)
						{
							j = itemstack.stackSize;
						}

						itemstack.stackSize -= j;
						EntityItem entityitem = new EntityItem(this.worldObj, this.posX + (double)f, this.posY + (double)f1, this.posZ + (double)f2, new ItemStack(itemstack.getItem(), j, itemstack.getItemDamage()));

						if (itemstack.hasTagCompound())
						{
							entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
						}

						float f3 = 0.05F;
						entityitem.motionX = (double)((float)this.rand.nextGaussian() * f3);
						entityitem.motionY = (double)((float)this.rand.nextGaussian() * f3 + 0.2F);
						entityitem.motionZ = (double)((float)this.rand.nextGaussian() * f3);
						if(!worldObj.isRemote)
						{
							this.worldObj.spawnEntityInWorld(entityitem);
						}
					}
				}
			}
		}

		super.setDead();
	}

	@Override
	public void travelToDimension(int dimension)
	{
		this.setDropContentsWhenDead(false);
		super.travelToDimension(dimension);
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

	public boolean dropContentsWhenDead()
	{
		return dropContentsWhenDead;
	}

	public void setDropContentsWhenDead(boolean drop)
	{
		this.dropContentsWhenDead = drop;
	}
}
