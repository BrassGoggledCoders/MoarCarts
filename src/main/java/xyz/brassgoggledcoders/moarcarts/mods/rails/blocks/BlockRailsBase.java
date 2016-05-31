package xyz.brassgoggledcoders.moarcarts.mods.rails.blocks;

import net.minecraft.block.BlockRailBase;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import xyz.brassgoggledcoders.boilerplate.lib.client.models.IHasModel;
import xyz.brassgoggledcoders.boilerplate.lib.common.items.IHasRecipe;
import xyz.brassgoggledcoders.moarcarts.MoarCarts;
import xyz.brassgoggledcoders.moarcarts.utils.Predicates;

public abstract class BlockRailsBase extends BlockRailBase implements IHasModel, IHasRecipe
{
	public static final PropertyEnum<EnumRailDirection> SHAPE =
			PropertyEnum.create("shape", BlockRailBase.EnumRailDirection.class, Predicates.FLAT_STRAIGHT);
	public String name;

	protected BlockRailsBase(String name)
	{
		this(name, false);
	}

	protected BlockRailsBase(String name, boolean powered)
	{
		super(powered);
		name += "_rail";
		this.name = name;
		this.setUnlocalizedName(name);
		this.setCreativeTab(MoarCarts.moarcartsTab);
		this.setDefaultState(this.blockState.getBaseState().withProperty(SHAPE, EnumRailDirection.NORTH_SOUTH));
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, SHAPE);
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState();
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return 0;
	}

	@Override
	public boolean canMakeSlopes(IBlockAccess world, BlockPos pos)
	{
		return false;
	}

	@Override
	public boolean isFlexibleRail(IBlockAccess world, BlockPos pos)
	{
		return false;
	}

	@Override
	public IProperty<EnumRailDirection> getShapeProperty()
	{
		return SHAPE;
	}

	@Override
	public String[] getResourceLocations()
	{
		return new String[] {name};
	}
}
