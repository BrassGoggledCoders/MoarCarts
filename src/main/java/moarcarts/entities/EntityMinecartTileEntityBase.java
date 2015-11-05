package moarcarts.entities;

import moarcarts.MoarCarts;
import moarcarts.fakeworld.FakePlayer;
import moarcarts.fakeworld.FakeWorld;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public abstract class EntityMinecartTileEntityBase extends EntityMinecartBase
{
	protected TileEntity tileEntity;
	protected FakeWorld fakeWorld;

	private int FAKE_NBT_ITEM_DW = 27;

	private String FAKE_NBT_ITEM_NAME = "NBT STORAGE";

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

	public void onEntityInit()
	{
		dataWatcher.addObject(FAKE_NBT_ITEM_DW, );
	}

	public boolean interactFirst(EntityPlayer player)
	{
		FakePlayer fakePlayer = new FakePlayer(player, this);
		return this.getCartBlock().onBlockActivated(fakeWorld, 0, 0, 0, fakePlayer, this.getMetadata(), 0, 0, 0);
	}

	public void markDirty()
	{

	}

	public TileEntity getTileEntity()
	{
		return tileEntity;
	}

	public void setTileEntity(TileEntity tileEntity)
	{
		if(this.getTileEntity() != null)
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
		if(this.getTileEntity() != null)
		{
			fakeWorld = new FakeWorld(worldObj, this);
			this.getTileEntity().setWorldObj(fakeWorld);
		} else
		{
			MoarCarts.logger.error("Null Tile Entity Reported. THIS IS BAD!");
		}
	}
}
