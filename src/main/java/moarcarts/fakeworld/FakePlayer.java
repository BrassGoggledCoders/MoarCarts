package moarcarts.fakeworld;

import moarcarts.MoarCarts;
import moarcarts.entities.EntityMinecartTileEntityBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class FakePlayer extends EntityPlayer
{
	protected EntityPlayer entityPlayer;
	private EntityMinecartTileEntityBase entityMinecartTileEntityBase;

	public FakePlayer(EntityPlayer entityPlayer, EntityMinecartTileEntityBase entityMinecartTileEntityBase)
	{
		super(entityPlayer.getEntityWorld(), entityPlayer.getGameProfile());
		this.setEntityPlayer(entityPlayer);
		this.setEntityMinecartTileEntityBase(entityMinecartTileEntityBase);
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
		if(this.getEntityMinecartTileEntityBase() != null) {
			return this.entityPlayer.getDistanceSq(this.getEntityMinecartTileEntityBase().posX,
				this.getEntityMinecartTileEntityBase().posY, this.getEntityMinecartTileEntityBase().posZ);
		} else
		{
			return 64;
		}
	}

	@Override
	public void openGui(Object mod, int id, World world, int posX, int posY, int poxZ)
	{
		this.getEntityPlayer().openGui(MoarCarts.instance, this.getEntityMinecartTileEntityBase().getEntityId(),
				world, posX, posY, poxZ);
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

	public EntityMinecartTileEntityBase getEntityMinecartTileEntityBase()
	{
		return entityMinecartTileEntityBase;
	}

	public void setEntityMinecartTileEntityBase(EntityMinecartTileEntityBase entityMinecartTileEntityBase)
	{
		this.entityMinecartTileEntityBase = entityMinecartTileEntityBase;
	}
}
