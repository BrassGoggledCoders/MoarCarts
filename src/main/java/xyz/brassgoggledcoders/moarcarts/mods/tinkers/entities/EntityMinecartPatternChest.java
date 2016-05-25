package xyz.brassgoggledcoders.moarcarts.mods.tinkers.entities;

import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartInventoryTEBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.tinkers.TinkersModule;

public class EntityMinecartPatternChest extends EntityMinecartInventoryTEBase
{
	public EntityMinecartPatternChest(World world)
	{
		super(world, 4);
	}

	@Override
	public ItemMinecartBase getItem()
	{
		return TinkersModule.ITEM_MINECART_TINKERS_CHEST;
	}
}
