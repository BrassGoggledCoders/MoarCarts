package xyz.brassgoggledcoders.moarcarts.entities;

import mods.railcraft.api.carts.IItemCart;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

/**
 * @author SkySom
 * TODO: Railcraft - IItemcart
 */
@Optional.Interface(iface = "mods.railcraft.api.carts.IItemCart", modid = "RailcraftAPI|carts")
public abstract class EntityMinecartInventoryTEBase extends EntityMinecartCapableTEBase implements IItemCart
{
	private boolean dropContentsWhenDead = true;

	public EntityMinecartInventoryTEBase(World world, int metadata)
	{
		super(world, metadata);
	}

	@Override
	public void setDead()
	{
		if (this.dropContentsWhenDead() && !this.shouldSaveDataToItem())
		{
			IItemHandler itemHandler = getItemHandler();
			if(itemHandler != null)
			{
				for (int i = 0; i < itemHandler.getSlots(); ++i)
				{
					ItemStack itemstack = itemHandler.getStackInSlot(i);

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
		}

		super.setDead();
	}

	@Override
	public void travelToDimension(int dimension)
	{
		this.setDropContentsWhenDead(false);
		super.travelToDimension(dimension);
	}

	//TODO Move to Boilerplate. Added Comparator stuff for Capabilities
	public IItemHandler getItemHandler()
	{
		if(hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP))
		{
			return getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
		}
		return null;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityPlayer)
	{
		return entityPlayer.getDistance(this.posX, this.posY, this.posZ) <= 64D;
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
