package moarcarts.fakeworld;

import moarcarts.entities.EntityMinecartTileEntityBase;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author SkySom
 */
public class FakeWorld extends World
{
	private EntityMinecartTileEntityBase entityMinecartTileEntityBase;

	public FakeWorld(EntityMinecartTileEntityBase entityMinecartTileEntityBase)
	{
		this(entityMinecartTileEntityBase.worldObj, entityMinecartTileEntityBase);
	}

	public FakeWorld(World world, EntityMinecartTileEntityBase entityMinecartTileEntityBase)
	{
		super(world.getSaveHandler(), world.getWorldInfo().getWorldName(), world.provider,
				new WorldSettings(world.getWorldInfo()), world.theProfiler);
		this.setEntityMinecartTileEntityBase(entityMinecartTileEntityBase);
	}

	@Override
	protected IChunkProvider createChunkProvider()
	{
		return chunkProvider;
	}

	@Override
	public void markTileEntityChunkModified(int posX, int posY, int posZ, TileEntity tileEntity)
	{
		this.getEntityMinecartTileEntityBase().markDirty();
	}

	@Override
	public void markBlockForUpdate(int posX, int posY, int posZ)
	{
		//NO-OP
	}

	@Override
	public void func_147453_f(int posX, int posY, int posZ, Block block)
	{
		//NO-OP
	}

	@Override
	protected int func_152379_p()
	{
		return 0;
	}

	@Override
	public Block getBlock(int x, int y, int z)
	{
		return this.getEntityMinecartTileEntityBase().getCartBlock();
	}

	@Override
	public void addBlockEvent(int x, int y, int z, Block block, int metadata, int p_14745) {}

	@Override
	public boolean isSideSolid(int x, int y, int z, ForgeDirection blockSide)
	{
		return false;
	}

	@Override
	public TileEntity getTileEntity(int x, int y, int z)
	{
		return this.getEntityMinecartTileEntityBase().getTileEntity();
	}

	@Override
	public Entity getEntityByID(int id)
	{
		return this.getEntityMinecartTileEntityBase().worldObj.getEntityByID(id);
	}

	@Override
	public int getBlockMetadata(int posX, int posY, int posZ)
	{
		return this.getEntityMinecartTileEntityBase().getMetadata();
	}

	public EntityMinecartTileEntityBase getEntityMinecartTileEntityBase()
	{
		return entityMinecartTileEntityBase;
	}

	public void setEntityMinecartTileEntityBase(EntityMinecartTileEntityBase entityMinecartTileEntityBase)
	{
		this.entityMinecartTileEntityBase = entityMinecartTileEntityBase;
	}
}
