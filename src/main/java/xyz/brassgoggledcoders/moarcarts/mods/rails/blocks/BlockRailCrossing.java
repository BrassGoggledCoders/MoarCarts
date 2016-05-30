package xyz.brassgoggledcoders.moarcarts.mods.rails.blocks;

import net.minecraft.block.BlockRailBase;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.oredict.ShapedOreRecipe;
import xyz.brassgoggledcoders.boilerplate.lib.client.models.IHasModel;
import xyz.brassgoggledcoders.boilerplate.lib.common.items.IHasRecipe;
import xyz.brassgoggledcoders.moarcarts.MoarCarts;
import xyz.brassgoggledcoders.moarcarts.utils.Predicates;

import javax.annotation.Nullable;

public class BlockRailCrossing extends BlockRailBase implements IHasModel, IHasRecipe
{
	public static final PropertyEnum<EnumRailDirection> SHAPE =
			PropertyEnum.create("shape", BlockRailBase.EnumRailDirection.class, Predicates.FLAT_STRAIGHT);

	public BlockRailCrossing()
	{
		super(false);
		this.setUnlocalizedName("crossing_rail");
		this.setCreativeTab(MoarCarts.moarcartsTab);
		this.setDefaultState(this.blockState.getBaseState().withProperty(SHAPE, EnumRailDirection.NORTH_SOUTH));
	}

	@Override
	public boolean canMakeSlopes(IBlockAccess world, BlockPos pos)
	{
		return false;
	}

	@Override
	public EnumRailDirection getRailDirection(IBlockAccess world, BlockPos pos, IBlockState state, @Nullable
			EntityMinecart cart)
	{
		if(cart != null) {
			EnumFacing cartFacing = cart.getHorizontalFacing();
			if(cartFacing == EnumFacing.NORTH || cartFacing == EnumFacing.SOUTH)
			{
				return EnumRailDirection.EAST_WEST;
			}
		}
		return EnumRailDirection.NORTH_SOUTH;
	}

	@Override
	public IProperty<EnumRailDirection> getShapeProperty()
	{
		return SHAPE;
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
	public String[] getResourceLocations()
	{
		return new String[] {"crossing_rail"};
	}

	@Override
	public IRecipe[] getRecipes()
	{
		return new IRecipe[]{new ShapedOreRecipe(new ItemStack(this, 5)," R ","RRR", " R ", 'R', new ItemStack(Blocks.rail))};
	}
}
