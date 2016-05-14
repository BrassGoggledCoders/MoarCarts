package xyz.brassgoggledcoders.moarcarts.mods.ie.entities;

import net.minecraft.world.World;
import xyz.brassgoggledcoders.boilerplate.lib.client.guis.IOpenableGUI;

/**
 * @author SkySom
 */
public class EntityMinecartReinforcedCrate extends EntityMinecartWoodenCrate implements IOpenableGUI
{
	public EntityMinecartReinforcedCrate(World world)
	{
		super(world, 5);
	}
}
