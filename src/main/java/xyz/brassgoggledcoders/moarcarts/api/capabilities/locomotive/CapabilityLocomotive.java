package xyz.brassgoggledcoders.moarcarts.api.capabilities.locomotive;

import net.minecraft.nbt.*;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import xyz.brassgoggledcoders.moarcarts.api.capabilities.rollingstock.IRollingStock;
import xyz.brassgoggledcoders.moarcarts.mods.coupling.CouplingWorldData;

import java.util.ArrayList;
import java.util.List;
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
                NBTTagCompound nbtTagCompound = new NBTTagCompound();
                nbtTagCompound.setInteger("id", instance.getID());

                if(!instance.getRollingStock().isEmpty())
                {
                    int size = instance.getRollingStock().size();
                    int[] rollingStockArray = new int[size];
                    for(int i = 0; i < size; i++)
                    {
                        IRollingStock rollingStock = instance.getRollingStock().get(i);
                        if(rollingStock != null)
                        {
                            rollingStockArray[i] = rollingStock.getID();
                        }
                    }
                    NBTTagIntArray rollingStockNBT = new NBTTagIntArray(rollingStockArray);
                    nbtTagCompound.setTag("rollingStock", rollingStockNBT);
                }

                return nbtTagCompound;
            }

            @Override
            public void readNBT(Capability<ILocomotive> capability, ILocomotive instance, EnumFacing side, NBTBase nbtBase)
            {
                NBTTagCompound nbtTagCompound = (NBTTagCompound)nbtBase;
                instance.setID(nbtTagCompound.getInteger("id"));
                int[] rollingStockArray = nbtTagCompound.getIntArray("rollingStock");
                if(rollingStockArray != null && rollingStockArray.length > 0)
                {
                    List<IRollingStock> rollingStockList = new ArrayList<>();
                    for(int rollingStockID: rollingStockArray)
                    {
                        IRollingStock rollingStock = CouplingWorldData.getRollingStock(rollingStockID);
                        if(rollingStock != null)
                        {
                            rollingStockList.add(rollingStock.getCartNumber(), rollingStock);
                        }
                    }
                    instance.setRollingStock(rollingStockList);
                }
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
