package moarcarts.proxies;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
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
