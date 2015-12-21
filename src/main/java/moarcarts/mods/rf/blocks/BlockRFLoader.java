package moarcarts.mods.rf.blocks;

import blusunrize.immersiveengineering.common.util.Utils;
import cofh.api.energy.IEnergyHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import moarcarts.MoarCarts;
import moarcarts.mods.rf.tileentities.TileRFLoader;
import moarcarts.utils.ComparatorUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author SkySom
 */
public class BlockRFLoader extends BlockContainer
{
	IIcon topIcons[] = new IIcon[3];
	IIcon sideIcons[] = new IIcon[3];

	public BlockRFLoader()
	{
		super(Material.iron);
		this.setStepSound(Block.soundTypeMetal);
		this.setBlockName(MoarCarts.MODID + "." + "blockrfloader");
		this.setCreativeTab(CreativeTabs.tabTransport);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		TileEntity te = world.getTileEntity(x, y, z);
		if(te instanceof TileRFLoader)
		{
			if(!world.isRemote)
			{
				TileRFLoader tileRFLoader = (TileRFLoader)te;
				if(Utils.isHammer(player.getCurrentEquippedItem()))
				{
					if(player.isSneaking())
					{
						side = ForgeDirection.OPPOSITES[side];
					}
					tileRFLoader.toggleSide(side);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.topIcons[0] = iconRegister.registerIcon(MoarCarts.MODID + ":rf/rfloadertopinput");
		this.topIcons[1] = iconRegister.registerIcon(MoarCarts.MODID + ":rf/rfloadertopoff");
		this.topIcons[2] = iconRegister.registerIcon(MoarCarts.MODID + ":rf/rfloadertopoutput");

		this.sideIcons[0] = iconRegister.registerIcon(MoarCarts.MODID + ":rf/rfloadersideinput");
		this.sideIcons[1] = iconRegister.registerIcon(MoarCarts.MODID + ":rf/rfloadersideoff");
		this.sideIcons[2] = iconRegister.registerIcon(MoarCarts.MODID + ":rf/rfloadersideoutput");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int blockSide)
	{
		TileRFLoader tileRFLoader = (TileRFLoader) world.getTileEntity(x, y, z);
		if(blockSide < 2)
		{
			return this.topIcons[tileRFLoader.getSideValue(blockSide) + 1];
		} else
		{
			return this.sideIcons[tileRFLoader.getSideValue(blockSide) + 1];
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if(side < 2)
		{
			return this.topIcons[1];
		} else {
			return this.sideIcons[1];
		}
	}

	@Override
	public int getComparatorInputOverride(World world, int posX, int posY, int posZ, int metadata)
	{
		TileEntity tileEntity = world.getTileEntity(posX, posY, posZ);
		if(tileEntity instanceof TileRFLoader)
		{
			return ComparatorUtils.scaleStoredEnergyTo(15, (IEnergyHandler)tileEntity);
		}
		return 0;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metaData)
	{
		return new TileRFLoader();
	}
}
