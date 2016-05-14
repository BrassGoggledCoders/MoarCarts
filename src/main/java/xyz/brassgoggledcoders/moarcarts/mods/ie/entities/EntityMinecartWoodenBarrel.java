package xyz.brassgoggledcoders.moarcarts.mods.ie.entities;

import blusunrize.immersiveengineering.common.blocks.IEBlockInterfaces.IBlockOverlayText;
import blusunrize.immersiveengineering.common.blocks.wooden.TileEntityWoodenBarrel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartInventoryTEBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.ie.IEModule;

/**
 * @author SkySom
 */
public class EntityMinecartWoodenBarrel extends EntityMinecartInventoryTEBase implements IBlockOverlayText
{
	public EntityMinecartWoodenBarrel(World world)
	{
		this(world, 6);
	}

	public EntityMinecartWoodenBarrel(World world, int metadata)
	{
		super(world, metadata);
		TileEntityWoodenBarrel tileEntityWoodenBarrel = (TileEntityWoodenBarrel)this.getTileEntity();
		tileEntityWoodenBarrel.sideConfig[0] = 1;
	}

	@Override
	public ItemMinecartBase getItem()
	{
		return IEModule.ITEM_MINECART_WOODENBARREL;
	}

	@Override
	public boolean shouldSaveDataToItem()
	{
		return true;
	}

	@Override
	public boolean shouldAccessPlayerInventory()
	{
		return false;
	}

	@Override
	public String[] getOverlayText(EntityPlayer entityPlayer, MovingObjectPosition movingObjectPosition, boolean b)
	{
		return ((IBlockOverlayText)this.getTileEntity()).getOverlayText(entityPlayer, movingObjectPosition, b);
	}

	@Override
	public boolean useNixieFont(EntityPlayer entityPlayer, MovingObjectPosition movingObjectPosition)
	{
		return ((IBlockOverlayText)this.getTileEntity()).useNixieFont(entityPlayer, movingObjectPosition);
	}
}
