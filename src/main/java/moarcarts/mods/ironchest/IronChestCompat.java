package moarcarts.mods.ironchest;

import boilerplate.common.modcompat.ModCompat;
import boilerplate.common.utils.helpers.RegistryHelper;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import moarcarts.mods.ironchest.entities.*;
import moarcarts.mods.ironchest.items.ItemMinecartIronChest;
import moarcarts.recipes.NBTCartRecipe;
import moarcarts.renderers.RenderItemMinecartBase;
import net.minecraft.block.Block;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * @author SkySom
 */
public class IronChestCompat extends ModCompat
{
	public static ItemMinecartIronChest ITEM_MINECART_IRONCHEST;
	public static Block IRON_CHEST;

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

	@Override
	@SideOnly(Side.CLIENT)
	public void clientInit(FMLInitializationEvent event)
	{
		MinecraftForgeClient.registerItemRenderer(ITEM_MINECART_IRONCHEST, new RenderItemMinecartBase());
	}

	public void registerEntities()
	{
		RegistryHelper.registerEntity(EntityMinecartCopperChest.class, "entityminecartcopperchest");
		RegistryHelper.registerEntity(EntityMinecartCrystalChest.class, "entityminecartcrystalchest");
		RegistryHelper.registerEntity(EntityMinecartDiamondChest.class, "entityminecartdiamondchest");
		RegistryHelper.registerEntity(EntityMinecartDirtChest.class, "entityminecartdirtchest");
		RegistryHelper.registerEntity(EntityMinecartGoldChest.class, "entityminecartgoldchest");
		RegistryHelper.registerEntity(EntityMinecartIronChest.class, "entityminecartironchest");
		RegistryHelper.registerEntity(EntityMinecartObsidianChest.class, "entityminecartobsidianchest");
		RegistryHelper.registerEntity(EntityMinecartSilverChest.class, "entityminecartsilverchest");
	}

	public void registerItems()
	{
		ITEM_MINECART_IRONCHEST = new ItemMinecartIronChest();
		RegistryHelper.registerItem(ITEM_MINECART_IRONCHEST);
	}

	public void registerRecipes()
	{
		IRON_CHEST = GameRegistry.findBlock("IronChest", "BlockIronChest");
		for (int i = 0; i < 8; i++)
		{
			GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_IRONCHEST, i, IRON_CHEST, i));
		}
	}
}
