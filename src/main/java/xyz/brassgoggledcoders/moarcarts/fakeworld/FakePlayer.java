package xyz.brassgoggledcoders.moarcarts.fakeworld;

import xyz.brassgoggledcoders.moarcarts.MoarCarts;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartTEBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class FakePlayer extends EntityPlayer
{
	protected EntityPlayer entityPlayer;
	private EntityMinecartTEBase entityMinecartTEBase;
	private boolean accessInventory;

	public FakePlayer(EntityPlayer entityPlayer, EntityMinecartTEBase entityMinecartTEBase, boolean accessInventory)
	{
		super(entityPlayer.getEntityWorld(), entityPlayer.getGameProfile());
		this.accessInventory = accessInventory;
		if(this.accessInventory)
		{
			this.inventory = entityPlayer.inventory;
		}
		this.setEntityPlayer(entityPlayer);
		this.setEntityMinecartTEBase(entityMinecartTEBase);
	}

	@Override
	public void addChatMessage(IChatComponent iChatComponent)
	{
		this.getEntityPlayer().addChatComponentMessage(iChatComponent);
	}

	@Override
	public boolean canCommandSenderUseCommand(int p_70003_1_, String p_70003_2_)
	{
		return false;
	}

	@Override
	public double getDistanceSq(double x, double y, double z)
	{
		if(this.getEntityMinecartTEBase() != null) {
			return this.entityPlayer.getDistanceSq(this.getEntityMinecartTEBase().posX,
				this.getEntityMinecartTEBase().posY, this.getEntityMinecartTEBase().posZ);
		} else
		{
			return 64;
		}
	}

	@Override
	public void openGui(Object mod, int id, World world, int posX, int posY, int poxZ)
	{
		if(!getEntityPlayer().isSneaking())
		{
			this.getEntityPlayer().openGui(MoarCarts.instance, this.getEntityMinecartTEBase().getEntityId(),
					this.getEntityMinecartTEBase().worldObj, posX, posY, poxZ);
		}
	}

	@Override
	public ItemStack getEquipmentInSlot(int slot)
	{
		return this.getEntityPlayer().getEquipmentInSlot(slot);
	}

	@Override
	public ItemStack getHeldItem()
	{
		if(this.accessInventory)
		{
			return this.getEntityPlayer().getHeldItem();
		}
		return null;
	}

	@Override
	public void setCurrentItemOrArmor(int slot, ItemStack itemStack)
	{
		if(this.accessInventory)
		{
			this.getEntityPlayer().setCurrentItemOrArmor(slot, itemStack);
		}
	}

	@Override
	public boolean isSneaking()
	{
		return getEntityPlayer().isSneaking();
	}

	@Override
	public boolean isSpectator()
	{
		return false;
	}

	@Override
	public void displayGUIChest(IInventory iInventory)
	{
		this.getEntityPlayer().displayGUIChest(iInventory);
	}

	@Override
	public InventoryEnderChest getInventoryEnderChest()
	{
		return this.getEntityPlayer().getInventoryEnderChest();
	}

	public EntityPlayer getEntityPlayer()
	{
		return entityPlayer;
	}

	public void setEntityPlayer(EntityPlayer entityPlayer)
	{
		this.entityPlayer = entityPlayer;
	}

	public EntityMinecartTEBase getEntityMinecartTEBase()
	{
		return entityMinecartTEBase;
	}

	public void setEntityMinecartTEBase(EntityMinecartTEBase entityMinecartTEBase)
	{
		this.entityMinecartTEBase = entityMinecartTEBase;
	}
}
