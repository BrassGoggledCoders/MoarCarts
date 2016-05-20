package xyz.brassgoggledcoders.moarcarts.api.capabilities.rollingstock;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import xyz.brassgoggledcoders.moarcarts.api.capabilities.locomotive.ILocomotive;

public class RollingStockHandler implements IRollingStock, INBTSerializable<NBTTagCompound>
{
	private ILocomotive locomotive;
	private EntityMinecart rollingStock;
	private int cartNumber = -1;

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
		return null;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{

	}
}
