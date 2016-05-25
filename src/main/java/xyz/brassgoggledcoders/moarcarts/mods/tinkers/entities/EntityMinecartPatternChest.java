package xyz.brassgoggledcoders.moarcarts.mods.tinkers.entities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import slimeknights.tconstruct.tools.client.GuiPatternChest;
import slimeknights.tconstruct.tools.tileentity.TilePatternChest;
import xyz.brassgoggledcoders.boilerplate.lib.client.guis.IOpenableGUI;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartInventoryTEBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.tinkers.TinkersModule;
import xyz.brassgoggledcoders.moarcarts.mods.tinkers.containers.ContainerMinecartPatternChest;

public class EntityMinecartPatternChest extends EntityMinecartInventoryTEBase implements IOpenableGUI
{
	public EntityMinecartPatternChest(World world)
	{
		super(world, 4);
	}

	@Override
	public ItemMinecartBase getItem()
	{
		return TinkersModule.ITEM_MINECART_TINKERS_CHEST;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, BlockPos blockPos)
	{
		GuiPatternChest chest = new GuiPatternChest(player.inventory, world, blockPos,
				(TilePatternChest)this.getTileEntity());
		chest.inventorySlots = new ContainerMinecartPatternChest(player.inventory, this);
		return chest;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, BlockPos blockPos)
	{
		return new ContainerMinecartPatternChest(player.inventory, this);
	}
}
