package xyz.brassgoggledcoders.moarcarts.mods.tinkers.entities;

import net.minecraft.world.World;
import xyz.brassgoggledcoders.boilerplate.lib.client.guis.IOpenableGUI;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartInventoryTEBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.tinkers.TinkersModule;

public abstract class EntityMinecartTinkersChest extends EntityMinecartInventoryTEBase implements IOpenableGUI
{
	public EntityMinecartTinkersChest(World world, int metadata)
	{
		super(world, metadata);
	}

	@Override
	public ItemMinecartBase getItem()
	{
		return TinkersModule.ITEM_MINECART_TINKERS_CHEST;
	}

	@Override
	public int getMetadata()
	{
		return super.getMetadata() - 4;
	}

}
