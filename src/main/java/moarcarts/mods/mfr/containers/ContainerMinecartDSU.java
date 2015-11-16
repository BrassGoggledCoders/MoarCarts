package moarcarts.mods.mfr.containers;

import moarcarts.mods.mfr.entities.EntityMinecartDSU;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import powercrystals.minefactoryreloaded.gui.container.ContainerDeepStorageUnit;
import powercrystals.minefactoryreloaded.tile.machine.TileEntityDeepStorageUnit;

/**
 * @author SkySom
 */
public class ContainerMinecartDSU extends ContainerDeepStorageUnit
{
	private EntityMinecartDSU entityMinecartDSU;

	public ContainerMinecartDSU(InventoryPlayer inventoryPlayer, EntityMinecartDSU entityMinecartDSU)
	{
		super((TileEntityDeepStorageUnit)entityMinecartDSU.getTileEntity(), inventoryPlayer);
		this.entityMinecartDSU = entityMinecartDSU;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer)
	{
		return entityPlayer.getDistance(entityMinecartDSU.posX, entityMinecartDSU.posY, entityMinecartDSU.posZ) <= 64D;
	}
}
