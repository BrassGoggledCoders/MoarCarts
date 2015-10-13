package moarcarts.events;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import moarcarts.fakeworld.FakePlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;

/**
 * @author SkySom
 */
public class InteractionHandler
{
	private EntityPlayer entityPlayer;
	private Container container;

	@SubscribeEvent
	public void keepContainerOpen(PlayerOpenContainerEvent playerOpenContainerEvent)
	{
		entityPlayer = playerOpenContainerEvent.entityPlayer;
		container = entityPlayer.openContainer;
		if(container instanceof ContainerPlayer)
		{
			return;
		}

		if(container instanceof ContainerChest)
		{
			container.canInteractWith(new FakePlayer(entityPlayer));
			playerOpenContainerEvent.setResult(Event.Result.ALLOW);
		}
	}
}
