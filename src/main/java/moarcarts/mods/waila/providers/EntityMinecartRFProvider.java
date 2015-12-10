package moarcarts.mods.waila.providers;

import cofh.api.energy.IEnergyHandler;
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
		Entity accessorEntity = accessor.getEntity();
		if (accessorEntity instanceof EntityMinecartEnergyHandlerTEBase) {
			EntityMinecartEnergyHandlerTEBase energyHandlerTEBase = (EntityMinecartEnergyHandlerTEBase) accessorEntity;
			IEnergyHandler iEnergyHandler = energyHandlerTEBase.getIEnergyHandler();
			currenttip.add(String.format("%d / %d RF", iEnergyHandler.getEnergyStored(ForgeDirection.UNKNOWN),
					iEnergyHandler.getMaxEnergyStored(ForgeDirection.UNKNOWN)));
		}

		return currenttip;
	}
}
