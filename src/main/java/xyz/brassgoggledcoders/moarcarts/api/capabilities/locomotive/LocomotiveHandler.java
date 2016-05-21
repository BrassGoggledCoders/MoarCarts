package xyz.brassgoggledcoders.moarcarts.api.capabilities.locomotive;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraftforge.common.util.INBTSerializable;
import xyz.brassgoggledcoders.moarcarts.api.capabilities.rollingstock.IRollingStock;
import xyz.brassgoggledcoders.moarcarts.mods.coupling.CouplingWorldData;

import java.util.ArrayList;
import java.util.List;

public class LocomotiveHandler implements ILocomotive, INBTSerializable<NBTTagCompound>
{
    private EntityMinecart locomotive;
    private List<IRollingStock> rollingStockList;
    private int id;

    public LocomotiveHandler()
    {
        this(null);
    }

    public LocomotiveHandler(EntityMinecart locomotive)
    {
        this.locomotive = locomotive;
        this.rollingStockList = new ArrayList<>();
    }

    @Override
    public int getID()
    {
        return id;
    }

    @Override
    public void setID(int id)
    {
        this.id = id;
    }

    @Override
    public List<IRollingStock> getRollingStock()
    {
        return this.rollingStockList;
    }

    @Override
    public void addRollingStock(IRollingStock rollingStock)
    {
        this.rollingStockList.add(rollingStock);
    }

    @Override
    public void setRollingStock(List<IRollingStock> rollingStockList)
    {
        this.rollingStockList = rollingStockList;
    }

    @Override
    public void setLocomotiveEntity(EntityMinecart locomotive)
    {
        this.locomotive = locomotive;
    }

    @Override
    public EntityMinecart getLocomotiveEntity()
    {
        return locomotive;
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setInteger("id", this.getID());
        if(!getRollingStock().isEmpty())
        {
            int size = getRollingStock().size();
            int[] rollingStockArray = new int[size];
            for(int i = 0; i < size; i++)
            {
                IRollingStock rollingStock = getRollingStock().get(i);
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
    public void deserializeNBT(NBTTagCompound nbtTagCompound)
    {
        this.setID(nbtTagCompound.getInteger("id"));
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
            this.setRollingStock(rollingStockList);
        }
    }
}
