package moarcarts;

import boilerplate.api.IOpenableGUI;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class GuiHandler extends boilerplate.client.GuiHandler
{
	public GuiHandler() {
		NetworkRegistry.INSTANCE.registerGuiHandler(MoarCarts.instance, this);
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		Entity entity = world.getEntityByID(id);
		if(entity instanceof IOpenableGUI)
		{
			((IOpenableGUI) entity).getClientGuiElement(id, player, world, x, y, z);
		}
		return super.getClientGuiElement(id, player, world, x, y, z);
	}

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		Entity entity = world.getEntityByID(id);
		if(entity instanceof IOpenableGUI)
		{
			((IOpenableGUI) entity).getServerGuiElement(id, player, world, x, y, z);
		}
		return super.getServerGuiElement(id, player, world, x, y, z);
	}
}
