/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the MoarCarts Mod for Minecraft.
 *
 * MoarCarts is open-source and is distributed under the MIT License.
 *
 * MoarCarts is based on the original ExtraCarts Mod created by ScottDTA and SkySom.
 * ExtraCarts (c) ScottDTA 2014
 * (http://forum.feed-the-beast.com/threads/1-7-10-b0-7-0-extra-carts.47925/)
 *
 */
package xyz.brassgoggledcoders.moarcarts.config;

import net.minecraftforge.common.config.Configuration;

/**
 * @author SkySom
 */
public class ConfigSettings
{
	private static boolean minecartsBreakOnDrop;
	private static int minecartStackSize;

	public static Configuration init(Configuration configuration)
	{
		configuration.setCategoryComment("Tweaks", "Most these values will be overwritten by Railcraft values, if it is installed");
		minecartStackSize = configuration.get("Tweaks", "maxStackSize", 3,
				"change the value to your desired minecart stack size, vanilla=1, default=3, max=64").getInt(3);
		minecartsBreakOnDrop = configuration.get("Tweaks", "breakOnDrop", false,
				"change to '{t}=true' to restore vanilla behavior").getBoolean(false);
		return configuration;
	}

	public static boolean doMinecartsBreakOnDrop()
	{
		return minecartsBreakOnDrop;
	}

	public static void setMinecartsBreakOnDrop(boolean minecartsBreakOnDrop)
	{
		ConfigSettings.minecartsBreakOnDrop = minecartsBreakOnDrop;
	}

	public static int getMinecartStackSize()
	{
		return minecartStackSize;
	}

	public static void setMinecartStackSize(int minecartStackSize)
	{
		ConfigSettings.minecartStackSize = minecartStackSize;
	}
}
