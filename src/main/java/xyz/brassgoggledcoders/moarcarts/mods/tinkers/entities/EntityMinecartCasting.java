package xyz.brassgoggledcoders.moarcarts.mods.tinkers.entities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartFluidTEBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.tinkers.TinkersModule;

public class EntityMinecartCasting extends EntityMinecartFluidTEBase
{
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
	public boolean interactFirst(EntityPlayer player)
	{
		boolean activated = super.interactFirst(player);
		this.shouldUpdateBlockState = true;
		return activated;
	}

	@Override
	public ItemMinecartBase getItem()
	{
		return TinkersModule.ITEM_MINECART_CASTING;
	}
}
