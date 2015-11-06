package moarcarts.entities;

import moarcarts.MoarCarts;
import moarcarts.fakeworld.FakePlayer;
import moarcarts.fakeworld.FakeWorld;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @author SkySom
 */
public abstract class EntityMinecartTileEntityBase extends EntityMinecartBase
{
	protected TileEntity tileEntity;
	protected FakeWorld fakeWorld;
	protected ItemStack fakeNBTItemStack;
	protected Random random = new Random();

	private static int FAKE_NBT_ITEM_DW = 27;
	private static String FAKE_NBT_NAME = "TILEENTITYDATA";

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
	public void onUpdate()
	{
		if(random.nextInt(20) == 0)
		{
			NBTTagCompound nbtTagCompound = new NBTTagCompound();
			if(!worldObj.isRemote)
			{
				this.getTileEntity().writeToNBT(nbtTagCompound);
				this.writeNBTToDW(nbtTagCompound);
			}
			if(worldObj.isRemote)
			{
				this.readNBTFromDW(nbtTagCompound);
				this.getTileEntity().readFromNBT(nbtTagCompound);
			}
		}
	}

	@Override
	public void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(FAKE_NBT_ITEM_DW, new ItemStack(MoarCarts.FAKE_NBT_ITEM));
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbtTagCompound)
	{
		super.readEntityFromNBT(nbtTagCompound);
		this.setFakeNBTItem(ItemStack.loadItemStackFromNBT(this.readNBTFromDW(nbtTagCompound)));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbtTagCompound)
	{
		super.writeEntityToNBT(nbtTagCompound);
		if(this.getFakeNBTItem() != null)
		{
			NBTTagCompound fakeNBTCompound = new NBTTagCompound();
			this.writeNBTToDW(fakeNBTCompound);
		}
	}

	public boolean interactFirst(EntityPlayer player)
	{
		FakePlayer fakePlayer = new FakePlayer(player, this);
		return this.getCartBlock().onBlockActivated(fakeWorld, 0, 0, 0, fakePlayer, this.getMetadata(), 0, 0, 0);
	}

	public void markDirty()
	{
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		this.getTileEntity().writeToNBT(nbtTagCompound);
		this.writeNBTToDW(nbtTagCompound);
		if(worldObj.isRemote)
		{
			this.getTileEntity().readFromNBT(nbtTagCompound);
		}
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
		if(this.tileEntity != null)
		{
			try
			{
				NBTTagCompound nbtTagCompound = new NBTTagCompound();
				this.getTileEntity().writeToNBT(nbtTagCompound);
				tileEntity.readFromNBT(nbtTagCompound);
			} catch(Exception exception)
			{
				MoarCarts.logger.info("Couldn't transfer Tile NBT.");
			}
		}

		this.tileEntity = tileEntity;
		if(this.tileEntity != null)
		{
			fakeWorld = new FakeWorld(worldObj, this);
			this.tileEntity.setWorldObj(fakeWorld);
			NBTTagCompound nbtTagCompound = new NBTTagCompound();
			this.tileEntity.writeToNBT(nbtTagCompound);
			this.writeNBTToDW(nbtTagCompound);
		} else
		{
			MoarCarts.logger.error("Null Tile Entity Reported. THIS IS BAD!");
		}
	}

	public NBTTagCompound readNBTFromDW(NBTTagCompound nbtTagCompound)
	{
		fakeNBTItemStack = this.getFakeNBTItem();
		nbtTagCompound.setTag(FAKE_NBT_NAME, fakeNBTItemStack.getTagCompound());
		this.setFakeNBTItem(fakeNBTItemStack);
		return nbtTagCompound;
	}

	public void writeNBTToDW(NBTTagCompound nbtTagCompound)
	{
		fakeNBTItemStack = this.getFakeNBTItem();
		fakeNBTItemStack.setTagCompound(nbtTagCompound.getCompoundTag(FAKE_NBT_NAME));
		this.setFakeNBTItem(fakeNBTItemStack);
	}

	public ItemStack getFakeNBTItem()
	{
		return fakeNBTItemStack = dataWatcher.getWatchableObjectItemStack(FAKE_NBT_ITEM_DW);
	}

	public void setFakeNBTItem(ItemStack itemStack)
	{
		if(itemStack == null)
		{
			itemStack = new ItemStack(MoarCarts.FAKE_NBT_ITEM);
		}
		fakeNBTItemStack = itemStack;
		dataWatcher.updateObject(FAKE_NBT_ITEM_DW, itemStack);
	}
}
