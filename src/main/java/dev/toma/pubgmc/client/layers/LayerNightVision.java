package dev.toma.pubgmc.client.layers;

import dev.toma.pubgmc.api.capability.SpecialEquipmentSlot;
import dev.toma.pubgmc.api.client.model.AbstractNightVisionModel;
import dev.toma.pubgmc.api.inventory.SpecialInventoryProvider;
import dev.toma.pubgmc.api.item.NightVisionGoggles;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class LayerNightVision<T extends EntityLivingBase> implements LayerRenderer<T> {

    private static final Map<Item, ModelRenderData<AbstractNightVisionModel>> RENDER_DATA_MAP = new IdentityHashMap<>();
    private final RenderLivingBase<T> renderer;
    private final Function<T, SpecialInventoryProvider> inventoryProvider;
    private final Predicate<T> isNightVisionActive;

    public LayerNightVision(RenderLivingBase<T> renderer, Function<T, SpecialInventoryProvider> inventoryProvider, Predicate<T> isNightVisionActive) {
        this.renderer = renderer;
        this.inventoryProvider = inventoryProvider;
        this.isNightVisionActive = isNightVisionActive;
    }

    public static <T extends Item & NightVisionGoggles> void registerRenderer(T item, AbstractNightVisionModel model, ResourceLocation texture) {
        RENDER_DATA_MAP.put(item, new ModelRenderData<>(model, texture));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void doRenderLayer(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        SpecialInventoryProvider provider = inventoryProvider.apply((T) entity);
        if (provider == null) {
            return;
        }
        ItemStack nightVisionSlot = provider.getSpecialItemFromSlot(SpecialEquipmentSlot.NIGHT_VISION);
        if (nightVisionSlot.isEmpty()) {
            return;
        }
        ModelRenderData<AbstractNightVisionModel> renderData = RENDER_DATA_MAP.get(nightVisionSlot.getItem());
        if (renderData == null) {
            return;
        }
        AbstractNightVisionModel model = renderData.getModel();
        renderer.bindTexture(renderData.getTexture());
        model.setModelAttributes(renderer.getMainModel());
        model.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTicks);
        model.setupRotations(entity, isNightVisionActive.test((T) entity));
        model.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        GlStateManager.color(1f, 1f, 1f);
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
