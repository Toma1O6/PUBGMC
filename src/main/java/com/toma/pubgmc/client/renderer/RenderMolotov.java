package com.toma.pubgmc.client.renderer;

import com.toma.pubgmc.common.entity.EntityMolotov;
import com.toma.pubgmc.init.PMCRegistry;
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

import javax.annotation.Nullable;
import java.util.List;

public class RenderMolotov extends Render<EntityMolotov> {
    public RenderMolotov(RenderManager manager) {
        super(manager);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityMolotov entity) {
        return null;
    }

    @Override
    public void doRender(EntityMolotov entity, double x, double y, double z, float entityYaw, float partialTicks) {
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

            IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(new ItemStack(PMCRegistry.PMCItems.MOLOTOV));
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder buffer = tessellator.getBuffer();
            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.ITEM);

            for (EnumFacing enumfacing : EnumFacing.values()) {
                this.renderQuads(buffer, model.getQuads(null, enumfacing, 0L));
            }

            this.renderQuads(buffer, model.getQuads(null, null, 0L));
            tessellator.draw();

            GlStateManager.enableLighting();
        }
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    private void renderQuads(BufferBuilder buffer, List<BakedQuad> quads) {
        int i = 0;
        for (int j = quads.size(); i < j; ++i) {
            BakedQuad quad = quads.get(i);
            LightUtil.renderQuadColor(buffer, quad, -1);
        }
    }
}
