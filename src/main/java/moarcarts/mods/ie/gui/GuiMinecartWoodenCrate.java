package moarcarts.mods.ie.gui;

import blusunrize.immersiveengineering.client.gui.GuiCrate;
import blusunrize.immersiveengineering.common.blocks.wooden.TileEntityWoodenCrate;
import moarcarts.mods.ie.container.ContainerMinecartWoodenCrate;
import moarcarts.mods.ie.entities.EntityMinecartWoodenCrate;
import net.minecraft.entity.player.InventoryPlayer;

/**
 * @author SkySom
 */
public class GuiMinecartWoodenCrate extends GuiCrate
{
	public GuiMinecartWoodenCrate(InventoryPlayer inventoryPlayer, EntityMinecartWoodenCrate entityMinecartWoodenCrate)
	{
		super(inventoryPlayer, (TileEntityWoodenCrate)entityMinecartWoodenCrate.getTileEntity());
		this.inventorySlots = new ContainerMinecartWoodenCrate(inventoryPlayer, entityMinecartWoodenCrate);
	}
}
