package dev.toma.pubgmc.client.layers;

import dev.toma.pubgmc.api.capability.SpecialEquipmentSlot;
import dev.toma.pubgmc.api.inventory.SpecialInventoryProvider;
import dev.toma.pubgmc.api.item.Backpack;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Function;

public class LayerBackpack<E extends EntityLivingBase> implements LayerRenderer<E> {

    private static final Map<Item, ModelRenderData<?>> RENDER_DATA_MAP = new IdentityHashMap<>();
    private final RenderLivingBase<?> renderer;
    private final Function<E, SpecialInventoryProvider> inventoryProvider;

    public LayerBackpack(RenderLivingBase<E> renderer, Function<E, SpecialInventoryProvider> inventoryProvider) {
        this.renderer = renderer;
        this.inventoryProvider = inventoryProvider;
    }

    public static <T extends Item & Backpack> void registerRenderer(T item, ModelBase model, ResourceLocation texture) {
        RENDER_DATA_MAP.put(item, new ModelRenderData<>(model, texture));
    }

    @Override
    public void doRenderLayer(E entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        SpecialInventoryProvider provider = inventoryProvider.apply(entity);
        if (provider == null) {
            return;
        }
        ItemStack ghillieSlot = provider.getSpecialItemFromSlot(SpecialEquipmentSlot.GHILLIE);
        if (!ghillieSlot.isEmpty()) {
            return;
        }
        ItemStack backpackSlot = provider.getSpecialItemFromSlot(SpecialEquipmentSlot.BACKPACK);
        if (backpackSlot.isEmpty()) {
            return;
        }
        ModelRenderData<?> renderData = RENDER_DATA_MAP.get(backpackSlot.getItem());
        if (renderData == null) {
            return;
        }
        renderer.bindTexture(renderData.getTexture());
        renderData.getModel().render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
