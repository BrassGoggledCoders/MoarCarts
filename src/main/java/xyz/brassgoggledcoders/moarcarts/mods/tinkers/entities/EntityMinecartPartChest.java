package xyz.brassgoggledcoders.moarcarts.mods.tinkers.entities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import slimeknights.tconstruct.tools.client.GuiPartChest;
import slimeknights.tconstruct.tools.tileentity.TilePartChest;
import xyz.brassgoggledcoders.moarcarts.mods.tinkers.containers.ContainerMinecartPartChest;

public class EntityMinecartPartChest extends EntityMinecartTinkersChest
{
	public EntityMinecartPartChest(World world)
	{
		super(world, 5);
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
