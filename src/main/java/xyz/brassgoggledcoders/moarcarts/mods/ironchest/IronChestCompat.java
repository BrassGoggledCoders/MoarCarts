package xyz.brassgoggledcoders.moarcarts.mods.ironchest;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.lib.common.modcompat.ModCompat;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.EntityRegistry;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.ItemRegistry;
import xyz.brassgoggledcoders.moarcarts.mods.ironchest.entities.*;
import xyz.brassgoggledcoders.moarcarts.mods.ironchest.items.ItemMinecartIronChest;

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

	public void registerEntities()
	{
		EntityRegistry.registerEntity(EntityMinecartCopperChest.class);
		EntityRegistry.registerEntity(EntityMinecartCrystalChest.class);
		EntityRegistry.registerEntity(EntityMinecartDiamondChest.class);
		EntityRegistry.registerEntity(EntityMinecartDirtChest.class);
		EntityRegistry.registerEntity(EntityMinecartGoldChest.class);
		EntityRegistry.registerEntity(EntityMinecartIronChest.class);
		EntityRegistry.registerEntity(EntityMinecartObsidianChest.class);
		EntityRegistry.registerEntity(EntityMinecartSilverChest.class);
	}

	public void registerItems()
	{
		ITEM_MINECART_IRONCHEST = new ItemMinecartIronChest();
		ItemRegistry.registerItem(ITEM_MINECART_IRONCHEST);
	}
}
