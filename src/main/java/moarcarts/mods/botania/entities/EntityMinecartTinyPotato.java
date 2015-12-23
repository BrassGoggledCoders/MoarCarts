package moarcarts.mods.botania.entities;

import moarcarts.entities.EntityMinecartTEBase;
import moarcarts.items.ItemMinecartBase;
import moarcarts.mods.botania.BotaniaCompat;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class EntityMinecartTinyPotato extends EntityMinecartTEBase
{
	public EntityMinecartTinyPotato(World world)
	{
		super(world, 0);
	}

	@Override
	public ItemMinecartBase getItem()
	{
		return BotaniaCompat.ITEM_MINECART_TINYPOTATO;
	}

	@Override
	public boolean shouldTileUpdate()
	{
		return true;
	}

	@Override
	public void setTileEntityNBT(ItemStack itemStack)
	{
		if(itemStack != null && itemStack.hasDisplayName())
		{
			NBTTagCompound tagCompound = new NBTTagCompound();
			tagCompound.setString("name", itemStack.getDisplayName());
			this.getTileEntity().readFromNBT(tagCompound);
		}  else
		{
			super.setTileEntityNBT(itemStack);
		}
	}
}
