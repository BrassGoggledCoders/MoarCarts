package xyz.brassgoggledcoders.moarcarts.mods.hydraulicraft;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.brassgoggledcoders.boilerplate.lib.common.modules.Module;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.EntityRegistry;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.ItemRegistry;
import xyz.brassgoggledcoders.moarcarts.mods.extras.ExtrasModule;
import xyz.brassgoggledcoders.moarcarts.mods.hydraulicraft.entites.EntityMinecartFluidTank;
import xyz.brassgoggledcoders.moarcarts.mods.hydraulicraft.items.ItemMinecartFluidTank;

public class HydraulicraftModule extends Module
{
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
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		if(isOtherModuleActive("Extras"))
		{
			Block blockTank = GameRegistry.findBlock("HydCraft", "blockFluidTank");
			ExtrasModule.registerFluidLoaderRecipe(new ItemStack(blockTank));
		}
	}
}
