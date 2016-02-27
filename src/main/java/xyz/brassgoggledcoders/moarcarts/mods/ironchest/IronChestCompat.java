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
import xyz.brassgoggledcoders.boilerplate.lib.client.models.SafeModelLoader;
import xyz.brassgoggledcoders.boilerplate.lib.common.modcompat.ModCompat;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.helpers.RegistryHelper;
import xyz.brassgoggledcoders.moarcarts.MoarCarts;
import xyz.brassgoggledcoders.moarcarts.mods.ironchest.entities.*;
import xyz.brassgoggledcoders.moarcarts.mods.ironchest.items.ItemMinecartIronChest;
import xyz.brassgoggledcoders.moarcarts.mods.ironchest.renderers.RenderItemMinecartIronChest;
import xyz.brassgoggledcoders.moarcarts.recipes.NBTCartRecipe;
import xyz.brassgoggledcoders.moarcarts.renderers.ModelBakeHandler;

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
        RenderItemMinecartIronChest renderer = RenderItemMinecartIronChest.getInstance();
        ClientRegistry.bindTileEntitySpecialRenderer(renderer.getTileClass(), renderer);
        ModelResourceLocation[] variants = ITEM_MINECART_IRONCHEST.getModelDefinitions();
        for(int i = 0; i < 8; i++)
        {
            ModelLoader.setCustomModelResourceLocation(ITEM_MINECART_IRONCHEST, i, variants[i]);
            MoarCarts.logger.devInfo(variants[i].getResourceDomain() + " " + variants[i].getResourcePath());
            ModelBakeHandler.getInstance().registerModelToSwap(variants[i], renderer);
            //noinspection deprecation
            ForgeHooksClient.registerTESRItemStack(ITEM_MINECART_IRONCHEST, i, renderer.getTileClass());
        }
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		this.registerRecipes();
	}

	@Override
	public void clientInit(FMLInitializationEvent event)
	{
		RenderItemMinecartIronChest renderer = RenderItemMinecartIronChest.getInstance();
		ClientRegistry.bindTileEntitySpecialRenderer(renderer.getTileClass(), renderer);
		/*ModelResourceLocation[] variants = ITEM_MINECART_IRONCHEST.getModelDefinitions();
		for(int i = 0; i < 8; i++)
		{
			ModelLoader.setCustomModelResourceLocation(ITEM_MINECART_IRONCHEST, i, variants[i]);
			MoarCarts.logger.devInfo(variants[i].getResourceDomain() + " " + variants[i].getResourcePath());
			//ModelBakeHandler.getInstance().registerModelToSwap(variants[i], renderer);
			//ForgeHooksClient.registerTESRItemStack(ITEM_MINECART_IRONCHEST, i, renderer.getTileClass());
		}*/
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
		GameRegistry.registerItem(ITEM_MINECART_IRONCHEST, "minecartironchest");
		//SafeModelLoader.loadItemModel(ITEM_MINECART_IRONCHEST);
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
