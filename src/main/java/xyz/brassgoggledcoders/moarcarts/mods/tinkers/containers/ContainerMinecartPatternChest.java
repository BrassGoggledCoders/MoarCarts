package xyz.brassgoggledcoders.moarcarts.mods.tinkers.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import slimeknights.tconstruct.tools.inventory.ContainerPatternChest;
import slimeknights.tconstruct.tools.tileentity.TilePatternChest;
import xyz.brassgoggledcoders.moarcarts.mods.tinkers.entities.EntityMinecartPatternChest;

public class ContainerMinecartPatternChest extends ContainerPatternChest
{
	public EntityMinecartPatternChest entityMinecartPatternChest;

	public ContainerMinecartPatternChest(InventoryPlayer playerInventory, EntityMinecartPatternChest entityMinecartPatternChest)
	{
		super(playerInventory, (TilePatternChest)entityMinecartPatternChest.getTileEntity());
		this.entityMinecartPatternChest = entityMinecartPatternChest;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return entityMinecartPatternChest.isUseableByPlayer(player);
	}
}
