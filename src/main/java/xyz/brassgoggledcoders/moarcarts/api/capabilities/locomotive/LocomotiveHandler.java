package xyz.brassgoggledcoders.moarcarts.api.capabilities.locomotive;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import xyz.brassgoggledcoders.moarcarts.api.capabilities.rollingstock.CapabilityRollingStock;
import xyz.brassgoggledcoders.moarcarts.api.capabilities.rollingstock.IRollingStock;

import java.util.ArrayList;
import java.util.List;

public class LocomotiveHandler implements ILocomotive, INBTSerializable<NBTTagCompound>
{
    private EntityMinecart locomotive;
    private List<Integer> rollingStockIDs;
    private List<IRollingStock> rollingStock;

    public LocomotiveHandler()
    {
        this(null);
    }

    public LocomotiveHandler(EntityMinecart locomotive)
    {
        this.locomotive = locomotive;
        this.rollingStock = new ArrayList<>();
        this.rollingStockIDs = new ArrayList<>();
    }

    @Override
    public List<IRollingStock> getRollingStock()
    {
        if(rollingStockIDs.size() > rollingStock.size())
        {
            rollingStock = new ArrayList<>();
            if(getLocomotiveEntity() != null)
            {
                for(Integer id: rollingStockIDs)
                {
                    Entity entity = getLocomotiveEntity().getEntityWorld().getEntityByID(id);
                    if(entity.hasCapability(CapabilityRollingStock.ROLLING_STOCK_CAPABILITY, null))
                    {
                        rollingStock.add(entity.getCapability(CapabilityRollingStock.ROLLING_STOCK_CAPABILITY, null));
                    }
                }
            }
        }
        return rollingStock;
    }

    @Override
    public List<Integer> getRollingStockIDs()
    {
        return rollingStockIDs;
    }

    @Override
    public void setRollingStockIDs(List<Integer> rollingStockIDs)
    {
        this.rollingStockIDs = rollingStockIDs;
    }

    @Override
    public void addRollingStock(IRollingStock rollingStock)
    {
        if(rollingStock.getRollingStockEntity() != null)
        {
            this.rollingStockIDs.add(rollingStock.getRollingStockEntity().getEntityId());
            this.rollingStock.add(rollingStock);
        }
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

        return null;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {

    }
}
