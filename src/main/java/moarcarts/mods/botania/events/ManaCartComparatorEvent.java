package moarcarts.mods.botania.events;

import moarcarts.api.ComparatorTrackEvent;
import vazkii.botania.common.entity.EntityPoolMinecart;

/**
 * @author SkySom
 */
public class ManaCartComparatorEvent
{
	public void onManaCartCompared(ComparatorTrackEvent event)
	{
		if(event.minecart instanceof EntityPoolMinecart)
		{
			event.setIntResult((int)((double)((EntityPoolMinecart) event.minecart).getMana() / 1000000.0D * 15.0D));
			event.setCanceled(true);
		}
	}
}
