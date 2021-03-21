package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.common.items.attachment.AttachmentType;
import dev.toma.pubgmc.common.items.attachment.ItemAttachment;
import dev.toma.pubgmc.common.items.attachment.ItemMagazine;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.init.PMCItems;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;

import java.util.Objects;
import java.util.function.Predicate;

public abstract class ModelGun extends ModelBase {

    public abstract void renderModel(ItemStack stack);

    public final void render(ItemStack stack, ItemCameraTransforms.TransformType transformType) {
        GlStateManager.pushMatrix();
        this.renderModel(stack);
        GlStateManager.popMatrix();
    }

    public static boolean hasRedDot(ItemStack stack) {
        return has(stack, AttachmentType.SCOPE, scope -> scope == PMCItems.RED_DOT);
    }

    public static boolean hasHoloSight(ItemStack stack) {
        return has(stack, AttachmentType.SCOPE, scope -> scope == PMCItems.HOLOGRAPHIC);
    }

    public static boolean hasScopeAtachment(ItemStack stack) {
        return has(stack, AttachmentType.SCOPE, Objects::nonNull);
    }

    public static boolean hasExtendedMagazine(ItemStack stack) {
        return has(stack, AttachmentType.MAGAZINE, ItemMagazine::isExtended);
    }

    public static <I extends ItemAttachment> boolean has(ItemStack stack, AttachmentType<I> type, Predicate<I> predicate) {
        if(stack.getItem() instanceof GunBase) {
            GunBase gunBase = (GunBase) stack.getItem();
            I i = gunBase.getAttachment(type, stack);
            return i != null && predicate.test(i);
        }
        return false;
    }
}
