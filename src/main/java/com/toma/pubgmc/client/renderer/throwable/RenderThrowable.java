package com.toma.pubgmc.client.renderer.throwable;

import com.toma.pubgmc.common.entity.throwables.EntityThrowableExplodeable;
import com.toma.pubgmc.common.items.ItemExplodeable;
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

public class RenderThrowable<E extends EntityThrowableExplodeable, I extends ItemExplodeable> extends Render<E> {

    private final I item;
    private final ItemStack stack;
    @Nullable
    private final IRenderAction<E, I> renderAction;

    public RenderThrowable(RenderManager manager, I item) {
        this(manager, item, null);
    }

    public RenderThrowable(RenderManager manager, I item, @Nullable IRenderAction action) {
        super(manager);
        this.item = item;
        this.renderAction = action;
        this.stack = new ItemStack(this.item);
    }

    @Override
    public void doRender(E entity, double x, double y, double z, float entityYaw, float partialTicks) {
        if(entity.isInvisible()) {
            return;
        }
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        if(renderAction != null) {
            renderAction.onRender(entity, item, x, y, z, partialTicks);
        }
        GlStateManager.scale(0.6f, 0.6f, 0.6f);
        GlStateManager.translate(-0.5f, 0, -0.5f);
        float rotationProgress = entity.lastRotation + (entity.rotation - entity.lastRotation) * partialTicks;
        GlStateManager.rotate(rotationProgress, 1.0F, 0.5F, 1.0F);
        this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        GlStateManager.disableLighting();
        IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(this.stack);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.ITEM);
        for(EnumFacing facing : EnumFacing.values()) {
            renderQuads(bufferBuilder, model.getQuads(null, facing, 0L));
        }
        renderQuads(bufferBuilder, model.getQuads(null, null, 0L));
        tessellator.draw();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(E entity) {
        return null;
    }

    protected static void renderQuads(BufferBuilder buffer, List<BakedQuad> quads) {
        int i = 0;
        for (int j = quads.size(); i < j; ++i) {
            BakedQuad quad = quads.get(i);
            LightUtil.renderQuadColor(buffer, quad, -1);
        }
    }

    @FunctionalInterface
    public interface IRenderAction<E extends EntityThrowableExplodeable, I extends ItemExplodeable> {
        void onRender(E grenade, I item, double x, double y, double z, float partialTicks);
    }
}
