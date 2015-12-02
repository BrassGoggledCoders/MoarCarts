package moarcarts.proxies;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import moarcarts.MoarCarts;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class CommonProxy
{
	public void init(FMLInitializationEvent event)
	{
		MoarCarts.compatibilityHandler.init(event);
	}

	public World getWorld()
	{
		return null;
	}
}
