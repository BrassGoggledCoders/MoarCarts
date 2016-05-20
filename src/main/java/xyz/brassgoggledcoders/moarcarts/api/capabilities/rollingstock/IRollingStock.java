package xyz.brassgoggledcoders.moarcarts.api.capabilities.rollingstock;

import net.minecraft.entity.item.EntityMinecart;
import xyz.brassgoggledcoders.moarcarts.api.capabilities.locomotive.ILocomotive;

public interface IRollingStock {
    EntityMinecart getRollingStockEntity();

    void setRollingStockEntity(EntityMinecart rollingStock);

    ILocomotive getLocomotive();

    void setLocomotive(ILocomotive locomotive);

    int getCartNumber();

    void setCartNumber(int cartNumber);
}
