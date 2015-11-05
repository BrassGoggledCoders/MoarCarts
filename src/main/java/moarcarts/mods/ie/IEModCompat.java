package moarcarts.mods.ie;

import blusunrize.immersiveengineering.common.IEContent;
import boilerplate.common.modcompat.ModCompat;
import boilerplate.common.utils.helpers.RegistryHelper;
import boilerplate.common.utils.recipe.RecipeUtils;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import moarcarts.MoarCarts;
import moarcarts.mods.ie.entities.EntityMinecartWoodenBarrel;
import moarcarts.mods.ie.items.ItemMinecartWoodenBarrel;
import net.minecraft.item.ItemStack;

/**
 * @author SkySom
 */
public class IEModCompat extends ModCompat
{
	public static ItemMinecartWoodenBarrel ITEM_MINECART_WOODENBARREL;

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
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		RecipeUtils.addMinecartRecipe(new ItemStack(ITEM_MINECART_WOODENBARREL), new ItemStack(IEContent.blockWoodenDevice, 6));
	}
}
