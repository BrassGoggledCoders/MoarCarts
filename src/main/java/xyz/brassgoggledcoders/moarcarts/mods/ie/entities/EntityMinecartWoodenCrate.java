package xyz.brassgoggledcoders.moarcarts.mods.ie.entities;

import blusunrize.immersiveengineering.client.gui.GuiCrate;
import blusunrize.immersiveengineering.common.blocks.wooden.TileEntityWoodenCrate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.boilerplate.lib.client.guis.IOpenableGUI;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartInventoryTEBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.ie.IEModule;
import xyz.brassgoggledcoders.moarcarts.mods.ie.container.ContainerMinecartWoodenCrate;

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
	public Object getClientGuiElement(int i, EntityPlayer entityPlayer, World world, BlockPos blockPos)
	{
		GuiCrate guiCrate = new GuiCrate(entityPlayer.inventory, (TileEntityWoodenCrate)this.getTileEntity());
		guiCrate.inventorySlots = new ContainerMinecartWoodenCrate(entityPlayer.inventory, this);
		return guiCrate;
	}

	@Override
	public Object getServerGuiElement(int i, EntityPlayer entityPlayer, World world, BlockPos blockPos)
	{
		return new ContainerMinecartWoodenCrate(entityPlayer.inventory, this);
	}

	@Override
	public ItemMinecartBase getItem()
	{
		return IEModule.ITEM_MINECART_WOODENCRATE;
	}

	@Override
	public boolean shouldSaveDataToItem()
	{
		return true;
	}
}
