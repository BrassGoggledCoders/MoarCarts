package xyz.brassgoggledcoders.moarcarts.mods.tinkers.entities;

import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartInventoryTEBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.tinkers.TinkersModule;

public class EntityMinecartPartChest extends EntityMinecartInventoryTEBase
{
	public EntityMinecartPartChest(World world)
	{
		super(world, 5);
	}

	@Override
	public ItemMinecartBase getItem()
	{
		return TinkersModule.ITEM_MINECART_TINKERS_CHEST;
	}
}
