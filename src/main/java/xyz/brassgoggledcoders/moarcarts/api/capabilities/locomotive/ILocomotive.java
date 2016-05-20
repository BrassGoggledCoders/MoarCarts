package xyz.brassgoggledcoders.moarcarts.api.capabilities.locomotive;

import net.minecraft.entity.item.EntityMinecart;
import xyz.brassgoggledcoders.moarcarts.api.capabilities.rollingstock.IRollingStock;

import java.util.List;

public interface ILocomotive {
    List<IRollingStock> getRollingStock();

    List<Integer> getRollingStockIDs();

    void setRollingStockIDs(List<Integer> rollingStockIDs);

    void addRollingStock(IRollingStock rollingStock);

    void setLocomotiveEntity(EntityMinecart locomotive);

    EntityMinecart getLocomotiveEntity();
}
