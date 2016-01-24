package moarcarts.network;

import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import moarcarts.entities.EntityMinecartTEBase;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author SkySom
 */
public class EntityTileEntityUpdateMessage extends EntityTileEntityBaseMessage
{
	public NBTTagCompound nbtTagCompound;

	public EntityTileEntityUpdateMessage() {}

	public EntityTileEntityUpdateMessage(EntityMinecartTEBase entityMinecartTEBase)
	{
		super(entityMinecartTEBase);
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		entityMinecartTEBase.getTileEntity().writeToNBT(nbtTagCompound);
		this.nbtTagCompound = nbtTagCompound;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		super.fromBytes(buf);
		this.nbtTagCompound = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		super.toBytes(buf);
		ByteBufUtils.writeTag(buf, this.nbtTagCompound);
	}

	public static class Handler implements IMessageHandler<EntityTileEntityUpdateMessage, IMessage>
	{
		@Override
		public IMessage onMessage(EntityTileEntityUpdateMessage message, MessageContext ctx)
		{
			EntityMinecartTEBase minecartTEBase = message.getEntityMinecartTEBaseFromMessage(ctx);

			if(minecartTEBase != null)
			{
				minecartTEBase.getTileEntity().readFromNBT(message.nbtTagCompound);
			}
			return null;
		}
	}
}
