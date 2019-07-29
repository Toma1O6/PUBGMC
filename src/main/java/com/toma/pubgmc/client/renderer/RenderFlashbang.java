package com.toma.pubgmc.client.renderer;

import com.toma.pubgmc.common.entity.EntityFlashbang;
import com.toma.pubgmc.init.PMCRegistry.PMCItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.pipeline.LightUtil;
import org.lwjgl.opengl.GL11;

import java.util.List;

public class RenderFlashbang extends Render<EntityFlashbang> {

    public RenderFlashbang(RenderManager manager) {
        super(manager);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityFlashbang entity) {
        return null;
    }

    @Override
    public void doRender(EntityFlashbang entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        {
            GlStateManager.translate(x, y, z);
            GlStateManager.translate(-0.5, 0, -0.5);
            GlStateManager.scale(0.6f, 0.6f, 0.6f);
            if (entity.onGround) {
                GlStateManager.rotate(90, 0, 0, 1);
                GlStateManager.translate(-0.2, -1.0, 0.4);
            }
            this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            GlStateManager.disableLighting();
            IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(new ItemStack(PMCItems.FLASHBANG));
            Tessellator tesselator = Tessellator.getInstance();
            BufferBuilder builder = tesselator.getBuffer();
            builder.begin(GL11.GL_QUADS, DefaultVertexFormats.ITEM);
            for (EnumFacing facing : EnumFacing.values()) {
                this.renderQuads(builder, model.getQuads(null, facing, 0L));
            }
            this.renderQuads(builder, model.getQuads(null, null, 0L));
            tesselator.draw();
            GlStateManager.enableLighting();
        }
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    private void renderQuads(BufferBuilder buffer, List<BakedQuad> list) {
        for (BakedQuad quad : list) {
            LightUtil.renderQuadColor(buffer, quad, -1);
        }
    }
}
