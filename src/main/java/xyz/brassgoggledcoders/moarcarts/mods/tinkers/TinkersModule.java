package xyz.brassgoggledcoders.moarcarts.mods.tinkers;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.lib.common.modules.Module;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.BlockRegistry;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.EntityRegistry;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.ItemRegistry;
import xyz.brassgoggledcoders.moarcarts.mods.tinkers.blocks.BlockCartFaucet;
import xyz.brassgoggledcoders.moarcarts.mods.tinkers.entities.EntityMinecartCastingBasin;
import xyz.brassgoggledcoders.moarcarts.mods.tinkers.entities.EntityMinecartCastingTable;
import xyz.brassgoggledcoders.moarcarts.mods.tinkers.items.ItemMinecartCasting;

public class TinkersModule extends Module
{
	public static ItemMinecartCasting ITEM_MINECART_CASTING;

	@Override
	public String getName()
	{
		return "Tinkers";
	}

	@Override
	public boolean areRequirementsMet()
	{
		return Loader.isModLoaded("tconstruct");
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		ITEM_MINECART_CASTING = new ItemMinecartCasting();
		ItemRegistry.registerItem(ITEM_MINECART_CASTING);
		EntityRegistry.registerEntity(EntityMinecartCastingBasin.class);
		EntityRegistry.registerEntity(EntityMinecartCastingTable.class);

		Block BLOCK_CART_FAUCET = new BlockCartFaucet();
		BlockRegistry.registerBlock(BLOCK_CART_FAUCET);
	}
}
