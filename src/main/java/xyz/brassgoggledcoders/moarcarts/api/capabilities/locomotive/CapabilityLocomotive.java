package xyz.brassgoggledcoders.moarcarts.api.capabilities.locomotive;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import java.util.concurrent.Callable;

public class CapabilityLocomotive {
    @CapabilityInject(ILocomotive.class)
    public static Capability<ILocomotive> LOCOMOTIVE_CAPABILITY = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(ILocomotive.class, new Capability.IStorage<ILocomotive>()
        {
            @Override
            public NBTBase writeNBT(Capability<ILocomotive> capability, ILocomotive instance, EnumFacing side)
            {
                NBTTagList nbtTagList = new NBTTagList();
                NBTTagList rollingStockIDs = new NBTTagList();
                int size = instance.getRollingStockIDs().size();
                for(int i = 0; i < size; i++)
                {
                    rollingStockIDs.set(0, new NBTTagInt(instance.getRollingStockIDs().get(i)));
                }
                int index = 0;
                if(instance.getLocomotiveEntity() != null)
                {
                    nbtTagList.set(index++, new NBTTagInt(instance.getLocomotiveEntity().getEntityId()));
                }
                nbtTagList.set(index, rollingStockIDs);
                return nbtTagList;
            }

            @Override
            public void readNBT(Capability<ILocomotive> capability, ILocomotive instance, EnumFacing side, NBTBase nbt)
            {

            }
        }, new Callable<ILocomotive>()
        {
            @Override
            public ILocomotive call() throws Exception
            {
                return new LocomotiveHandler();
            }
        });
    }
}
