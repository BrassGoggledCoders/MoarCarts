package moarcarts.mods.rf.blocks;

import boilerplate.common.blocks.SideType;
import boilerplate.common.blocks.SidedBlock;
import boilerplate.common.utils.ComparatorUtils;
import cofh.api.energy.IEnergyHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import moarcarts.MoarCarts;
import moarcarts.mods.rf.tileentities.TileRFLoader;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class BlockRFLoader extends SidedBlock
{
	public BlockRFLoader()
	{
		super(Material.iron);
		this.setStepSound(Block.soundTypeMetal);
		this.setBlockName(MoarCarts.MODID + "." + "blockrfloader");
		this.setCreativeTab(CreativeTabs.tabTransport);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.topIcons[SideType.INPUT.ordinal()] = iconRegister.registerIcon(MoarCarts.MODID + ":rf/rfloadertopinput");
		this.topIcons[SideType.NONE.ordinal()] = iconRegister.registerIcon(MoarCarts.MODID + ":rf/rfloadertopoff");
		this.topIcons[SideType.OUTPUT.ordinal()] = iconRegister.registerIcon(MoarCarts.MODID + ":rf/rfloadertopoutput");

		this.sideIcons[SideType.INPUT.ordinal()] = iconRegister.registerIcon(MoarCarts.MODID + ":rf/rfloadersideinput");
		this.sideIcons[SideType.NONE.ordinal()] = iconRegister.registerIcon(MoarCarts.MODID + ":rf/rfloadersideoff");
		this.sideIcons[SideType.OUTPUT.ordinal()] = iconRegister.registerIcon(MoarCarts.MODID + ":rf/rfloadersideoutput");
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
