package xyz.brassgoggledcoders.moarcarts.utils;

import com.google.common.base.Predicate;
import net.minecraft.block.BlockRailBase.EnumRailDirection;

public class Predicates
{
	public static Predicate<EnumRailDirection> FLAT_STRAIGHT = new Predicate<EnumRailDirection>()
	{
		public boolean apply(EnumRailDirection direction)
		{
			return direction == EnumRailDirection.NORTH_SOUTH || direction == EnumRailDirection.EAST_WEST;
		}
	};
}
