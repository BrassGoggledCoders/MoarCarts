package xyz.brassgoggledcoders.moarcarts.mods.neotech.entities;

import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartFluidTEBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.NeotechCompat;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.items.ItemMinecartTank;

public class EntityMinecartIronTank extends EntityMinecartFluidTEBase
{
	public EntityMinecartIronTank(World world, ItemMinecartTank.TankType type)
	{
		super(world, type.ordinal());
	}

	public EntityMinecartIronTank(World world)
	{
		this(world, ItemMinecartTank.TankType.IRON);
	}

	@Override
	public int getMetadata()
	{
		return ItemMinecartTank.TankType.IRON.ordinal();
	}

	@Override
	public ItemMinecartBase getItem()
	{
		return NeotechCompat.ITEM_MINECART_IRONTANK;
	}
}
