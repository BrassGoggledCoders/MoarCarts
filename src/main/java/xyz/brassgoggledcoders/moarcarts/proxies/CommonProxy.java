package xyz.brassgoggledcoders.moarcarts.proxies;

import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * @author SkySom
 */
public class CommonProxy
{
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

	public void preInit()
	{
	}

	public void init()
	{
	}
}
