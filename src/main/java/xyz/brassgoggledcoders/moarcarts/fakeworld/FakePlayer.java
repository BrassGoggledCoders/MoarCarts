package xyz.brassgoggledcoders.moarcarts.fakeworld;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.moarcarts.MoarCarts;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartTEBase;

/**
 * @author SkySom
 */
public class FakePlayer extends EntityPlayer
{
	protected EntityPlayer entityPlayer;
	private EntityMinecartTEBase entityMinecartTEBase;

	public FakePlayer(EntityPlayer entityPlayer, EntityMinecartTEBase entityMinecartTEBase, boolean accessInventory)
	{
		super(entityPlayer.getEntityWorld(), entityPlayer.getGameProfile());
		if(accessInventory)
		{
			this.inventory = entityPlayer.inventory;
		} else
		{
			this.inventory = new InventoryPlayer(entityPlayer);
		}
		this.setEntityPlayer(entityPlayer);
		this.setEntityMinecartTEBase(entityMinecartTEBase);
	}

	@Override
	public void addChatMessage(ITextComponent iChatComponent)
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
	public boolean isCreative()
	{
		return getEntityPlayer().isCreative();
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
