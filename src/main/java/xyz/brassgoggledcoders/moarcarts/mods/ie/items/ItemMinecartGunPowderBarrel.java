package xyz.brassgoggledcoders.moarcarts.mods.ie.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartBase;
import xyz.brassgoggledcoders.moarcarts.items.ItemMinecartBase;
import xyz.brassgoggledcoders.moarcarts.mods.ie.IEModule;
import xyz.brassgoggledcoders.moarcarts.mods.ie.entities.EntityMinecartGunPowderBarrel;

public class ItemMinecartGunPowderBarrel extends ItemMinecartBase
{
	public ItemMinecartGunPowderBarrel()
	{
		super("ie", "gunpowderbarrel");
	}

	@Override
	public Block getCartBlock(ItemStack itemStack)
	{
		return IEModule.WOODEN_DEVICE;
	}

	@Override
	public int getCartBlockMetadata(ItemStack itemStack)
	{
		return 4;
	}

	@Override
	public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack)
	{
		return new EntityMinecartGunPowderBarrel(world);
	}
}
