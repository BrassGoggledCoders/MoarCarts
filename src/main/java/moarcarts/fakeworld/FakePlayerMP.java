package moarcarts.fakeworld;

import moarcarts.MoarCarts;
import moarcarts.entities.EntityMinecartTEBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class FakePlayerMP extends EntityPlayerMP
{
	protected EntityPlayerMP entityPlayer;
	private EntityMinecartTEBase entityMinecartTEBase;
	private boolean accessInventory;

	public FakePlayerMP(EntityPlayerMP entityPlayer, EntityMinecartTEBase entityMinecartTEBase, boolean accessInventory)
	{
		super(entityPlayer.mcServer, entityPlayer.getServerForPlayer(), entityPlayer.getGameProfile(),
				entityPlayer.theItemInWorldManager);
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
	public ChunkCoordinates getPlayerCoordinates()
	{
		return null;
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
		this.getEntityPlayer().openGui(MoarCarts.instance, this.getEntityMinecartTEBase().getEntityId(),
				this.getEntityMinecartTEBase().worldObj, posX, posY, poxZ);
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
	public void displayGUIChest(IInventory iInventory)
	{
		this.getEntityPlayer().displayGUIChest(iInventory);
	}

	@Override
	public InventoryEnderChest getInventoryEnderChest()
	{
		return this.getEntityPlayer().getInventoryEnderChest();
	}

	@Override
	public String getCommandSenderName()
	{
		return "MoarCarts FakePlayer";
	}

	public EntityPlayer getEntityPlayer()
	{
		return entityPlayer;
	}

	public void setEntityPlayer(EntityPlayerMP entityPlayer)
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
