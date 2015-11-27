/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the MoarCarts Mod for Minecraft.
 *
 * MoarCarts is open-source and is distributed under the MIT License.
 *
 * MoarCarts is based on the original ExtraCarts Mod created by ScottDTA and SkySom.
 * ExtraCarts (c) ScottDTA 2014
 * (http://forum.feed-the-beast.com/threads/1-7-10-b0-7-0-extra-carts.47925/)
 *
 */
package moarcarts.entities;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import moarcarts.MoarCarts;
import mods.railcraft.api.carts.IMinecart;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.minecart.MinecartInteractEvent;

/**
 * @author SkySom
 */
@Optional.Interface(iface = "mods.railcraft.api.carts.IMinecart", modid = "RailcraftAPI|carts")
public abstract class EntityMinecartBase extends EntityMinecart implements IMinecart
{
	protected Block cartBlock;

	private static int METADATA_DW = 31;
	private static String METADATA = "METADATA";

	public EntityMinecartBase(World world, Block block, int metadata)
	{
		super(world);
		this.setMetadata(metadata);
		this.setCartBlock(block);
	}

	public abstract ItemStack getCartItem();

	@Override
	public void entityInit(){
		super.entityInit();
		dataWatcher.addObject(METADATA_DW, 0);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbtTagCompound)
	{
		super.readEntityFromNBT(nbtTagCompound);
		this.setMetadata(nbtTagCompound.getInteger(METADATA));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbtTagCompound)
	{
		super.writeEntityToNBT(nbtTagCompound);
		nbtTagCompound.setInteger(METADATA, this.getMetadata());
	}

	@Override
	public Block func_145820_n()
	{
		return this.getCartBlock();
	}

	@Override
	public Block func_145817_o()
	{
		return this.getCartBlock();
	}

	@Override
	public int getDisplayTileData()
	{
		return this.getMetadata();
	}

	@Override
	public void killMinecart(DamageSource damageSource)
	{
		this.setDead();
	}

	@Override
	public int getMinecartType()
	{
		return 1;
	}

	@Override
	public boolean interactFirst(EntityPlayer player)
	{
		if(net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new MinecartInteractEvent(this, player)))
		{
			return true;
		}

		if (!this.worldObj.isRemote && !player.isSneaking())
		{
			FMLNetworkHandler.openGui(player, MoarCarts.instance, 2, player.worldObj, this.getEntityId(), 0, 0);
		}

		return true;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound)
	{
		super.writeToNBT(nbtTagCompound);
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	@Optional.Method(modid = "RailcraftAPI|carts")
	public boolean doesCartMatchFilter(ItemStack itemStack, EntityMinecart entityMinecart)
	{
		if(itemStack != null && entityMinecart instanceof EntityMinecartBase)
		{
			return itemStack.isItemEqual(entityMinecart.getCartItem());
		}
		return false;
	}

	public Block getCartBlock()
	{
		return cartBlock;
	}

	public void setCartBlock(Block cartBlock)
	{
		this.cartBlock = cartBlock;
	}

	public int getMetadata()
	{
		return this.dataWatcher.getWatchableObjectInt(METADATA_DW);
	}

	public void setMetadata(int metadata)
	{
		this.dataWatcher.updateObject(METADATA_DW, metadata);
	}
}
