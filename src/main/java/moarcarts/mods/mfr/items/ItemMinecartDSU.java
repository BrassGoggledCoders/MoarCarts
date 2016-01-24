package moarcarts.mods.mfr.items;

import net.minecraftforge.fml.common.registry.GameRegistry;
import moarcarts.entities.EntityMinecartBase;
import moarcarts.items.ItemMinecartBase;
import moarcarts.mods.mfr.MFRCompat;
import moarcarts.mods.mfr.entities.EntityMinecartDSU;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class ItemMinecartDSU extends ItemMinecartBase
{
	public static Block dsu = GameRegistry.findBlock("MineFactoryReloaded", "machine.1");

	public ItemMinecartDSU()
	{
		super("mfr", "minecartdsu");
	}

	@Override
	public Block getCartBlock(ItemStack itemStack)
	{
		return MFRCompat.DSU;
	}

	@Override
	public int getCartBlockMetadata(ItemStack itemStack)
	{
		return 3;
	}

	@Override
	public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack)
	{
		return new EntityMinecartDSU(world);
	}
}
