package xyz.brassgoggledcoders.moarcarts.mods.ie;

import blusunrize.immersiveengineering.api.shader.ShaderRegistry;
import blusunrize.immersiveengineering.api.shader.ShaderRegistry.ShaderRegistryEntry;
import net.minecraft.item.EnumRarity;

/**
 * @author SkySom
 */
public class Shaders
{
	public static ShaderRegistryEntry superChief;
	public static ShaderRegistryEntry empireBuilder;

	public static void initShaders()
	{
		int[] yellow = {224, 187, 65, 255};
		int[] red = {192, 0, 15, 255};
		int[] grey = {100, 100, 100, 255};
		int[] orange = {255, 102, 0, 255};
		int[] green = {43, 58, 61, 255};

		superChief = ShaderRegistry.registerShader("Super Chief", "0", EnumRarity.EPIC, grey, red, yellow, grey, null,
				true, true);
		empireBuilder = ShaderRegistry.registerShader("Empire Builder", "0", EnumRarity.COMMON, green, orange, green,
				green, null, true, true);
	}
}
