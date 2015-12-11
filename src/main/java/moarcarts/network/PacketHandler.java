package moarcarts.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import moarcarts.MoarCarts;
import moarcarts.entities.EntityMinecartTEBase;

/**
 * @author SkySom
 */
public class PacketHandler
{
	private SimpleNetworkWrapper networkWrapper;

	public PacketHandler()
	{
		networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(MoarCarts.MODID);
		networkWrapper.registerMessage(EntityTileEntityUpdateMessage.Handler.class,
				EntityTileEntityUpdateMessage.class, 0, Side.CLIENT);
		networkWrapper.registerMessage(EntityTileEntityUpdateRequestMessage.Handler.class,
				EntityTileEntityUpdateRequestMessage.class, 0, Side.SERVER);
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
