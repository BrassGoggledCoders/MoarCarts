package xyz.brassgoggledcoders.moarcarts.entities;

import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.ComparatorUtils;

/**
 * @author SkySom
 * TODO: RF Stuff
 */
public abstract class EntityMinecartEnergyHandlerTEBase extends EntityMinecartTEBase implements IEnergyReceiver,
		IEnergyProvider
{
	public EntityMinecartEnergyHandlerTEBase(World world, int metadata)
	{
		super(world, metadata);
	}

	@Override
	public int receiveEnergy(EnumFacing enumFacing, int amount, boolean simulate)
	{
		return ((IEnergyReceiver)this.getIEnergyHandler()).receiveEnergy(EnumFacing.UP, amount, simulate);
	}

	@Override
	public int extractEnergy(EnumFacing enumFacing, int amount, boolean simulate)
	{
		return ((IEnergyProvider)this.getIEnergyHandler()).extractEnergy(EnumFacing.DOWN, amount, simulate);
	}

	@Override
	public int getEnergyStored(EnumFacing enumFacing)
	{
		return this.getIEnergyHandler().getEnergyStored(enumFacing);
	}

	@Override
	public int getMaxEnergyStored(EnumFacing enumFacing)
	{
		return this.getIEnergyHandler().getMaxEnergyStored(enumFacing);
	}

	@Override
	public boolean canConnectEnergy(EnumFacing enumFacing)
	{
		return this.getIEnergyHandler().canConnectEnergy(enumFacing);
	}

	public IEnergyHandler getIEnergyHandler()
	{
		return (IEnergyHandler)this.getTileEntity();
	}

	@Override
	public int getComparatorInputOverride()
	{
		if(!this.getCartBlock().hasComparatorInputOverride())
		{
			return ComparatorUtils.scaleStoredEnergyTo(15, this);
		}
		return super.getComparatorInputOverride();
	}
}
