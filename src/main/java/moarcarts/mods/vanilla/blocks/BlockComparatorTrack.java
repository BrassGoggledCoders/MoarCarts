package moarcarts.mods.vanilla.blocks;

import cofh.api.energy.IEnergyHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import moarcarts.MoarCarts;
import moarcarts.api.ComparatorTrackEvent;
import moarcarts.api.IComparatorCart;
import moarcarts.utils.EnergyUtils;
import net.minecraft.block.BlockRailDetector;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidTankInfo;
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
		this.setCreativeTab(CreativeTabs.tabTransport);
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
					comparatorOutput = ((IComparatorCart) list.get(0)).getComparatorInputOverride();
				} else if(minecart instanceof IEnergyHandler)
				{
					comparatorOutput = EnergyUtils.scaleStoredEnergyTo(15, (IEnergyHandler)list.get(0));
				} else if(minecart instanceof IFluidHandler)
				{
					if(((IFluidHandler) list.get(0)).getTankInfo(ForgeDirection.UNKNOWN)[0] != null)
					{
						FluidTankInfo tankInfo = ((IFluidHandler) list.get(0)).getTankInfo(ForgeDirection.UNKNOWN)[0];
						if(tankInfo.fluid != null)
						{
							comparatorOutput = (int)(tankInfo.fluid.amount / (float)tankInfo.capacity);
						}
					}
				} else if(minecart instanceof IInventory)
				{
					comparatorOutput = Container.calcRedstoneFromInventory((IInventory)list.get(0));
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
