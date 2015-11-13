package moarcarts.mods.ie.entities;

import blusunrize.immersiveengineering.common.IEContent;
import boilerplate.api.IOpenableGUI;
import moarcarts.entities.EntityMinecartTileEntityBase;
import moarcarts.mods.ie.container.ContainerMinecartWoodenCrate;
import moarcarts.mods.ie.gui.GuiMinecartWoodenCrate;
import moarcarts.mods.ie.items.ItemMinecartWoodenCrate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class EntityMinecartWoodenCrate extends EntityMinecartTileEntityBase implements IOpenableGUI
{
	public EntityMinecartWoodenCrate(World world)
	{
		super(world, IEContent.blockWoodenDevice, 4, 27, "Wooden Crate Cart");
	}

	@Override
	public ItemStack getCartItem()
	{
		return new ItemStack(new ItemMinecartWoodenCrate());
	}

	@Override
	public Object getClientGuiElement(int i, EntityPlayer entityPlayer, World world, int i1, int i2, int i3)
	{
		return new GuiMinecartWoodenCrate(entityPlayer.inventory, this);
	}

	@Override
	public Object getServerGuiElement(int i, EntityPlayer entityPlayer, World world, int i1, int i2, int i3)
	{
		return new ContainerMinecartWoodenCrate(entityPlayer.inventory, this);
	}
}
