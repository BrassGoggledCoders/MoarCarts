package moarcarts.mods.ironchest;

import boilerplate.common.modcompat.ModCompat;
import boilerplate.common.utils.helpers.RegistryHelper;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import moarcarts.MoarCarts;
import moarcarts.mods.ironchest.entities.*;
import moarcarts.mods.ironchest.items.ItemMinecartIronChest;
import moarcarts.recipes.NBTCartRecipe;
import net.minecraft.block.Block;

/**
 * @author SkySom
 */
public class IronChestCompat extends ModCompat
{
	public static ItemMinecartIronChest ITEM_MINECART_IRONCHEST;

	@Override
	public String getName()
	{
		return "Iron Chests";
	}

	@Override
	public boolean areRequirementsMet()
	{
		return Loader.isModLoaded("IronChest");
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		this.registerItems();
		this.registerEntities();
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		this.registerRecipes();
	}

	public void registerEntities()
	{
		RegistryHelper.registerEntity(MoarCarts.instance, EntityMinecartCopperChest.class, "entityminecartcopperchest");
		RegistryHelper.registerEntity(MoarCarts.instance, EntityMinecartCrystalChest.class, "entityminecartcrystalchest");
		RegistryHelper.registerEntity(MoarCarts.instance, EntityMinecartDiamondChest.class, "entityminecartdiamondchest");
		RegistryHelper.registerEntity(MoarCarts.instance, EntityMinecartDirtChest.class, "entityminecartdirtchest");
		RegistryHelper.registerEntity(MoarCarts.instance, EntityMinecartGoldChest.class, "entityminecartgoldchest");
		RegistryHelper.registerEntity(MoarCarts.instance, EntityMinecartIronChest.class, "entityminecartironchest");
		RegistryHelper.registerEntity(MoarCarts.instance, EntityMinecartObsidianChest.class, "entityminecartobsidianchest");
		RegistryHelper.registerEntity(MoarCarts.instance, EntityMinecartSilverChest.class, "entityminecartsilverchest");
	}

	public void registerItems()
	{
		ITEM_MINECART_IRONCHEST = new ItemMinecartIronChest();
		RegistryHelper.registerItem(ITEM_MINECART_IRONCHEST, MoarCarts.MODID);
	}

	public void registerRecipes()
	{
		Block blockIronChest = Block.getBlockFromName("IronChest:BlockIronChest");
		for (int i = 0; i < 8; i++)
		{
			GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_IRONCHEST, i, blockIronChest, i));
		}
	}
}
