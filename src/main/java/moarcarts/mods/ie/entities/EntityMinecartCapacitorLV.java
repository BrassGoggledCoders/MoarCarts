package moarcarts.mods.ie.entities;

import blusunrize.immersiveengineering.common.blocks.IEBlockInterfaces.IBlockOverlayText;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityCapacitorLV;
import moarcarts.entities.EntityMinecartEnergyHandlerTEBase;
import moarcarts.items.ItemMinecartBase;
import moarcarts.mods.ie.IEModCompat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class EntityMinecartCapacitorLV extends EntityMinecartEnergyHandlerTEBase implements IBlockOverlayText
{
	public EntityMinecartCapacitorLV(World world)
	{
		this(world, 1);
	}

	public EntityMinecartCapacitorLV(World world, int metadata)
	{
		super(world, metadata);
		TileEntityCapacitorLV tileEntityCapacitorLV = (TileEntityCapacitorLV)this.getTileEntity();
		tileEntityCapacitorLV.toggleSide(0);
		tileEntityCapacitorLV.toggleSide(1);
		tileEntityCapacitorLV.toggleSide(1);
	}

	@Override
	public ItemStack getCartItem()
	{
		int itemDamage = 0;
		switch(this.getMetadata())
		{
			case 1:
				itemDamage = 0;
				break;
			case 3:
				itemDamage = 1;
				break;
			case 7:
				itemDamage = 2;
				break;
			case 8:
				itemDamage = 3;
				break;
			default:
				break;
		}
		return new ItemStack(this.getItem(), 1, itemDamage);
	}

	@Override
	public ItemMinecartBase getItem()
	{
		return IEModCompat.ITEM_MINECART_CAPACITOR;
	}

	@Override
	public RenderMethod getRenderMethod()
	{
		return RenderMethod.ISBRH;
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

	@Override
	public boolean useNixieFont(EntityPlayer entityPlayer, MovingObjectPosition movingObjectPosition)
	{
		return ((IBlockOverlayText)this.getTileEntity()).useNixieFont(entityPlayer, movingObjectPosition);
	}
}
