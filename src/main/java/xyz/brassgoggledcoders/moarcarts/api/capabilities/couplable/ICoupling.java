package xyz.brassgoggledcoders.moarcarts.api.capabilities.couplable;

import net.minecraft.entity.item.EntityMinecart;

import java.util.List;

public interface ICoupling
{
	int getID();

	void setID(int id);

	List<ICoupling> getTrain();

	int addCartToTrain(ICoupling newCoupling);

	int addCartToTrain(int cartPosition, ICoupling newCoupling);

	void setTrain(List<ICoupling> train);

	void setCartEntity(EntityMinecart entityMinecart);

	EntityMinecart getCartEntity();

	int getCartPositionInTrain();

	int setCartPositionInTrain(int position);

	int getTrainSize();

	ICoupling getFrontCart();

	boolean isReady();
}
