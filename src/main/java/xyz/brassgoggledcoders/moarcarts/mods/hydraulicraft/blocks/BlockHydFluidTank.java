package xyz.brassgoggledcoders.moarcarts.mods.hydraulicraft.blocks;

import k4unl.minecraft.Hydraulicraft.blocks.storage.BlockFluidTank;
import xyz.brassgoggledcoders.boilerplate.lib.client.models.IHasModel;
import xyz.brassgoggledcoders.moarcarts.MoarCarts;

public class BlockHydFluidTank extends BlockFluidTank implements IHasModel
{
	public BlockHydFluidTank()
	{
		super();
		setCreativeTab(MoarCarts.moarcartsTab);
		setUnlocalizedName("fluid_tank");
		setRegistryName("fluid_tank");
	}

	@Override
	public String[] getResourceLocations()
	{
		return new String[] {"fluid_tank"};
	}
}
