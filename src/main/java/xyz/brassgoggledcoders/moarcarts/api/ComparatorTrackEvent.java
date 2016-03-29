package xyz.brassgoggledcoders.moarcarts.api;

import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event.HasResult;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraftforge.event.entity.minecart.MinecartEvent;

/**
 * @author SkySom
 */
@HasResult
@Cancelable
public class ComparatorTrackEvent extends MinecartEvent
{
	private int intResult = 0;

	public ComparatorTrackEvent(EntityMinecart minecart)
	{
		super(minecart);
	}

	public void setIntResult(int result)
	{
		this.intResult = result;
	}

	public int getIntResult()
	{
		return this.intResult;
	}
}
