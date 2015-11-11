package moarcarts.mods.ie.entities;

import blusunrize.immersiveengineering.common.IEContent;
import moarcarts.entities.EntityMinecartFluidBase;
import moarcarts.mods.ie.items.ItemMinecartWoodenBarrel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class EntityMinecartWoodenBarrel extends EntityMinecartFluidBase
{
	public EntityMinecartWoodenBarrel(World world)
	{
		super(world, IEContent.blockWoodenDevice, 6, 0, "Wooden Barrel Cart");
	}

	@Override
	public ItemStack getCartItem()
	{
		return new ItemStack(new ItemMinecartWoodenBarrel());
	}

	@Override
	public boolean interactFirst(EntityPlayer entityPlayer)
	{
		this.markDirty();
		return super.interactFirst(entityPlayer);
	}

	@Override
	public RenderMethod getRenderMethod()
	{
		return RenderMethod.ISBRH;
	}
}
