package moarcarts.mods.minechem.entities;

import xyz.brassgoggledcoders.boilerplate.api.IOpenableGUI;
import minechem.tileentity.leadedchest.LeadedChestGui;
import minechem.tileentity.leadedchest.LeadedChestTileEntity;
import moarcarts.entities.EntityMinecartInventoryTEBase;
import moarcarts.items.ItemMinecartBase;
import moarcarts.mods.minechem.MinechemCompat;
import moarcarts.mods.minechem.containers.ContainerMinecartLeadedChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class EntityMinecartLeadedChest extends EntityMinecartInventoryTEBase implements IOpenableGUI
{
	public EntityMinecartLeadedChest(World world)
	{
		super(world, 0);
	}

	@Override
	public ItemMinecartBase getItem()
	{
		return MinechemCompat.ITEM_MINECART_LEADEDCHEST;
	}

	@Override
	public Object getClientGuiElement(int i, EntityPlayer entityPlayer, World world, int i1, int i2, int i3)
	{
		LeadedChestGui leadedChestGui = new LeadedChestGui(entityPlayer.inventory, (LeadedChestTileEntity)this.getTileEntity());
		leadedChestGui.inventorySlots = new ContainerMinecartLeadedChest(entityPlayer.inventory, this);
		return leadedChestGui;
	}

	@Override
	public Object getServerGuiElement(int i, EntityPlayer entityPlayer, World world, int i1, int i2, int i3)
	{
		return new ContainerMinecartLeadedChest(entityPlayer.inventory, this);
	}


}
