package xyz.brassgoggledcoders.moarcarts.mods.ironchest;

import net.minecraft.block.Block;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.brassgoggledcoders.boilerplate.lib.common.modcompat.ModCompat;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.EntityRegistry;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.ItemRegistry;
import xyz.brassgoggledcoders.moarcarts.mods.ironchest.entities.*;
import xyz.brassgoggledcoders.moarcarts.mods.ironchest.items.ItemMinecartIronChest;
import xyz.brassgoggledcoders.moarcarts.recipes.NBTCartRecipe;
import xyz.brassgoggledcoders.moarcarts.renderers.RenderItemMinecartBase;

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

	public void registerEntities()
	{
		EntityRegistry.registerEntity(EntityMinecartCopperChest.class, "entityminecartcopperchest");
		EntityRegistry.registerEntity(EntityMinecartCrystalChest.class, "entityminecartcrystalchest");
		EntityRegistry.registerEntity(EntityMinecartDiamondChest.class, "entityminecartdiamondchest");
		EntityRegistry.registerEntity(EntityMinecartDirtChest.class, "entityminecartdirtchest");
		EntityRegistry.registerEntity(EntityMinecartGoldChest.class, "entityminecartgoldchest");
		EntityRegistry.registerEntity(EntityMinecartIronChest.class, "entityminecartironchest");
		EntityRegistry.registerEntity(EntityMinecartObsidianChest.class, "entityminecartobsidianchest");
		EntityRegistry.registerEntity(EntityMinecartSilverChest.class, "entityminecartsilverchest");
	}

	public void registerItems()
	{
		ITEM_MINECART_IRONCHEST = new ItemMinecartIronChest();
		ItemRegistry.registerItem(ITEM_MINECART_IRONCHEST);
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
