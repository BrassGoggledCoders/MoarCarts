package xyz.brassgoggledcoders.moarcarts.api.capabilities.couplable;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import xyz.brassgoggledcoders.moarcarts.mods.coupling.CouplingData;

import java.util.ArrayList;
import java.util.List;

public class CouplingHandler implements ICoupling, INBTSerializable<NBTTagCompound>, ICapabilityProvider
{
	private int id = -1;
	private int cartPosition = 0;
	private boolean isReady = false;
	private int[] trainIDs;
	private List<ICoupling> train;
	private EntityMinecart cart;

	public CouplingHandler()
	{
		this(null);
	}

	public CouplingHandler(EntityMinecart entityMinecart)
	{
		this.setCartEntity(entityMinecart);
		train = new ArrayList<>();
		train.add(this);
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
	public List<ICoupling> getTrain()
	{
		return train;
	}

	@Override
	public int addCartToTrain(ICoupling newCoupling)
	{
		return this.addCartToTrain(this.train.size(), newCoupling);
	}

	@Override
	public int addCartToTrain(int cartPosition, ICoupling newCoupling)
	{
		if(cartPosition >= train.size())
		{
			train.add(newCoupling);
		} else {
			train.add(cartPosition, newCoupling);
		}
		int newCouplingCartPosition = train.indexOf(newCoupling);
		newCoupling.setCartPositionInTrain(newCouplingCartPosition);
		newCoupling.setTrain(this.getTrain());
		return newCouplingCartPosition;
	}

	@Override
	public void setTrain(List<ICoupling> train)
	{
		this.isReady = true;
		this.train = train;
	}

	@Override
	public void setCartEntity(EntityMinecart entityMinecart)
	{
		this.cart = entityMinecart;
	}

	@Override
	public EntityMinecart getCartEntity()
	{
		return this.cart;
	}

	@Override
	public int getCartPositionInTrain()
	{
		return this.cartPosition;
	}

	@Override
	public int setCartPositionInTrain(int position)
	{
		return this.cartPosition = position;
	}

	@Override
	public ICoupling getFrontCart()
	{
		return this.train.get(0);
	}

	@Override
	public boolean isReady()
	{
		return this.isReady;
	}

	@Override
	public NBTTagCompound serializeNBT()
	{
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		nbtTagCompound.setInteger("id", this.getID());
		nbtTagCompound.setInteger("cartPosition", this.getCartPositionInTrain());
		nbtTagCompound.setInteger("frontTrainID", this.getFrontCart().getID());
		nbtTagCompound.setInteger("trainLength", this.getTrain().size());

		return nbtTagCompound;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbtTagCompound)
	{
		this.setID(nbtTagCompound.getInteger("id"));
		this.setCartPositionInTrain(nbtTagCompound.getInteger("cartPosition"));
		int frontTrainID = nbtTagCompound.getInteger("frontTrainID");
		int trainLength = nbtTagCompound.getInteger("trainLength");

		CouplingData.attemptLoadingCouplings(frontTrainID, trainLength, this);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return capability == CouplingCapability.COUPLING_CAPABILITY;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if(capability == CouplingCapability.COUPLING_CAPABILITY)
		{
			return (T)this;
		}
		return null;
	}
}
