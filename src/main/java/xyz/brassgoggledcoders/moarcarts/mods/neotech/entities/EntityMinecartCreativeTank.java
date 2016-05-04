package xyz.brassgoggledcoders.moarcarts.mods.neotech.entities;

import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.items.ItemMinecartTank;

public class EntityMinecartCreativeTank extends EntityMinecartIronTank
{
	public EntityMinecartCreativeTank(World world)
	{
		super(world, ItemMinecartTank.TankType.EMERALD);
	}

	@Override
	public int getMetadata()
	{
		return ItemMinecartTank.TankType.EMERALD.ordinal();
	}
}
