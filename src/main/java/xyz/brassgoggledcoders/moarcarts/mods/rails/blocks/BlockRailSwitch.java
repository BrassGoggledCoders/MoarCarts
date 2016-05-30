package xyz.brassgoggledcoders.moarcarts.mods.rails.blocks;

import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;
import xyz.brassgoggledcoders.moarcarts.utils.Utils;

public class BlockRailSwitch extends BlockRailsBase
{
	public static final PropertyBool NORTH_WEST = PropertyBool.create("north_west");
	public static final PropertyBool STRAIGHT = PropertyBool.create("straight");
	public static final PropertyBool LEFT = PropertyBool.create("left");

	public BlockRailSwitch()
	{
		super("switch");
		this.setDefaultState(this.blockState.getBaseState().withProperty(SHAPE, EnumRailDirection.NORTH_SOUTH)
			.withProperty(NORTH_WEST, true).withProperty(STRAIGHT, true).withProperty(LEFT, true));
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
