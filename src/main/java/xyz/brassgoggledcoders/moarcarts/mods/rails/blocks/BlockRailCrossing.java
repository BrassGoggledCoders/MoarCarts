package xyz.brassgoggledcoders.moarcarts.mods.rails.blocks;

import net.minecraft.block.BlockRailBase;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.oredict.ShapedOreRecipe;
import xyz.brassgoggledcoders.moarcarts.utils.Predicates;

import javax.annotation.Nullable;

public class BlockRailCrossing extends BlockRailsBase
{
	public static final PropertyEnum<EnumRailDirection> SHAPE =
			PropertyEnum.create("shape", BlockRailBase.EnumRailDirection.class, Predicates.FLAT_STRAIGHT);

	public BlockRailCrossing()
	{
		super("crossing");
		this.setDefaultState(this.blockState.getBaseState().withProperty(SHAPE, EnumRailDirection.NORTH_SOUTH));
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
	public IRecipe[] getRecipes()
	{
		return new IRecipe[]{new ShapedOreRecipe(new ItemStack(this, 5)," R ","RRR", " R ", 'R', new ItemStack(Blocks.rail))};
	}
}
