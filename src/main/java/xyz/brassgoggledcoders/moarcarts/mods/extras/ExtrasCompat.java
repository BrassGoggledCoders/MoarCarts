package xyz.brassgoggledcoders.moarcarts.mods.extras;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.lib.common.modcompat.ModCompat;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.BlockRegistry;
import xyz.brassgoggledcoders.moarcarts.mods.extras.block.BlockFluidHopper;

public class ExtrasCompat extends ModCompat
{
	public static BlockFluidHopper blockLiquidHopper;

	@Override
	public String getName()
	{
		return "Extras";
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		blockLiquidHopper = new BlockFluidHopper();
		BlockRegistry.registerBlock(blockLiquidHopper);
	}
}
