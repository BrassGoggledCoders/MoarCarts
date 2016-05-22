package xyz.brassgoggledcoders.moarcarts.api.capabilities.couplingdevice;

import xyz.brassgoggledcoders.moarcarts.api.capabilities.couplable.ICoupling;

public interface ICouplingDevice
{
	boolean tryCouplingCart(ICoupling coupling);
}
