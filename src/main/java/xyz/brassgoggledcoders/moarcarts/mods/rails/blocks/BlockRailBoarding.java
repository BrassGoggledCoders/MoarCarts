package xyz.brassgoggledcoders.moarcarts.mods.rails.blocks;

import net.minecraft.block.BlockRailBase;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import xyz.brassgoggledcoders.moarcarts.utils.Predicates;

public class BlockRailBoarding extends BlockRailBase
{
	public static final PropertyEnum<EnumRailDirection>
			SHAPE = PropertyEnum.create("shape", BlockRailBase.EnumRailDirection.class, Predicates.FLAT_STRAIGHT);
	public static final PropertyBool POWERED = PropertyBool.create("powered");

	public BlockRailBoarding()
	{
		super(true);
		this.setDefaultState(this.blockState.getBaseState().withProperty(SHAPE, BlockRailBase.EnumRailDirection.NORTH_SOUTH).withProperty(POWERED, Boolean.valueOf(false)));
	}

	@Override
	public IProperty<EnumRailDirection> getShapeProperty()
	{
		return SHAPE;
	}
}
