package dev.toma.pubgmc.client.renderer.tileentity;

import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.common.tileentity.TileEntityLootGenerator;
import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.init.PMCItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class LootSpawnerRenderer extends TileEntitySpecialRenderer<TileEntityLootGenerator> {

    @Override
    public void render(TileEntityLootGenerator te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        if (te == null || te.isInvalid()) {
            return;
        }
        double distance = Math.sqrt(x * x + y * y + z * z);
        if(distance > ConfigPMC.other().maxLootRenderDistance.get())
            return;
        Minecraft mc = Minecraft.getMinecraft();
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        for (int i = 0; i < te.getSizeInventory(); i++) {
            ItemStack stack = te.getStackInSlot(i);
            if(stack.isEmpty())
                continue;
            RenderItem renderItem = mc.getRenderItem();
            int j = i / 3;
            int k = i % 3;
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.2 + j * 0.3, 0, 0.2 + k * 0.3);
            preRenderCallback(stack.getItem(), i);
            renderItem.renderItem(stack, ItemCameraTransforms.TransformType.GROUND);
            GlStateManager.popMatrix();
        }
        GlStateManager.popMatrix();
        GlStateManager.translate(x, y, z);
    }

    @SuppressWarnings("ConditionCoveredByFurtherCondition")
    void preRenderCallback(Item item, int index) {
        if(item instanceof GunBase) {
            GlStateManager.scale(0.4F, 0.4F, 0.4F);
            GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
        } else if(item == PMCItems.ENERGYDRINK || item == PMCItems.PAINKILLERS) {
            GlStateManager.translate(0, 0.15, 0);
        } else if(item == PMCItems.FIRSTAIDKIT || item == PMCItems.BANDAGE) {
            GlStateManager.translate(0, 0.2, 0);
        } else if(item == PMCItems.MEDKIT) {
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0, 0.2, 0);
        } else if(item == PMCItems.FUELCAN) {
            GlStateManager.scale(0.7F, 0.7F, 0.7F);
            GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.translate(0, 0.3, 0);
        } else if(!(item instanceof ItemBlock)) {
            GlStateManager.scale(0.6F, 0.6F, 0.6F);
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
        }
    }
}
