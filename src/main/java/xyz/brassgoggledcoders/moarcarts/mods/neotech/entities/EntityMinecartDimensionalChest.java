package xyz.brassgoggledcoders.moarcarts.mods.neotech.entities;

import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartInventoryTEBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.NeotechCompat;

public class EntityMinecartDimensionalChest extends EntityMinecartInventoryTEBase
{
	public EntityMinecartDimensionalChest(World world)
	{
		super(world, 2);
	}

	@Override
	public ItemMinecartBase getItem()
	{
		return NeotechCompat.ITEM_MINECART_DIMENSIONALCHEST;
	}
}
