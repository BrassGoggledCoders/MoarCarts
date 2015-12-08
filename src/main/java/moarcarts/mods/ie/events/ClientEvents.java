package moarcarts.mods.ie.events;

import blusunrize.immersiveengineering.client.ClientUtils;
import blusunrize.immersiveengineering.common.blocks.IEBlockInterfaces.IBlockOverlayText;
import blusunrize.immersiveengineering.common.util.Utils;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

/**
 * @author SkySom
 */
public class ClientEvents
{
	@SubscribeEvent
	public void renderOverLay(RenderGameOverlayEvent.Post event)
	{
		if(ClientUtils.mc().thePlayer!=null && event.type == RenderGameOverlayEvent.ElementType.TEXT)
		{
			EntityPlayer player = ClientUtils.mc().thePlayer;
			if(player.getCurrentEquippedItem()!=null)
			{
				ItemStack equipped = player.getCurrentEquippedItem();
				boolean hammer = Utils.isHammer(equipped);
				MovingObjectPosition mop = ClientUtils.mc().objectMouseOver;
				if(mop!=null)
				{
					Entity entity = mop.entityHit;
					if(entity instanceof IBlockOverlayText)
					{
						IBlockOverlayText overlayBlock = (IBlockOverlayText)entity;
						String[] text = overlayBlock.getOverlayText(ClientUtils.mc().thePlayer, mop, hammer);
						if(text != null && text.length > 0)
						{
							int i = 0;
							for(String s : text)
								if(s != null)
									ClientUtils.font().drawString(s, event.resolution.getScaledWidth() / 2 + 8,
									event.resolution.getScaledHeight() / 2 + 8 + (i++) * ClientUtils.font().FONT_HEIGHT,
									0xcccccc, true);
						}
					}
				}
			}
		}
	}
}
