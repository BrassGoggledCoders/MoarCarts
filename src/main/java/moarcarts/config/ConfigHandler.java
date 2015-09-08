/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the MoarCarts Mod for Minecraft.
 *
 * MoarCarts is open-source and is distributed under the MIT License.
 *
 * MoarCarts is based on the original ExtraCarts Mod created by ScottDTA and SkySom.
 * ExtraCarts (c) ScottDTA 2014
 * (http://forum.feed-the-beast.com/threads/1-7-10-b0-7-0-extra-carts.47925/)
 *
 */
package moarcarts.config;

import boilerplate.common.modcompat.ModCompat;
import moarcarts.MoarCarts;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * @author SkySom
 */
public class ConfigHandler
{
	public static File configFile = null;
	public static Configuration configuration;

	public static void init(){
		configuration= new Configuration(configFile);
		configuration.load();
		configuration = ConfigSettings.init(configuration);
		for(ModCompat modCompat: MoarCarts.compatibilityHandler.getModCompat()) {
			modCompat.setIsActive(configuration.get("Modules", modCompat.getName() + " Enabled", true).getBoolean(true));
		}
		configuration.save();
	}

	public static void setConfigFile(File suggestedConfigurationFile) {
		configFile = suggestedConfigurationFile;
	}
}
