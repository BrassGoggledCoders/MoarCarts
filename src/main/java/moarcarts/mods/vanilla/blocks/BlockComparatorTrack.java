package moarcarts.mods.vanilla.blocks;

import moarcarts.api.IComparatorCart;
import net.minecraft.block.BlockRailDetector;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author SkySom
 */
public class BlockComparatorTrack extends BlockRailDetector
{
	public int getComparatorInputOverride(World world, int posX, int posY, int posZ, int side)
	{
		if ((world.getBlockMetadata(posX, posY, posZ) & 8) > 0)
		{
			float f = 0.125F;
			List list = world.getEntitiesWithinAABB(EntityMinecart.class, AxisAlignedBB
					.getBoundingBox((double)((float)posX + f), (double)posY, (double)((float)posZ + f),
							(double)((float)(posX + 1) - f), (double)((float)(posY + 1) - f),
							(double)((float)(posZ + 1) - f)));

			if(list.get(0) instanceof IComparatorCart)
			{
				((IComparatorCart) list.get(0)).getComparatorInputOverride();
			}
		}

		return 0;
	}
}
