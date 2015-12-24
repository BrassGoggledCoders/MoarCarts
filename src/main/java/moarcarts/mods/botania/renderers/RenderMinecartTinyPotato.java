package moarcarts.mods.botania.renderers;

import moarcarts.entities.EntityMinecartTEBase;
import moarcarts.renderers.RenderMinecartTEBase;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import vazkii.botania.api.item.TinyPotatoRenderEvent;
import vazkii.botania.client.core.handler.ContributorFancinessHandler;
import vazkii.botania.client.core.helper.ShaderHelper;
import vazkii.botania.client.lib.LibResources;
import vazkii.botania.client.model.ModelTinyPotato;
import vazkii.botania.common.block.tile.TileTinyPotato;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.bauble.ItemFlightTiara;
import vazkii.botania.common.item.material.ItemManaResource;
import vazkii.botania.common.item.relic.ItemInfiniteFruit;

/**
 * @author SkySom
 */
public class RenderMinecartTinyPotato extends RenderMinecartTEBase
{
	private static final ResourceLocation texture = new ResourceLocation(LibResources.MODEL_TINY_POTATO);
	private static final ResourceLocation textureGrayscale = new ResourceLocation(LibResources.MODEL_TINY_POTATO_GS);
	private static final ModelTinyPotato model = new ModelTinyPotato();

	@Override
	protected void renderCustom(EntityMinecartTEBase entityMinecartTEBase, Block block)
	{
		TileTinyPotato potato = (TileTinyPotato)entityMinecartTEBase.getTileEntity();
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1F, 1F, 1F, 1F);

		Minecraft mc = Minecraft.getMinecraft();
		mc.renderEngine.bindTexture(texture);
		String name = potato.name.toLowerCase();

		boolean usedShader = false;
		if(name.startsWith("gaia ")) {
			ShaderHelper.useShader(ShaderHelper.doppleganger);
			name = name.substring(5);
			usedShader = true;
		} else if(name.startsWith("hot ")) {
			ShaderHelper.useShader(ShaderHelper.halo);
			name = name.substring(4);
			usedShader = true;
		} else if(name.startsWith("magic ")) {
			ShaderHelper.useShader(ShaderHelper.enchanterRune);
			name = name.substring(6);
			usedShader = true;
		} else if(name.startsWith("gold ")) {
			ShaderHelper.useShader(ShaderHelper.gold);
			name = name.substring(5);
			usedShader = true;
		} else if(name.startsWith("snoop ")) {
			ShaderHelper.useShader(ShaderHelper.terraPlateRune);
			name = name.substring(6);
			usedShader = true;
		}

		GL11.glTranslatef(0.5F, 1.5F, 0.5F);
		GL11.glScalef(1F, -1F, -1F);
		int meta = potato.getWorldObj() == null ? 3 : potato.getBlockMetadata();
		float rotY = meta * 90F - 180F;
		GL11.glRotatef(rotY, 0F, 1F, 0F);

		float jump = potato.jumpTicks;
		if(jump > 0)
			jump -= 1.0F;

		float up = (float) -Math.abs(Math.sin(jump / 10 * Math.PI)) * 0.2F;
		float rotZ = (float) Math.sin(jump / 10 * Math.PI) * 2;

		GL11.glTranslatef(0F, up, 0F);
		GL11.glRotatef(rotZ, 0F, 0F, 1F);

		GL11.glPushMatrix();
		if(name.equals("pahimar")) {
			GL11.glScalef(1F, 0.3F, 1F);
			GL11.glTranslatef(0F, 3.5F, 0F);
		} else if(name.equals("kyle hyde"))
			mc.renderEngine.bindTexture(textureGrayscale);
		else if(name.equals("dinnerbone") || name.equals("grumm")) {
			GL11.glRotatef(180F, 0F, 0F, 1F);
			GL11.glTranslatef(0F, -2.625F, 0F);
		} else if(name.equals("aureylian"))
			GL11.glColor3f(1F, 0.5F, 1F);


		boolean render = !(name.equals("mami") || name.equals("soaryn") || name.equals("eloraam") && jump != 0);
		if(render)
			model.render();
		if(name.equals("kingdaddydmac")) {
			GL11.glTranslated(0.5F, 0F, 0F);
			model.render();
		}

		if(usedShader)
			ShaderHelper.releaseShader();

		GL11.glPopMatrix();

		if(!name.isEmpty()) {
			GL11.glPushMatrix();
			mc.renderEngine.bindTexture(TextureMap.locationItemsTexture);

			ContributorFancinessHandler.firstStart();

			float scale = 1F / 4F;
			GL11.glTranslatef(0F, 1F, 0F);
			GL11.glScalef(scale, scale, scale);
			if(name.equals("phi") || name.equals("vazkii")) {
				GL11.glTranslatef(0.45F, 0F, 0.4F);
				GL11.glRotatef(90F, 0F, 1F, 0F);
				GL11.glRotatef(20F, 1F, 0F, 1F);
				renderIcon(((ItemManaResource) ModItems.manaResource).phiFlowerIcon);

				if(name.equals("vazkii")) {
					GL11.glRotatef(-20F, 1F, 0F, 1F);
					GL11.glScalef(1.25F, 1.25F, 1.25F);
					GL11.glRotatef(180F, 0F, 0F, 1F);
					GL11.glTranslatef(-1.5F, -1.3F, -0.75F);
					renderIcon(((ItemManaResource) ModItems.manaResource).nerfBatIcon);
				}
			} else if(name.equals("skull kid")) {
				GL11.glScalef(1.25F, 1.25F, 1.25F);
				GL11.glRotatef(180F, 0F, 0F, 1F);
				GL11.glTranslatef(-0.5F, -1.2F, -0.4F);
				renderIcon(ModItems.cosmetic.getIconFromDamage(23));
			} else if(name.equals("kamina")) {
				GL11.glScalef(1.25F, 1.25F, 1.25F);
				GL11.glRotatef(180F, 0F, 0F, 1F);
				GL11.glTranslatef(-0.5F, -1.1F, -0.4F);
				renderIcon(ModItems.cosmetic.getIconFromDamage(26));
			} else if(name.equals("haighyorkie")) {
				GL11.glScalef(1.25F, 1.25F, 1.25F);
				GL11.glRotatef(180F, 0F, 0F, 1F);
				GL11.glRotatef(90F, 0F, 1F, 0F);
				GL11.glTranslatef(-0.5F, -1.2F, -0.4F);
				renderIcon(((ItemManaResource) ModItems.manaResource).goldfishIcon);
			} else if(name.equals("chitoge")) {
				GL11.glScalef(1.25F, 1.25F, 1.25F);
				GL11.glRotatef(180F, 0F, 0F, 1F);
				GL11.glTranslatef(-0.5F, -0.7F, 0.1F);
				renderIcon(ModItems.cosmetic.getIconFromDamage(7));
			} else if(name.equals("direwolf20")) {
				GL11.glRotatef(180F, 0F, 0F, 1F);
				GL11.glTranslatef(-0.5F, -2.2F, -0.5F);
				renderIcon(ModItems.cosmetic.getIconFromDamage(0));
			} else if(name.equals("doctor")) {
				GL11.glScalef(1.25F, 1.25F, 1.25F);
				GL11.glRotatef(180F, 0F, 0F, 1F);
				GL11.glTranslatef(-0.5F, -1.15F, -0.4F);
				renderIcon(ModItems.cosmetic.getIconFromDamage(25));
			} else if(name.equals("snoo")) {
				GL11.glScalef(1.25F, 1.25F, 1.25F);
				GL11.glRotatef(180F, 0F, 0F, 1F);
				GL11.glTranslatef(-0.5F, -0.7F, 0.1F);
				GL11.glRotatef(20F, 0F, 0F, 1F);
				renderIcon(ModItems.cosmetic.getIconFromDamage(24));
			} else if(name.equals("charlotte")) {
				GL11.glScalef(1.25F, 1.25F, 1.25F);
				GL11.glRotatef(180F, 0F, 0F, 1F);
				GL11.glTranslatef(-0.5F, -1.2F, -0.4F);
				renderIcon(ModItems.cosmetic.getIconFromDamage(12));
			} else if(name.equals("greg") || name.equals("gregorioust")) {
				GL11.glScalef(1.25F, 1.25F, 1.25F);
				GL11.glRotatef(180F, 0F, 0F, 1F);
				GL11.glRotatef(90F, 0F, 1F, 0F);
				GL11.glTranslatef(-0.5F, -1.5F, -0.4F);
				renderIcon(Items.book.getIconFromDamage(0));

				mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
				GL11.glTranslatef(0.5F, 0.5F, 0F);
				GL11.glScalef(0.3F, 0.3F, 0.3F);

				RenderBlocks.getInstance().renderBlockAsItem(Blocks.iron_ore, 0, 1F);
			} else if(name.equals("profmobius")) {
				GL11.glScalef(1.25F, 1.25F, 1.25F);
				GL11.glRotatef(180F, 0F, 0F, 1F);
				GL11.glRotatef(90F, 0F, 1F, 0F);
				GL11.glTranslatef(-0.5F, -1.2F, -0.4F);
				renderIcon(Items.bread.getIconFromDamage(0));
			} else if(name.equals("martysgames") || name.equals("marty")) {
				GL11.glScalef(0.7F, 0.7F, 0.7F);
				GL11.glRotatef(180F, 0F, 0F, 1F);
				GL11.glTranslatef(-0.75F, -2.4F, -0.7F);
				GL11.glRotatef(10F, 0F, 0F, 1F);
				renderIcon(ItemInfiniteFruit.dasBootIcon);
			} else if(name.equals("tromped")) {
				GL11.glScalef(1.25F, 1.25F, 1.25F);
				GL11.glRotatef(180F, 0F, 0F, 1F);
				GL11.glRotatef(90F, 0F, 1F, 0F);
				GL11.glTranslatef(-0.5F, -1.2F, -0.4F);
				renderIcon(ModItems.cacophonium.getIconFromDamage(0));
			} else if(name.equals("kain vinosec")) {
				GL11.glScalef(1.25F, 1.25F, 1.25F);
				GL11.glRotatef(180F, 0F, 0F, 1F);
				GL11.glRotatef(90F, 0F, 1F, 0F);
				GL11.glTranslatef(-0.3F, -1.5F, -0.4F);
				renderIcon(ModItems.recordGaia1.getIconFromDamage(0));
				GL11.glTranslatef(0F, 0F, 0.85F);
				renderIcon(ModItems.recordGaia2.getIconFromDamage(0));
			} else if(name.equals("mankrik")) {
				GL11.glScalef(1.25F, 1.25F, 1.25F);
				GL11.glRotatef(180F, 0F, 0F, 1F);
				GL11.glTranslatef(-0.5F, -0.2F, -0.1F);
				renderIcon(ModItems.cosmetic.getIconFromDamage(31));
			} else if(name.equals("kurumi")) {
				GL11.glScalef(0.4F, 0.4F, 0.4F);
				GL11.glRotatef(180F, 0F, 0F, 1F);
				GL11.glTranslatef(-0.9F, -2.5F, -1.3F);
				renderIcon(ModItems.cosmetic.getIconFromDamage(17));
			} else if(name.equals("ichun")) {
				GL11.glScalef(1.25F, 1.25F, 1.25F);
				GL11.glRotatef(180F, 0F, 0F, 1F);
				GL11.glTranslatef(-0.5F, -1.2F, -0.4F);
				renderIcon(ModItems.cosmetic.getIconFromDamage(15));
			} else if(name.equals("wiiv") || name.equals("dylan4ever") || name.equals("dylankaiser")) {
				GL11.glScalef(1.5F, 1.5F, 1.5F);
				GL11.glRotatef(180F, 0F, 0F, 1F);
				GL11.glRotatef(90F, 0F, 1F, 0F);
				GL11.glTranslatef(-0.5F, -1.1F, -0.325F);
				renderIcon(Items.painting.getIconFromDamage(0));
			} else if(name.equals("jibril")) {
				GL11.glScalef(1.5F, 1.5F, 1.5F);
				GL11.glTranslatef(0F, 0.7F, 0F);
				GL11.glRotatef(90F, 0F, 1F, 0F);
				ItemFlightTiara.renderHalo(null, 0.0F);
			} else if(name.equals("nebris")) {
				mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
				GL11.glRotatef(180F, 1F, 0F, 0F);
				RenderBlocks.getInstance().renderBlockAsItem(Blocks.glowstone, 0, 1F);
			} else if(name.equals("ible")) {
				mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
				GL11.glScalef(1.2F, 1.2F, 1.2F);
				GL11.glTranslatef(0F, 0.7F, 0F);
				GL11.glRotatef(180F, 1F, 0F, 0F);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				RenderBlocks.getInstance().renderBlockAsItem(Blocks.portal, 0, 1F);
			} else if(name.equals("razz") || name.equals("razzleberryfox")) {
				GL11.glScalef(1.25F, 1.25F, 1.25F);
				GL11.glRotatef(180F, 0F, 0F, 1F);
				GL11.glRotatef(90F, 0F, 1F, 0F);
				GL11.glTranslatef(-0.5F, -1F, 0.45F);
				renderIcon(ModItems.cosmetic.getIconFromDamage(8));
			} else if(name.equals("etho") || name.equals("ethoslab")) {
				GL11.glScalef(1.25F, 1.25F, 1.25F);
				GL11.glRotatef(180F, 0F, 0F, 1F);
				GL11.glRotatef(90F, 0F, 1F, 0F);
				GL11.glTranslatef(-0.5F, -1.2F, -0.4F);
				renderIcon(Items.cookie.getIconFromDamage(0));
			} else if(name.equals("sethbling")) {
				mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
				GL11.glScalef(1.2F, 1.2F, 1.2F);
				GL11.glTranslatef(0F, 0.9F, 0F);
				GL11.glRotatef(180F, 1F, 0F, 0F);
				RenderBlocks.getInstance().renderBlockAsItem(Blocks.command_block, 0, 1F);
			} else if(name.equals("bdoubleo100") || name.equals("bdoubleo")) {
				GL11.glScalef(1.25F, 1.25F, 1.25F);
				GL11.glRotatef(180F, 0F, 0F, 1F);
				GL11.glTranslatef(-1F, -1.1F, -0.1F);
				renderIcon(Items.stick.getIconFromDamage(0));
			} else if(name.equals("kingdaddydmac")) {
				GL11.glScalef(0.5F, 0.5F, 0.5F);
				GL11.glRotatef(180F, 0F, 0F, 1F);
				GL11.glRotatef(90F, 0F, 1F, 0F);
				GL11.glTranslatef(-0.3F, -2.5F, 1.075F);
				renderIcon(ModItems.manaRing.getIconFromDamage(0));
				GL11.glTranslatef(0F, 0F, -4F);
				renderIcon(ModItems.manaRing.getIconFromDamage(0));

				mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
				GL11.glScalef(1.5F, 1.5F, 1.5F);
				GL11.glTranslatef(1.5F, -0.5F, 0.7F);
				RenderBlocks.getInstance().renderBlockAsItem(Blocks.cake, 0, 1F);
			} else if(name.equals("sjin")) {
				GL11.glScalef(1.25F, 1.25F, 1.25F);
				GL11.glRotatef(180F, 0F, 0F, 1F);
				GL11.glTranslatef(-0.5F, -1.27F, -0.4F);
				renderIcon(ModItems.cosmetic.getIconFromDamage(27));
			} else if(name.equals("martyn") || name.equals("inthelittlewood")) {
				GL11.glScalef(1.25F, 1.25F, 1.25F);
				GL11.glRotatef(180F, 0F, 0F, 1F);
				GL11.glTranslatef(-0.5F, -0.45F, -0.1F);
				mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
				renderIcon(Blocks.sapling.getIcon(0, 0));
			}else if(ContributorFancinessHandler.flowerMap != null && ContributorFancinessHandler.flowerMap.containsKey(name)) {
				IIcon icon = ContributorFancinessHandler.flowerMap.get(name);
				if(icon != null) {
					mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
					GL11.glRotatef(180F, 1F, 0F, 0F);
					GL11.glTranslatef(-0.5F, -0.5F, 0F);
					ShaderHelper.useShader(ShaderHelper.gold);
					renderIcon(icon);
					ShaderHelper.releaseShader();
				}
			}

			GL11.glPopMatrix();
		}

		MinecraftForge.EVENT_BUS.post(new TinyPotatoRenderEvent(potato, potato.name, 0, 0, 0, 0));

		GL11.glRotatef(-rotZ, 0F, 0F, 1F);
		GL11.glRotatef(-rotY, 0F, 1F, 0F);
		GL11.glColor3f(1F, 1F, 1F);
		GL11.glScalef(1F, -1F, -1F);

		GL11.glPopMatrix();
	}

	public void renderIcon(IIcon icon) {
		float f = icon.getMinU();
		float f1 = icon.getMaxU();
		float f2 = icon.getMinV();
		float f3 = icon.getMaxV();
		ItemRenderer.renderItemIn2D(Tessellator.instance, f1, f2, f, f3, icon.getIconWidth(), icon.getIconHeight(), 1F / 16F);
	}
}
