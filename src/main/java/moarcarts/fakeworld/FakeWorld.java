package moarcarts.fakeworld;

import moarcarts.entities.EntityMinecartTileEntityBase;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.chunk.IChunkProvider;

/**
 * @author SkySom
 */
public class FakeWorld extends World
{
	private EntityMinecartTileEntityBase entityMinecartTileEntityBase;

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
	protected int func_152379_p()
	{
		return 0;
	}

	@Override
	public Entity getEntityByID(int p_73045_1_)
	{
		return null;
	}

	@Override
	public Block getBlock(int x, int y, int z)
	{
		return entityMinecartTileEntityBase.getCartBlock();
	}

	@Override
	public void addBlockEvent(int x, int y, int z, Block block, int metadata, int p_14745) {}


	@Override
	public TileEntity getTileEntity(int x, int y, int z)
	{
		return entityMinecartTileEntityBase.getTileEntity();
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
