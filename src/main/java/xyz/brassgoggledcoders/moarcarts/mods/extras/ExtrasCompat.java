package xyz.brassgoggledcoders.moarcarts.mods.extras;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.lib.common.modcompat.ModCompat;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.BlockRegistry;
import xyz.brassgoggledcoders.moarcarts.mods.extras.block.BlockFluidHopper;

public class ExtrasCompat extends ModCompat
{
	public static BlockFluidHopper BLOCK_FLUID_HOPPER;

	@Override
	public String getName()
	{
		return "Extras";
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		BLOCK_FLUID_HOPPER = new BlockFluidHopper();
		BlockRegistry.registerBlock(BLOCK_FLUID_HOPPER);
	}
}
