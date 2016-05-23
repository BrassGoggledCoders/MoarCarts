package xyz.brassgoggledcoders.moarcarts.mods.tinkers.tiles;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import slimeknights.tconstruct.common.TinkerNetwork;
import slimeknights.tconstruct.smeltery.network.FaucetActivationPacket;
import slimeknights.tconstruct.smeltery.tileentity.TileFaucet;
import xyz.brassgoggledcoders.moarcarts.utils.Utils;

//Code Pulled up from TileFaucet and switched to carts for filling :D
public class TileCartFaucet extends TileFaucet
{
	@Override
	public void update() {
		if(worldObj.isRemote) {
			return;
		}
		// nothing to do if not pouring
		if(!isPouring) {
			return;
		}

		if(drained != null) {
			// done draining
			if(drained.amount <= 0) {
				drained = null;
				// pour me another, if we want to.
				if(!stopPouring) {
					doTransfer();
				}
				else {
					reset();
				}
			}
			else {
				// reduce amount (cooldown)
				pour();
			}
		}
	}

	@Override
	protected void doTransfer() {
		// still got content left
		if(drained != null) {
			return;
		}
		TileEntity drainTE = worldObj.getTileEntity(pos.offset(direction));
		EntityMinecart fillMinecart = Utils.getMinecartAt(worldObj, pos.down(), 1f);
		if(drainTE instanceof IFluidHandler && fillMinecart instanceof IFluidHandler) {
			IFluidHandler toDrain = (IFluidHandler) drainTE;
			IFluidHandler toFill = (IFluidHandler) fillMinecart;

			// can we drain?
			FluidStack drained = toDrain.drain(direction, TRANSACTION_AMOUNT, false);
			if(drained != null) {
				// can we fill?
				int filled = toFill.fill(EnumFacing.UP, drained, false);
				if(filled > 0) {
					// drain the liquid and transfer it, buffer the amount for delay
					this.drained = toDrain.drain(direction, filled, true);
					this.isPouring = true;
					pour();

					// sync to clients
					if(!worldObj.isRemote && worldObj instanceof WorldServer) {
						TinkerNetwork.sendToClients((WorldServer) worldObj, pos, new FaucetActivationPacket(pos, drained));
					}

					return;
				}
			}
		}
		// draining unsuccessful
		reset();
	}
}
