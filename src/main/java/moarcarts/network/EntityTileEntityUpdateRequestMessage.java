package moarcarts.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
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
			if(minecartTEBase != null)
			{
				return new EntityTileEntityUpdateMessage(minecartTEBase);
			}
			return null;
		}
	}
}
