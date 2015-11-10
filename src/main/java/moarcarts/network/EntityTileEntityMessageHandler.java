package moarcarts.network;

import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import moarcarts.MoarCarts;
import moarcarts.entities.EntityMinecartTileEntityBase;
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
		MoarCarts.logger.devInfo("On message called");
		World world = getWorld(ctx);
		if(world != null)
		{
			Entity entity = world.getEntityByID(message.getEntityID());
			if(entity instanceof EntityMinecartTileEntityBase)
			{
				EntityMinecartTileEntityBase entityMinecartTileEntityBase = (EntityMinecartTileEntityBase)entity;
				entityMinecartTileEntityBase.getTileEntity().readFromNBT(message.getNbtTagCompound());
				MoarCarts.logger.devInfo("Just set NBT");
			} else
			{
				MoarCarts.logger.devInfo("Did not set NBT");
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