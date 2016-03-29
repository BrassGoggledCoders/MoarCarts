package xyz.brassgoggledcoders.moarcarts.mods.rf.blocks;

import cofh.api.energy.IEnergyHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.boilerplate.lib.common.blocks.SidedBlock;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.ComparatorUtils;
import xyz.brassgoggledcoders.moarcarts.mods.rf.tileentities.TileRFLoader;

/**
 * @author SkySom
 */
public class BlockRFLoader extends SidedBlock
{
	public BlockRFLoader()
	{
		super(Material.iron);
		this.setStepSound(Block.soundTypeMetal);
	}

	@Override
	public int getComparatorInputOverride(World world, BlockPos pos)
	{
		TileEntity tileEntity = world.getTileEntity(pos);
		if(tileEntity instanceof TileRFLoader)
		{
			return ComparatorUtils.scaleStoredEnergyTo(15, (IEnergyHandler)tileEntity);
		}
		return 0;
	}

	@Override
	public boolean hasComparatorInputOverride()
	{
		return true;
	}

	@Override
	public Class<? extends TileEntity> getTileEntityClass()
	{
		return TileRFLoader.class;
	}
}
