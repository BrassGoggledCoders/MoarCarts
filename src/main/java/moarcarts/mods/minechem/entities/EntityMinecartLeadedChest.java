package moarcarts.mods.minechem.entities;

import boilerplate.api.IOpenableGUI;
import minechem.MinechemBlocksGeneration;
import minechem.tileentity.leadedchest.LeadedChestGui;
import minechem.tileentity.leadedchest.LeadedChestTileEntity;
import moarcarts.entities.EntityMinecartInventoryTEBase;
import moarcarts.mods.minechem.containers.ContainerMinecartLeadedChest;
import moarcarts.mods.minechem.items.ItemMinecartLeadedChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class EntityMinecartLeadedChest extends EntityMinecartInventoryTEBase implements IOpenableGUI
{
	public EntityMinecartLeadedChest(World world)
	{
		super(world, MinechemBlocksGeneration.leadChest, 0);
	}

	@Override
	public ItemStack getCartItem()
	{
		return new ItemStack(new ItemMinecartLeadedChest());
	}

	@Override
	public RenderMethod getRenderMethod()
	{
		return RenderMethod.TESR;
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
