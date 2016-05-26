package xyz.brassgoggledcoders.moarcarts.mods.tinkers.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import slimeknights.tconstruct.tools.TinkerTools;
import slimeknights.tconstruct.tools.block.BlockToolTable;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemSubMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.tinkers.entities.EntityMinecartPartChest;
import xyz.brassgoggledcoders.moarcarts.mods.tinkers.entities.EntityMinecartPatternChest;

public class ItemMinecartTinkersChest extends ItemSubMinecartBase
{
	public ItemMinecartTinkersChest()
	{
		super("tinkers", "tinkerschest");
	}

	@Override
	public Block getCartBlock(ItemStack itemStack)
	{
		return TinkerTools.toolTables;
	}

	@Override
	public int getCartBlockMetadata(ItemStack itemStack)
	{
		return itemStack.getItemDamage() + 4;
	}

	@Override
	public String getUnlocalizedNameMetaExtension(int meta)
	{
		return BlockToolTable.TableTypes.fromMeta(meta + 4).toString().toLowerCase();
	}

	@Override
	public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack)
	{
		return itemStack.getItemDamage() == 0 ? new EntityMinecartPatternChest(world) :
				new EntityMinecartPartChest(world);
	}

	@Override
	public int getNumberOfSubItems()
	{
		return 2;
	}
}
