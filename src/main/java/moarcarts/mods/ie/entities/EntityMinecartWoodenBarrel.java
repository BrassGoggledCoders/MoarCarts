package moarcarts.mods.ie.entities;

import blusunrize.immersiveengineering.common.IEContent;
import blusunrize.immersiveengineering.common.blocks.IEBlockInterfaces.IBlockOverlayText;
import blusunrize.immersiveengineering.common.blocks.wooden.TileEntityWoodenBarrel;
import moarcarts.entities.EntityMinecartFluidTEBase;
import moarcarts.mods.ie.IEModCompat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class EntityMinecartWoodenBarrel extends EntityMinecartFluidTEBase implements IBlockOverlayText
{
	public EntityMinecartWoodenBarrel(World world)
	{
		super(world, IEContent.blockWoodenDevice, 6);
		TileEntityWoodenBarrel tileEntityWoodenBarrel = (TileEntityWoodenBarrel)this.getFluidTileEntity();
		tileEntityWoodenBarrel.sideConfig[0] = 1;

	}

	@Override
	public RenderMethod getRenderMethod()
	{
		return RenderMethod.ISBRH;
	}

	@Override
	public Item getItem()
	{
		return IEModCompat.ITEM_MINECART_WOODENBARREL;
	}

	@Override
	public boolean shouldSaveDataToItem()
	{
		return true;
	}

	@Override
	public String[] getOverlayText(EntityPlayer entityPlayer, MovingObjectPosition movingObjectPosition, boolean b)
	{
		return ((IBlockOverlayText)this.getTileEntity()).getOverlayText(entityPlayer, movingObjectPosition, b);
	}
}
