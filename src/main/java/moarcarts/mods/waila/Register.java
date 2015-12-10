package moarcarts.mods.waila;

import mcp.mobius.waila.api.IWailaRegistrar;
import moarcarts.entities.EntityMinecartEnergyHandlerTEBase;
import moarcarts.mods.waila.providers.EntityMinecartRFProvider;
import moarcarts.mods.waila.providers.EntityMinecartTEBaseProvider;

/**
 * @author SkySom
 */
public class Register
{
	public static void callback(IWailaRegistrar registrar) {
		registrar.registerBodyProvider(new EntityMinecartRFProvider(), EntityMinecartEnergyHandlerTEBase.class);
		registrar.registerNBTProvider(new EntityMinecartTEBaseProvider(), EntityMinecartTEBaseProvider.class);
	}
}
