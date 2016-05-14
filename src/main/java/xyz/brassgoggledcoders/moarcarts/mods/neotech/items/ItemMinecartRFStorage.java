package xyz.brassgoggledcoders.moarcarts.mods.neotech.items;

import com.dyonovan.neotech.managers.BlockManager;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemSubMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.entities.EntityMinecartAdvancedRFStorage;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.entities.EntityMinecartBasicRFStorage;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.entities.EntityMinecartCreativeRFStorage;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.entities.EntityMinecartEliteRFStorage;

public class ItemMinecartRFStorage extends ItemSubMinecartBase
{
	public ItemMinecartRFStorage()
	{
		super("neotech", "rfstorage");
	}

	@Override
	public int getNumberOfSubItems()
	{
		return 4;
	}

	@Override
	public Block getCartBlock(ItemStack itemStack)
	{
		return StorageType.values()[itemStack.getItemDamage()].getBlock();
	}

	@Override
	public String getUnlocalizedNameMetaExtension(int meta)
	{
		return StorageType.values()[meta].toString().toLowerCase();
	}

	@Override
	public int getCartBlockMetadata(ItemStack itemStack)
	{
		return 0;
	}

	@Override
	public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack)
	{
		EntityMinecartBasicRFStorage entityMinecartBasicRFStorage;
		switch(itemStack.getItemDamage())
		{
			case 1:
				entityMinecartBasicRFStorage = new EntityMinecartAdvancedRFStorage(world);
				break;
			case 2:
				entityMinecartBasicRFStorage = new EntityMinecartEliteRFStorage(world);
				break;
			case 3:
				entityMinecartBasicRFStorage = new EntityMinecartCreativeRFStorage(world);
				break;
			default:
				entityMinecartBasicRFStorage = new EntityMinecartBasicRFStorage(world);
				break;
		}
		return entityMinecartBasicRFStorage;
	}

	public enum StorageType {
		BASIC(BlockManager.basicRFStorage()),
		ADVANCED(BlockManager.advancedRFStorage()),
		ELITE(BlockManager.eliteRFStorage()),
		CREATIVE(BlockManager.creativeRFStorage());

		private Block cartBlock;
		StorageType(Block block)
		{
			cartBlock = block;
		}

		public Block getBlock()
		{
			return cartBlock;
		}
	}
}
