package moarcarts.blocks;

import moarcarts.MoarCarts;
import net.minecraft.block.material.Material;
import xyz.brassgoggledcoders.boilerplate.lib.common.blocks.BaseBlock;

public class BlockMinecart extends BaseBlock
{
	public BlockMinecart()
	{
		super(Material.iron);
		setResistance(3.5F);
		setHardness(1.05F);
		setStepSound(soundTypeMetal);
		setUnlocalizedName("minecart");
		setRegistryName("minecart");
		this.setCreativeTab(MoarCarts.moarcartsTab);
	}
}
