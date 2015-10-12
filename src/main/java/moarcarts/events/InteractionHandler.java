package moarcarts.events;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;

/**
 * @author SkySom
 */
public class InteractionHandler
{
	@SubscribeEvent
	public void keepContainerOpen(PlayerOpenContainerEvent playerOpenContainerEvent)
	{
		if(playerOpenContainerEvent.entityPlayer.openContainer instanceof ContainerPlayer)
		{
			return;
		}

		if(playerOpenContainerEvent.entityPlayer.openContainer. instanceof ContainerChest)
		{
			playerOpenContainerEvent.setResult(Event.Result.ALLOW);
		}
	}
}
