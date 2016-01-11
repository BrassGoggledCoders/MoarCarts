package moarcarts.mods.rf.events;

import boilerplate.common.utils.ComparatorUtils;
import cofh.api.energy.IEnergyHandler;
import moarcarts.api.ComparatorTrackEvent;

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
