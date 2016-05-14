package xyz.brassgoggledcoders.moarcarts.mods.waila.providers;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import xyz.brassgoggledcoders.boilerplate.lib.common.tileentities.TileEntitySidedBase;

import java.util.List;

public class BlockProviderSided extends BlockProviderBase
{
	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor,
			IWailaConfigHandler config)
	{
		TileEntity tileEntity = accessor.getTileEntity();
		if(tileEntity instanceof TileEntitySidedBase)
		{
			TileEntitySidedBase tileEntitySidedBase = (TileEntitySidedBase)tileEntity;
			EnumFacing facing = accessor.getSide();
			currenttip.add("Side: " + facing.toString());
			currenttip.add("Connection Type: " + tileEntitySidedBase.getSideValue(facing.ordinal()));
		}
		return currenttip;
	}
}
