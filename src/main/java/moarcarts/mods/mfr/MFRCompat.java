package moarcarts.mods.mfr;

import boilerplate.common.modcompat.ModCompat;
import boilerplate.common.utils.helpers.RegistryHelper;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import moarcarts.mods.mfr.entities.EntityMinecartDSU;
import moarcarts.mods.mfr.items.ItemMinecartDSU;
import moarcarts.recipes.NBTCartRecipe;
import moarcarts.renderers.RenderItemMinecartBase;
import net.minecraft.block.Block;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * @author SkySom
 */
public class MFRCompat extends ModCompat
{
	public static ItemMinecartDSU ITEM_MINECART_DSU;
	public static Block DSU;

	@Override
	public String getName()
	{
		return "MineFactory Reload";
	}

	@Override
	public boolean areRequirementsMet()
	{
		return Loader.isModLoaded("MineFactoryReloaded");
	}

	@Override
	public String dependencies()
	{
		return "after:MineFactoryReloaded;";
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		ITEM_MINECART_DSU = new ItemMinecartDSU();
		RegistryHelper.registerItem(ITEM_MINECART_DSU);
		RegistryHelper.registerEntity(EntityMinecartDSU.class, "entityminecartdsu");
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		DSU = GameRegistry.findBlock("MineFactoryReloaded", "machine.1");
		GameRegistry.addRecipe(new NBTCartRecipe(ITEM_MINECART_DSU, DSU, 3));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void clientInit(FMLInitializationEvent event)
	{
		MinecraftForgeClient.registerItemRenderer(ITEM_MINECART_DSU, new RenderItemMinecartBase());
	}
}
