package moarcarts.mods.railcraft;

import cpw.mods.fml.relauncher.ReflectionHelper;
import mods.railcraft.common.core.RailcraftConfig;

/**
 * Created by Skylar on 9/7/2015.
 */
public class RailcraftConfigValues
{
	public static int getMinecraftItemStackSize()
	{
		Configur
		return Integer.getInteger(ReflectionHelper.findField(RailcraftConfig.class, "minecartStackSize").toString());
	}
}
