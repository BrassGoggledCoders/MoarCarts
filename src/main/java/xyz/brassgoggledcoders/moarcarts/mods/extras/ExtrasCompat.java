package xyz.brassgoggledcoders.moarcarts.mods.extras;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import xyz.brassgoggledcoders.boilerplate.lib.common.modules.Module;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.BlockRegistry;
import xyz.brassgoggledcoders.moarcarts.mods.extras.block.BlockFluidHopper;

public class ExtrasCompat extends Module
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

	public static void registerFluidLoaderRecipe(ItemStack itemStack)
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(BLOCK_FLUID_HOPPER, "I I", "ITI", " I ", 'I', "ingotIron",
				'T', itemStack));
	}
}
