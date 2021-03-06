package xyz.brassgoggledcoders.moarcarts.fakeworld;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartBase;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartTEBase;

import java.util.List;

/**
 * @author SkySom
 */
public class FakeWorld extends World
{
	private EntityMinecartTEBase entityMinecartTEBase;
	private EntityMinecartBase entityMinecartBase;

	public FakeWorld(EntityMinecartBase entityMinecartBase)
	{
		this(entityMinecartBase.worldObj, entityMinecartBase);
	}

	public FakeWorld(World world, EntityMinecartBase entityMinecartBase)
	{
		super(world.getSaveHandler(), world.getWorldInfo(), world.provider, world.theProfiler, world.isRemote);
		this.setEntityMinecartBase(entityMinecartBase);
		if(entityMinecartBase instanceof EntityMinecartTEBase)
		{
			this.setEntityMinecartTEBase((EntityMinecartTEBase)entityMinecartBase);
		}
	}

	//MFR grabs TE's just a bit different than most
	@Override
	protected IChunkProvider createChunkProvider()
	{
		return chunkProvider;
	}

	//Pretty sure this for IE's blocks originally though other use it.
	@Override
	public void markBlockForUpdate(BlockPos blockPos)
	{
		if(this.getEntityMinecartTEBase() != null)
		{
			this.getEntityMinecartTEBase().setDirty(true);
		}
	}

	//Enderchest use this for open and close
	@Override
	public void addBlockEvent(BlockPos blockPos, Block block, int metadata, int p_14745) {}

	@Override
	public boolean isSideSolid(BlockPos blockPos, EnumFacing blockSide)
	{
		return false;
	}

	@Override
	public TileEntity getTileEntity(BlockPos blockPos)
	{
		if(blockPos.equals(this.getEntityMinecartBase().ORIGIN_POS)) {
			if(this.getEntityMinecartTEBase() != null)
			{
				return this.getEntityMinecartTEBase().getTileEntity();
			}
		}
		return null;
	}

	//Really hope this doesn't break anything. Welp.
	@Override
	protected int getRenderDistanceChunks()
	{
		return 0;
	}

	@Override
	public Entity getEntityByID(int id)
	{
		return this.getEntityMinecartBase().worldObj.getEntityByID(id);
	}

	//Most Blocks use this.
	@Override
	public IBlockState getBlockState(BlockPos blockPos)
	{
		if(blockPos.equals(this.getEntityMinecartBase().ORIGIN_POS)) {
			return this.getEntityMinecartBase().getDisplayTile();
		}
		return this.getEntityMinecartBase().getDefaultDisplayTile();
	}

	//Enderchest Particles
	@Override
	public void spawnParticle(EnumParticleTypes enumParticleType, double posX, double posY, double posZ, double velX,
			double velY, double velZ, int... what)
	{
		int intCartX = (int)Math.floor(this.getCartX());
		int intCartY = (int)Math.floor(this.getCartY());
		int intCartZ = (int)Math.floor(this.getCartZ());
		double changeInX = (this.getCartX() - (double)intCartX) / 2.0;
		double changeInY = (this.getCartY() - (double)intCartY) / 2.0;
		double changeInZ = (this.getCartZ() - (double)intCartZ) / 2.0;
		this.getCartWorld().spawnParticle(enumParticleType, posX - changeInX, posY - changeInY, posZ - changeInZ,
				velX, velY, velZ);
	}

	//Infinitato tries to get Entities and add potion effects
	@Override
	public <T extends Entity> List<T> getEntitiesWithinAABB(Class<? extends T > entityClass, AxisAlignedBB axisAlignedBB)
	{
		axisAlignedBB.getAverageEdgeLength();

		return this.getCartWorld().getEntitiesWithinAABB(entityClass, axisAlignedBB);
	}

	//Infinitato creates explosions when it lands
	@Override
	public Explosion createExplosion(Entity entity, double posX, double posY, double posZ, float size, boolean damage)
	{
		return this.getCartWorld().createExplosion(entity, this.getCartX(), this.getCartY(), this.getCartZ(), size, damage);
	}

	//Shia Labouef tiny potato screams "Just do it"
	@Override
	public void playSoundEffect(double posX, double posY, double posZ, String sound, float noice, float soundTimes)
	{
		this.getCartWorld().playSoundAtEntity(this.getEntityMinecartBase(), sound, noice, soundTimes);
	}

	@Override
	public Chunk getChunkFromChunkCoords(int chunkX, int chunkZ)
	{
		return this.getCartWorld().getChunkFromChunkCoords(chunkX, chunkZ);
	}

	@Override
	protected boolean isChunkLoaded(int x, int z, boolean allowEmpty)
	{
		return true;
	}

	@Override
	public void markChunkDirty(BlockPos pos, TileEntity unusedTileEntity)
	{
		if(this.getEntityMinecartTEBase() != null)
		{
			this.getEntityMinecartTEBase().markDirty();
		}
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
