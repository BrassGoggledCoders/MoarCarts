package moarcarts.mods.waila.providers;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import moarcarts.entities.EntityMinecartEnergyHandlerTEBase;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.util.ForgeDirection;

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
			currenttip.add(String.format("%d / %d RF", energyHandlerTEBase.getEnergyStored(ForgeDirection.UNKNOWN),
					energyHandlerTEBase.getMaxEnergyStored(ForgeDirection.UNKNOWN)));
		}

		return currenttip;
	}
}
