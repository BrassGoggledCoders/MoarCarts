package moarcarts.mods.ie.entities;

import blusunrize.immersiveengineering.common.IEContent;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityMetalBarrel;
import moarcarts.entities.EntityMinecartFluidTEBase;
import moarcarts.mods.ie.IEModCompat;
import net.minecraft.item.Item;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class EntityMinecartMetalBarrel extends EntityMinecartFluidTEBase
{
	public EntityMinecartMetalBarrel(World world)
	{
		super(world, IEContent.blockMetalDevice2, 7);
		TileEntityMetalBarrel tileEntityMetalBarrel = (TileEntityMetalBarrel)this.getFluidTileEntity();
		tileEntityMetalBarrel.sideConfig[0] = 1;
	}
	
	@Override
	public RenderMethod getRenderMethod()
	{
		return RenderMethod.ISBRH;
	}

	@Override
	public Item getItem()
	{
		return IEModCompat.ITEM_MINECART_METALBARREL;
	}

	@Override
	public boolean shouldSaveDataToItem()
	{
		return true;
	}
}
