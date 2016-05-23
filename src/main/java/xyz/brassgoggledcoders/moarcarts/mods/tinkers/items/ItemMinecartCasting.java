package xyz.brassgoggledcoders.moarcarts.mods.tinkers.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.smeltery.block.BlockCasting;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemSubMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.tinkers.entities.EntityMinecartCastingBasin;
import xyz.brassgoggledcoders.moarcarts.mods.tinkers.entities.EntityMinecartCastingTable;
import xyz.brassgoggledcoders.moarcarts.renderers.IRenderBlock.RenderMethod;

public class ItemMinecartCasting extends ItemSubMinecartBase
{
	public ItemMinecartCasting()
	{
		super("tinkers", "Casting");
	}

	@Override
	public Block getCartBlock(ItemStack itemStack)
	{
		return TinkerSmeltery.castingBlock;
	}

	@Override
	public RenderMethod getCartBlockRenderMethod(ItemStack itemStack)
	{
		return RenderMethod.COMBO;
	}

	@Override
	public String getUnlocalizedNameMetaExtension(int meta)
	{
		return BlockCasting.CastingType.values()[meta].getName().toLowerCase();
	}

	@Override
	public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack)
	{
		return itemStack.getItemDamage() == 0 ? new EntityMinecartCastingTable(world) :
				new EntityMinecartCastingBasin(world);
	}

	@Override
	public int getNumberOfSubItems()
	{
		return 2;
	}
}
