package moarcarts.entities;

import moarcarts.fakeworld.FakePlayer;
import moarcarts.fakeworld.FakeWorld;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public abstract class EntityMinecartTileEntityBase extends EntityMinecartBase
{
	protected TileEntity tileEntity;
	protected FakeWorld fakeWorld;

	public EntityMinecartTileEntityBase(World world, Block cartBlock, int inventorySize, String inventoryName)
	{
		this(world, cartBlock, 0, inventorySize, inventoryName);
	}

	public EntityMinecartTileEntityBase(World world, Block cartBlock, int metadata, int inventorySize, String inventoryName)
	{
		super(world, cartBlock, metadata, inventorySize, inventoryName);
		if(cartBlock instanceof ITileEntityProvider)
		{
			fakeWorld = new FakeWorld(world, this);
			this.setTileEntity(((ITileEntityProvider)cartBlock).createNewTileEntity(world, metadata));
			this.getTileEntity().setWorldObj(fakeWorld);
		}
	}

	public boolean interactFirst(EntityPlayer player)
	{
		FakePlayer fakePlayer = new FakePlayer(player);
		return this.getCartBlock().onBlockActivated(fakeWorld, 0, 0, 0, fakePlayer, this.metadata, 0, 0, 0);
	}

	public TileEntity getTileEntity()
	{
		return tileEntity;
	}

	public void setTileEntity(TileEntity tileEntity)
	{
		this.tileEntity = tileEntity;
	}
}
