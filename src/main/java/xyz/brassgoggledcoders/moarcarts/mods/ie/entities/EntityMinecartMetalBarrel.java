package xyz.brassgoggledcoders.moarcarts.mods.ie.entities;

import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.items.ItemMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.ie.IEModule;

/**
 * @author SkySom
 */
public class EntityMinecartMetalBarrel extends EntityMinecartWoodenBarrel
{
	public EntityMinecartMetalBarrel(World world)
	{
		super(world, 4);
	}

	@Override
	public ItemMinecartBase getItem()
	{
		return IEModule.ITEM_MINECART_METALBARREL;
	}

}
