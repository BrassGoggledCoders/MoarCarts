package xyz.brassgoggledcoders.moarcarts.mods.vanilla.blocks;

import com.google.common.base.Predicate;
import net.minecraft.block.BlockRailDetector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.IFluidHandler;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;
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
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
		setResistance(3.5F);
		setHardness(1.05F);
		setStepSound(soundTypeMetal);
		setUnlocalizedName("comparatortrack");
		setRegistryName("comparatortrack");
		this.setCreativeTab(MoarCarts.moarcartsTab);
	}

	@Override
	@SuppressWarnings("unchecked")
	public int getComparatorInputOverride(World world, BlockPos blockPos)
	{
		int comparatorOutput = 0;
		if (world.getBlockState(blockPos).getValue(POWERED))
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

	@Override
	public ResourceLocation[] getResourceLocations()
	{
		return new ResourceLocation[] {new ResourceLocation(BoilerplateLib.getMod().getPrefix() + "vanilla/" +
			getUnlocalizedName().substring(5))};
	}

	@Override
	public IRecipe[] getRecipes()
	{
		ArrayList<ItemStack> materials = new ArrayList<ItemStack>();
		materials.add(new ItemStack(Items.comparator));
		materials.add(new ItemStack(Blocks.detector_rail));
		return new IRecipe[]{new ShapelessRecipes(new ItemStack(this), materials)};
	}
}

class MinecartPredicate implements Predicate<Entity>
{
	@Override
	public boolean apply(@Nullable Entity input)
	{
		return false;
	}
}
