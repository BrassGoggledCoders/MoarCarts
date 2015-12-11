package moarcarts.proxies;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import moarcarts.MoarCarts;
import moarcarts.entities.EntityMinecartTEBase;
import moarcarts.renderers.RenderMinecartTEBase;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

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
		RenderingRegistry.registerEntityRenderingHandler(EntityMinecartTEBase.class, new RenderMinecartTEBase());
	}

	public World getWorld()
	{
		return Minecraft.getMinecraft().theWorld;
	}

	public World getWorld(MessageContext ctx)
	{
		return Minecraft.getMinecraft().theWorld;
	}
}
