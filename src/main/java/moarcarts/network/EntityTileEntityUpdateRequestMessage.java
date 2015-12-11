package moarcarts.network;

import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import moarcarts.MoarCarts;
import moarcarts.entities.EntityMinecartTEBase;

/**
 * @author SkySom
 */
public class EntityTileEntityUpdateRequestMessage extends EntityTileEntityBaseMessage
{
	public EntityTileEntityUpdateRequestMessage() { }

	public EntityTileEntityUpdateRequestMessage(EntityMinecartTEBase entityMinecartTEBase)
	{
		super(entityMinecartTEBase);
	}

	public static class Handler implements IMessageHandler<EntityTileEntityUpdateRequestMessage,
			EntityTileEntityUpdateMessage>
	{
		@Override
		public EntityTileEntityUpdateMessage onMessage(EntityTileEntityUpdateRequestMessage message,
				MessageContext ctx)
		{
			EntityMinecartTEBase minecartTEBase = message.getEntityMinecartTEBaseFromMessage(ctx);
			MoarCarts.logger.devInfo(minecartTEBase.toString());
			if(minecartTEBase != null)
			{
				return new EntityTileEntityUpdateMessage(minecartTEBase);
			}
			return null;
		}
	}
}
