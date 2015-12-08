package moarcarts.mods.minechem.items;

import minechem.MinechemBlocksGeneration;
import moarcarts.entities.EntityMinecartBase;
import moarcarts.items.ItemMinecartBase;
import moarcarts.mods.minechem.entities.EntityMinecartLeadedChest;
import moarcarts.renderers.IRenderBlock.RenderMethod;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class ItemMinecartLeadedChest extends ItemMinecartBase
{
	public ItemMinecartLeadedChest()
	{
		super("minechem", "minecartleadedchest");
	}

	@Override
	public Block getCartBlock(ItemStack itemStack)
	{
		return MinechemBlocksGeneration.leadChest;
	}

	@Override
	public RenderMethod getCartBlockRenderMethod(ItemStack itemStack)
	{
		return RenderMethod.TESR;
	}

	@Override
	public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack)
	{
		return new EntityMinecartLeadedChest(world);
	}
}
