package moarcarts.mods.botania.entities;

import moarcarts.entities.EntityMinecartTEBase;
import moarcarts.items.ItemMinecartBase;
import moarcarts.mods.botania.BotaniaModCompat;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class EntityMinecartTinyPotato extends EntityMinecartTEBase
{
	public EntityMinecartTinyPotato(World world)
	{
		super(world, 0);
	}

	@Override
	public ItemMinecartBase getItem()
	{
		return BotaniaModCompat.ITEM_MINECART_TINYPOTATO;
	}

	@Override
	public boolean shouldTileUpdate()
	{
		return true;
	}

	@Override
	public boolean shouldSaveDataToItem()
	{
		return true;
	}
}
