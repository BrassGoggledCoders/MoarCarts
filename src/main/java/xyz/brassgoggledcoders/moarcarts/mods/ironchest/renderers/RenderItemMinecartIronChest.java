package xyz.brassgoggledcoders.moarcarts.mods.ironchest.renderers;

import net.minecraft.tileentity.TileEntity;
import xyz.brassgoggledcoders.moarcarts.renderers.RenderItemMinecartBase;

public class RenderItemMinecartIronChest extends RenderItemMinecartBase
{
	private static final RenderItemMinecartIronChest INSTANCE = new RenderItemMinecartIronChest();

	public static RenderItemMinecartIronChest getInstance()
	{
		return INSTANCE;
	}

	@Override
	public Class getTileClass()
	{
		return TileEntityDummy.class;
	}

	public static class TileEntityDummy extends TileEntity
	{}
}
