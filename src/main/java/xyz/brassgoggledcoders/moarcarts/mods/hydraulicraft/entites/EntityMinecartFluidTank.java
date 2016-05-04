package xyz.brassgoggledcoders.moarcarts.mods.hydraulicraft.entites;

import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartFluidTEBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.hydraulicraft.HydraulicraftCompat;

public class EntityMinecartFluidTank extends EntityMinecartFluidTEBase
{
	public EntityMinecartFluidTank(World world)
	{
		super(world, 0);
	}

	@Override
	public ItemMinecartBase getItem()
	{
		return HydraulicraftCompat.ITEM_MINECART_FLUIDTANK;
	}

	@Override
	public boolean shouldSaveDataToItem()
	{
		return true;
	}
}
