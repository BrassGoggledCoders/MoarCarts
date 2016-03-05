package xyz.brassgoggledcoders.moarcarts.entities;

import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;

public abstract class EntityMinecartCapableTEBase extends EntityMinecartTEBase
{
	public EntityMinecartCapableTEBase(World world, int metadata)
	{
		super(world, metadata);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		return getTileEntity().getCapability(capability, facing);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return getTileEntity().hasCapability(capability, facing);
	}
}
