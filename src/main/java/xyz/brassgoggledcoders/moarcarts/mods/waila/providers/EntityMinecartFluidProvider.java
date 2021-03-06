package xyz.brassgoggledcoders.moarcarts.mods.waila.providers;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import net.minecraft.entity.Entity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

import java.util.List;

/**
 * @author SkySom
 */
public class EntityMinecartFluidProvider extends EntityMinecartTEBaseProvider
{
	@Override
	public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor,
			IWailaConfigHandler config)
	{
		if(entity instanceof IFluidHandler)
		{
			IFluidHandler iFluidHandler = (IFluidHandler)entity;
			if(iFluidHandler.getTankInfo(null).length > 0 &&
					iFluidHandler.getTankInfo(null)[0] != null)
			{
				FluidTankInfo fluidTankInfo = iFluidHandler.getTankInfo(null)[0];
				if(fluidTankInfo.fluid != null)
				{
					FluidStack fluid = fluidTankInfo.fluid;
					currenttip.add(String.format("%s: %d / %d mB", fluid.getLocalizedName(), fluid.amount,
							fluidTankInfo.capacity));
				}
			}
		}

		return currenttip;
	}
}
