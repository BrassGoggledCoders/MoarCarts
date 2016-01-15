package moarcarts.mods.vanilla.blocks;

import boilerplate.common.utils.ComparatorUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import moarcarts.MoarCarts;
import moarcarts.api.ComparatorTrackEvent;
import moarcarts.api.IComparatorCart;
import net.minecraft.block.BlockRailDetector;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.IFluidHandler;

import java.util.List;

/**
 * @author SkySom
 */
public class BlockComparatorTrack extends BlockRailDetector
{
	IIcon trackIcon;

	public BlockComparatorTrack()
	{
		super();
		this.setBlockName("blockcomparatorrail");
		this.setBlockTextureName(MoarCarts.MODID + "vanilla/blockraildetector");
		this.setCreativeTab(MoarCarts.moarcartsTab);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.trackIcon = iconRegister.registerIcon(MoarCarts.MODID + ":vanilla/blockcomparatortrack");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return this.trackIcon;
	}

	public int getComparatorInputOverride(World world, int posX, int posY, int posZ, int side)
	{
		int comparatorOutput = 0;
		if ((world.getBlockMetadata(posX, posY, posZ) & 8) > 0)
		{
			float f = 0.125F;
			List list = world.getEntitiesWithinAABB(EntityMinecart.class, AxisAlignedBB
					.getBoundingBox((double)((float)posX + f), (double)posY, (double)((float)posZ + f),
							(double)((float)(posX + 1) - f), (double)((float)(posY + 1) - f),
							(double)((float)(posZ + 1) - f)));

			if(list.size() > 0 && list.get(0) != null)
			{
				EntityMinecart minecart = (EntityMinecart)list.get(0);
				if(minecart instanceof IComparatorCart)
				{
					comparatorOutput = ((IComparatorCart)minecart).getComparatorInputOverride();
				} else if(minecart instanceof IFluidHandler)
				{
					comparatorOutput = ComparatorUtils.scaleSingleFluidLevelTo(15, (IFluidHandler)minecart);
				} else if(minecart instanceof IInventory)
				{
					comparatorOutput = Container.calcRedstoneFromInventory((IInventory)minecart);
				} else if(minecart.canBeRidden())
				{
					comparatorOutput = (minecart.riddenByEntity != null) ? 15 : 0;
				} else
				{
					ComparatorTrackEvent comparatorTrackEvent = new ComparatorTrackEvent(minecart);
					MinecraftForge.EVENT_BUS.post(comparatorTrackEvent);
					comparatorOutput = comparatorTrackEvent.getIntResult();
				}
			}
		}

		if(comparatorOutput > 15)
		{
			comparatorOutput = 15;
		}

		if(comparatorOutput < 0)
		{
			comparatorOutput = 0;
		}

		return comparatorOutput;
	}
}
