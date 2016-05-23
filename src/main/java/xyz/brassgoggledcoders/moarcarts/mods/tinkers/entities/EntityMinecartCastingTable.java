package xyz.brassgoggledcoders.moarcarts.mods.tinkers.entities;

import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartFluidTEBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.tinkers.TinkersModule;

public class EntityMinecartCastingTable extends EntityMinecartFluidTEBase
{
	public EntityMinecartCastingTable(World world)
{
	super(world, 0);
}

	@Override
	public ItemMinecartBase getItem()
	{
		return TinkersModule.ITEM_MINECART_CASTING;
	}
}
