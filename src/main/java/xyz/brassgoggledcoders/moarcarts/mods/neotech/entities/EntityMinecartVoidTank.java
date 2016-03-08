package xyz.brassgoggledcoders.moarcarts.mods.neotech.entities;

import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.items.ItemMinecartTank;

public class EntityMinecartVoidTank extends EntityMinecartIronTank
{
	public EntityMinecartVoidTank(World world)
	{
		super(world, ItemMinecartTank.TankType.VOID);
	}
}
