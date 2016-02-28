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
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.tileentity.TileEntity;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.lib.client.ClientHelper;
import xyz.brassgoggledcoders.boilerplate.lib.common.tileentities.TileEntityBase;
import xyz.brassgoggledcoders.moarcarts.MoarCarts;
import xyz.brassgoggledcoders.moarcarts.behaviors.CartDispenserBehavior;
import xyz.brassgoggledcoders.moarcarts.config.ConfigSettings;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartBase;
import xyz.brassgoggledcoders.moarcarts.entities.EntityMinecartTEBase;
import xyz.brassgoggledcoders.moarcarts.renderers.IRenderBlock.RenderMethod;
import mods.railcraft.api.core.items.IMinecartItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.BlockUtils;
import xyz.brassgoggledcoders.moarcarts.renderers.ISpecialRenderedItem;
import xyz.brassgoggledcoders.moarcarts.renderers.ItemSpecialRenderer;
import xyz.brassgoggledcoders.moarcarts.renderers.RenderItemMinecartBase;

/**
 * @author SkySom
 */
@Optional.Interface(modid = "RailcraftAPI|items", iface = "mods.railcraft.api.core.items.IMinecartItem")
public abstract class ItemMinecartBase extends ItemMinecart implements IMinecartItem, ISpecialRenderedItem
{
	private TileEntity renderTileEntity;

	public ItemMinecartBase(String mod, String name)
	{
		super(null);
		this.setUnlocalizedName(name);
		this.setCreativeTab(MoarCarts.moarcartsTab);
		this.setMaxStackSize(ConfigSettings.getMinecartStackSize());
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

	public TileEntity getRenderTileEntity(ItemStack itemStack)
	{
		if(renderTileEntity == null)
		{
			renderTileEntity = getCartBlock(itemStack).createTileEntity(ClientHelper.world(), getCartBlockState(itemStack));
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
	public ItemSpecialRenderer getSpecialRenderer()
	{
		return RenderItemMinecartBase.getInstance();
	}

	@Override
	public ModelResourceLocation[] getModelDefinitions()
	{
		return new ModelResourceLocation[] {new ModelResourceLocation(BoilerplateLib.getInstance().mod.getPrefix() +
				getUnlocalizedName().substring(5))};
	}

	public abstract Block getCartBlock(ItemStack itemStack);

	public abstract EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack);
}
