package moarcarts.mods.avaritia.entities;

import moarcarts.items.ItemMinecartBase;
import moarcarts.mods.avaritia.AvaritiaCompat;
import moarcarts.mods.botania.entities.EntityMinecartTinyPotato;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class EntityMinecartInfinitato extends EntityMinecartTinyPotato
{
	public EntityMinecartInfinitato(World world)
	{
		super(world);
	}

	@Override
	public ItemMinecartBase getItem()
	{
		return AvaritiaCompat.ITEM_MINECART_INFINITATO;
	}
}
