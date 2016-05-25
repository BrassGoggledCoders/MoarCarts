package xyz.brassgoggledcoders.moarcarts.mods.tinkers.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import slimeknights.tconstruct.tools.inventory.ContainerPartChest;
import slimeknights.tconstruct.tools.tileentity.TilePartChest;
import xyz.brassgoggledcoders.moarcarts.mods.tinkers.entities.EntityMinecartPartChest;

public class ContainerMinecartPartChest extends ContainerPartChest
{
	protected EntityMinecartPartChest entityMinecartPartChest;

	public ContainerMinecartPartChest(InventoryPlayer inventoryPlayer, EntityMinecartPartChest entityMinecartPartChest)
	{
		super(inventoryPlayer, (TilePartChest) entityMinecartPartChest.getTileEntity());
		this.entityMinecartPartChest = entityMinecartPartChest;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer)
	{
		return entityMinecartPartChest.isUseableByPlayer(entityPlayer);
	}
}
