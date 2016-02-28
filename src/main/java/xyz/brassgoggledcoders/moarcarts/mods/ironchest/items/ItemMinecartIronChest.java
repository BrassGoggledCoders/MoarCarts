package xyz.brassgoggledcoders.moarcarts.mods.ironchest.items;

import cpw.mods.ironchest.IronChest;
import cpw.mods.ironchest.IronChestType;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.lib.common.items.IHasRecipe;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemSubMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.ironchest.entities.*;
import xyz.brassgoggledcoders.moarcarts.renderers.IRenderBlock.RenderMethod;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class ItemMinecartIronChest extends ItemSubMinecartBase
{
	public ItemMinecartIronChest()
	{
		super("ironchest", "minecartironchest");
	}


	public IronChestType getIronChestType(ItemStack itemStack)
	{
		if(itemStack != null && itemStack.getItemDamage() < IronChestType.values().length)
		{
			return IronChestType.values()[itemStack.getItemDamage()];
		}
		return null;
	}

	@Override
	public String getUnlocalizedNameMetaExtension(int meta)
	{
		return IronChestType.values()[meta].name().toLowerCase();
	}

	@Override
	public int getNumberOfSubItems()
	{
		return 8;
	}

	@Override
	public Block getCartBlock(ItemStack itemStack)
	{
		return IronChest.ironChestBlock;
	}

	@Override
	public RenderMethod getCartBlockRenderMethod(ItemStack itemStack)
	{
		return RenderMethod.TESR;
	}

	@Override
	public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack)
	{
		EntityMinecartIronChest entityMinecartIronChest;
		switch(itemStack.getItemDamage()) {
			case 1:
				entityMinecartIronChest = new EntityMinecartGoldChest(world);
				break;
			case 2:
				entityMinecartIronChest = new EntityMinecartDiamondChest(world);
				break;
			case 3:
				entityMinecartIronChest = new EntityMinecartCopperChest(world);
				break;
			case 4:
				entityMinecartIronChest = new EntityMinecartSilverChest(world);
				break;
			case 5:
				entityMinecartIronChest = new EntityMinecartCrystalChest(world);
				break;
			case 6:
				entityMinecartIronChest = new EntityMinecartObsidianChest(world);
				break;
			case 7:
				entityMinecartIronChest = new EntityMinecartDirtChest(world);
				break;
			default:
				entityMinecartIronChest = new EntityMinecartIronChest(world, itemStack.getItemDamage());
		}
		return entityMinecartIronChest;
	}
}
