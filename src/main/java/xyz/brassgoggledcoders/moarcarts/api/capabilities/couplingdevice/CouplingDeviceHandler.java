package xyz.brassgoggledcoders.moarcarts.api.capabilities.couplingdevice;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import xyz.brassgoggledcoders.moarcarts.api.capabilities.couplable.ICoupling;

public class CouplingDeviceHandler implements ICouplingDevice, ICapabilityProvider
{
	ICoupling firstCoupling;

	@Override
	public boolean tryCouplingCart(ICoupling coupling)
	{
		boolean coupledSuccessful = false;
		if(firstCoupling == null)
		{
			firstCoupling = coupling;
		} else
		{
			EntityMinecart firstCouplingEntity = firstCoupling.getCartEntity();
			EntityMinecart secondCouplingEntity = coupling.getCartEntity();
			//TODO: Figure out proper spacing
			if(firstCouplingEntity.getDistanceSqToEntity(secondCouplingEntity) <= .5F)
			{
				coupledSuccessful = firstCoupling.addCartToTrain(coupling) > -1;
			}
		}
		return coupledSuccessful;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return capability == CouplingDeviceCapability.COUPLING_DEVICE_CAPABILITY;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if(capability == CouplingDeviceCapability.COUPLING_DEVICE_CAPABILITY)
		{
			return (T)this;
		}
		return null;
	}
}
