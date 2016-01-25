package moarcarts.mods.ironchest;

import moarcarts.MoarCarts;
import moarcarts.mods.ironchest.entities.*;
import moarcarts.mods.ironchest.items.ItemMinecartIronChest;
import moarcarts.recipes.NBTCartRecipe;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xyz.brassgoggledcoders.boilerplate.common.modcompat.ModCompat;
import xyz.brassgoggledcoders.boilerplate.common.utils.helpers.RegistryHelper;

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
		//MinecraftForgeClient.registerItemRenderer(ITEM_MINECART_IRONCHEST, new RenderItemMinecartBase());
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
