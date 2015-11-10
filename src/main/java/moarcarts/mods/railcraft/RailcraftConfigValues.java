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
package moarcarts.mods.railcraft;

import moarcarts.MoarCarts;
import moarcarts.config.ConfigSettings;
import mods.railcraft.common.core.RailcraftConfig;

/**
 * @author Skysom
 */
public class RailcraftConfigValues
{
	public static void setConfigValues()
	{
		try
		{
			ConfigSettings.setMinecartsBreakOnDrop(RailcraftConfig.doCartsBreakOnDrop());
			ConfigSettings.setMinecartStackSize(RailcraftConfig.getMinecartStackSize());
		} catch(Exception exception)
		{
			MoarCarts.logger.warning("Couldn't grab Railcraft config settings. Warn the author, as this is not good");
		}
	}
}
