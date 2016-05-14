package xyz.brassgoggledcoders.moarcarts.mods.neotech.entities;

import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.items.ItemMinecartRFStorage;

public class EntityMinecartCreativeRFStorage extends EntityMinecartBasicRFStorage
{
	public EntityMinecartCreativeRFStorage(World world)
	{
		super(world, ItemMinecartRFStorage.StorageType.CREATIVE);
	}

	@Override
	public int getMetadata()
	{
		return ItemMinecartRFStorage.StorageType.CREATIVE.ordinal();
	}
}
