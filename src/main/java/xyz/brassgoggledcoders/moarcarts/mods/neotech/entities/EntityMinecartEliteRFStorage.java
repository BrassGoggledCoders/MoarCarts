package xyz.brassgoggledcoders.moarcarts.mods.neotech.entities;

import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.items.ItemMinecartRFStorage;

public class EntityMinecartEliteRFStorage extends EntityMinecartBasicRFStorage
{
	public EntityMinecartEliteRFStorage(World world)
	{
		super(world, ItemMinecartRFStorage.StorageType.ELITE);
	}

	@Override
	public int getMetadata()
	{
		return ItemMinecartRFStorage.StorageType.ELITE.ordinal();
	}
}

