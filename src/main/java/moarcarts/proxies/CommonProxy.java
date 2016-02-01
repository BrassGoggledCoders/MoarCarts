package moarcarts.proxies;

import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
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

	public World getWorld(MessageContext ctx)
	{
		return ctx.getServerHandler().playerEntity.getEntityWorld();
	}

	public IThreadListener getIThreadListener(MessageContext messageContext)
	{
		return (WorldServer)messageContext.getServerHandler().playerEntity.worldObj;
	}
}
