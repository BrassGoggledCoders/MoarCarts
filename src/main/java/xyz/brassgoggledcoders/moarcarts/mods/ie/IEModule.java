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
import xyz.brassgoggledcoders.moarcarts.mods.ie.items.ItemMinecartCapacitor;
import xyz.brassgoggledcoders.moarcarts.mods.ie.items.ItemMinecartMetalBarrel;
import xyz.brassgoggledcoders.moarcarts.mods.ie.items.ItemMinecartWoodenBarrel;
import xyz.brassgoggledcoders.moarcarts.mods.ie.items.ItemMinecartWoodenCrate;
import xyz.brassgoggledcoders.moarcarts.mods.rf.RFModule;
import xyz.brassgoggledcoders.moarcarts.recipes.NBTCartRecipe;

public class IEModule extends Module
{
	public static ItemMinecartWoodenBarrel ITEM_MINECART_WOODENBARREL;
	public static ItemMinecartMetalBarrel ITEM_MINECART_METALBARREL;
	public static ItemMinecartWoodenCrate ITEM_MINECART_WOODENCRATE;
	public static ItemMinecartCapacitor ITEM_MINECART_CAPACITOR;

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

		ITEM_MINECART_WOODENCRATE = new ItemMinecartWoodenCrate();
		ItemRegistry.registerItem(ITEM_MINECART_WOODENCRATE);
		EntityRegistry.registerEntity(EntityMinecartWoodenCrate.class, "entityminecartwoodencrate");

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

		GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_WOODENBARREL, WOODEN_DEVICE, 6));
		GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_METALBARREL, METAL_DEVICE2, 7));
		GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_WOODENCRATE, WOODEN_DEVICE, 4));
		if(isRFModuleEnabled)
		{
			GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_CAPACITOR, 0, METAL_DEVICE, 1));
			GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_CAPACITOR, 1, METAL_DEVICE, 4));
			GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_CAPACITOR, 2, METAL_DEVICE, 7));
			GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_CAPACITOR, 3, METAL_DEVICE2, 8));
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
