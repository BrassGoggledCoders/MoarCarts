package xyz.brassgoggledcoders.moarcarts.mods.waila.providers;

import cofh.api.energy.IEnergyHandler;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import java.util.List;

public class BlockProviderRF extends BlockProviderBase
{
	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor,
			IWailaConfigHandler config)
	{
		TileEntity tileEntity = accessor.getTileEntity();
		if(tileEntity instanceof IEnergyHandler)
		{
			IEnergyHandler energyHandler = (IEnergyHandler)tileEntity;
			currenttip.add(String.format("%d / %d RF", energyHandler.getEnergyStored(null),
					energyHandler.getMaxEnergyStored(null)));
		}
		return currenttip;
	}
}
