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

import moarcarts.fakeworld.CartBlockPos;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import moarcarts.MoarCarts;
import moarcarts.api.IComparatorCart;
import moarcarts.config.ConfigSettings;
import moarcarts.fakeworld.FakeWorld;
import moarcarts.items.ItemMinecartBase;
//import mods.railcraft.api.carts.IMinecart;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.minecart.MinecartInteractEvent;

import java.util.Random;

/**
 * @author SkySom
 */
@Optional.Interface(iface = "mods.railcraft.api.carts.IMinecart", modid = "RailcraftAPI|carts")
public abstract class EntityMinecartBase extends EntityMinecart implements /*IMinecart,*/ IComparatorCart
{
	protected Block cartBlock;
	protected Random random;
	protected FakeWorld fakeWorld;
	protected CartBlockPos cartBlockPos;

	protected static BlockPos ORIGIN_POS = new BlockPos(0, 0, 0);

	private static int METADATA_DW = 31;
	private static String METADATA = "METADATA";

	public EntityMinecartBase(World world, IBlockState blockState)
	{
		super(world);
		this.func_174899_a(blockState);
		this.fakeWorld = new FakeWorld(this);
		this.cartBlockPos = new CartBlockPos(this);
		this.random = new Random();
	}

	public abstract ItemMinecartBase getItem();

	public ItemStack getCartItem()
	{
		int metaData = (this.getItem().getHasSubtypes()) ? this.getMetadata() : 0;
		return new ItemStack(this.getItem(), 1, metaData);
	}

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
	public void killMinecart(DamageSource damageSource)
	{
		this.setDead();
	}

	@Override
	public EnumMinecartType getMinecartType()
	{
		return null;
	}

	@Override
	public boolean isPoweredCart()
	{
		return false;
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
	public void onUpdate()
	{
		super.onUpdate();
		if(this.shouldDoDisplayTick() && this.worldObj.isRemote && random.nextInt(10) == 0)
		{
			this.doDisplayTick();
		}
	}

	public void afterEntitySpawned()
	{

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
	public void setDead()
	{
		super.setDead();
		ItemStack blockCartItemStack;

		if(ConfigSettings.doMinecartsBreakOnDrop())
		{
			blockCartItemStack = new ItemStack(this.getCartBlock());
			ItemStack cartItemStack = new ItemStack(Items.minecart, 1);
			if(!worldObj.isRemote)
			{
				this.entityDropItem(cartItemStack, 0.1F);
			}
		} else
		{
			blockCartItemStack = this.getCartItem();
			if(this.getName() != null && !this.getName().isEmpty())
			{
				blockCartItemStack.setStackDisplayName(this.getName());
			}
		}
		this.dropCart(blockCartItemStack);
	}

	public void dropCart(ItemStack cartItem)
	{
		if(!worldObj.isRemote)
		{
			this.entityDropItem(cartItem, 0.1F);
		}
	}

	/* TODO: Railcraft
	@Override
	@Optional.Method(modid = "RailcraftAPI|carts")
	public boolean doesCartMatchFilter(ItemStack itemStack, EntityMinecart entityMinecart)
	{
		return itemStack != null && entityMinecart instanceof EntityMinecartBase &&
				itemStack.isItemEqual(entityMinecart.getCartItem());
	}
	*/
	@Override
	public int getComparatorInputOverride()
	{
		if(this.getCartBlock().hasComparatorInputOverride())
		{
			return this.getCartBlock().getComparatorInputOverride(this.getFakeWorld(), ORIGIN_POS);
		}
		return 0;
	}

	@Override
	public boolean canBeRidden()
	{
		return false;
	}

	public boolean shouldDoDisplayTick()
	{
		return false;
	}

	public void doDisplayTick()
	{
		int intPosX = (int)Math.floor(this.posX);
		int intPosY = (int)Math.floor(this.posY);
		int intPosZ = (int)Math.floor(this.posZ);
		this.getCartBlock().randomDisplayTick(this.getFakeWorld(), ORIGIN_POS, this.getDisplayTile(), random);
	}

	public Block getCartBlock()
	{
		return this.getItem().getCartBlock(this.getCartItem());
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

	public FakeWorld getFakeWorld()
	{
		if(fakeWorld == null)
		{
			fakeWorld = new FakeWorld(this);
		}
		return fakeWorld;
	}

	public void setFakeWorld(FakeWorld fakeWorld)
	{
		this.fakeWorld = fakeWorld;
	}
}
