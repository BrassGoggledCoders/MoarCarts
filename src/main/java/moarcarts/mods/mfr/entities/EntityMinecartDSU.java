package moarcarts.mods.mfr.entities;

import boilerplate.api.IOpenableGUI;
import cpw.mods.fml.common.registry.GameRegistry;
import moarcarts.entities.EntityMinecartDeepStorageTEBase;
import moarcarts.mods.mfr.containers.ContainerMinecartDSU;
import moarcarts.mods.mfr.items.ItemMinecartDSU;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.gui.client.GuiDeepStorageUnit;
import powercrystals.minefactoryreloaded.tile.machine.TileEntityDeepStorageUnit;

/**
 * @author SkySom
 */
public class EntityMinecartDSU extends EntityMinecartDeepStorageTEBase implements IOpenableGUI
{
	public static Block dsu = GameRegistry.findBlock("MineFactoryReloaded", "machine.1");

	public EntityMinecartDSU(World world)
	{
		super(world, dsu, 3);
	}

	@Override
	public ItemStack getCartItem()
	{
		return new ItemStack(new ItemMinecartDSU());
	}

	@Override
	public Object getClientGuiElement(int i, EntityPlayer entityPlayer, World world, int i1, int i2, int i3)
	{
		ContainerMinecartDSU containerMinecartDSU = new ContainerMinecartDSU(entityPlayer.inventory, this);
		return new GuiDeepStorageUnit(containerMinecartDSU, (TileEntityDeepStorageUnit)this.getTileEntity());
	}

	@Override
	public Object getServerGuiElement(int i, EntityPlayer entityPlayer, World world, int i1, int i2, int i3)
	{
		return new ContainerMinecartDSU(entityPlayer.inventory, this);
	}
}