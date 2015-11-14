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
	public PacketHandler()
	{
		INSTANCE.registerMessage(EntityTileEntityMessageHandler.class, EntityTileEntityMessage.class, 0, Side.CLIENT);
	}

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(MoarCarts.MODID);

	public void sendToAllAround(IMessage message, EntityMinecartTEBase minecartTEBase)
	{
		INSTANCE.sendToAllAround(message, new NetworkRegistry.TargetPoint(minecartTEBase.worldObj.provider
				.dimensionId, minecartTEBase.posX, minecartTEBase.posY, minecartTEBase.posZ, 64));
	}
}
