package moarcarts.entities;

import moarcarts.MoarCarts;
import moarcarts.fakeworld.FakePlayer;
import moarcarts.fakeworld.FakeWorld;
import moarcarts.network.EntityTileEntityUpdateMessage;
import moarcarts.network.EntityTileEntityUpdateRequestMessage;
import moarcarts.renderers.IRenderBlock;
import net.minecraft.block.BlockContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public abstract class EntityMinecartTEBase extends EntityMinecartBase implements IRenderBlock
{
	protected TileEntity tileEntity;

	private static int IS_DIRTY_DW = 30;
	private static int UPDATE_TICKS = 200;

	public EntityMinecartTEBase(World world, int metadata)
	{
		super(world, metadata);
		if(cartBlock instanceof BlockContainer)
		{
			this.setTileEntity(cartBlock.createTileEntity(world, metadata));
		}
		this.setDirty(true);
	}

	@Override
	public void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(IS_DIRTY_DW, (byte) 0);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		if(random.nextInt(UPDATE_TICKS) == 0 && this.isDirty())
		{
			this.setDirty(false);
			this.sendUpdateToAllAround();
		}
		if(shouldTileUpdate())
		{
			this.getTileEntity().updateEntity();
		}
	}

	@Override
	public boolean interactFirst(EntityPlayer player)
	{
		this.sendUpdateToAllAround();
		if(!player.isSneaking())
		{
			FakePlayer fakePlayer = new FakePlayer(player, this);
			return this.getCartBlock().onBlockActivated(this.getFakeWorld(), 0, 0, 0, fakePlayer, this.getMetadata(), 0, 0, 0);
		}
		return false;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbtTagCompound)
	{
		super.readEntityFromNBT(nbtTagCompound);
		this.getTileEntity().readFromNBT(nbtTagCompound.getCompoundTag("TILEENTITY"));
		this.setDirty(nbtTagCompound.getBoolean("DIRTY"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbtTagCompound)
	{
		super.writeEntityToNBT(nbtTagCompound);
		NBTTagCompound tileEntityNBTTagCompound = new NBTTagCompound();
		this.getTileEntity().writeToNBT(tileEntityNBTTagCompound);
		nbtTagCompound.setTag("TILEENTITY", tileEntityNBTTagCompound);
		nbtTagCompound.setBoolean("DIRTY", this.isDirty());
	}

	public void markDirty()
	{
		this.setDirty(true);
	}

	@Override
	public RenderMethod getRenderMethod()
	{
		return this.getItem().getCartBlockRenderMethod(this.getCartItem());
	}

	public void sendUpdateToAllAround()
	{
		MoarCarts.packetHandler.sendToAllAround(new EntityTileEntityUpdateMessage(this), this);
	}

	public void requestClientUpdate()
	{
		MoarCarts.packetHandler.sendToServer(new EntityTileEntityUpdateRequestMessage(this));
	}

	@Override
	public void afterEntitySpawned()
	{
		this.sendUpdateToAllAround();
	}

	public boolean shouldSaveDataToItem()
	{
		return false;
	}

	public void dropCart(ItemStack cartItem)
	{
		if(this.shouldSaveDataToItem())
		{
			NBTTagCompound nbtTagCompound = new NBTTagCompound();
			NBTTagCompound itemNBTTagCompound;
			this.getTileEntity().writeToNBT(nbtTagCompound);
			if(cartItem.hasTagCompound())
			{
				itemNBTTagCompound = cartItem.getTagCompound();
			} else
			{
				itemNBTTagCompound = new NBTTagCompound();
			}
			this.getTileEntity().writeToNBT(nbtTagCompound);
			itemNBTTagCompound.setTag("tilenbt", nbtTagCompound);
			cartItem.setTagCompound(itemNBTTagCompound);
		}
		super.dropCart(cartItem);
	}

	public TileEntity createTileEntity()
	{
		if(worldObj != null && this.getCartBlock() != null)
		{
			return this.getCartBlock().createTileEntity(worldObj, this.getMetadata());
		}
		return null;
	}

	public TileEntity getTileEntity()
	{
		if(this.tileEntity == null)
		{
			this.setTileEntity(this.createTileEntity());
		}
		return tileEntity;
	}

	public void setTileEntity(TileEntity tileEntity)
	{
		NBTTagCompound nbtTagCompound = null;
		if(this.tileEntity != null)
		{
			nbtTagCompound = new NBTTagCompound();
			this.tileEntity.writeToNBT(nbtTagCompound);
		}

		this.tileEntity = tileEntity;
		if(this.tileEntity != null)
		{
			this.setFakeWorld(new FakeWorld(this));
			this.tileEntity.setWorldObj(this.getFakeWorld());
			if(nbtTagCompound != null)
			{
				this.tileEntity.readFromNBT(nbtTagCompound);
			}
			this.sendUpdateToAllAround();
		} else
		{
			MoarCarts.logger.error("Null Tile Entity Reported. THIS IS BAD!");
		}
	}

	@Override
	public void setMetadata(int metadata)
	{
		super.setMetadata(metadata);

		TileEntity tileEntity = this.createTileEntity();
		if(tileEntity != null)
		{
			this.setTileEntity(tileEntity);
		}
	}

	public boolean shouldTileUpdate()
	{
		return false;
	}

	public boolean isDirty()
	{
		return dataWatcher.getWatchableObjectByte(IS_DIRTY_DW) != 0;
	}

	public void setDirty(boolean isDirty)
	{
		dataWatcher.updateObject(IS_DIRTY_DW, isDirty ? 1 : (byte) 0);
	}
}
