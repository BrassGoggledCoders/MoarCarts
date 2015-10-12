package moarcarts.fakeworld;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;

/**
 * @author SkySom
 */
public class FakePlayer extends EntityPlayer
{
	protected EntityPlayer entityPlayer;
	public FakePlayer(EntityPlayer entityPlayer)
	{
		super(entityPlayer.getEntityWorld(), entityPlayer.getGameProfile());
		this.setEntityPlayer(entityPlayer);
	}

	@Override
	public void addChatMessage(IChatComponent p_145747_1_)
	{

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
		return 64;
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
}
