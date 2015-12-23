package moarcarts.mods.ie.entities;

import blusunrize.immersiveengineering.common.blocks.IEBlockInterfaces.IBlockOverlayText;
import blusunrize.immersiveengineering.common.blocks.wooden.TileEntityWoodenBarrel;
import moarcarts.entities.EntityMinecartFluidTEBase;
import moarcarts.items.ItemMinecartBase;
import moarcarts.mods.ie.IEModCompat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class EntityMinecartWoodenBarrel extends EntityMinecartFluidTEBase implements IBlockOverlayText
{
	public EntityMinecartWoodenBarrel(World world)
	{
		this(world, 6);
	}

	public EntityMinecartWoodenBarrel(World world, int metadata)
	{
		super(world, metadata);
		TileEntityWoodenBarrel tileEntityWoodenBarrel = (TileEntityWoodenBarrel)this.getFluidTileEntity();
		tileEntityWoodenBarrel.sideConfig[0] = 1;
	}

	@Override
	public ItemMinecartBase getItem()
	{
		return IEModCompat.ITEM_MINECART_WOODENBARREL;
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
