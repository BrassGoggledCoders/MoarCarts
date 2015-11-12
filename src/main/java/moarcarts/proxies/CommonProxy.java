package moarcarts.proxies;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import moarcarts.MoarCarts;

/**
 * @author SkySom
 */
public class CommonProxy
{
	public void init(FMLInitializationEvent event)
	{
		MoarCarts.compatibilityHandler.init(event);
	}
}
