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

import cpw.mods.fml.common.registry.GameRegistry;
import moarcarts.MoarCarts;
import net.minecraft.item.Item;

/**
 * @author SkySom
 */
public class RegistryUtils
{
	public static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, MoarCarts.MODID + "_" + item.getUnlocalizedName());
	}
}
