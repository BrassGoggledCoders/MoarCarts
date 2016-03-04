package xyz.brassgoggledcoders.moarcarts.mods.neotech;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.lib.common.modcompat.ModCompat;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.EntityRegistry;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.ItemRegistry;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.entity.EntityMinecartFlushableChest;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.items.ItemMinecartFlushableChest;

public class NeotechCompat extends ModCompat
{
	public static ItemMinecartFlushableChest ITEM_MINECART_FLUSHABLECHEST;

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
	}
}
