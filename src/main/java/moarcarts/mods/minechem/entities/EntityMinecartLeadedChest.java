package moarcarts.mods.minechem.entities;

import minechem.MinechemBlocksGeneration;
import moarcarts.entities.EntityMinecartInventoryTEBase;
import moarcarts.mods.minechem.items.ItemMinecartLeadedChest;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class EntityMinecartLeadedChest extends EntityMinecartInventoryTEBase
{
	public EntityMinecartLeadedChest(World world)
	{
		super(world, MinechemBlocksGeneration.leadChest, 0);
	}

	@Override
	public ItemStack getCartItem()
	{
		return new ItemStack(new ItemMinecartLeadedChest());
	}

	@Override
	public RenderMethod getRenderMethod()
	{
		return RenderMethod.TESR;
	}
}
