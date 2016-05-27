package xyz.brassgoggledcoders.moarcarts.mods.rails.blocks;

import net.minecraft.block.BlockRailBase;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import xyz.brassgoggledcoders.boilerplate.lib.client.models.IHasModel;
import xyz.brassgoggledcoders.boilerplate.lib.common.items.IHasRecipe;
import xyz.brassgoggledcoders.moarcarts.MoarCarts;
import xyz.brassgoggledcoders.moarcarts.utils.Predicates;

import java.util.ArrayList;
import java.util.List;

public class BlockRailBoarding extends BlockRailBase implements IHasModel, IHasRecipe
{
	public static final PropertyEnum<EnumRailDirection>
			SHAPE = PropertyEnum.create("shape", BlockRailBase.EnumRailDirection.class, Predicates.FLAT_STRAIGHT);
	public static final PropertyBool POWERED = PropertyBool.create("powered");
	public static final PropertyBool DIRECTION = PropertyBool.create("direction");

	public BlockRailBoarding()
	{
		super(true);
		this.setUnlocalizedName("boarding_rail");
		this.setCreativeTab(MoarCarts.moarcartsTab);
		this.setDefaultState(this.blockState.getBaseState()
				.withProperty(SHAPE, EnumRailDirection.NORTH_SOUTH).withProperty(POWERED, false)
				.withProperty(DIRECTION, true));
	}

	@Override
	public boolean canMakeSlopes(IBlockAccess world, BlockPos pos)
	{
		return false;
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, SHAPE, POWERED, DIRECTION);
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return createBlockState().getBaseState();
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return 0;
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
