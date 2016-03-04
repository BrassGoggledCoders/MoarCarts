package xyz.brassgoggledcoders.moarcarts.mods.neotech.containers;

import com.dyonovan.neotech.common.container.storage.ContainerFlushableChest;
import com.dyonovan.neotech.common.tiles.storage.TileFlushableChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import xyz.brassgoggledcoders.moarcarts.mods.neotech.entities.EntityMinecartFlushableChest;

public class ContainerMinecartFlushableChest extends ContainerFlushableChest
{
	private EntityMinecartFlushableChest minecartFlushableChest;

	public ContainerMinecartFlushableChest(InventoryPlayer inventory, EntityMinecartFlushableChest minecart)
	{
		super(inventory, (TileFlushableChest)minecart.getTileEntity());
		this.minecartFlushableChest = minecart;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer)
	{
		return minecartFlushableChest.isUseableByPlayer(entityPlayer);
	}
}
