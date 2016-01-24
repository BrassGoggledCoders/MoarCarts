package moarcarts.entities;

import xyz.brassgoggledcoders.boilerplate.common.utils.ComparatorUtils;
import cofh.api.energy.IEnergyHandler;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumFacing;

/**
 * @author SkySom
 */
public abstract class EntityMinecartEnergyHandlerTEBase extends EntityMinecartTEBase implements IEnergyHandler
{
	public EntityMinecartEnergyHandlerTEBase(World world, int metadata)
	{
		super(world, metadata);
	}

	@Override
	public int receiveEnergy(EnumFacing EnumFacing, int amount, boolean simulate)
	{
		return this.getIEnergyHandler().receiveEnergy(EnumFacing.UP, amount, simulate);
	}

	@Override
	public int extractEnergy(EnumFacing EnumFacing, int amount, boolean simulate)
	{
		return this.getIEnergyHandler().extractEnergy(EnumFacing.DOWN, amount, simulate);
	}

	@Override
	public int getEnergyStored(EnumFacing EnumFacing)
	{
		return this.getIEnergyHandler().getEnergyStored(EnumFacing);
	}

	@Override
	public int getMaxEnergyStored(EnumFacing EnumFacing)
	{
		return this.getIEnergyHandler().getMaxEnergyStored(EnumFacing);
	}

	@Override
	public boolean canConnectEnergy(EnumFacing EnumFacing)
	{
		return this.getIEnergyHandler().canConnectEnergy(EnumFacing);
	}

	public IEnergyHandler getIEnergyHandler()
	{
		return (IEnergyHandler)this.getTileEntity();
	}

	@Override
	public int getComparatorInputOverride()
	{
		if(!this.cartBlock.hasComparatorInputOverride())
		{
			return ComparatorUtils.scaleStoredEnergyTo(15, this);
		}
		return super.getComparatorInputOverride();
	}
}
