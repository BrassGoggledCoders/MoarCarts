package xyz.brassgoggledcoders.moarcarts.mods.ie.entities;

import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartExplosiveBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.ie.IEModule;

public class EntityMinecartGunPowderBarrel extends EntityMinecartExplosiveBase
{
	public EntityMinecartGunPowderBarrel(World world)
	{
		super(world, 4);
	}

	@Override
	public ItemMinecartBase getItem()
	{
		return IEModule.ITEM_MINECART_GUNPOWDER_BARREL;
	}
}
