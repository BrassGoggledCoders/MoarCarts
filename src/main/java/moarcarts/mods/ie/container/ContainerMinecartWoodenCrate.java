package moarcarts.mods.ie.container;

import blusunrize.immersiveengineering.common.blocks.wooden.TileEntityWoodenCrate;
import blusunrize.immersiveengineering.common.gui.ContainerCrate;
import moarcarts.mods.ie.entities.EntityMinecartWoodenCrate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

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
		return this.entityMinecartWoodenCrate.isUseableByPlayer(entityPlayer);
	}
}
