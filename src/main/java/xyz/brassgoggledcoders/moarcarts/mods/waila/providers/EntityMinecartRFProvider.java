package xyz.brassgoggledcoders.moarcarts.mods.waila.providers;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import net.minecraft.entity.Entity;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartEnergyHandlerTEBase;

import java.util.List;

/**
 * @author SkySom
 */
public class EntityMinecartRFProvider extends EntityMinecartTEBaseProvider
{
	@Override
	public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor,
			IWailaConfigHandler config)
	{
		if (entity instanceof EntityMinecartEnergyHandlerTEBase) {
			EntityMinecartEnergyHandlerTEBase energyHandlerTEBase = (EntityMinecartEnergyHandlerTEBase) entity;
			currenttip.add(String.format("%d / %d RF", energyHandlerTEBase.getEnergyStored(null),
					energyHandlerTEBase.getMaxEnergyStored(null)));
		}

		return currenttip;
	}
}
