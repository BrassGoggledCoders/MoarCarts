package xyz.brassgoggledcoders.moarcarts.mods.tinkers.entities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import slimeknights.tconstruct.tools.client.GuiPartChest;
import slimeknights.tconstruct.tools.tileentity.TilePartChest;
import xyz.brassgoggledcoders.boilerplate.lib.client.guis.IOpenableGUI;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartInventoryTEBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.tinkers.TinkersModule;
import xyz.brassgoggledcoders.moarcarts.mods.tinkers.containers.ContainerMinecartPartChest;

public class EntityMinecartPartChest extends EntityMinecartInventoryTEBase implements IOpenableGUI
{
	public EntityMinecartPartChest(World world)
	{
		super(world, 5);
	}

	@Override
	public ItemMinecartBase getItem()
	{
		return TinkersModule.ITEM_MINECART_TINKERS_CHEST;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, BlockPos blockPos)
	{
		GuiPartChest chest = new GuiPartChest(player.inventory, world, blockPos,
				(TilePartChest)this.getTileEntity());
		chest.inventorySlots = new ContainerMinecartPartChest(player.inventory, this);
		return chest;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, BlockPos blockPos)
	{
		return new ContainerMinecartPartChest(player.inventory, this);
	}
}
