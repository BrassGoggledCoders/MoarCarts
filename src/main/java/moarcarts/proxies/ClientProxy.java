package moarcarts.proxies;

import moarcarts.entities.EntityMinecartTEBase;
import moarcarts.mods.ironchest.entities.EntityMinecartIronChest;
import moarcarts.renderers.RenderMinecartTEBase;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

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
	public void init()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityMinecartTEBase.class, RenderMinecartTEBase.Factory.INSTANCE);
		RenderingRegistry.registerEntityRenderingHandler(EntityMinecartIronChest.class, RenderMinecartTEBase.Factory.INSTANCE);
	}
}
