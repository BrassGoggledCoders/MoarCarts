package moarcarts.proxies;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import moarcarts.MoarCarts;
import moarcarts.entities.EntityMinecartTEBase;
import moarcarts.fakeworld.FakePlayerSP;
import moarcarts.renderers.RenderMinecartTEBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class ClientProxy extends CommonProxy
{
	@Override
	public void init(FMLInitializationEvent event)
	{
		super.init(event);
		this.registerRenderers();
		MoarCarts.compatibilityHandler.clientInit(event);
	}

	private void registerRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityMinecartTEBase.class, new RenderMinecartTEBase());
	}

	@Override
	public World getWorld()
	{
		return Minecraft.getMinecraft().theWorld;
	}

	@Override
	public World getWorld(MessageContext ctx)
	{
		return Minecraft.getMinecraft().theWorld;
	}

	@Override
	public EntityPlayer getFakePlayer(EntityPlayer entityPlayer, EntityMinecartTEBase entityMinecartBase)
	{
		if(entityPlayer instanceof EntityPlayerSP)
		{
			return new FakePlayerSP((EntityPlayerSP)entityPlayer, entityMinecartBase, true);
		}
		return super.getFakePlayer(entityPlayer, entityMinecartBase);
	}
}
