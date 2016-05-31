package xyz.brassgoggledcoders.moarcarts.mods.rails.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import xyz.brassgoggledcoders.boilerplate.lib.common.items.ToolUtils;
import xyz.brassgoggledcoders.moarcarts.utils.Utils;

import javax.annotation.Nullable;

public class BlockRailSwitch extends BlockRailsBase
{
	public static final PropertyBool NORTH_WEST = PropertyBool.create("north_west");
	public static final PropertyBool STRAIGHT = PropertyBool.create("straight");
	public static final PropertyBool LEFT = PropertyBool.create("left");

	public static EnumRailDirection curves[] = new EnumRailDirection[] {EnumRailDirection.NORTH_EAST,
			EnumRailDirection.NORTH_WEST, EnumRailDirection.SOUTH_WEST, EnumRailDirection.SOUTH_EAST};

	public BlockRailSwitch()
	{
		super("switch", true);
		this.setDefaultState(this.blockState.getBaseState().withProperty(SHAPE, EnumRailDirection.NORTH_SOUTH)
			.withProperty(NORTH_WEST, true).withProperty(STRAIGHT, true).withProperty(LEFT, true));
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,
			EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if(!world.isRemote && ToolUtils.isItemATool(player.getCurrentEquippedItem())) {
			if(state.getValue(LEFT))
			{
				state = state.withProperty(LEFT, false);
			} else
			{
				if(state.getValue(NORTH_WEST))
				{
					state = state.withProperty(NORTH_WEST, false);
				} else
				{
					if(state.getValue(SHAPE) == EnumRailDirection.NORTH_SOUTH)
					{
						state = state.withProperty(SHAPE, EnumRailDirection.EAST_WEST);
					} else
					{
						state = state.withProperty(SHAPE, EnumRailDirection.NORTH_SOUTH);
					}
					state = state.withProperty(NORTH_WEST, true);
				}
				state = state.withProperty(LEFT, true);
			}
			world.setBlockState(pos, state);
			world.markBlockForUpdate(pos);
			return true;
		}
		return false;
	}

	@Override
	public EnumRailDirection getRailDirection(IBlockAccess world, BlockPos pos, IBlockState state, @Nullable
			EntityMinecart cart)
	{
		EnumRailDirection railDirection = state.getValue(SHAPE);
		if(cart != null) {
			EnumFacing cartFacing = cart.getHorizontalFacing();
			if(!isTrackParallelToCartFacing(cartFacing, railDirection) || !state.getValue(STRAIGHT))
			{
				railDirection = getCurveForRail(state);
			}
		}
		return railDirection;
	}

	public boolean isTrackParallelToCartFacing(EnumFacing cartFacing, EnumRailDirection railDirection)
	{
		if(cartFacing == EnumFacing.EAST || cartFacing == EnumFacing.WEST)
		{
			return railDirection == EnumRailDirection.NORTH_SOUTH;
		} else
		{
			return railDirection == EnumRailDirection.EAST_WEST;
		}
	}

	public EnumRailDirection getCurveForRail(IBlockState state)
	{
		return curves[getMetaFromState(state) % 4];
	}

	@Override
	protected void onNeighborChangedInternal(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
	{
		boolean isStraight = state.getValue(STRAIGHT);
		boolean isRedstonePowered = worldIn.isBlockPowered(pos);

		if (isStraight == isRedstonePowered)
		{
			worldIn.setBlockState(pos, state.withProperty(STRAIGHT, !isRedstonePowered), 3);
			worldIn.notifyNeighborsOfStateChange(pos.down(), this);
		}
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, SHAPE, STRAIGHT, NORTH_WEST, LEFT);
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		IBlockState state = this.getDefaultState();
		boolean[] stateArray = Utils.getBooleanArrayFromInt(meta, 4);
		state = (stateArray[0] ? state.withProperty(SHAPE, EnumRailDirection.NORTH_SOUTH) :
				state.withProperty(SHAPE, EnumRailDirection.EAST_WEST));
		state = state.withProperty(STRAIGHT, stateArray[1]).withProperty(NORTH_WEST, stateArray[2]);
		state = state.withProperty(LEFT, stateArray[3]);
		return state;
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		boolean[] stateArray = new boolean[4];
		stateArray[0] = state.getValue(SHAPE) == EnumRailDirection.NORTH_SOUTH;
		stateArray[1] = state.getValue(STRAIGHT);
		stateArray[2] = state.getValue(NORTH_WEST);
		stateArray[3] = state.getValue(LEFT);

		return Utils.getIntFromBooleanArray(stateArray);
	}

	@Override
	public IRecipe[] getRecipes()
	{
		return new IRecipe[] {new ShapedOreRecipe(new ItemStack(this, 4),"RRR", " R ", 'R', new ItemStack(Blocks.rail))};
	}
}
