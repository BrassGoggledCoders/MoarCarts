package xyz.brassgoggledcoders.moarcarts.api.capabilities.rollingstock;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import xyz.brassgoggledcoders.moarcarts.mods.coupling.CouplingWorldData;

import java.util.concurrent.Callable;

public class CapabilityRollingStock {
    @CapabilityInject(IRollingStock.class)
    public static Capability<IRollingStock> ROLLING_STOCK_CAPABILITY = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(IRollingStock.class, new Capability.IStorage<IRollingStock>()
        {
            @Override
            public NBTBase writeNBT(Capability<IRollingStock> capability, IRollingStock instance, EnumFacing side)
            {
                NBTTagCompound nbtTagCompound = new NBTTagCompound();
                nbtTagCompound.setInteger("id", instance.getID());
                nbtTagCompound.setInteger("cartNumber", instance.getCartNumber());
                int locomotiveID = -1;
                if(instance.getLocomotive() != null)
                {
                    locomotiveID = instance.getLocomotive().getID();
                }
                nbtTagCompound.setInteger("locomotiveID", locomotiveID);
                return nbtTagCompound;
            }

            @Override
            public void readNBT(Capability<IRollingStock> capability, IRollingStock instance, EnumFacing side, NBTBase nbtBase)
            {
                NBTTagCompound nbtTagCompound = (NBTTagCompound)nbtBase;
                instance.setID(nbtTagCompound.getInteger("id"));
                instance.setCartNumber(nbtTagCompound.getInteger("cartNumber"));
                int locomotiveID =  nbtTagCompound.getInteger("locomotiveID");
                if(locomotiveID > -1)
                {
                    instance.setLocomotive(CouplingWorldData.getLocomotive(locomotiveID));
                }
            }
        }, new Callable<IRollingStock>()
        {
            @Override
            public IRollingStock call() throws Exception
            {
                return new RollingStockHandler();
            }
        });
    }
}
