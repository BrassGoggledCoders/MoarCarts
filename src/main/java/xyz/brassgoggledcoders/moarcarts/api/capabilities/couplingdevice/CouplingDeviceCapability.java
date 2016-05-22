package xyz.brassgoggledcoders.moarcarts.api.capabilities.couplingdevice;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CouplingDeviceCapability
{
	@CapabilityInject(ICouplingDevice.class)
	public static Capability<ICouplingDevice> COUPLING_DEVICE_CAPABILITY;

	public static void init()
	{
		CapabilityManager.INSTANCE.register(ICouplingDevice.class, new Capability.IStorage<ICouplingDevice>()
		{
			@Override
			public NBTBase writeNBT(Capability<ICouplingDevice> capability, ICouplingDevice instance, EnumFacing side)
			{
				return null;
			}

			@Override
			public void readNBT(Capability<ICouplingDevice> capability, ICouplingDevice instance, EnumFacing side,
					NBTBase nbt)
			{

			}
		}, ICouplingDevice.class);
	}
}
