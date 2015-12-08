package moarcarts.mods.ie.entities;

import blusunrize.immersiveengineering.common.IEContent;
import blusunrize.immersiveengineering.common.blocks.IEBlockInterfaces.IBlockOverlayText;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityCapacitorLV;
import moarcarts.entities.EntityMinecartEnergyHandlerTEBase;
import moarcarts.mods.ie.IEModCompat;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
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
		this(world, IEContent.blockMetalDevice, metadata);
	}

	public EntityMinecartCapacitorLV(World world, Block block, int metadata)
	{
		super(world, block, metadata);
		TileEntityCapacitorLV tileEntityCapacitorLV = (TileEntityCapacitorLV)this.getTileEntity();
		tileEntityCapacitorLV.toggleSide(0);
		tileEntityCapacitorLV.toggleSide(1);
		tileEntityCapacitorLV.toggleSide(1);
	}

	@Override
	public ItemStack getCartItem()
	{
		return new ItemStack(this.getItem(), 1, this.getMetadata() - 1);
	}

	@Override
	public Item getItem()
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
}
