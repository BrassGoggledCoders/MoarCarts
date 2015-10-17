package moarcarts.mods.ironchests;

import boilerplate.common.modcompat.ModCompat;
import cpw.mods.fml.common.Loader;

/**
 * @author SkySom
 */
public class IronChestsCompat extends ModCompat
{
	@Override
	public String getName()
	{
		return "Iron Chests";
	}

	@Override
	public boolean areRequirementsMet()
	{
		return Loader.isModLoaded("ironchests");
	}
}
