package xyz.brassgoggledcoders.moarcarts.mods.coupling;

import xyz.brassgoggledcoders.moarcarts.api.capabilities.locomotive.ILocomotive;
import xyz.brassgoggledcoders.moarcarts.api.capabilities.rollingstock.IRollingStock;

import java.util.HashMap;

public class CouplingWorldData
{
	private static HashMap<Integer, ILocomotive> locomotivesMap;
	private static HashMap<Integer, IRollingStock> rollingStockMap;

	private static void init()
	{
		locomotivesMap = new HashMap<>();
		rollingStockMap = new HashMap<>();
	}

	public static ILocomotive getLocomotive(int id)
	{
		return locomotivesMap.get(id);
	}

	public static IRollingStock getRollingStock(int id)
	{
		return rollingStockMap.get(id);
	}

	public static void addLocomotive(ILocomotive locomotive)
	{
		locomotivesMap.put(locomotive.getID(), locomotive);
	}

	public static void addRollingStock(IRollingStock rollingStock)
	{
		rollingStockMap.put(rollingStock.getID(), rollingStock);
	}
}
