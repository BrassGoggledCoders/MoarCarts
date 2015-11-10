package moarcarts.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import moarcarts.MoarCarts;
import moarcarts.entities.EntityMinecartTileEntityBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;

import java.io.IOException;

/**
 * @author SkySom
 */
public class EntityTileEntityMessage implements IMessage
{
	private EntityMinecartTileEntityBase entityMinecartTileEntityBase;
	private int entityID;
	private NBTTagCompound nbtTagCompound;

	public EntityTileEntityMessage() {}

	public EntityTileEntityMessage(EntityMinecartTileEntityBase minecartTileEntityBase)
	{
		this.entityMinecartTileEntityBase = minecartTileEntityBase;
		this.setEntityID(entityMinecartTileEntityBase.getEntityId());
		this.setNbtTagCompound(new NBTTagCompound());
		minecartTileEntityBase.getTileEntity().writeToNBT(getNbtTagCompound());
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		try {
			PacketBuffer packetBuffer = new PacketBuffer(buf);
			this.setEntityID(packetBuffer.readInt());
			this.setNbtTagCompound(packetBuffer.readNBTTagCompoundFromBuffer());
		} catch(IOException exception)
		{
			MoarCarts.logger.error("A packet failed to serialize.");
			exception.printStackTrace();
		}
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		try
		{
			PacketBuffer packetBuffer = new PacketBuffer(buf);
			packetBuffer.writeInt(this.getEntityID());
			packetBuffer.writeNBTTagCompoundToBuffer(this.getNbtTagCompound());
		} catch(IOException exception)
		{
			MoarCarts.logger.error("A packet failed to serialize.");
			exception.printStackTrace();
		}
	}

	public int getEntityID()
	{
		return entityID;
	}

	public void setEntityID(int entityID)
	{
		this.entityID = entityID;
	}

	public NBTTagCompound getNbtTagCompound()
	{
		return nbtTagCompound;
	}

	public void setNbtTagCompound(NBTTagCompound nbtTagCompound)
	{
		this.nbtTagCompound = nbtTagCompound;
	}
}
