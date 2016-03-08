package xyz.brassgoggledcoders.moarcarts.mods.neotech.entities;

import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.items.ItemMinecartTank;

public class EntityMinecartGoldTank extends EntityMinecartIronTank
{
	public EntityMinecartGoldTank(World world)
	{
		super(world, ItemMinecartTank.TankType.GOLD);
	}
}
