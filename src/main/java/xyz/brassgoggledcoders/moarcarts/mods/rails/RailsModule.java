package xyz.brassgoggledcoders.moarcarts.mods.rails;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.lib.common.modules.Module;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.BlockRegistry;
import xyz.brassgoggledcoders.moarcarts.mods.rails.blocks.BlockRailBoarding;

public class RailsModule extends Module
{
	public static BlockRailBoarding BOARDING_RAIL;

	@Override
	public String getName()
	{
		return "Rails";
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		BOARDING_RAIL = new BlockRailBoarding();
		BlockRegistry.registerBlock(BOARDING_RAIL);
	}
}
