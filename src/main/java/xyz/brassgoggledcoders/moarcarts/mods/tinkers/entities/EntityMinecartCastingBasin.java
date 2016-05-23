package xyz.brassgoggledcoders.moarcarts.mods.tinkers.entities;

import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartFluidTEBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.tinkers.TinkersModule;

public class EntityMinecartCastingBasin extends EntityMinecartFluidTEBase
{
	public EntityMinecartCastingBasin(World world)
	{
		super(world, 1);
	}

	@Override
	public ItemMinecartBase getItem()
	{
		return TinkersModule.ITEM_MINECART_CASTING;
	}
}
