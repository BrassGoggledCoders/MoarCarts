package xyz.brassgoggledcoders.moarcarts.mods.extras.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xyz.brassgoggledcoders.boilerplate.lib.common.blocks.BlockTEBase;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.Selectors;
import xyz.brassgoggledcoders.moarcarts.mods.extras.tiles.TileFluidHopper;

import java.util.List;

public class BlockFluidHopper extends BlockTEBase
{
	public static final PropertyDirection FACING = PropertyDirection.create("facing", Selectors.NOT_UP);
	public static final PropertyBool ENABLED = PropertyBool.create("enabled");

	public BlockFluidHopper()
	{
		super(Material.iron, "fluid_hopper");
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.DOWN).withProperty(ENABLED, true));
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
	{
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List<AxisAlignedBB> list, Entity collidingEntity)
	{
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
		float f = 0.125F;
		this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
		this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
		this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		EnumFacing enumfacing = facing.getOpposite();

		if (enumfacing == EnumFacing.UP)
		{
			enumfacing = EnumFacing.DOWN;
		}

		return this.getDefaultState().withProperty(FACING, enumfacing).withProperty(ENABLED, true);
	}

	@Override
	public boolean isFullCube()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
	{
		return true;
	}

	public static EnumFacing getFacing(int meta)
	{
		return EnumFacing.getFront(meta & 7);
	}

	public static boolean isEnabled(int meta)
	{
		return (meta & 8) != 8;
	}

	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.CUTOUT_MIPPED;
	}

	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(FACING, getFacing(meta)).withProperty(ENABLED, isEnabled(meta));
	}

	public int getMetaFromState(IBlockState state)
	{
		int i = 0;
		i = i | state.getValue(FACING).getIndex();

		if (!state.getValue(ENABLED))
		{
			i |= 8;
		}

		return i;
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, FACING, ENABLED);
	}

	@Override
	public Class<? extends TileEntity> getTileEntityClass()
	{
		return TileFluidHopper.class;
	}
}
