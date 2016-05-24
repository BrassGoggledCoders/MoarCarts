package xyz.brassgoggledcoders.moarcarts.mods.tinkers.blocks;

import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import slimeknights.tconstruct.smeltery.block.BlockFaucet;
import xyz.brassgoggledcoders.boilerplate.lib.client.models.IHasModel;
import xyz.brassgoggledcoders.boilerplate.lib.common.blocks.IHasItemBlock;
import xyz.brassgoggledcoders.boilerplate.lib.common.blocks.IHasTileEntity;
import xyz.brassgoggledcoders.moarcarts.MoarCarts;
import xyz.brassgoggledcoders.moarcarts.mods.tinkers.tiles.TileCartFaucet;

public class BlockCartFaucet extends BlockFaucet implements IHasTileEntity, IHasModel, IHasItemBlock
{
	public BlockCartFaucet()
	{
		super();
		this.setUnlocalizedName("cart_faucet");
		this.setCreativeTab(MoarCarts.moarcartsTab);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileCartFaucet();
	}

	@Override
	public Class<? extends TileEntity> getTileEntityClass()
	{
		return TileCartFaucet.class;
	}

	@Override
	public String[] getResourceLocations()
	{
		return new String[]{"cart_faucet"};
	}

	@Override
	public Class<? extends ItemBlock> getItemBlockClass()
	{
		return ItemBlock.class;
	}
}
