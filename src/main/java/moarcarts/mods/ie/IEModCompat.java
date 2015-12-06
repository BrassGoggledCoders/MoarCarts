package moarcarts.mods.ie;

import boilerplate.common.modcompat.ModCompat;
import boilerplate.common.utils.helpers.RegistryHelper;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import moarcarts.MoarCarts;
import moarcarts.mods.ie.entities.EntityMinecartLVCapacitor;
import moarcarts.mods.ie.entities.EntityMinecartMetalBarrel;
import moarcarts.mods.ie.entities.EntityMinecartWoodenBarrel;
import moarcarts.mods.ie.entities.EntityMinecartWoodenCrate;
import moarcarts.mods.ie.items.ItemMinecartCapacitor;
import moarcarts.mods.ie.items.ItemMinecartMetalBarrel;
import moarcarts.mods.ie.items.ItemMinecartWoodenBarrel;
import moarcarts.mods.ie.items.ItemMinecartWoodenCrate;
import moarcarts.recipes.NBTCartRecipe;
import net.minecraft.block.Block;

/**
 * @author SkySom
 */
public class IEModCompat extends ModCompat
{
	public static ItemMinecartWoodenBarrel ITEM_MINECART_WOODENBARREL;
	public static ItemMinecartMetalBarrel ITEM_MINECART_METALBARREL;
	public static ItemMinecartWoodenCrate ITEM_MINECART_WOODENCRATE;
	public static ItemMinecartCapacitor ITEM_MINECART_CAPACITOR;

	public static Block woodenDevice = GameRegistry.findBlock("ImmersiveEngineering", "blockWoodenDevice");
	public static Block metalDevice = GameRegistry.findBlock("ImmersiveEngineering", "blockMetalDevice");
	public static Block metalDevice2 = GameRegistry.findBlock("ImmersiveEngineering", "blockMetalDevice2");

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

		ITEM_MINECART_CAPACITOR = new ItemMinecartCapacitor();
		RegistryHelper.registerItem(ITEM_MINECART_CAPACITOR, MoarCarts.MODID);
		RegistryHelper.registerEntity(MoarCarts.instance, EntityMinecartLVCapacitor.class, "entityminecartlvcapacitor");
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_WOODENBARREL, woodenDevice, 6));
		GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_METALBARREL, metalDevice2, 7));
		GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_WOODENCRATE, woodenDevice, 4));
		GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_CAPACITOR, metalDevice, 1));
	}
}
