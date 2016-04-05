package xyz.brassgoggledcoders.moarcarts.mods.neotech.entities;

import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.items.ItemMinecartTank;

public class EntityMinecartDiamondTank extends EntityMinecartIronTank
{
	public EntityMinecartDiamondTank(World world)
	{
		super(world, ItemMinecartTank.TankType.DIAMOND);
	}

	@Override
	public int getMetadata()
	{
		return ItemMinecartTank.TankType.DIAMOND.ordinal();
	}
}
