package xyz.brassgoggledcoders.moarcarts.mods.tinkers.guis;

import net.minecraft.entity.player.InventoryPlayer;
import slimeknights.tconstruct.tools.client.GuiPatternChest;
import slimeknights.tconstruct.tools.tileentity.TilePatternChest;
import xyz.brassgoggledcoders.moarcarts.mods.tinkers.entities.EntityMinecartPatternChest;

public class GuiMinecartPatternChest extends GuiPatternChest
{
	public GuiMinecartPatternChest(InventoryPlayer inventoryPlayer, EntityMinecartPatternChest minecart)
	{
		super(inventoryPlayer, minecart.getFakeWorld(), minecart.ORIGIN_POS, (TilePatternChest)minecart.getTileEntity());

	}
}
