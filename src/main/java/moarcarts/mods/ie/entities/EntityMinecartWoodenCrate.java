package moarcarts.mods.ie.entities;

import blusunrize.immersiveengineering.client.gui.GuiCrate;
import blusunrize.immersiveengineering.common.blocks.wooden.TileEntityWoodenCrate;
import xyz.brassgoggledcoders.boilerplate.api.IOpenableGUI;
import moarcarts.entities.EntityMinecartInventoryTEBase;
import moarcarts.items.ItemMinecartBase;
import moarcarts.mods.ie.IEModCompat;
import moarcarts.mods.ie.container.ContainerMinecartWoodenCrate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class EntityMinecartWoodenCrate extends EntityMinecartInventoryTEBase implements IOpenableGUI
{
	public EntityMinecartWoodenCrate(World world)
	{
		super(world, 4);
	}

	@Override
	public Object getClientGuiElement(int i, EntityPlayer entityPlayer, World world, int i1, int i2, int i3)
	{
		GuiCrate guiCrate = new GuiCrate(entityPlayer.inventory, (TileEntityWoodenCrate)this.getTileEntity());
		guiCrate.inventorySlots = new ContainerMinecartWoodenCrate(entityPlayer.inventory, this);
		return guiCrate;
	}

	@Override
	public Object getServerGuiElement(int i, EntityPlayer entityPlayer, World world, int i1, int i2, int i3)
	{
		return new ContainerMinecartWoodenCrate(entityPlayer.inventory, this);
	}

	@Override
	public ItemMinecartBase getItem()
	{
		return IEModCompat.ITEM_MINECART_WOODENCRATE;
	}

	@Override
	public boolean shouldSaveDataToItem()
	{
		return true;
	}
}
