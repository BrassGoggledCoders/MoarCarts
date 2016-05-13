package xyz.brassgoggledcoders.moarcarts.mods.rf.blocks;

import cofh.api.energy.IEnergyHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.boilerplate.lib.client.models.IHasModel;
import xyz.brassgoggledcoders.boilerplate.lib.common.blocks.BlockSidedBase;
import xyz.brassgoggledcoders.boilerplate.lib.common.blocks.Properties;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.ComparatorUtils;
import xyz.brassgoggledcoders.moarcarts.mods.rf.tileentities.TileRFLoader;

/**
 * @author SkySom
 */
public class BlockRFLoader extends BlockSidedBase implements IHasModel
{
	public BlockRFLoader()
	{
		super(Material.iron, "rf_loader");
		this.setStepSound(Block.soundTypeMetal);
		this.setHardness(3.0F);
		this.setResistance(15.0F);
		this.setDefaultState(this.blockState.getBaseState());
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, Properties.SIDECONFIG[0], Properties.SIDECONFIG[1],
				Properties.SIDECONFIG[2], Properties.SIDECONFIG[3], Properties.SIDECONFIG[4], Properties.SIDECONFIG[5]);
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState();
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		TileEntity tileEntity = world.getTileEntity(pos);
		if(tileEntity instanceof TileRFLoader)
		{
			state = ((TileRFLoader) tileEntity).writeBlockState(state);
		}
		return state;
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

	@Override
	public String[] getResourceLocations()
	{
		return new String[] {"rf_loader"};
	}
}
