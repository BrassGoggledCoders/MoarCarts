package moarcarts.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import moarcarts.MoarCarts;
import moarcarts.entities.EntityMinecartTEBase;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class EntityTileEntityBaseMessage implements IMessage
{
	public EntityMinecartTEBase entityMinecartTEBase;
	public int entityID;

	public EntityTileEntityBaseMessage() {}

	public EntityTileEntityBaseMessage(EntityMinecartTEBase entityMinecartTEBase)
	{
		this.entityMinecartTEBase = entityMinecartTEBase;
		this.entityID = entityMinecartTEBase.getEntityId();
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.entityID = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.entityID);
	}

	public EntityMinecartTEBase getEntityMinecartTEBaseFromMessage(MessageContext messageContext)
	{
		World world = MoarCarts.proxy.getWorld(messageContext);
		if(world != null)
		{
			Entity entity = world.getEntityByID(this.entityID);
			if(entity instanceof EntityMinecartTEBase)
			{
				return (EntityMinecartTEBase)entity;
			}
		} else
		{
			MoarCarts.logger.devInfo("The world was null");
		}

		return null;
	}
}
