package moarcarts.utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.world.World;

/**
 * Created by Skylar on 9/7/2015.
 */
public class BlockUtils
{
	public boolean isRailBlock(Block block, World world, int posX, int posY, int posZ)
	{
		return BlockRailBase.func_150051_a(world.getBlock(posX, posY, posZ));
	}
}
