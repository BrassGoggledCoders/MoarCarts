package xyz.brassgoggledcoders.moarcarts.fakeworld;

import xyz.brassgoggledcoders.moarcarts.MoarCarts;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartTEBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.chunk.Chunk;

/**
 * @author SkySom
 */
public class FakeChunk extends Chunk
{
	FakeWorld fakeWorld;
	EntityMinecartTEBase minecartTEBase;

	public FakeChunk(FakeWorld world)
	{
		super(world, 0, 0);
		this.fakeWorld = world;
		this.minecartTEBase = world.getEntityMinecartTEBase();
	}

	public TileEntity getTileEntityUnsafe(int x, int y, int z)
	{
		MoarCarts.logger.devInfo(this.minecartTEBase.getTileEntity().toString());
		return this.minecartTEBase.getTileEntity();
	}
}
