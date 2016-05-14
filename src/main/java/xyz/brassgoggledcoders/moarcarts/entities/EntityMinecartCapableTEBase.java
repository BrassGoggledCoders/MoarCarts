package xyz.brassgoggledcoders.moarcarts.entities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;

public abstract class EntityMinecartCapableTEBase extends EntityMinecartTEBase
{
	public EntityMinecartCapableTEBase(World world, int metadata)
	{
		super(world, metadata);
	}

	public boolean isUseableByPlayer(EntityPlayer entityPlayer)
	{
		return !isDead && entityPlayer.getDistanceSqToEntity(this) <= 64.0D;
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
