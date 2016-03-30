package xyz.brassgoggledcoders.moarcarts.mods.vanilla.blocks;

import com.google.common.base.Predicate;
import net.minecraft.block.BlockRailDetector;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.IFluidHandler;
import xyz.brassgoggledcoders.boilerplate.lib.client.models.IHasModel;
import xyz.brassgoggledcoders.boilerplate.lib.common.items.IHasRecipe;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.ComparatorUtils;
import xyz.brassgoggledcoders.moarcarts.MoarCarts;
import xyz.brassgoggledcoders.moarcarts.api.ComparatorTrackEvent;
import xyz.brassgoggledcoders.moarcarts.api.IComparatorCart;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SkySom
 */
public class BlockComparatorTrack extends BlockRailDetector implements IHasModel, IHasRecipe
{
	public BlockComparatorTrack()
	{
		super();
		setResistance(3.5F);
		setHardness(1.05F);
		setUnlocalizedName("comparator_track");
		setRegistryName("comparator_track");
		this.setCreativeTab(MoarCarts.moarcartsTab);
	}

	@Override
	@SuppressWarnings("unchecked")
	public int getComparatorInputOverride(IBlockState blockState, World world, BlockPos blockPos)
	{
		int comparatorOutput = 0;
		if (blockState.getValue(POWERED))
		{
			List<EntityMinecart> list = this.findMinecarts(world, blockPos, EntityMinecart.class, new MinecartPredicate());
			if(list.size() > 0 && list.get(0) != null)
			{
				EntityMinecart minecart = list.get(0);
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
					comparatorOutput = minecart.isBeingRidden() ? 15 : 0;
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

	@Override
	public IRecipe[] getRecipes()
	{
		ArrayList<ItemStack> materials = new ArrayList<ItemStack>();
		materials.add(new ItemStack(Items.comparator));
		materials.add(new ItemStack(Blocks.detector_rail));
		return new IRecipe[]{new ShapelessRecipes(new ItemStack(this), materials)};
	}

	@Override
	public String[] getResourceLocations()
	{
		return new String[] {"comparator_track"};
	}
}

class MinecartPredicate implements Predicate<Entity>
{
	@Override
	public boolean apply(@Nullable Entity input)
	{
		return input instanceof EntityMinecart;
	}
}