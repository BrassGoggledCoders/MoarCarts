package moarcarts.mods.ironchest.entities;

import boilerplate.api.IOpenableGUI;
import cpw.mods.ironchest.IronChest;
import cpw.mods.ironchest.IronChestType;
import cpw.mods.ironchest.client.GUIChest;
import moarcarts.entities.EntityMinecartInventoryTEBase;
import moarcarts.mods.ironchest.IronChestCompat;
import moarcarts.mods.ironchest.containers.ContainerMinecartIronChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import java.lang.reflect.Constructor;

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
		super(world, IronChest.ironChestBlock, metadata);
	}

	@Override
	public Item getItem()
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
		try {
			Constructor<GUIChest> constructor = GUIChest.class.getDeclaredConstructor(GUIChest.GUI.class,
					IInventory.class, IInventory.class);
			constructor.setAccessible(true);
			return constructor.newInstance(GUIChest.GUI.values()[this.getMetadata()], entityPlayer.inventory, this);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Object getServerGuiElement(int i, EntityPlayer entityPlayer, World world, int i1, int i2, int i3)
	{
		return new ContainerMinecartIronChest(entityPlayer.inventory, this);
	}
}
