package xyz.brassgoggledcoders.moarcarts.mods.rails.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.BlockRailPowered;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.boilerplate.lib.client.models.IHasModel;
import xyz.brassgoggledcoders.boilerplate.lib.common.items.IHasRecipe;
import xyz.brassgoggledcoders.boilerplate.lib.common.items.ToolUtils;
import xyz.brassgoggledcoders.moarcarts.MoarCarts;
import xyz.brassgoggledcoders.moarcarts.utils.Predicates;
import xyz.brassgoggledcoders.moarcarts.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class BlockRailBoarding extends BlockRailPowered implements IHasModel, IHasRecipe
{
	public static final PropertyEnum<EnumRailDirection>
			SHAPE = PropertyEnum.create("shape", BlockRailBase.EnumRailDirection.class, Predicates.FLAT_STRAIGHT);
	public static final PropertyBool POWERED = PropertyBool.create("powered");
	public static final PropertyBool NORTH_WEST = PropertyBool.create("north_west");

	public BlockRailBoarding()
	{
		super();
		this.setUnlocalizedName("boarding_rail");
		this.setCreativeTab(MoarCarts.moarcartsTab);
		this.setDefaultState(this.blockState.getBaseState()
				.withProperty(SHAPE, EnumRailDirection.NORTH_SOUTH).withProperty(POWERED, false)
				.withProperty(NORTH_WEST, true));
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,
			EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if(!world.isRemote && ToolUtils.isItemATool(player.getCurrentEquippedItem())) {
			state = state.withProperty(NORTH_WEST, !state.getValue(NORTH_WEST));
			world.setBlockState(pos, state);
			world.markBlockForUpdate(pos);
			return true;
		}
		return false;
	}

	@Override
	public boolean canMakeSlopes(IBlockAccess world, BlockPos pos)
	{
		return false;
	}

	@Override
	public void onMinecartPass(World world, EntityMinecart cart, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos);
		if(state.getValue(POWERED))
		{
			float speedIncrease = .5f;
			if(state.getValue(NORTH_WEST))
			{
				speedIncrease *= -1;
			}
			if(state.getValue(SHAPE) == EnumRailDirection.NORTH_SOUTH)
			{
				cart.motionZ += speedIncrease;
			} else
			{
				cart.motionX += speedIncrease;
			}
		} else
		{
			cart.motionX = 0;
			cart.motionY = 0;
			cart.motionZ = 0;
		}
	}

	@Override
	protected void onNeighborChangedInternal(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
	{
		boolean isStatePowered = state.getValue(POWERED);
		boolean isWorldPowered = worldIn.isBlockPowered(pos) || this.func_176566_a(worldIn, pos, state, true, 0) ||
				this.func_176566_a(worldIn, pos, state, false, 0);

		if (isWorldPowered != isStatePowered)
		{
			worldIn.setBlockState(pos, state.withProperty(POWERED, isWorldPowered), 3);
			worldIn.notifyNeighborsOfStateChange(pos.down(), this);
		}
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, SHAPE, POWERED, NORTH_WEST);
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		IBlockState state = this.getDefaultState();
		boolean[] stateArray = Utils.getBooleanArrayFromInt(meta, 4);
		state = (stateArray[0] ? state.withProperty(SHAPE, EnumRailDirection.NORTH_SOUTH) :
				state.withProperty(SHAPE, EnumRailDirection.EAST_WEST));
		state = state.withProperty(POWERED, stateArray[1]).withProperty(NORTH_WEST, stateArray[2]);
		return state;
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		boolean[] stateArray = new boolean[4];
		stateArray[0] = state.getValue(SHAPE) == EnumRailDirection.NORTH_SOUTH;
		stateArray[1] = state.getValue(POWERED);
		stateArray[2] = state.getValue(NORTH_WEST);

		return Utils.getIntFromBooleanArray(stateArray);
	}

	@Override
	public IProperty<EnumRailDirection> getShapeProperty()
	{
		return SHAPE;
	}

	@Override
	public String[] getResourceLocations()
	{
		return new String[] {"boarding_rail"};
	}

	@Override
	public IRecipe[] getRecipes()
	{
		List<ItemStack> items = new ArrayList<>();
		items.add(new ItemStack(Blocks.golden_rail));
		return new IRecipe[]{new ShapelessRecipes(new ItemStack(this), items)};
	}
}
