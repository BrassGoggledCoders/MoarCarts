package xyz.brassgoggledcoders.moarcarts.mods.ie.items;

import blusunrize.immersiveengineering.common.IEContent;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemSubMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.ie.entities.EntityMinecartCapacitorCreative;
import xyz.brassgoggledcoders.moarcarts.mods.ie.entities.EntityMinecartCapacitorHV;
import xyz.brassgoggledcoders.moarcarts.mods.ie.entities.EntityMinecartCapacitorLV;
import xyz.brassgoggledcoders.moarcarts.mods.ie.entities.EntityMinecartCapacitorMV;

/**
 * @author SkySom
 */
public class ItemMinecartCapacitor extends ItemSubMinecartBase
{
	public ItemMinecartCapacitor()
	{
		super("ie", "capacitor");
	}

	@Override
	public String getUnlocalizedNameMetaExtension(int meta)
	{
		String unlocalizedName = "";
		switch(meta)
		{
			case 0:
				unlocalizedName += "lv";
				break;
			case 1:
				unlocalizedName += "mv";
				break;
			case 2:
				unlocalizedName += "hv";
				break;
			case 3:
				unlocalizedName += "creative";
				break;
			default:
				break;
		}
		return unlocalizedName;
	}

	@Override
	public int getNumberOfSubItems() {
		return 4;
	}

	@Override
	public Block getCartBlock(ItemStack itemStack)
	{
		return IEContent.blockMetalDevice0;
	}

	@Override
	public int getCartBlockMetadata(ItemStack itemStack)
	{
		return itemStack.getItemDamage();
	}

	@Override
	public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack)
	{
		EntityMinecartCapacitorLV entityMinecartCapacitor;
		switch(itemStack.getItemDamage())
		{
			case 1:
				entityMinecartCapacitor = new EntityMinecartCapacitorMV(world);
				break;
			case 2:
				entityMinecartCapacitor = new EntityMinecartCapacitorHV(world);
				break;
			case 3:
				entityMinecartCapacitor = new EntityMinecartCapacitorCreative(world);
				break;
			default:
				entityMinecartCapacitor = new EntityMinecartCapacitorLV(world);
				break;
		}
		return entityMinecartCapacitor;
	}
}
