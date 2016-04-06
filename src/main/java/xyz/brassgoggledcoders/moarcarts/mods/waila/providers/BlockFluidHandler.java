package xyz.brassgoggledcoders.moarcarts.mods.waila.providers;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import xyz.brassgoggledcoders.moarcarts.mods.extras.ExtrasCompat;

import java.util.List;

public class BlockFluidHandler implements IWailaDataProvider
{
	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return new ItemStack(ExtrasCompat.blockLiquidHopper);
	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor,
			IWailaConfigHandler config)
	{
		return currenttip;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor,
			IWailaConfigHandler config)
	{
		TileEntity tileEntity = accessor.getTileEntity();
		if(tileEntity instanceof IFluidHandler)
		{
			IFluidHandler iFluidHandler = (IFluidHandler)tileEntity;
			if(iFluidHandler.getTankInfo(null).length > 0 &&
					iFluidHandler.getTankInfo(null)[0] != null)
			{
				FluidTankInfo fluidTankInfo = iFluidHandler.getTankInfo(null)[0];
				if(fluidTankInfo.fluid != null)
				{
					FluidStack fluid = fluidTankInfo.fluid;
					currenttip.add(String.format("%s: %d / %d mB", fluid.getLocalizedName(), fluid.amount,
							fluidTankInfo.capacity));
				}
			}
		}
		return currenttip;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor,
			IWailaConfigHandler config)
	{
		return currenttip;
	}

	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world,
			BlockPos pos)
	{
		return tag;
	}
}
