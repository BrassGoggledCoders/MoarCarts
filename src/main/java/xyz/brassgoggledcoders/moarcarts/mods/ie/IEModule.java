package xyz.brassgoggledcoders.moarcarts.mods.ie;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xyz.brassgoggledcoders.boilerplate.lib.common.modules.Module;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.EntityRegistry;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.ItemRegistry;
import xyz.brassgoggledcoders.moarcarts.mods.ie.entities.*;
import xyz.brassgoggledcoders.moarcarts.mods.ie.events.ClientEvents;
import xyz.brassgoggledcoders.moarcarts.mods.ie.items.*;
import xyz.brassgoggledcoders.moarcarts.mods.rf.RFModule;

public class IEModule extends Module
{
	public static ItemMinecartWoodenBarrel ITEM_MINECART_WOODENBARREL;
	public static ItemMinecartMetalBarrel ITEM_MINECART_METALBARREL;
	public static ItemMinecartCrate ITEM_MINECART_WOODENCRATE;
	public static ItemMinecartCapacitor ITEM_MINECART_CAPACITOR;
	public static ItemMinecartGunPowderBarrel ITEM_MINECART_GUNPOWDER_BARREL;

	public static Block WOODEN_DEVICE;
	public static Block METAL_DEVICE;
	public static Block METAL_DEVICE2;

	public boolean isRFModuleEnabled = false;

	@Override
	public String getName()
	{
		return "Immersive Engineering";
	}

	@Override
	public boolean areRequirementsMet()
	{
		return Loader.isModLoaded("ImmersiveEngineering");
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		ITEM_MINECART_WOODENBARREL = new ItemMinecartWoodenBarrel();
		ItemRegistry.registerItem(ITEM_MINECART_WOODENBARREL);
		EntityRegistry.registerEntity(EntityMinecartWoodenBarrel.class, "entityminecartwoodenbarrel");

		ITEM_MINECART_METALBARREL = new ItemMinecartMetalBarrel();
		ItemRegistry.registerItem(ITEM_MINECART_METALBARREL);
		EntityRegistry.registerEntity(EntityMinecartMetalBarrel.class, "entityminecartmetalbarrel");

		ITEM_MINECART_WOODENCRATE = new ItemMinecartCrate();
		ItemRegistry.registerItem(ITEM_MINECART_WOODENCRATE);
		EntityRegistry.registerEntity(EntityMinecartWoodenCrate.class, "entityminecartwoodencrate");
		EntityRegistry.registerEntity(EntityMinecartReinforcedCrate.class, "entityminecartreinforcedcrate");

		ITEM_MINECART_GUNPOWDER_BARREL = new ItemMinecartGunPowderBarrel();
		ItemRegistry.registerItem(ITEM_MINECART_GUNPOWDER_BARREL);
		EntityRegistry.registerEntity(EntityMinecartGunPowderBarrel.class, "entityminecartgunpowderbarrel");

		isRFModuleEnabled = isOtherModuleActive("RF");

		if(isRFModuleEnabled)
		{
			ITEM_MINECART_CAPACITOR = new ItemMinecartCapacitor();
			ItemRegistry.registerItem(ITEM_MINECART_CAPACITOR);
			EntityRegistry.registerEntity(EntityMinecartCapacitorLV.class, "entityminecartcapacitorlv");
			EntityRegistry.registerEntity(EntityMinecartCapacitorMV.class, "entityminecartcapacitormv");
			EntityRegistry.registerEntity(EntityMinecartCapacitorHV.class, "entityminecartcapacitorhv");
			EntityRegistry.registerEntity(EntityMinecartCapacitorCreative.class, "entityminecartcapacitorcreative");
		}

		Shaders.initShaders();
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		WOODEN_DEVICE = GameRegistry.findBlock("ImmersiveEngineering", "woodenDevice0");
		METAL_DEVICE = GameRegistry.findBlock("ImmersiveEngineering", "metalDevice0");
		METAL_DEVICE2 = GameRegistry.findBlock("ImmersiveEngineering", "metalDevice1");

		if(isRFModuleEnabled)
		{
			RFModule.registerRFLoaderRecipe(new ItemStack(METAL_DEVICE, 1, 1));
			RFModule.registerRFLoaderRecipe(new ItemStack(METAL_DEVICE, 1, 4));
			RFModule.registerRFLoaderRecipe(new ItemStack(METAL_DEVICE, 1, 7));
			RFModule.registerRFLoaderRecipe(new ItemStack(METAL_DEVICE2, 1, 8));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void clientInit(FMLInitializationEvent event)
	{
		ClientEvents clientEvents = new ClientEvents();
		MinecraftForge.EVENT_BUS.register(clientEvents);
	}
}
