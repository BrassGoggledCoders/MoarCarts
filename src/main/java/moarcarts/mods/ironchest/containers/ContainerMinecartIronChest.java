package moarcarts.mods.ironchest.containers;

import cpw.mods.ironchest.ContainerIronChest;
import cpw.mods.ironchest.TileEntityIronChest;
import moarcarts.mods.ironchest.entities.EntityMinecartIronChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

/**
 * @author SkySom
 */
public class ContainerMinecartIronChest extends ContainerIronChest
{
	protected EntityMinecartIronChest entityMinecartIronChest;

	public ContainerMinecartIronChest(IInventory playerInventory, EntityMinecartIronChest entityMinecartIronChest)
	{
		super(playerInventory, (TileEntityIronChest)entityMinecartIronChest.getTileEntity(),
				entityMinecartIronChest.getIronChestType(), entityMinecartIronChest.getIronChestType().getRowCount(),
				entityMinecartIronChest.getIronChestType().getRowLength());
		this.entityMinecartIronChest = entityMinecartIronChest;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer)
	{
		return entityMinecartIronChest.isUseableByPlayer(entityPlayer);
	}
}
