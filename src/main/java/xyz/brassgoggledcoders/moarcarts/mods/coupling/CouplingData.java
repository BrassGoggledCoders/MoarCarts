package xyz.brassgoggledcoders.moarcarts.mods.coupling;

import xyz.brassgoggledcoders.moarcarts.api.capabilities.couplable.ICoupling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CouplingData
{
	private static HashMap<Integer, List<ICoupling>> tryingToCoupleMap;

	public static void init()
	{
		tryingToCoupleMap = new HashMap<>();
	}

	public static void attemptLoadingCouplings(int frontTrainID, int trainLength, ICoupling coupling)
	{
		if(trainLength > 1)
		{
			List<ICoupling> currentAttemptedCoupling = tryingToCoupleMap.get(frontTrainID);
			if(currentAttemptedCoupling != null)
			{
				currentAttemptedCoupling.set(coupling.getCartPositionInTrain(), coupling);
				boolean completedCoupling = true;
				for(ICoupling attemptedCoupling: currentAttemptedCoupling)
				{
					if(attemptedCoupling == null)
					{
						completedCoupling = false;
						break;
					}
				}

				if(completedCoupling)
				{
					for(ICoupling attemptedCoupling: currentAttemptedCoupling)
					{
						attemptedCoupling.setTrain(currentAttemptedCoupling);
					}
					tryingToCoupleMap.remove(frontTrainID);
				}
			} else
			{
				List<ICoupling> newAttemptedCoupling = new ArrayList<>(trainLength);
				newAttemptedCoupling.set(coupling.getCartPositionInTrain(), coupling);
				tryingToCoupleMap.put(frontTrainID, newAttemptedCoupling);
			}
		}
	}
}
