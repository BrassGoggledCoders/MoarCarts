package xyz.brassgoggledcoders.moarcarts.mods.neotech.entity;

import com.dyonovan.neotech.client.gui.storage.GuiFlushableChest;
import com.dyonovan.neotech.common.tiles.storage.TileFlushableChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.boilerplate.lib.client.guis.IOpenableGUI;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartInventoryTEBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.NeotechCompat;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.containers.ContainerMinecartFlushableChest;

public class EntityMinecartFlushableChest extends EntityMinecartInventoryTEBase implements IOpenableGUI
{
	public EntityMinecartFlushableChest(World world)
	{
		super(world, 2);
	}

	@Override
	public ItemMinecartBase getItem()
	{
		return NeotechCompat.ITEM_MINECART_FLUSHABLECHEST;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, BlockPos blockPos)
	{
		GuiContainer gui = new GuiFlushableChest(player, (TileFlushableChest)this.getTileEntity());
		gui.inventorySlots = new ContainerMinecartFlushableChest(player.inventory, this);
		return gui;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, BlockPos blockPos)
	{
		return new ContainerMinecartFlushableChest(player.inventory, this);
	}
}
