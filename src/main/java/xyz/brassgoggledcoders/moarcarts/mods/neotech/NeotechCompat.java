package xyz.brassgoggledcoders.moarcarts.mods.neotech;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.lib.common.modcompat.ModCompat;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.EntityRegistry;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.ItemRegistry;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.entities.*;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.items.ItemMinecartDimensionalChest;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.items.ItemMinecartTank;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.items.ItemMinecartFlushableChest;

public class NeotechCompat extends ModCompat
{
	public static ItemMinecartFlushableChest ITEM_MINECART_FLUSHABLECHEST;
	public static ItemMinecartDimensionalChest ITEM_MINECART_DIMENSIONALCHEST;
	public static ItemMinecartTank ITEM_MINECART_IRONTANK;

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

		//ITEM_MINECART_DIMENSIONALCHEST = new ItemMinecartDimensionalChest();
		//ItemRegistry.registerItem(ITEM_MINECART_DIMENSIONALCHEST);
		//EntityRegistry.registerEntity(EntityMinecartDimensionalChest.class);

		ITEM_MINECART_IRONTANK = new ItemMinecartTank();
		ItemRegistry.registerItem(ITEM_MINECART_IRONTANK);
		EntityRegistry.registerEntity(EntityMinecartIronTank.class);
		EntityRegistry.registerEntity(EntityMinecartGoldTank.class);
		EntityRegistry.registerEntity(EntityMinecartDiamondTank.class);
		EntityRegistry.registerEntity(EntityMinecartCreativeTank.class);
		EntityRegistry.registerEntity(EntityMinecartVoidTank.class);
	}
}
