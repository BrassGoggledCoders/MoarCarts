package moarcarts;

import boilerplate.common.modcompat.ModCompat;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by Skylar on 9/7/2015.
 */
public class ConfigHandler
{
	public static File configFile = null;

	public static void init(){
		Configuration config = new Configuration(configFile);
		config.load();
		for(ModCompat modCompat: MoarCarts.compatibilityHandler.getModCompat()) {
			modCompat.setIsActive(config.get("Modules", modCompat.getName() + " Enabled", true).getBoolean(true));
		}
		config.save();
	}

	public static void setConfigFile(File suggestedConfigurationFile) {
		configFile = suggestedConfigurationFile;
	}
}
