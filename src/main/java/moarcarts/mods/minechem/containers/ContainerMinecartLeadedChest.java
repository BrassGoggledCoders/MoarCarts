package moarcarts.mods.minechem.containers;

import minechem.tileentity.leadedchest.LeadedChestContainer;
import minechem.tileentity.leadedchest.LeadedChestTileEntity;
import moarcarts.mods.minechem.entities.EntityMinecartLeadedChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

/**
 * @author SkySom
 */
public class ContainerMinecartLeadedChest extends LeadedChestContainer
{
	private EntityMinecartLeadedChest entityMinecartLeadedChest;

	public ContainerMinecartLeadedChest(InventoryPlayer inventoryPlayer, EntityMinecartLeadedChest entityMinecartLeadedChest)
	{
		super(inventoryPlayer, (LeadedChestTileEntity)entityMinecartLeadedChest.getTileEntity());
		this.entityMinecartLeadedChest = entityMinecartLeadedChest;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer)
	{
		return entityMinecartLeadedChest.isUseableByPlayer(entityPlayer);
	}
}
