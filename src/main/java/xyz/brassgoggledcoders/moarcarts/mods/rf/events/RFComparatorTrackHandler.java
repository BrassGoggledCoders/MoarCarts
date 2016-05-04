package xyz.brassgoggledcoders.moarcarts.mods.rf.events;

import cofh.api.energy.IEnergyHandler;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.ComparatorUtils;
import xyz.brassgoggledcoders.moarcarts.api.ComparatorTrackEvent;

/**
 * @author SkySom
 */
public class RFComparatorTrackHandler
{
	public void onComparatorTrack(ComparatorTrackEvent event)
	{
		if(event.entity instanceof IEnergyHandler)
		{
			event.setIntResult(ComparatorUtils.scaleStoredEnergyTo(15, (IEnergyHandler)event.entity));
		}
	}
}
