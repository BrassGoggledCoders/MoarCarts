package moarcarts.mods.vanilla.blocks;

import com.google.common.base.Predicate;
import moarcarts.MoarCarts;
import moarcarts.api.ComparatorTrackEvent;
import moarcarts.api.IComparatorCart;
import net.minecraft.block.BlockRailDetector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.IFluidHandler;
import xyz.brassgoggledcoders.boilerplate.common.utils.ComparatorUtils;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author SkySom
 */
public class BlockComparatorTrack extends BlockRailDetector
{
	public BlockComparatorTrack()
	{
		super();
		this.setRegistryName("blockcomparatorrail");
		this.setCreativeTab(MoarCarts.moarcartsTab);
	}

	@Override
	public int getComparatorInputOverride(World world, BlockPos blockPos)
	{
		int comparatorOutput = 0;
		if (world.getBlockState(blockPos).getValue(POWERED))
		{
			float f = 0.125F;
			List<? extends EntityMinecart> list = this.findMinecarts(world, blockPos, EntityMinecart.class, new Predicate<Entity>()
			{
				@Override
				public boolean apply(@Nullable Entity input)
				{
					return input instanceof EntityMinecart;
				}
			});

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
}
