package moarcarts.proxies;

import net.minecraft.client.Minecraft;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
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
}
