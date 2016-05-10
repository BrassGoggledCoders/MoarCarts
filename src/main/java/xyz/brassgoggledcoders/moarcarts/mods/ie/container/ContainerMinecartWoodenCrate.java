package xyz.brassgoggledcoders.moarcarts.mods.ie.container;

import blusunrize.immersiveengineering.common.blocks.wooden.TileEntityWoodenCrate;
import blusunrize.immersiveengineering.common.gui.ContainerCrate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import xyz.brassgoggledcoders.moarcarts.mods.ie.entities.EntityMinecartWoodenCrate;

/**
 * @author SkySom
 */
public class ContainerMinecartWoodenCrate extends ContainerCrate
{
	protected EntityMinecartWoodenCrate entityMinecartWoodenCrate;

	public ContainerMinecartWoodenCrate(InventoryPlayer inventoryPlayer, EntityMinecartWoodenCrate entityMinecartWoodenCrate)
	{
		super(inventoryPlayer, (TileEntityWoodenCrate)entityMinecartWoodenCrate.getTileEntity());
		this.entityMinecartWoodenCrate = entityMinecartWoodenCrate;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer)
	{
		return !entityMinecartWoodenCrate.isDead && entityPlayer.getDistanceSqToEntity(entityMinecartWoodenCrate) <= 64.0D;
	}
}
