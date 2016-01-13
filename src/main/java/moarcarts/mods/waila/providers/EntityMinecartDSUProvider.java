package moarcarts.mods.waila.providers;

import boilerplate.common.utils.ItemStackUtils;
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

			if(ItemStackUtils.isItemNonNull(deepStorageTEBase.getStoredItemType()))
			{
				currenttip.add(String.format("Quantity: %d", deepStorageTEBase.getItemQuantity()));
				currenttip.add(String.format("Itemstack: %s", deepStorageTEBase.getStoredItemType().getItem()
					.getItemStackDisplayName(deepStorageTEBase.getStoredItemType())));
			}

		}
		return currenttip;
	}
}
