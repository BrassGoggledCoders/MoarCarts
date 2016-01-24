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
package moarcarts.mods.railcraft;

import xyz.brassgoggledcoders.boilerplate.common.modcompat.ModCompat;
import xyz.brassgoggledcoders.boilerplate.common.utils.helpers.RegistryHelper;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import moarcarts.MoarCarts;
import moarcarts.mods.railcraft.entities.EntityMinecartMetalsChest;
import moarcarts.mods.railcraft.entities.EntityMinecartVoidChest;
import moarcarts.mods.railcraft.items.ItemMinecartMachineBeta;
import moarcarts.mods.railcraft.renderers.RenderItemMinecartMachineBeta;
import moarcarts.mods.railcraft.renderers.RenderMinecartMachineBeta;
import moarcarts.recipes.NBTCartRecipe;
import net.minecraft.block.Block;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * @author SkySom
 */
public class RailcraftCompat extends ModCompat
{
	public static Block MACHINE_BETA;
	public static ItemMinecartMachineBeta ITEM_MINECART_MACHINEBETA;

	@Override
	public String getName()
	{
		return "Railcraft";
	}

	@Override
	public boolean areRequirementsMet()
	{
		return Loader.isModLoaded("Railcraft");
	}

	@Override
	public String dependencies()
	{
		return "after:Railcraft;";
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		ITEM_MINECART_MACHINEBETA = new ItemMinecartMachineBeta();
		RegistryHelper.registerItem(ITEM_MINECART_MACHINEBETA, MoarCarts.MODID);
		RegistryHelper.registerEntity(MoarCarts.instance, EntityMinecartVoidChest.class, "entityminecartvoidchest");
		RegistryHelper.registerEntity(MoarCarts.instance, EntityMinecartMetalsChest.class, "entityminecartmetalschest");
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		RailcraftConfigValues.setConfigValues();

		MACHINE_BETA = GameRegistry.findBlock("Railcraft", "machine.beta");
		GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_MACHINEBETA, 0, MACHINE_BETA, 11));
		GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_MACHINEBETA, 1, MACHINE_BETA, 12));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void clientInit(FMLInitializationEvent event)
	{
		MinecraftForgeClient.registerItemRenderer(ITEM_MINECART_MACHINEBETA, new RenderItemMinecartMachineBeta());
		RenderingRegistry.registerEntityRenderingHandler(EntityMinecartVoidChest.class, new RenderMinecartMachineBeta());
		RenderingRegistry.registerEntityRenderingHandler(EntityMinecartMetalsChest.class, new RenderMinecartMachineBeta());
	}
}
