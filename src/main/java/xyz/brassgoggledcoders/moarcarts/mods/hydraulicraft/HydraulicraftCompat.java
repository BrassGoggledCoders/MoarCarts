package xyz.brassgoggledcoders.moarcarts.mods.hydraulicraft;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.lib.common.modcompat.ModCompat;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.BlockRegistry;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.EntityRegistry;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.ItemRegistry;
import xyz.brassgoggledcoders.moarcarts.mods.hydraulicraft.blocks.BlockHydFluidTank;
import xyz.brassgoggledcoders.moarcarts.mods.hydraulicraft.entites.EntityMinecartFluidTank;
import xyz.brassgoggledcoders.moarcarts.mods.hydraulicraft.items.ItemMinecartFluidTank;

public class HydraulicraftCompat extends ModCompat
{
	public static BlockHydFluidTank BLOCK_HYD_FLUID_TANK;
	public static ItemMinecartFluidTank ITEM_MINECART_FLUIDTANK;

	@Override
	public String getName()
	{
		return "Hydraulicraft";
	}

	@Override
	public boolean areRequirementsMet()
	{
		return Loader.isModLoaded("HydCraft");
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		ITEM_MINECART_FLUIDTANK = new ItemMinecartFluidTank();
		ItemRegistry.registerItem(ITEM_MINECART_FLUIDTANK);
		EntityRegistry.registerEntity(EntityMinecartFluidTank.class);

		BLOCK_HYD_FLUID_TANK = new BlockHydFluidTank();
		BlockRegistry.registerBlock(BLOCK_HYD_FLUID_TANK, "fluid_tank");
	}
}
