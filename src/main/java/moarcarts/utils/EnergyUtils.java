package moarcarts.utils;

import cofh.api.energy.IEnergyHandler;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author SkySom
 */
public class EnergyUtils
{
	public static int scaleStoredEnergyTo(int scale, IEnergyHandler energyHandler)
	{
		return scaleStoredEnergyTo(scale, energyHandler, ForgeDirection.UNKNOWN);
	}

	public static int scaleStoredEnergyTo(int scale, IEnergyHandler energyHandler, ForgeDirection direction)
	{
		return (int)(scale * (energyHandler.getEnergyStored(direction)
				/ (float)energyHandler.getMaxEnergyStored(direction)));
	}
}
