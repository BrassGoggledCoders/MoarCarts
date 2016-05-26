package xyz.brassgoggledcoders.moarcarts.mods.tinkers.blocks;

import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.smeltery.block.BlockFaucet;
import xyz.brassgoggledcoders.boilerplate.lib.client.models.IHasModel;
import xyz.brassgoggledcoders.boilerplate.lib.common.blocks.IHasItemBlock;
import xyz.brassgoggledcoders.boilerplate.lib.common.blocks.IHasTileEntity;
import xyz.brassgoggledcoders.boilerplate.lib.common.items.IHasRecipe;
import xyz.brassgoggledcoders.moarcarts.MoarCarts;
import xyz.brassgoggledcoders.moarcarts.mods.tinkers.tiles.TileCartFaucet;

import java.util.ArrayList;
import java.util.List;

public class BlockCartFaucet extends BlockFaucet implements IHasTileEntity, IHasModel, IHasItemBlock, IHasRecipe
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

	@Override
	public IRecipe[] getRecipes()
	{
		List<ItemStack> items = new ArrayList<>();
		items.add(new ItemStack(Items.minecart));
		items.add(new ItemStack(TinkerSmeltery.faucet));
		return new IRecipe[] {new ShapelessRecipes(new ItemStack(this), items)};
	}
}
