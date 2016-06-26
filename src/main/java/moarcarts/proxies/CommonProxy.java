package moarcarts.proxies;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import moarcarts.MoarCarts;
import moarcarts.entities.EntityMinecartTEBase;
import moarcarts.fakeworld.FakePlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class CommonProxy
{
	public void init(FMLInitializationEvent event)
	{
		MoarCarts.compatibilityHandler.init(event);
	}

	public World getWorld()
	{
		return null;
	}

	public World getWorld(MessageContext ctx)
	{
		return ctx.getServerHandler().playerEntity.getEntityWorld();
	}

	public EntityPlayer getFakePlayer(EntityPlayer entityPlayer, EntityMinecartTEBase entityMinecartBase)
	{
		if(entityPlayer instanceof EntityPlayerMP)
		{
			return new FakePlayerMP((EntityPlayerMP)entityPlayer, entityMinecartBase, true);
		}
		return null;
	}

	public void resetPlayer(EntityPlayer player)
	{
		if(player instanceof EntityPlayerMP)
		{
			((EntityPlayerMP) player).theItemInWorldManager.thisPlayerMP = (EntityPlayerMP)player;
		}
	}
}
