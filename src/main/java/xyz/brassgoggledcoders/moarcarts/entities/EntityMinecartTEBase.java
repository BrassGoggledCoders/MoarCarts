package xyz.brassgoggledcoders.moarcarts.entities;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;
import xyz.brassgoggledcoders.moarcarts.MoarCarts;
import xyz.brassgoggledcoders.moarcarts.fakeworld.FakePlayer;
import xyz.brassgoggledcoders.moarcarts.fakeworld.FakeWorld;
import xyz.brassgoggledcoders.moarcarts.network.EntityTileEntityUpdateMessage;
import xyz.brassgoggledcoders.moarcarts.renderers.IRenderBlock;

import java.util.LinkedHashMap;

/**
 * @author SkySom
 */
public abstract class EntityMinecartTEBase extends EntityMinecartBase implements IRenderBlock
{
	protected TileEntity tileEntity;

	private static final DataParameter<Boolean>
			IS_DIRTY = EntityDataManager.<Boolean>createKey(EntityMinecart.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean>
			IS_CLIENT_NEEDY = EntityDataManager.<Boolean>createKey(EntityMinecart.class, DataSerializers.BOOLEAN);

	public EntityMinecartTEBase(World world, int metadata)
	{
		super(world, metadata);
		if(this.getCartBlock() instanceof ITileEntityProvider)
		{
			this.setTileEntity(this.getCartBlock().createTileEntity(world, this.getDisplayTile()));
		}
		this.setDirty(true);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(IS_DIRTY, true);
		this.dataManager.register(IS_CLIENT_NEEDY, true);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		if(!this.worldObj.isRemote && (this.isClientNeedy() ||
				(this.isDirty() && random.nextInt(200) == 0)))
		{
			this.setDirty(false);
			this.setClientNeedy(false);
			this.sendUpdateToAllAround();
		}
		if(shouldTileUpdate() && this.getTileEntity() instanceof ITickable)
		{
			((ITickable)this.getTileEntity()).update();
		}
	}

	@Override
	public boolean processInitialInteract(EntityPlayer player, ItemStack stack, EnumHand hand)
	{
		this.sendUpdateToAllAround();
		EntityPlayer fakePlayer = new FakePlayer(player, this, this.shouldAccessPlayerInventory());
		return this.getCartBlock().onBlockActivated(this.getFakeWorld(), ORIGIN_POS, this.getDisplayTile(),
					fakePlayer, hand, stack, EnumFacing.NORTH, 0, 0, 0);
	}

	public boolean shouldAccessPlayerInventory()
	{
		return false;
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
	public void setDisplayTile(IBlockState blockState)
	{
		super.setDisplayTile(blockState);

		TileEntity tileEntity = this.createTileEntity();
		if(tileEntity != null)
		{
			this.setTileEntity(tileEntity);
		}
	}

	@Override
	public LinkedHashMap<String, String> getDebugStrings()
	{
		LinkedHashMap<String, String> debugStrings = super.getDebugStrings();
		debugStrings.put("tile", "Cart TE: " + getTileEntity().toString());
		return debugStrings;
	}

	public boolean shouldTileUpdate()
	{
		return false;
	}

	public boolean isDirty()
	{
		return this.dataManager.get(IS_DIRTY);
	}

	public void setDirty(boolean isDirty)
	{
		this.dataManager.set(IS_DIRTY, isDirty);
	}

	public boolean isClientNeedy()
	{
		return this.dataManager.get(IS_CLIENT_NEEDY);
	}

	public void setClientNeedy(boolean needy)
	{
		this.dataManager.set(IS_CLIENT_NEEDY, needy);
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
