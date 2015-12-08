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
		networkWrapper.registerMessage(EntityTileEntityMessageHandler.class, EntityTileEntityMessage.class, 0, Side.CLIENT);
	}

	public void sendToAllAround(IMessage message, EntityMinecartTEBase minecartTEBase)
	{
		networkWrapper.sendToAllAround(message, new NetworkRegistry.TargetPoint(minecartTEBase.worldObj.provider
				.dimensionId, minecartTEBase.posX, minecartTEBase.posY, minecartTEBase.posZ, 64));
	}
}
