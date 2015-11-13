package moarcarts.mods.ironchest.entities;

import boilerplate.api.IOpenableGUI;
import cpw.mods.ironchest.ContainerIronChest;
import cpw.mods.ironchest.IronChest;
import cpw.mods.ironchest.IronChestType;
import cpw.mods.ironchest.TileEntityIronChest;
import cpw.mods.ironchest.client.GUIChest;
import moarcarts.entities.EntityMinecartTileEntityBase;
import moarcarts.mods.ironchest.items.ItemMinecartIronChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class EntityMinecartIronChest extends EntityMinecartTileEntityBase implements IOpenableGUI
{
	public EntityMinecartIronChest(World world)
	{
		this(world, IronChestType.IRON);
	}

	public EntityMinecartIronChest(World world, IronChestType ironChestType)
	{
		super(world, IronChest.ironChestBlock, ironChestType.ordinal(), ironChestType.getRowCount() *
						ironChestType.getRowLength(), "Iron chest cart");
	}

	@Override
	public ItemStack getCartItem()
	{
		return new ItemStack(new ItemMinecartIronChest());
	}

	public IronChestType getIronChestType()
	{
		return IronChestType.values()[this.getMetadata()];
	}

	@Override
	public Object getClientGuiElement(int i, EntityPlayer entityPlayer, World world, int i1, int i2, int i3)
	{
		return GUIChest.GUI.buildGUI(this.getIronChestType(), entityPlayer.inventory, (TileEntityIronChest)this.getTileEntity());
	}

	@Override
	public Object getServerGuiElement(int i, EntityPlayer entityPlayer, World world, int i1, int i2, int i3)
	{
		return this.createIronChestContainer(entityPlayer.inventory);
	}

	private ContainerIronChest createIronChestContainer(InventoryPlayer inventoryPlayer)
	{
		ContainerIronChest containerIronChest = new ContainerIronChest(inventoryPlayer, this,
				this.getIronChestType(), this.getIronChestType().getRowLength(), this.getIronChestType().getRowCount());
		return containerIronChest;
	}
}
