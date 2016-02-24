package xyz.brassgoggledcoders.moarcarts.renderers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelMinecart;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ISmartItemModel;

import java.util.Collections;
import java.util.List;

/**
 * @author SkySom
 */
public class RenderItemMinecartBase implements ISmartItemModel
{
	public static ResourceLocation TEXTURE = new ResourceLocation("textures/entity/minecart.png");

	public ModelBase model = new ModelMinecart();
	public TileEntity renderTileEntity;


	@Override
	public IBakedModel handleItemState(ItemStack stack)
	{
		return null;
	}

	@Override
	public List<BakedQuad> getFaceQuads(EnumFacing p_177551_1_)
	{
		return Collections.emptyList();
	}

	@Override
	public List<BakedQuad> getGeneralQuads()
	{
		return null;
	}

	@Override
	public boolean isAmbientOcclusion()
	{
		return true;
	}

	@Override
	public boolean isGui3d()
	{
		return false;
	}

	@Override
	public boolean isBuiltInRenderer()
	{
		return false;
	}

	@Override
	public TextureAtlasSprite getParticleTexture()
	{
		return null;
	}

	@Override
	public ItemCameraTransforms getItemCameraTransforms()
	{
		return ItemCameraTransforms.DEFAULT;
	}
}
