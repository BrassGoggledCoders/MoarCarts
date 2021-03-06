package xyz.brassgoggledcoders.moarcarts.entities;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.minecart.MinecartInteractEvent;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;
import xyz.brassgoggledcoders.moarcarts.MoarCarts;
import xyz.brassgoggledcoders.moarcarts.fakeworld.FakePlayer;
import xyz.brassgoggledcoders.moarcarts.fakeworld.FakeWorld;
import xyz.brassgoggledcoders.moarcarts.network.EntityTileEntityUpdateMessage;
import xyz.brassgoggledcoders.moarcarts.renderers.IRenderBlock;

/**
 * @author SkySom
 */
public abstract class EntityMinecartTEBase extends EntityMinecartBase implements IRenderBlock
{
	protected TileEntity tileEntity;

	private static int IS_DIRTY_DW = 30;
	private static int IS_CLIENT_NEEDY = 28;
	private static int UPDATE_TICKS = 200;
	private boolean isReallyDirty = false;
	protected boolean shouldUpdateBlockState = false;

	public EntityMinecartTEBase(World world, int metadata)
	{
		super(world, metadata);
		this.setTileEntity(this.getCartBlock().createTileEntity(world, this.getDisplayTile()));
		this.setDirty(true);
	}

	@Override
	public void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(IS_DIRTY_DW, (byte) 1);
		dataWatcher.addObject(IS_CLIENT_NEEDY, (byte) 1);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		if(!this.worldObj.isRemote && (this.isClientNeedy() || (this.isDirty() &&
				random.nextInt(UPDATE_TICKS) == 0) || (this.isReallyDirty() && random.nextInt(UPDATE_TICKS / 10) == 0)))
		{
			this.setDirty(false);
			this.setClientNeedy(false);
			this.setReallyDirty(false);
			this.sendUpdateToAllAround();
		}
		if(shouldTileUpdate() && this.getTileEntity() instanceof ITickable)
		{
			((ITickable)this.getTileEntity()).update();
		}
		if(shouldUpdateBlockState)
		{
			IBlockState blockState = this.getBlockState(this.getMetadata());
			blockState = this.getCartBlock().getActualState(blockState, getFakeWorld(), BlockPos.ORIGIN);
			blockState = this.getCartBlock().getExtendedState(blockState, getFakeWorld(), BlockPos.ORIGIN);
			this.func_174899_a(blockState);
			this.shouldUpdateBlockState = false;
			this.sendUpdateToAllAround();
		}
	}

	@Override
	public boolean interactFirst(EntityPlayer player)
	{
		if(!MinecraftForge.EVENT_BUS.post(new MinecartInteractEvent(this, player)))
		{
			this.sendUpdateToAllAround();
			EntityPlayer fakePlayer = new FakePlayer(player, this, this.shouldAccessPlayerInventory());
			boolean blockActivated = this.getCartBlock()
					.onBlockActivated(this.getFakeWorld(), ORIGIN_POS, this.getDisplayTile(), fakePlayer,
							EnumFacing.NORTH, 0, 0, 0);
			this.sendUpdateToAllAround();
			return blockActivated;
		}
		return true;
	}

	public boolean shouldAccessPlayerInventory()
	{
		return true;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbtTagCompound)
	{
		super.readEntityFromNBT(nbtTagCompound);
		this.getTileEntity().readFromNBT(nbtTagCompound.getCompoundTag("TILEENTITY"));
		this.setDirty(nbtTagCompound.getBoolean("DIRTY"));
		this.setClientNeedy(nbtTagCompound.getBoolean("NEEDY"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbtTagCompound)
	{
		super.writeEntityToNBT(nbtTagCompound);
		NBTTagCompound tileEntityNBTTagCompound = new NBTTagCompound();
		this.getTileEntity().writeToNBT(tileEntityNBTTagCompound);
		nbtTagCompound.setTag("TILEENTITY", tileEntityNBTTagCompound);
		nbtTagCompound.setBoolean("DIRTY", this.isDirty());
		nbtTagCompound.setBoolean("NEEDY", this.isClientNeedy());
	}

	public void markDirty()
	{
		this.setDirty(true);
	}

	public void setTileEntityNBT(ItemStack itemStack)
	{
		if(itemStack.hasTagCompound() && this.shouldSaveDataToItem())
		{
			this.getTileEntity().readFromNBT(itemStack.getTagCompound().getCompoundTag("tilenbt"));
		}
	}

	@Override
	public RenderMethod getRenderMethod()
	{
		return this.getItem().getCartBlockRenderMethod(this.getCartItem());
	}

	public void sendUpdateToAllAround()
	{
		if(!worldObj.isRemote)
		{
			BoilerplateLib.getPacketHandler().sendToAllAround(new EntityTileEntityUpdateMessage(this), this);
		}
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

	@Override
	public void dropCartItemStack(ItemStack cartItem)
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
		super.dropCartItemStack(cartItem);
	}

	public TileEntity createTileEntity()
	{
		if(worldObj != null && this.getCartBlock() != null)
		{
			return this.getCartBlock().createTileEntity(worldObj, this.getDisplayTile());
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
	public void func_174899_a(IBlockState blockState)
	{
		super.func_174899_a(blockState);

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

	public boolean isReallyDirty()
	{
		return this.isReallyDirty;
	}

	public void setReallyDirty(boolean isReallyDirty)
	{
		this.isReallyDirty = isReallyDirty;
	}

	public boolean isClientNeedy()
	{
		return dataWatcher.getWatchableObjectByte(IS_CLIENT_NEEDY) != 0;
	}

	public void setClientNeedy(boolean needy)
	{
		dataWatcher.updateObject(IS_CLIENT_NEEDY, needy ? 1 : (byte) 0);
	}

	public boolean showHalo()
	{
		return false;
	}

	public String getHaloString()
	{
		return "";
	}
}
