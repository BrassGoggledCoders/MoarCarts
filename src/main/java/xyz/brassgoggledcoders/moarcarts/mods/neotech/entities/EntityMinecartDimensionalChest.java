package xyz.brassgoggledcoders.moarcarts.mods.neotech.entities;

import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartCapableTEBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.NeotechModule;

public class EntityMinecartDimensionalChest extends EntityMinecartCapableTEBase
{
	public EntityMinecartDimensionalChest(World world)
	{
		super(world, 2);
	}

	@Override
	public boolean shouldTileUpdate()
	{
		return true;
	}

	@Override
	public boolean shouldAccessPlayerInventory()
	{
		return true;
	}

	@Override
	public ItemMinecartBase getItem()
	{
		return NeotechModule.ITEM_MINECART_DIMENSIONALCHEST;
	}
}
