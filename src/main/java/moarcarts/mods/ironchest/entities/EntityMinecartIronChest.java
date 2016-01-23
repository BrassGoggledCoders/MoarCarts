package moarcarts.mods.ironchest.entities;

import boilerplate.api.IOpenableGUI;
import cpw.mods.ironchest.IronChestType;
import cpw.mods.ironchest.TileEntityIronChest;
import cpw.mods.ironchest.client.GUIChest;
import moarcarts.entities.EntityMinecartInventoryTEBase;
import moarcarts.items.ItemMinecartBase;
import moarcarts.mods.ironchest.IronChestCompat;
import moarcarts.mods.ironchest.containers.ContainerIronChestCart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class EntityMinecartIronChest extends EntityMinecartInventoryTEBase implements IOpenableGUI
{
	public EntityMinecartIronChest(World world)
	{
		this(world, 0);
	}

	public EntityMinecartIronChest(World world, int metadata)
	{
		super(world, metadata);
	}

	@Override
	public ItemMinecartBase getItem()
	{
		return IronChestCompat.ITEM_MINECART_IRONCHEST;
	}

	public IronChestType getIronChestType()
	{
		return IronChestType.values()[this.getMetadata()];
	}

	@Override
	public Object getClientGuiElement(int i, EntityPlayer entityPlayer, World world, int i1, int i2, int i3)
	{
		GUIChest guiChest = GUIChest.GUI.buildGUI(this.getIronChestType(), entityPlayer.inventory,
				(TileEntityIronChest)this.getTileEntity());
		guiChest.inventorySlots = new ContainerIronChestCart(entityPlayer.inventory, this);
		return guiChest;
	}

	@Override
	public Object getServerGuiElement(int i, EntityPlayer entityPlayer, World world, int i1, int i2, int i3)
	{
		return new ContainerIronChestCart(entityPlayer.inventory, this);
	}
}
