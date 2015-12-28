package moarcarts.mods.waila;

import mcp.mobius.waila.api.IWailaRegistrar;
import moarcarts.entities.EntityMinecartDeepStorageTEBase;
import moarcarts.entities.EntityMinecartEnergyHandlerTEBase;
import moarcarts.entities.EntityMinecartFluidInventoryTEBase;
import moarcarts.entities.EntityMinecartFluidTEBase;
import moarcarts.mods.waila.providers.EntityMinecartDSUProvider;
import moarcarts.mods.waila.providers.EntityMinecartFluidProvider;
import moarcarts.mods.waila.providers.EntityMinecartRFProvider;
import moarcarts.mods.waila.providers.EntityMinecartTEBaseProvider;

/**
 * @author SkySom
 */
public class Register
{
	public static void callback(IWailaRegistrar registrar) {
		registrar.registerNBTProvider(new EntityMinecartTEBaseProvider(), EntityMinecartTEBaseProvider.class);
		registrar.registerBodyProvider(new EntityMinecartFluidProvider(), EntityMinecartFluidInventoryTEBase.class);
		registrar.registerBodyProvider(new EntityMinecartFluidProvider(), EntityMinecartFluidTEBase.class);
		registrar.registerBodyProvider(new EntityMinecartDSUProvider(), EntityMinecartDeepStorageTEBase.class);
		registrar.registerBodyProvider(new EntityMinecartRFProvider(), EntityMinecartEnergyHandlerTEBase.class);
	}
}
