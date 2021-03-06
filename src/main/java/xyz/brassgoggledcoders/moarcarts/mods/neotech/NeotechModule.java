package xyz.brassgoggledcoders.moarcarts.mods.neotech;

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
import xyz.brassgoggledcoders.moarcarts.mods.neotech.entities.*;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.items.ItemMinecartDimensionalChest;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.items.ItemMinecartFlushableChest;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.items.ItemMinecartRFStorage;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.items.ItemMinecartTank;
import xyz.brassgoggledcoders.moarcarts.mods.rf.RFModule;

import java.util.Arrays;
import java.util.List;

public class NeotechModule extends Module
{
	public static ItemMinecartFlushableChest ITEM_MINECART_FLUSHABLECHEST;
	public static ItemMinecartDimensionalChest ITEM_MINECART_DIMENSIONALCHEST;
	public static ItemMinecartTank ITEM_MINECART_IRONTANK;
	public static ItemMinecartRFStorage ITEM_MINECART_RFSTORAGE;

	@Override
	public String getName()
	{
		return "Neotech";
	}

	@Override
	public boolean areRequirementsMet()
	{
		return Loader.isModLoaded("neotech");
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		ITEM_MINECART_FLUSHABLECHEST = new ItemMinecartFlushableChest();
		ItemRegistry.registerItem(ITEM_MINECART_FLUSHABLECHEST);
		EntityRegistry.registerEntity(EntityMinecartFlushableChest.class);

		ITEM_MINECART_IRONTANK = new ItemMinecartTank();
		ItemRegistry.registerItem(ITEM_MINECART_IRONTANK);
		EntityRegistry.registerEntity(EntityMinecartIronTank.class);
		EntityRegistry.registerEntity(EntityMinecartGoldTank.class);
		EntityRegistry.registerEntity(EntityMinecartDiamondTank.class);
		EntityRegistry.registerEntity(EntityMinecartCreativeTank.class);
		EntityRegistry.registerEntity(EntityMinecartVoidTank.class);

		ITEM_MINECART_RFSTORAGE = new ItemMinecartRFStorage();
		ItemRegistry.registerItem(ITEM_MINECART_RFSTORAGE);
		EntityRegistry.registerEntity(EntityMinecartBasicRFStorage.class);
		EntityRegistry.registerEntity(EntityMinecartAdvancedRFStorage.class);
		EntityRegistry.registerEntity(EntityMinecartEliteRFStorage.class);
		EntityRegistry.registerEntity(EntityMinecartCreativeRFStorage.class);
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		if(isOtherModuleActive("Extras"))
		{
			List<String> tankNames = Arrays.asList("ironTank", "goldTank", "diamondTank", "creativeTank", "voidTank");
			for(String name : tankNames)
			{
				Block tank = GameRegistry.findBlock("neotech", name);
				if(tank != null)
				{
					ExtrasModule.registerFluidLoaderRecipe(new ItemStack(tank, 1, 0));
				}
			}
		}

		if(isOtherModuleActive("RF"))
		{
			List<String> rfNames = Arrays.asList("basicRFStorage", "advancedRFStorage", "eliteRFStorage", "creativeRFStorage");
			for(String name: rfNames)
			{
				Block rf = GameRegistry.findBlock("neotech", name);
				if(rf != null)
				{
					RFModule.registerRFLoaderRecipe(new ItemStack(rf, 1, 0));
				}
			}
		}
	}
}
