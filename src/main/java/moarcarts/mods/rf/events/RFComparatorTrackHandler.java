package moarcarts.mods.rf.events;

import cofh.api.energy.IEnergyHandler;
import moarcarts.api.ComparatorTrackEvent;
import moarcarts.utils.ComparatorUtils;

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
