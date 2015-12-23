package moarcarts.mods.avaritia.items;

import moarcarts.entities.EntityMinecartBase;
import moarcarts.items.ItemMinecartBase;
import moarcarts.mods.avaritia.AvaritiaCompat;
import moarcarts.mods.avaritia.entities.EntityMinecartInfinitato;
import moarcarts.renderers.IRenderBlock.RenderMethod;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class ItemMinecartInfinitato extends ItemMinecartBase
{
	public ItemMinecartInfinitato()
	{
		super("avaritia", "minecartinfinitato");
	}

	@Override
	public Block getCartBlock(ItemStack itemStack)
	{
		return AvaritiaCompat.INFINITATO;
	}

	@Override
	public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack)
	{
		return new EntityMinecartInfinitato(world);
	}

	@Override
	public RenderMethod getCartBlockRenderMethod(ItemStack itemStack)
	{
		return RenderMethod.TESR;
	}
}
