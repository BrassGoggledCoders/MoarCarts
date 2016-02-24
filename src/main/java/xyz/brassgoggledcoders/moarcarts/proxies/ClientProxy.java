package xyz.brassgoggledcoders.moarcarts.proxies;

import net.minecraft.client.Minecraft;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartTEBase;
import xyz.brassgoggledcoders.moarcarts.renderers.ModelBakeHandler;
import xyz.brassgoggledcoders.moarcarts.renderers.RenderMinecartTEBase;

/**
 * @author SkySom
 */
public class ClientProxy extends CommonProxy
{
	@Override
	public World getWorld()
	{
		return Minecraft.getMinecraft().theWorld;
	}

	@Override
	public World getWorld(MessageContext ctx)
	{
		return Minecraft.getMinecraft().theWorld;
	}

	@Override
	public IThreadListener getIThreadListener(MessageContext messageContext)
	{
		return Minecraft.getMinecraft();
	}

	@Override
	public void preInit()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityMinecartTEBase.class, RenderMinecartTEBase.Factory.INSTANCE);
	}

	@Override
	public void init()
	{
		MinecraftForge.EVENT_BUS.register(ModelBakeHandler.getInstance());
	}
}
