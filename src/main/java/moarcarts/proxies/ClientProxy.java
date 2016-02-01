package moarcarts.proxies;

import moarcarts.MoarCarts;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * @author SkySom
 */
public class ClientProxy extends CommonProxy
{
	@Override
	public void init(FMLInitializationEvent event)
	{
		super.init(event);
		this.registerRenderers();
		MoarCarts.compatibilityHandler.clientInit(event);
	}

	private void registerRenderers()
	{
		//TODO: Rendering
		//RenderingRegistry.registerEntityRenderingHandler(EntityMinecartTEBase.class, new RenderMinecartTEBase());
	}

	public World getWorld()
	{
		return Minecraft.getMinecraft().theWorld;
	}

	public World getWorld(MessageContext ctx)
	{
		return Minecraft.getMinecraft().theWorld;
	}

	@Override
	public IThreadListener getIThreadListener(MessageContext messageContext)
	{
		return Minecraft.getMinecraft();
	}
}
