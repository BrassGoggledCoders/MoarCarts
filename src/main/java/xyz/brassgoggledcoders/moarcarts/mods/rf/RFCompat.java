package xyz.brassgoggledcoders.moarcarts.mods.rf;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.ModAPIManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import xyz.brassgoggledcoders.boilerplate.lib.common.modules.Module;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.BlockRegistry;
import xyz.brassgoggledcoders.moarcarts.mods.rf.blocks.BlockRFLoader;
import xyz.brassgoggledcoders.moarcarts.mods.rf.events.RFComparatorTrackHandler;

/**
 * @author SkySom
 */
public class RFCompat extends Module
{
	public static Block RFLOADER;

	@Override
	public String getName()
	{
		return "RF";
	}

	@Override
	public boolean areRequirementsMet()
	{
		return ModAPIManager.INSTANCE.hasAPI("CoFHAPI|energy");
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		RFLOADER = new BlockRFLoader();
		BlockRegistry.registerBlock(RFLOADER, "blockrfloader");
		MinecraftForge.EVENT_BUS.register(new RFComparatorTrackHandler());
	}

	public static void registerRFLoaderRecipe(ItemStack itemStack)
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(RFLOADER, "III", "WEW", "IRI", 'I', "ingotIron", 'W', "plankWood",
				'R', "dustRedstone", 'E', itemStack));
	}
}
