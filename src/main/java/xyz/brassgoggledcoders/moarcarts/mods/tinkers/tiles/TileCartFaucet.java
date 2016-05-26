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
						TinkerNetwork.sendToClients((WorldServer)worldObj, pos, new FaucetActivationPacket(pos, drained));
					}

					return;
				}
			}
		}
		// draining unsuccessful
		reset();
	}

	@Override
	protected void pour() {
		if(drained == null) {
			return;
		}

		EntityMinecart fillMinecart = Utils.getMinecartAt(worldObj, pos.down(), 1f);
		if(fillMinecart instanceof IFluidHandler)  {
			IFluidHandler toFill = (IFluidHandler) fillMinecart;

			FluidStack fillStack = drained.copy();
			fillStack.amount = Math.min(drained.amount, LIQUID_TRANSFER);

			// can we fill?
			int filled = toFill.fill(EnumFacing.UP, fillStack, false);
			if(filled > 0) {
				// transfer it
				this.drained.amount -= filled;
				fillStack.amount = filled;
				toFill.fill(EnumFacing.UP, fillStack, true);
			}
		}
		else {
			// filling TE got lost. reset. all liquid buffered is lost.
			reset();
		}
	}
}
