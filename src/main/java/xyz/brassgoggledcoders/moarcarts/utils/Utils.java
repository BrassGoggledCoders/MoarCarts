package xyz.brassgoggledcoders.moarcarts.utils;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class Utils
{
	//Pulled from RailCraft-API Thanks CovertJaguar!
	public static EntityMinecart getMinecartAt(World world, BlockPos pos, float sensitivity)
	{
		List<EntityMinecart> minecarts = getMinecartsAt(world, pos, sensitivity);
		if(minecarts.size() > 0)
		{
			return minecarts.get(0);
		}
		return null;
	}

	//Pulled from RailCraft-API Thanks CovertJaguar!
	public static List<EntityMinecart> getMinecartsAt(World world, BlockPos pos, float sensitivity)
	{
		sensitivity = Math.min(sensitivity, 0.49f);
		List entities = world.getEntitiesWithinAABB(EntityMinecart.class, AxisAlignedBB
				.fromBounds(pos.getX() + sensitivity, pos.getY() + sensitivity, pos.getZ() + sensitivity,
						pos.getX() + 1 - sensitivity, pos.getY() + 1 - sensitivity, pos.getZ() + 1 - sensitivity));
		List<EntityMinecart> carts = new ArrayList<>();
		for (Object o : entities)
		{
			EntityMinecart cart = (EntityMinecart) o;
			if (!cart.isDead)
			{
				carts.add((EntityMinecart) o);
			}
		}
		return carts;
	}

	public static int getIntFromBooleanArray(boolean[] array)
	{
		int result = 0;
		for (boolean b : array)
		{
			result = (result << 1) | (b ? 1 : 0);
		}
		return result;
	}

	public static boolean[] getBooleanArrayFromInt(int input, int arraySize)
	{
		boolean[] bits = new boolean[arraySize];
		for (int i = arraySize - 1; i >= 0; i--) {
			bits[i] = (input & (1 << i)) != 0;
		}

		return bits;
	}
}
