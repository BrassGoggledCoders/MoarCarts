package moarcarts.mods.ironchest;

import boilerplate.common.modcompat.ModCompat;
import boilerplate.common.utils.helpers.RegistryHelper;
import boilerplate.common.utils.recipe.RecipeUtils;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import moarcarts.MoarCarts;
import moarcarts.mods.ironchest.entities.EntityMinecartIronChest;
import moarcarts.mods.ironchest.items.ItemMinecartIronChest;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

/**
 * @author SkySom
 */
public class IronChestCompat extends ModCompat
{
	public static Block blockIronChest;
	public static ItemMinecartIronChest itemMinecartIronChest;

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
		RegistryHelper.registerEntity(MoarCarts.instance, EntityMinecartIronChest.class, "entityminecartironchest");
	}

	public void registerItems()
	{
		itemMinecartIronChest = new ItemMinecartIronChest();
		RegistryHelper.registerItem(itemMinecartIronChest, MoarCarts.MODID);
	}

	public void registerRecipes()
	{
		blockIronChest = Block.getBlockFromName("IronChest:BlockIronChest");
		for (int i = 0; i < 8; i++)
		{
			RecipeUtils.addMinecartRecipe(new ItemStack(itemMinecartIronChest, 1, i), new ItemStack(blockIronChest, 1, i));
		}
	}
}
