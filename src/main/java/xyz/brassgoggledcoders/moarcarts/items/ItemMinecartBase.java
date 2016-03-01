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
package xyz.brassgoggledcoders.moarcarts.items;

import com.mojang.authlib.GameProfile;
import mods.railcraft.api.core.items.IMinecartItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import xyz.brassgoggledcoders.boilerplate.lib.client.renderers.ISpecialRenderedItem;
import xyz.brassgoggledcoders.boilerplate.lib.common.config.ConfigEntry;
import xyz.brassgoggledcoders.boilerplate.lib.common.config.IConfigListener;
import xyz.brassgoggledcoders.boilerplate.lib.common.items.IHasRecipe;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.ConfigRegistry;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.ItemRegistry;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.BlockUtils;
import xyz.brassgoggledcoders.moarcarts.MoarCarts;
import xyz.brassgoggledcoders.moarcarts.behaviors.CartDispenserBehavior;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartBase;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartTEBase;
import xyz.brassgoggledcoders.moarcarts.recipes.NBTCartRecipe;
import xyz.brassgoggledcoders.moarcarts.renderers.IRenderBlock.RenderMethod;

/**
 * @author SkySom
 */
@Optional.Interface(modid = "RailcraftAPI|items", iface = "mods.railcraft.api.core.items.IMinecartItem")
public abstract class ItemMinecartBase extends ItemMinecart implements IMinecartItem, ISpecialRenderedItem, IHasRecipe,
		IConfigListener
{
	private TileEntity renderTileEntity;
	protected String mod;

	public ItemMinecartBase(String mod, String name)
	{
		super(null);
		this.mod = mod;
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(MoarCarts.moarcartsTab);
		this.setMaxStackSize(ConfigRegistry.getInt("maxStackSize", 3));
		BlockDispenser.dispenseBehaviorRegistry.putObject(this, new CartDispenserBehavior());
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos blockPos, EnumFacing facing,
			float par8, float par9, float par10) {
		return placeCart(itemStack, world, blockPos, this.getEntityFromItem(world, itemStack));
	}

	@Override
	@Optional.Method(modid = "RailcraftAPI|items")
	public boolean canBePlacedByNonPlayer(ItemStack itemStack)
	{
		return itemStack != null;
	}

	@Override
	@Optional.Method(modid = "RailcraftAPI|items")
	public EntityMinecart placeCart(GameProfile gameProfile, ItemStack itemStack, World world, int posX, int posY, int posZ)
	{
		BlockPos blockPos = new BlockPos(posX, posY, posZ);
		EntityMinecartBase entityMinecart = getEntityFromItem(world, itemStack);
		if (placeCart(itemStack, world, blockPos, entityMinecart))
		{
			return entityMinecart;
		}
		return null;
	}

	public boolean placeCart(ItemStack itemStack, World world, BlockPos blockPos, EntityMinecartBase entityMinecart)
	{
		if (itemStack != null && BlockUtils.isRailBlock(world.getBlockState(blockPos)))
		{
			if(itemStack.hasDisplayName())
			{
				entityMinecart.setCustomNameTag(itemStack.getDisplayName());
			}

			if (!world.isRemote)
			{
				entityMinecart.posX = (float)blockPos.getX() + 0.5F;
				entityMinecart.posY = (float)blockPos.getY() + 0.5F;
				entityMinecart.posZ = (float)blockPos.getZ() + 0.5F;
				if(entityMinecart instanceof EntityMinecartTEBase)
				{
					EntityMinecartTEBase entityMinecartTEBase = (EntityMinecartTEBase)entityMinecart;
					entityMinecartTEBase.setTileEntityNBT(itemStack);
					world.spawnEntityInWorld(entityMinecartTEBase);
				} else
				{
					world.spawnEntityInWorld(entityMinecart);
				}
				entityMinecart.afterEntitySpawned();
			}
			--itemStack.stackSize;
			return true;
		}
		return false;
	}

	public IBlockState getCartBlockState(ItemStack itemStack)
	{
		return this.getCartBlock(itemStack).getStateFromMeta(this.getCartBlockMetadata(itemStack));
	}

	public TileEntity getRenderTileEntity(ItemStack itemStack, World world)
	{
		if(renderTileEntity == null)
		{
			renderTileEntity = getCartBlock(itemStack).createTileEntity(world, getCartBlockState(itemStack));
		}
		return renderTileEntity;
	}

	public RenderMethod getCartBlockRenderMethod(ItemStack itemStack)
	{
		return RenderMethod.VMC;
	}

	public int getCartBlockMetadata(ItemStack itemStack)
	{
		return 0;
	}

	@Override
	public String getSpecialRendererPath()
	{
		return "xyz.brassgoggledcoders.moarcarts.renderers.RenderItemMinecartBase";
	}

	@Override
	public ResourceLocation[] getResourceLocations()
	{
		return new ResourceLocation[] {new ResourceLocation(getRegistryName())};
	}

	@Override
	public IRecipe[] getRecipes()
	{
		Item itemMinecartBase = ItemRegistry.getItem(this.getUnlocalizedName().substring(5));
		ItemStack itemStack = new ItemStack(itemMinecartBase, 1, 0);
		return new IRecipe[]{new NBTCartRecipe(itemMinecartBase, getCartBlock(itemStack))};
	}

	@Override
	public void onConfigChange(String name, ConfigEntry entry)
	{
		if("maxStackSize".equalsIgnoreCase(name))
		{
			MoarCarts.logger.devInfo("Max Stack Size has changed to " + entry.getInt(3));
			setMaxStackSize(entry.getInt(3));
		}
	}

	public abstract Block getCartBlock(ItemStack itemStack);

	public abstract EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack);
}
