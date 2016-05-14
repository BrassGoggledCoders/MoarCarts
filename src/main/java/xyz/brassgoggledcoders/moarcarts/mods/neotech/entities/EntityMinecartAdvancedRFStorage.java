package xyz.brassgoggledcoders.moarcarts.mods.neotech.entities;

import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.items.ItemMinecartRFStorage;

public class EntityMinecartAdvancedRFStorage extends EntityMinecartBasicRFStorage
{
	public EntityMinecartAdvancedRFStorage(World world)
	{
		super(world, ItemMinecartRFStorage.StorageType.ADVANCED);
	}

	@Override
	public int getMetadata()
	{
		return ItemMinecartRFStorage.StorageType.ADVANCED.ordinal();
	}
}
