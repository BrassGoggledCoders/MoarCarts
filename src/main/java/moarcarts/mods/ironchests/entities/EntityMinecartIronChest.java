package moarcarts.mods.ironchests.entities;

import cpw.mods.ironchest.IronChest;
import cpw.mods.ironchest.IronChestType;
import moarcarts.entities.EntityMinecartTileEntityBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class EntityMinecartIronChest extends EntityMinecartTileEntityBase
{
	protected IronChestType ironChestType;

	public EntityMinecartIronChest(World world, IronChestType ironChestType)
	{
		super(world, IronChest.ironChestBlock, ironChestType.ordinal(), ironChestType.getRowCount() *
						ironChestType.getRowLength(), "Iron chest cart");
		this.ironChestType = ironChestType;
	}

	@Override
	public ItemStack getCartItem()
	{
		return null;
	}
}
