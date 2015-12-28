package moarcarts.mods.waila.providers;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import moarcarts.entities.EntityMinecartDeepStorageTEBase;
import net.minecraft.entity.Entity;

import java.util.List;

/**
 * @author SkySom
 */
public class EntityMinecartDSUProvider extends EntityMinecartTEBaseProvider
{
	@Override
	public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor,
			IWailaConfigHandler config)
	{
		if(entity instanceof EntityMinecartDeepStorageTEBase)
		{
			EntityMinecartDeepStorageTEBase deepStorageTEBase = (EntityMinecartDeepStorageTEBase) entity;
			currenttip.add(String.format("%d stacks of %s", deepStorageTEBase.getItemQuantity(),
					deepStorageTEBase.getStoredItemType().getItem().getItemStackDisplayName(deepStorageTEBase.getStoredItemType())));
		}
		return currenttip;
	}
}
