package xyz.brassgoggledcoders.moarcarts.api.capabilities.locomotive;

import net.minecraft.entity.item.EntityMinecart;
import xyz.brassgoggledcoders.moarcarts.api.capabilities.rollingstock.IRollingStock;

import java.util.List;

public interface ILocomotive {
    int getID();

    void setID(int id);

    List<IRollingStock> getRollingStock();

    void addRollingStock(IRollingStock rollingStock);

    void setRollingStock(List<IRollingStock> rollingStockList);

    void setLocomotiveEntity(EntityMinecart locomotive);

    EntityMinecart getLocomotiveEntity();
}
