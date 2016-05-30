package xyz.brassgoggledcoders.moarcarts.mods.rails;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.lib.common.modules.Module;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.BlockRegistry;
import xyz.brassgoggledcoders.moarcarts.mods.rails.blocks.BlockRailBoarding;
import xyz.brassgoggledcoders.moarcarts.mods.rails.blocks.BlockRailCrossing;
import xyz.brassgoggledcoders.moarcarts.mods.rails.blocks.BlockRailSwitch;

public class RailsModule extends Module
{
	public static BlockRailBoarding BOARDING_RAIL;
	public static BlockRailCrossing CROSSING_RAIL;
	public static BlockRailSwitch SWITCH_RAIL;

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

		CROSSING_RAIL = new BlockRailCrossing();
		BlockRegistry.registerBlock(CROSSING_RAIL);

		SWITCH_RAIL = new BlockRailSwitch();
		BlockRegistry.registerBlock(SWITCH_RAIL);
	}
}
