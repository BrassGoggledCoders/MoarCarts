package moarcarts.entities;

import moarcarts.MoarCarts;
import moarcarts.fakeworld.FakePlayer;
import moarcarts.fakeworld.FakeWorld;
import moarcarts.network.EntityTileEntityMessage;
import moarcarts.renderers.IRenderBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @author SkySom
 */
public abstract class EntityMinecartTileEntityBase extends EntityMinecartBase implements IRenderBlock
{
	protected TileEntity tileEntity;
	protected FakeWorld fakeWorld;
	protected Random random = new Random();

	private static int IS_DIRTY_DW = 27;
	private static int UPDATE_TICKS = 200;

	public EntityMinecartTileEntityBase(World world, Block cartBlock, int inventorySize, String inventoryName)
	{
		this(world, cartBlock, 0, inventorySize, inventoryName);
	}

	public EntityMinecartTileEntityBase(World world, Block cartBlock, int metadata, int inventorySize, String inventoryName)
	{
		super(world, cartBlock, metadata, inventorySize, inventoryName);
		if(cartBlock instanceof BlockContainer)
		{
			this.setTileEntity(cartBlock.createTileEntity(world, metadata));
		}
	}

	@Override
	public void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(IS_DIRTY_DW, Byte.valueOf((byte) 0));
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		if(random.nextInt(UPDATE_TICKS) == 0 && this.isDirty())
		{
			this.setDirty(false);
			this.sendUpdate();
		}
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

	@Override
	public boolean interactFirst(EntityPlayer player)
	{
		this.sendUpdate();
		FakePlayer fakePlayer = new FakePlayer(player, this);
		return this.getCartBlock().onBlockActivated(getFakeWorld(), 0, 0, 0, fakePlayer, this.getMetadata(), 0, 0, 0);
	}

	@Override
	public void markDirty()
	{
		super.markDirty();
		this.setDirty(true);
	}

	@Override
	public 	RenderMethod getRenderMethod()
	{
		return RenderMethod.VMC;
	}

	public void sendUpdate()
	{
		MoarCarts.packetHandler.sendToAllAround(new EntityTileEntityMessage(this), this);
	}

	public TileEntity createTileEntity()
	{
		return this.getCartBlock().createTileEntity(worldObj, this.getMetadata());
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
			this.sendUpdate();
		} else
		{
			MoarCarts.logger.error("Null Tile Entity Reported. THIS IS BAD!");
		}
	}

	public boolean isDirty()
	{
		return dataWatcher.getWatchableObjectByte(IS_DIRTY_DW) != 0;
	}

	public void setDirty(boolean isDirty)
	{
		dataWatcher.updateObject(IS_DIRTY_DW, Byte.valueOf(isDirty ? 1 : (byte) 0));
	}

	public FakeWorld getFakeWorld()
	{
		if(fakeWorld == null)
		{
			fakeWorld = new FakeWorld(this);
		}
		return fakeWorld;
	}

	public void setFakeWorld(FakeWorld fakeWorld)
	{
		this.fakeWorld = fakeWorld;
	}
}
