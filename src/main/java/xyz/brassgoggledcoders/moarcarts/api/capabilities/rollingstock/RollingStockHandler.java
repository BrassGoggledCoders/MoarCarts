package xyz.brassgoggledcoders.moarcarts.api.capabilities.rollingstock;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import xyz.brassgoggledcoders.moarcarts.api.capabilities.locomotive.ILocomotive;
import xyz.brassgoggledcoders.moarcarts.mods.coupling.CouplingWorldData;

public class RollingStockHandler implements IRollingStock, INBTSerializable<NBTTagCompound>
{
	private ILocomotive locomotive;
	private EntityMinecart rollingStock;
	private int cartNumber = -1;
	private int id;

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
	public EntityMinecart getRollingStockEntity()
	{
		return rollingStock;
	}

	@Override
	public void setRollingStockEntity(EntityMinecart rollingStock)
	{
		this.rollingStock = rollingStock;
	}

	@Override
	public ILocomotive getLocomotive()
	{
		return locomotive;
	}

	@Override
	public void setLocomotive(ILocomotive locomotive)
	{
		this.locomotive = locomotive;
	}

	@Override
	public int getCartNumber()
	{
		return cartNumber;
	}

	@Override
	public void setCartNumber(int cartNumber)
	{
		this.cartNumber = cartNumber;
	}

	@Override
	public NBTTagCompound serializeNBT()
	{
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		nbtTagCompound.setInteger("id", this.getID());
		nbtTagCompound.setInteger("cartNumber", this.getCartNumber());
		int locomotiveID = -1;
		if(this.getLocomotive() != null)
		{
			locomotiveID = this.getLocomotive().getID();
		}
		nbtTagCompound.setInteger("locomotiveID", locomotiveID);
		return nbtTagCompound;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbtTagCompound)
	{
		this.setID(nbtTagCompound.getInteger("id"));
		this.setCartNumber(nbtTagCompound.getInteger("cartNumber"));
		int locomotiveID =  nbtTagCompound.getInteger("locomotiveID");
		if(locomotiveID > -1)
		{
			this.setLocomotive(CouplingWorldData.getLocomotive(locomotiveID));
		}
	}
}
