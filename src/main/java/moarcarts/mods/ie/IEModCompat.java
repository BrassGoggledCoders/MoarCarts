package moarcarts.mods.ie;

import blusunrize.immersiveengineering.common.IEContent;
import boilerplate.common.modcompat.ModCompat;
import boilerplate.common.utils.helpers.RegistryHelper;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import moarcarts.MoarCarts;
import moarcarts.mods.ie.entities.EntityMinecartMetalBarrel;
import moarcarts.mods.ie.entities.EntityMinecartWoodenBarrel;
import moarcarts.mods.ie.entities.EntityMinecartWoodenCrate;
import moarcarts.mods.ie.items.ItemMinecartMetalBarrel;
import moarcarts.mods.ie.items.ItemMinecartWoodenBarrel;
import moarcarts.mods.ie.items.ItemMinecartWoodenCrate;
import moarcarts.recipes.NBTCartRecipe;

/**
 * @author SkySom
 */
public class IEModCompat extends ModCompat
{
	public static ItemMinecartWoodenBarrel ITEM_MINECART_WOODENBARREL;
	public static ItemMinecartMetalBarrel ITEM_MINECART_METALBARREL;
	public static ItemMinecartWoodenCrate ITEM_MINECART_WOODENCRATE;

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
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_WOODENBARREL, IEContent.blockWoodenDevice, 6));
		GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_METALBARREL, IEContent.blockMetalDevice2, 7));
		GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_WOODENCRATE, IEContent.blockWoodenDevice, 4));
	}
}
