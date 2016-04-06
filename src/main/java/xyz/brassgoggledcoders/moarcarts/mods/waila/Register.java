package xyz.brassgoggledcoders.moarcarts.mods.waila;

import mcp.mobius.waila.api.IWailaRegistrar;
import xyz.brassgoggledcoders.boilerplate.lib.common.tileentities.TileEntityFluidBase;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartEnergyHandlerTEBase;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartFluidTEBase;
import xyz.brassgoggledcoders.moarcarts.mods.waila.providers.BlockFluidHandler;
import xyz.brassgoggledcoders.moarcarts.mods.waila.providers.EntityMinecartFluidProvider;
import xyz.brassgoggledcoders.moarcarts.mods.waila.providers.EntityMinecartRFProvider;
import xyz.brassgoggledcoders.moarcarts.mods.waila.providers.EntityMinecartTEBaseProvider;

/**
 * @author SkySom
 */
public class Register
{
	public static void callback(IWailaRegistrar registrar) {
		registrar.registerNBTProvider(new EntityMinecartTEBaseProvider(), EntityMinecartTEBaseProvider.class);
		registrar.registerBodyProvider(new EntityMinecartFluidProvider(), EntityMinecartFluidTEBase.class);
		registrar.registerBodyProvider(new EntityMinecartRFProvider(), EntityMinecartEnergyHandlerTEBase.class);
		registrar.registerBodyProvider(new BlockFluidHandler(), TileEntityFluidBase.class);
	}
}
