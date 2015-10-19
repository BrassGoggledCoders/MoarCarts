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

import boilerplate.common.modcompat.ModCompat;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * @author SkySom
 */
public class RailcraftCompat extends ModCompat
{
	@Override
	public String getName()
	{
		return "Railcraft";
	}

	@Override
	public boolean areRequirementsMet()
	{
		return Loader.isModLoaded("Railcraft");
	}

	@Override
	public String dependencies()
	{
		return "after:Railcraft;";
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		RailcraftConfigValues.setConfigValues();
	}
}
