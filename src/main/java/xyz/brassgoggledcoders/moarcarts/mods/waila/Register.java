package xyz.brassgoggledcoders.moarcarts.mods.waila;

import mcp.mobius.waila.api.IWailaRegistrar;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.lib.common.tileentities.TileEntityFluidBase;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartEnergyHandlerTEBase;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartFluidTEBase;
import xyz.brassgoggledcoders.moarcarts.mods.rf.blocks.BlockRFLoader;
import xyz.brassgoggledcoders.moarcarts.mods.waila.providers.*;

/**
 * @author SkySom
 */
public class Register
{
	public static void callback(IWailaRegistrar registrar) {
		registrar.registerNBTProvider(new EntityMinecartTEBaseProvider(), EntityMinecartTEBaseProvider.class);
		registrar.registerBodyProvider(new EntityMinecartFluidProvider(), EntityMinecartFluidTEBase.class);

		if(BoilerplateLib.getModuleHandler().isModuleEnabled("RF"))
		{
			registrar.registerBodyProvider(new EntityMinecartRFProvider(), EntityMinecartEnergyHandlerTEBase.class);
			registrar.registerBodyProvider(new BlockProviderRF(), BlockRFLoader.class);
			registrar.registerBodyProvider(new BlockProviderSided(), BlockRFLoader.class);
		}

		if(BoilerplateLib.getModuleHandler().isModuleEnabled("Extras"))
		{
			registrar.registerBodyProvider(new BlockFluidHandlerProviders(), TileEntityFluidBase.class);
		}
	}
}
