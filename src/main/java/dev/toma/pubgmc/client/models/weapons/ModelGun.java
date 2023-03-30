package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.ClientHooks;
import dev.toma.pubgmc.client.animation.AnimationElement;
import dev.toma.pubgmc.client.animation.AnimationProcessor;
import dev.toma.pubgmc.client.models.atachments.ModelAttachment;
import dev.toma.pubgmc.client.renderer.item.gun.WeaponRenderer;
import dev.toma.pubgmc.common.capability.player.PlayerData;
import dev.toma.pubgmc.common.items.attachment.AttachmentType;
import dev.toma.pubgmc.common.items.attachment.ItemAttachment;
import dev.toma.pubgmc.common.items.attachment.ItemMagazine;
import dev.toma.pubgmc.common.items.attachment.ItemStock;
import dev.toma.pubgmc.common.items.guns.GunBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class ModelGun extends ModelBase {

    private final List<AnimateEntry> entries = new ArrayList<>();

    public void transformModel() {

    }

    public abstract void renderModel(ItemStack stack);

    public final void addEntry(AnimationElement element, Function<ItemStack, ModelRenderer> stack2RendererFunc) {
        this.entries.add(new AnimateEntry.SingletonEntry(element, stack2RendererFunc));
    }

    public final void addEntryArray(AnimationElement element, Function<ItemStack, ModelRenderer[]> stack2RendererFunc) {
        this.entries.add(new AnimateEntry.ArrayEntry(element, stack2RendererFunc));
    }

    public final void render(ItemStack stack, ItemCameraTransforms.TransformType transformType) {
        GlStateManager.pushMatrix();
        this.transformModel();
        this.renderModel(stack);
        EntityPlayer client = Minecraft.getMinecraft().player;
        boolean flag = client.getHeldItemMainhand() == stack && transformType == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND;
        AnimationProcessor processor = AnimationProcessor.instance();
        for (AnimateEntry entry : entries) {
            GlStateManager.pushMatrix();
            if(flag)
                processor.process(entry.getElement());
            entry.render(stack, 1.0F);
            GlStateManager.popMatrix();
        }
        GlStateManager.popMatrix();
    }

    public final List<AnimationElement> getDefinedElements() {
        List<AnimationElement> list = new ArrayList<>();
        for (AnimateEntry entry : entries)
            list.add(entry.element);
        return list;
    }

    public static void renderBuiltInScope(ModelRenderer scope, ModelRenderer reticle, ModelRenderer overlay, ResourceLocation texture) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = mc.player;
        float aimProgress = ClientHooks.getTransformType() == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND ? PlayerData.get(player).getAimInfo().getProgress(ClientHooks.getRenderTickTime()) : 0.0F;
        float invertedAim = 1.0F - aimProgress;
        TextureManager textureManager = mc.getTextureManager();
        GlStateManager.pushMatrix();
        GlStateManager.scale(1.0F, 1.0F, 0.05F + 0.95F * invertedAim);
        scope.render(1.0F);
        textureManager.bindTexture(texture);
        GlStateManager.color(1.0F, 1.0F, 1.0F, aimProgress);
        reticle.render(1.0F);
        textureManager.bindTexture(ModelAttachment.OVERLAY);
        GlStateManager.color(0.0F, 0.0F, 0.0F, invertedAim);
        overlay.render(1.0F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        textureManager.bindTexture(WeaponRenderer.GUN_TEXTURES);
        GlStateManager.popMatrix();
    }

    public static boolean hasScopeAtachment(ItemStack stack) {
        return has(stack, AttachmentType.SCOPE, Objects::nonNull);
    }

    public static boolean hasExtendedMagazine(ItemStack stack) {
        return has(stack, AttachmentType.MAGAZINE, ItemMagazine::isExtended);
    }

    public static boolean hasQuickdrawMagazine(ItemStack stack) {
        return has(stack, AttachmentType.MAGAZINE, ItemMagazine::isQuickdraw);
    }

    public static boolean hasBulletLoops(ItemStack stack) {
        return has(stack, AttachmentType.STOCK, ItemStock::isFasterReload);
    }

    public static <I extends ItemAttachment> boolean has(ItemStack stack, AttachmentType<I> type, Predicate<I> predicate) {
        if(stack.getItem() instanceof GunBase) {
            GunBase gunBase = (GunBase) stack.getItem();
            I i = gunBase.getAttachment(type, stack);
            return i != null && predicate.test(i);
        }
        return false;
    }

    static abstract class AnimateEntry {

        final AnimationElement element;

        AnimateEntry(AnimationElement element) {
            this.element = element;
        }

        AnimationElement getElement() {
            return element;
        }

        abstract void render(ItemStack stack, float scale);

        static class SingletonEntry extends AnimateEntry {

            final Function<ItemStack, ModelRenderer> stack2RendererFunc;

            SingletonEntry(AnimationElement element, Function<ItemStack, ModelRenderer> stack2RendererFunc) {
                super(element);
                this.stack2RendererFunc = stack2RendererFunc;
            }

            @Override
            void render(ItemStack stack, float scale) {
                stack2RendererFunc.apply(stack).render(scale);
            }
        }

        static class ArrayEntry extends AnimateEntry {

            final Function<ItemStack, ModelRenderer[]> stack2RendererFunc;

            ArrayEntry(AnimationElement element, Function<ItemStack, ModelRenderer[]> stack2RendererFunc) {
                super(element);
                this.stack2RendererFunc = stack2RendererFunc;
            }

            @Override
            void render(ItemStack stack, float scale) {
                for (ModelRenderer renderer : stack2RendererFunc.apply(stack))
                    renderer.render(scale);
            }
        }
    }
}
