package xyz.brassgoggledcoders.moarcarts.mods.tinkers.entities;

import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartFluidTEBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.tinkers.TinkersModule;

public class EntityMinecartCasting extends EntityMinecartFluidTEBase
{
	//TODO INVENTORY STUFF IS A BLOCK STATE THE WORLD IS A LIE. THAT IS ALL
	public EntityMinecartCasting(World world, int meta)
	{
		super(world, meta);
	}

	@Override
	public boolean shouldTileUpdate()
	{
		return true;
	}

	@Override
	public ItemMinecartBase getItem()
	{
		return TinkersModule.ITEM_MINECART_CASTING;
	}
}
