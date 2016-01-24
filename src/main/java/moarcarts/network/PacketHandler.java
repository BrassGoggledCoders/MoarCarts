package moarcarts.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import moarcarts.MoarCarts;
import moarcarts.entities.EntityMinecartTEBase;

/**
 * @author SkySom
 */
public class PacketHandler
{
	private SimpleNetworkWrapper networkWrapper;
	private int id = -1;
	public PacketHandler()
	{
		networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(MoarCarts.MODID);
		networkWrapper.registerMessage(EntityTileEntityUpdateMessage.Handler.class,
				EntityTileEntityUpdateMessage.class, ++id, Side.CLIENT);
		networkWrapper.registerMessage(EntityTileEntityUpdateRequestMessage.Handler.class,
				EntityTileEntityUpdateRequestMessage.class, ++id, Side.SERVER);
	}

	public void sendToAllAround(IMessage message, EntityMinecartTEBase minecartTEBase)
	{
		networkWrapper.sendToAllAround(message, new NetworkRegistry.TargetPoint(minecartTEBase.worldObj.provider
				.dimensionId, minecartTEBase.posX, minecartTEBase.posY, minecartTEBase.posZ, 64));
	}

	public void sendToServer(IMessage message)
	{
		networkWrapper.sendToServer(message);
	}
}
