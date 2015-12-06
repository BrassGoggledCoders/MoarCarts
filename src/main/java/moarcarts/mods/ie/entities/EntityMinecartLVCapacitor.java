package moarcarts.mods.ie.entities;

import blusunrize.immersiveengineering.common.IEContent;
import moarcarts.entities.EntityMinecartEnergyHandlerTEBase;
import moarcarts.mods.ie.IEModCompat;
import net.minecraft.item.Item;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class EntityMinecartLVCapacitor extends EntityMinecartEnergyHandlerTEBase
{
	public EntityMinecartLVCapacitor(World world)
	{
		super(world, IEContent.blockMetalDevice, 1);
	}

	@Override
	public Item getItem()
	{
		return IEModCompat.ITEM_MINECART_CAPACITOR;
	}

	@Override
	public RenderMethod getRenderMethod()
	{
		return RenderMethod.ISBRH;
	}
}
