package moarcarts.mods.ie.entities;

import blusunrize.immersiveengineering.common.IEContent;
import blusunrize.immersiveengineering.common.blocks.wooden.TileEntityWoodenBarrel;
import moarcarts.entities.EntityMinecartFluidTEBase;
import moarcarts.mods.ie.IEModCompat;
import net.minecraft.item.Item;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class EntityMinecartWoodenBarrel extends EntityMinecartFluidTEBase
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
}
