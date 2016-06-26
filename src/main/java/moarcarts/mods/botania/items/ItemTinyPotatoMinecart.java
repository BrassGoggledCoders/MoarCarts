package moarcarts.mods.botania.items;

import moarcarts.entities.EntityMinecartBase;
import moarcarts.items.ItemMinecartBase;
import moarcarts.mods.botania.BotaniaCompat;
import moarcarts.mods.botania.entities.EntityMinecartTinyPotato;
import moarcarts.renderers.IRenderBlock.RenderMethod;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class ItemTinyPotatoMinecart extends ItemMinecartBase
{
	public ItemTinyPotatoMinecart()
	{
		super("botania", "minecarttinypotato");
	}

	@Override
	public Block getCartBlock(ItemStack itemStack)
	{
		return BotaniaCompat.TINYPOTATO;
	}

	@Override
	public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack)
	{
		return new EntityMinecartTinyPotato(world);
	}

	@Override
	public RenderMethod getCartBlockRenderMethod(ItemStack itemStack)
	{
		return RenderMethod.TESR;
	}
}