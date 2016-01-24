package moarcarts.mods.rf;

import xyz.brassgoggledcoders.boilerplate.common.modcompat.ModCompat;
import net.minecraftforge.fml.common.ModAPIManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import moarcarts.mods.rf.blocks.BlockRFLoader;
import moarcarts.mods.rf.events.RFComparatorTrackHandler;
import moarcarts.mods.rf.tileentities.TileRFLoader;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * @author SkySom
 */
public class RFCompat extends ModCompat
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
		GameRegistry.registerBlock(RFLOADER, "blockrfloader");
		GameRegistry.registerTileEntity(TileRFLoader.class, "tilerfloader");
		MinecraftForge.EVENT_BUS.register(new RFComparatorTrackHandler());
	}

	public static void registerRFLoaderRecipe(ItemStack itemStack)
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(RFLOADER, "III", "WEW", "IRI", 'I', "ingotIron", 'W', "plankWood",
				'R', "dustRedstone", 'E', itemStack));
	}
}
