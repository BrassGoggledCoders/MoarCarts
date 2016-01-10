package moarcarts.entities;

import cofh.api.energy.IEnergyHandler;
import moarcarts.utils.ComparatorUtils;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

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
	public int receiveEnergy(ForgeDirection forgeDirection, int amount, boolean simulate)
	{
		return this.getIEnergyHandler().receiveEnergy(ForgeDirection.UP, amount, simulate);
	}

	@Override
	public int extractEnergy(ForgeDirection forgeDirection, int amount, boolean simulate)
	{
		return this.getIEnergyHandler().extractEnergy(ForgeDirection.DOWN, amount, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection forgeDirection)
	{
		return this.getIEnergyHandler().getEnergyStored(forgeDirection);
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection forgeDirection)
	{
		return this.getIEnergyHandler().getMaxEnergyStored(forgeDirection);
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection forgeDirection)
	{
		return this.getIEnergyHandler().canConnectEnergy(forgeDirection);
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
