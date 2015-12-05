package moarcarts.mods.rf;

import boilerplate.common.modcompat.ModCompat;
import cpw.mods.fml.common.ModAPIManager;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import moarcarts.mods.rf.blocks.BlockRFLoader;
import moarcarts.mods.rf.tileentities.TileRFLoader;

/**
 * @author SkySom
 */
public class RFCompat extends ModCompat
{
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
		GameRegistry.registerBlock(new BlockRFLoader(), "blockrfloader");
		GameRegistry.registerTileEntity(TileRFLoader.class, "tilerfloader");
	}
}
