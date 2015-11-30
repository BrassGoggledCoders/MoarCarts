package moarcarts.mods.ironchest.entities;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class EntityMinecartDirtChest extends EntityMinecartIronChest
{
	public EntityMinecartDirtChest(World world)
	{
		super(world, 7);
	}

	@Override
	public boolean canAcceptPushedItem(EntityMinecart entityMinecart, ItemStack itemStack)
	{
		return itemStack != null && itemStack.getItem() == new ItemStack(Blocks.dirt).getItem();
	}
}
