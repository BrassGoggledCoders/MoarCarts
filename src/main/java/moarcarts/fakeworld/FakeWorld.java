package moarcarts.fakeworld;

import moarcarts.entities.EntityMinecartBase;
import moarcarts.entities.EntityMinecartTEBase;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author SkySom
 */
public class FakeWorld extends World
{
	private EntityMinecartTEBase entityMinecartTEBase;
	private EntityMinecartBase entityMinecartBase;
	private FakeChunk fakeChunk;

	public FakeWorld(EntityMinecartBase entityMinecartBase)
	{
		this(entityMinecartBase.worldObj, entityMinecartBase);
	}

	public FakeWorld(World world, EntityMinecartBase entityMinecartBase)
	{
		super(world.getSaveHandler(), world.getWorldInfo().getWorldName(), world.provider,
				new WorldSettings(world.getWorldInfo()), world.theProfiler);
		this.setEntityMinecartBase(entityMinecartBase);
		if(entityMinecartBase instanceof EntityMinecartTEBase)
		{
			this.setEntityMinecartTEBase((EntityMinecartTEBase)entityMinecartBase);
		}
	}

	@Override
	protected IChunkProvider createChunkProvider()
	{
		return chunkProvider;
	}

	@Override
	public void markTileEntityChunkModified(int posX, int posY, int posZ, TileEntity tileEntity)
	{
		if(this.getEntityMinecartTEBase() != null)
		{
			this.getEntityMinecartTEBase().markDirty();
		}
	}

	@Override
	public Chunk getChunkFromBlockCoords(int x, int z)
	{
		if(this.fakeChunk == null)
		{
			this.fakeChunk = new FakeChunk(this);
		}
		return this.fakeChunk;
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
		if(this.getEntityMinecartBase() == null)
		{
			this.setEntityMinecartBase(this.getEntityMinecartTEBase());
		}
		return this.getEntityMinecartBase().getCartBlock();
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
		if(this.getEntityMinecartTEBase() != null)
		{
			return this.getEntityMinecartTEBase().getTileEntity();
		}
		return null;
	}

	@Override
	public Entity getEntityByID(int id)
	{
		return this.getEntityMinecartBase().worldObj.getEntityByID(id);
	}

	@Override
	public int getBlockMetadata(int posX, int posY, int posZ)
	{
		if(this.getEntityMinecartBase() == null)
		{
			this.setEntityMinecartBase(this.getEntityMinecartTEBase());
		}
		return this.getEntityMinecartBase().getMetadata();
	}

	@Override
	public void spawnParticle(String name, double posX, double posY, double posZ, double velX, double velY, double velZ)
	{
		int intCartX = (int)Math.floor(this.getCartX());
		int intCartY = (int)Math.floor(this.getCartY());
		int intCartZ = (int)Math.floor(this.getCartZ());
		double changeInX = (this.getCartX() - (double)intCartX) / 2.0;
		double changeInY = (this.getCartY() - (double)intCartY) / 2.0;
		double changeInZ = (this.getCartZ() - (double)intCartZ) / 2.0;
		this.getCartWorld().spawnParticle(name, posX - changeInX, posY - changeInY, posZ - changeInZ, velX, velY, velZ);
	}

	public EntityMinecartTEBase getEntityMinecartTEBase()
	{
		if(entityMinecartTEBase == null && entityMinecartBase != null)
		{
			this.setEntityMinecartTEBase((EntityMinecartTEBase)entityMinecartBase);
		}
		return entityMinecartTEBase;
	}

	public void setEntityMinecartTEBase(EntityMinecartTEBase entityMinecartTEBase)
	{
		this.entityMinecartTEBase = entityMinecartTEBase;
	}

	public EntityMinecartBase getEntityMinecartBase()
	{
		if(entityMinecartBase == null && entityMinecartTEBase != null)
		{
				this.setEntityMinecartBase(entityMinecartTEBase);
		}
		return entityMinecartBase;
	}

	public void setEntityMinecartBase(EntityMinecartBase entityMinecartBase)
	{
		this.entityMinecartBase = entityMinecartBase;
	}

	public double getCartX()
	{
		return this.getEntityMinecartBase().posX;
	}

	public double getCartY()
	{
		return this.getEntityMinecartBase().posY;
	}

	public double getCartZ()
	{
		return this.getEntityMinecartBase().posZ;
	}

	public World getCartWorld()
	{
		return this.getEntityMinecartBase().worldObj;
	}
}
