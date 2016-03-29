package xyz.brassgoggledcoders.moarcarts.mods.ironchest.containers;

import cpw.mods.ironchest.ContainerIronChest;
import xyz.brassgoggledcoders.moarcarts.mods.ironchest.entities.EntityMinecartIronChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

/**
 * @author SkySom
 */
public class ContainerIronChestCart extends ContainerIronChest
{
	private EntityMinecartIronChest minecartIronChest;
	public ContainerIronChestCart(IInventory playerInventory, EntityMinecartIronChest minecartIronChest)
	{
		super(playerInventory, (IInventory)minecartIronChest.getTileEntity(), minecartIronChest.getIronChestType(),
				ContainerSize.values()[minecartIronChest.getIronChestType().ordinal()].xSize,
				ContainerSize.values()[minecartIronChest.getIronChestType().ordinal()].ySize);
		this.minecartIronChest = minecartIronChest;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return !minecartIronChest.isDead && player.getDistanceSqToEntity(minecartIronChest) <= 64.0D;
	}

	public enum ContainerSize
	{
		IRON(184, 202),
		GOLD(184, 256),
		DIAMOND(238, 256),
		COPPER(184, 184),
		SILVER(184, 238),
		CRYSTAL(238, 256),
		OBSIDIAN(238, 256),
		DIRTCHEST9000(184, 184);

		public int xSize;
		public int ySize;

		ContainerSize(int xSize, int ySize)
		{
			this.xSize = xSize;
			this.ySize = ySize;
		}
	}
}
