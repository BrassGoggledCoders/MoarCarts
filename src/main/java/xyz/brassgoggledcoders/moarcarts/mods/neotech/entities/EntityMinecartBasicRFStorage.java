package xyz.brassgoggledcoders.moarcarts.mods.neotech.entities;

import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartEnergyHandlerTEBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.NeotechModule;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.items.ItemMinecartRFStorage;

public class EntityMinecartBasicRFStorage extends EntityMinecartEnergyHandlerTEBase
{
	public EntityMinecartBasicRFStorage(World world)
	{
		super(world, 0);
	}

	public EntityMinecartBasicRFStorage(World world, ItemMinecartRFStorage.StorageType type)
	{
		super(world, type.ordinal());
	}

	@Override
	public int getMetadata()
	{
		return ItemMinecartRFStorage.StorageType.BASIC.ordinal();
	}

	@Override
	public boolean shouldSaveDataToItem()
	{
		return true;
	}

	@Override
	public ItemMinecartBase getItem()
	{
		return NeotechModule.ITEM_MINECART_RFSTORAGE;
	}
}
