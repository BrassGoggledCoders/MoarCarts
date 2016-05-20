package xyz.brassgoggledcoders.moarcarts.api.capabilities.rollingstock;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

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
                return null;
            }

            @Override
            public void readNBT(Capability<IRollingStock> capability, IRollingStock instance, EnumFacing side, NBTBase nbt)
            {

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
