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
package moarcarts.utils;

import moarcarts.ModInfo;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author SkySom
 */
public class LoggerMoarCarts
{
	public static void log(Level level, String message)
	{
		getLogger().log(level, message);
	}

	public static void warning(String message)
	{
		log(Level.WARN, message);
	}

	public static void info(String message)
	{
		log(Level.INFO, message);
	}

	public static void fatal(String message)
	{
		log(Level.FATAL, message);
	}

	public static void error(String message)
	{
		log(Level.ERROR, message);
	}

	protected static Logger getLogger()
	{
		return LogManager.getLogger(ModInfo.MODID);
	}
}
