package moarcarts.mods.ie;

import boilerplate.common.modcompat.ModCompat;
import boilerplate.common.utils.helpers.RegistryHelper;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import moarcarts.MoarCarts;
import moarcarts.mods.ie.entities.*;
import moarcarts.mods.ie.events.ClientEvents;
import moarcarts.mods.ie.items.ItemMinecartCapacitor;
import moarcarts.mods.ie.items.ItemMinecartMetalBarrel;
import moarcarts.mods.ie.items.ItemMinecartWoodenBarrel;
import moarcarts.mods.ie.items.ItemMinecartWoodenCrate;
import moarcarts.mods.rf.RFCompat;
import moarcarts.recipes.NBTCartRecipe;
import moarcarts.renderers.RenderItemMinecartBase;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author SkySom
 */
public class IEModCompat extends ModCompat
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
		RegistryHelper.registerItem(ITEM_MINECART_WOODENBARREL, MoarCarts.MODID);
		RegistryHelper.registerEntity(MoarCarts.instance, EntityMinecartWoodenBarrel.class, "entityminecartwoodenbarrel");

		ITEM_MINECART_METALBARREL = new ItemMinecartMetalBarrel();
		RegistryHelper.registerItem(ITEM_MINECART_METALBARREL, MoarCarts.MODID);
		RegistryHelper.registerEntity(MoarCarts.instance, EntityMinecartMetalBarrel.class, "entityminecartmetalbarrel");

		ITEM_MINECART_WOODENCRATE = new ItemMinecartWoodenCrate();
		RegistryHelper.registerItem(ITEM_MINECART_WOODENCRATE, MoarCarts.MODID);
		RegistryHelper.registerEntity(MoarCarts.instance, EntityMinecartWoodenCrate.class, "entityminecartwoodencrate");

		ModCompat rfCompat = MoarCarts.compatibilityHandler.getModCompatEnabled().get("RF");
		isRFModuleEnabled = rfCompat.getIsActive();

		if(isRFModuleEnabled)
		{
			ITEM_MINECART_CAPACITOR = new ItemMinecartCapacitor();
			RegistryHelper.registerItem(ITEM_MINECART_CAPACITOR, MoarCarts.MODID);
			RegistryHelper.registerEntity(MoarCarts.instance, EntityMinecartCapacitorLV.class, "entityminecartcapacitorlv");
			RegistryHelper.registerEntity(MoarCarts.instance, EntityMinecartCapacitorMV.class, "entityminecartcapacitormv");
			RegistryHelper.registerEntity(MoarCarts.instance, EntityMinecartCapacitorHV.class, "entityminecartcapacitorhv");
			RegistryHelper.registerEntity(MoarCarts.instance, EntityMinecartCapacitorCreative.class, "entityminecartcapacitorcreative");
		}

		Shaders.initShaders();
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		WOODEN_DEVICE = GameRegistry.findBlock("ImmersiveEngineering", "woodenDevice");
		METAL_DEVICE = GameRegistry.findBlock("ImmersiveEngineering", "metalDevice");
		METAL_DEVICE2 = GameRegistry.findBlock("ImmersiveEngineering", "metalDevice2");

		GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_WOODENBARREL, WOODEN_DEVICE, 6));
		GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_METALBARREL, METAL_DEVICE2, 7));
		GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_WOODENCRATE, WOODEN_DEVICE, 4));
		if(isRFModuleEnabled)
		{
			GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_CAPACITOR, 0, METAL_DEVICE, 1));
			GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_CAPACITOR, 1, METAL_DEVICE, 4));
			GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_CAPACITOR, 2, METAL_DEVICE, 7));
			GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_CAPACITOR, 3, METAL_DEVICE2, 8));
			RFCompat.registerRFLoaderRecipe(new ItemStack(METAL_DEVICE, 1, 1));
			RFCompat.registerRFLoaderRecipe(new ItemStack(METAL_DEVICE, 1, 4));
			RFCompat.registerRFLoaderRecipe(new ItemStack(METAL_DEVICE, 1, 7));
			RFCompat.registerRFLoaderRecipe(new ItemStack(METAL_DEVICE2, 1, 8));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void clientInit(FMLInitializationEvent event)
	{
		ClientEvents clientEvents = new ClientEvents();
		MinecraftForge.EVENT_BUS.register(clientEvents);

		MinecraftForgeClient.registerItemRenderer(ITEM_MINECART_WOODENBARREL, new RenderItemMinecartBase());
		MinecraftForgeClient.registerItemRenderer(ITEM_MINECART_METALBARREL, new RenderItemMinecartBase());
		MinecraftForgeClient.registerItemRenderer(ITEM_MINECART_WOODENCRATE, new RenderItemMinecartBase());

		if(isRFModuleEnabled)
		{
			MinecraftForgeClient.registerItemRenderer(ITEM_MINECART_CAPACITOR, new RenderItemMinecartBase());
		}

	}
}
