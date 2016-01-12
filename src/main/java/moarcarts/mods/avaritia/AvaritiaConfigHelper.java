package moarcarts.mods.avaritia;

import fox.spiteful.avaritia.Config;

/**
 * @author SkySom
 */
public class AvaritiaConfigHelper
{
	public static boolean isInfinitatoActive()
	{
		return !Config.craftingOnly;
	}
}
