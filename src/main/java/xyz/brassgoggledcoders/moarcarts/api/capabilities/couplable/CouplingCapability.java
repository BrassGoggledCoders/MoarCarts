package xyz.brassgoggledcoders.moarcarts.api.capabilities.couplable;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import xyz.brassgoggledcoders.moarcarts.mods.coupling.CouplingData;

import java.util.concurrent.Callable;

public class CouplingCapability
{
	@CapabilityInject(ICoupling.class)
	public static Capability<ICoupling> COUPLING_CAPABILITY;
	
	public static void init()
	{
		CapabilityManager.INSTANCE.register(ICoupling.class, new Capability.IStorage<ICoupling>()
		{
			@Override 
			public NBTBase writeNBT(Capability<ICoupling> capability, ICoupling instance, EnumFacing side)
			{
				NBTTagCompound nbtTagCompound = new NBTTagCompound();
				nbtTagCompound.setInteger("id", instance.getID());
				nbtTagCompound.setInteger("cartPosition", instance.getCartPositionInTrain());
				nbtTagCompound.setInteger("frontTrainID", instance.getFrontCart().getID());
				nbtTagCompound.setInteger("trainLength", instance.getTrain().size());

				return nbtTagCompound;
			}

			@Override
			public void readNBT(Capability<ICoupling> capability, ICoupling instance, EnumFacing side, NBTBase nbt)
			{
				NBTTagCompound nbtTagCompound = (NBTTagCompound)nbt;
				instance.setID(nbtTagCompound.getInteger("id"));
				instance.setCartPositionInTrain(nbtTagCompound.getInteger("cartPosition"));

				int[] trainIDs = nbtTagCompound.getIntArray("trainIDs");
				int frontTrainID = nbtTagCompound.getInteger("frontTrainID");
				int trainLength = nbtTagCompound.getInteger("trainLength");

				CouplingData.attemptLoadingCouplings(frontTrainID, trainLength, instance);
			}
		}, new Callable<ICoupling>()
		{
			@Override 
			public ICoupling call() throws Exception
			{
				return new CouplingHandler();
			}
		});
	}
}
