package xyz.brassgoggledcoders.moarcarts.fakeworld;

import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartBase;
import net.minecraft.util.math.BlockPos;

/**
 * @author SkySom
 */
public class CartBlockPos extends BlockPos
{
	EntityMinecartBase entityMinecartBase;

	public CartBlockPos(EntityMinecartBase entityMinecartBase)
	{
		super(0, 0, 0);
		this.entityMinecartBase = entityMinecartBase;
	}

	@Override
	public int getX()
	{
		return (int)Math.floor(entityMinecartBase.posX);
	}

	@Override
	public int getY()
	{
		return (int)Math.floor(entityMinecartBase.posY);
	}

	@Override
	public int getZ()
	{
		return (int)Math.floor(entityMinecartBase.posZ);
	}
}
