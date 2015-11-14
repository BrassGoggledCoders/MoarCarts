package moarcarts.network;

import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import moarcarts.MoarCarts;
import moarcarts.entities.EntityMinecartTEBase;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class EntityTileEntityMessageHandler implements IMessageHandler<EntityTileEntityMessage, EntityTileEntityMessage>
{
	@Override
	public EntityTileEntityMessage onMessage(EntityTileEntityMessage message, MessageContext ctx)
	{
		World world = getWorld(ctx);
		if(world != null)
		{
			Entity entity = world.getEntityByID(message.getEntityID());
			if(entity instanceof EntityMinecartTEBase)
			{
				EntityMinecartTEBase entityMinecartTileEntityBase = (EntityMinecartTEBase)entity;
				entityMinecartTileEntityBase.getTileEntity().readFromNBT(message.getNbtTagCompound());
			}
		} else
		{
			MoarCarts.logger.devInfo("The world was null");
		}

		return null;
	}

	public World getWorld(MessageContext ctx)
	{
		if(ctx.side == Side.SERVER) {
			return ctx.getServerHandler().playerEntity.worldObj;
		} else {
			return Minecraft.getMinecraft().theWorld;
		}
	}
}
