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

import cpw.mods.fml.common.registry.EntityRegistry;
import moarcarts.MoarCarts;

/**
 * @author SkySom
 */
public class EntityUtils
{
	private static int entityID = 0;

	public static void registerEntity(Class entityClass, String name) {
		int entityID = getNextAvailableEntityID();
		EntityRegistry.registerModEntity(entityClass, name, entityID, MoarCarts.instance, 64, 1, true);
	}

	private static int getNextAvailableEntityID() {
		return entityID++;
	}
}
