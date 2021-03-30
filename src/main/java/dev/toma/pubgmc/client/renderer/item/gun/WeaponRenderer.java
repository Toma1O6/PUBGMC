package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.ClientHooks;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.client.animation.AnimationElement;
import dev.toma.pubgmc.client.animation.AnimationLoader;
import dev.toma.pubgmc.client.animation.AnimationSpec;
import dev.toma.pubgmc.client.animation.interfaces.ElementProvider;
import dev.toma.pubgmc.client.animation.interfaces.HandAnimate;
import dev.toma.pubgmc.client.gui.hands.GuiHandPlacer;
import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.renderer.IRenderConfig;
import dev.toma.pubgmc.client.renderer.item.attachment.AttachmentRenderer;
import dev.toma.pubgmc.common.capability.player.IPlayerData;
import dev.toma.pubgmc.common.capability.player.PlayerData;
import dev.toma.pubgmc.common.items.attachment.AttachmentType;
import dev.toma.pubgmc.common.items.attachment.ItemAttachment;
import dev.toma.pubgmc.common.items.attachment.ItemScope;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.common.items.guns.IReloader;
import dev.toma.pubgmc.proxy.ClientProxy;
import dev.toma.pubgmc.util.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class WeaponRenderer extends TileEntityItemStackRenderer implements ElementProvider {

    public static final ResourceLocation GUN_TEXTURES = Pubgmc.getResource("textures/weapon/gun_textures.png");
    public static final ResourceLocation ATTACHMENT_TEXTURES = Pubgmc.getResource("textures/weapon/attachment_textures.png");
    private final Pair<IRenderConfig, IRenderConfig> handRenderConfigs;
    private final Map<ItemScope, ResourceLocation> attachment2AimAnimationMap = new HashMap<>();
    private IntHashMap<ResourceLocation> reloadAnimations;
    private Map<ItemAttachment, IRenderConfig> renderConfigs = new HashMap<>();
    private ResourceLocation shootAnimation = Pubgmc.getResource(getResourcePrefix() + "_shoot");

    public WeaponRenderer() {
        this.handRenderConfigs = createHandRenderConfigs();
    }

    public abstract ModelGun getWeaponModel();

    public abstract void registerAttachmentRenders();

    public abstract String getResourcePrefix();

    public Pair<IRenderConfig, IRenderConfig> createHandRenderConfigs() {
        return Pair.of(GuiHandPlacer.left, GuiHandPlacer.right);
    }

    public void renderArm(EnumHandSide side) {
        HandAnimate.renderHand(side, side == EnumHandSide.RIGHT ? handRenderConfigs.getRight() : handRenderConfigs.getLeft());
    }

    public void preRender(ItemCameraTransforms.TransformType transformType) {
    }

    @Override
    public final void renderByItem(ItemStack itemStackIn) {
        TextureManager manager = Minecraft.getMinecraft().getTextureManager();
        ItemCameraTransforms.TransformType transformType = ClientHooks.getTransformType();
        preRender(transformType);
        manager.bindTexture(GUN_TEXTURES);
        getWeaponModel().render(itemStackIn, transformType);
        GunBase gun = (GunBase) itemStackIn.getItem();
        IPlayerData data = PlayerData.get(Minecraft.getMinecraft().player);
        float aimPct = transformType == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND ? data.getAimInfo().getProgress(ClientHooks.getRenderTickTime()) : 0.0F;
        for (AttachmentType<?> type : AttachmentType.allTypes) {
            ItemAttachment attachment = gun.getAttachment(type, itemStackIn);
            if(attachment != null) {
                AttachmentRenderer<ItemAttachment> renderer = AttachmentRenderer.getRenderFor(attachment);
                if(renderer == null)
                    continue;
                manager.bindTexture(ATTACHMENT_TEXTURES);
                renderer.render(this, attachment, aimPct);
            }
        }
    }

    public final void init(GunBase gun) {
        this.registerAttachmentRenders();
        registerAimAnimation(null);
        AnimationLoader loader = ClientProxy.getAnimationLoader();
        loader.registerEntry(shootAnimation);
        reloadAnimations = gun.getReloader().registerReloadAnimations(gun, getResourcePrefix(), loader);
    }

    public AnimationSpec getAimAnimation(ItemStack stack) {
        ItemScope scope = ((GunBase) stack.getItem()).getAttachment(AttachmentType.SCOPE, stack);
        ResourceLocation location = attachment2AimAnimationMap.get(scope);
        return ClientProxy.getAnimationLoader().getAnimationSpecification(location);
    }

    public AnimationSpec getReloadAnimation(GunBase gun, ItemStack stack) {
        IReloader reloader = gun.getReloader();
        int ammo = gun.getAmmo(stack);
        if(reloader == IReloader.MAGAZINE) {
            int i = ammo == 0 ? 0 : 1;
            return ClientProxy.getAnimationLoader().getAnimationSpecification(reloadAnimations.lookup(i));
        } else {
            return ClientProxy.getAnimationLoader().getAnimationSpecification(reloadAnimations.lookup(ammo));
        }
    }

    public AnimationSpec getShootingAnimation() {
        return ClientProxy.getAnimationLoader().getAnimationSpecification(shootAnimation);
    }

    public final void registerRenderConfig(ItemAttachment attachment, IRenderConfig config) {
        renderConfigs.put(attachment, config);
        if(attachment instanceof ItemScope) {
            registerAimAnimation((ItemScope) attachment);
        }
    }

    public final void registerRenderConfig(IRenderConfig config, ItemAttachment... attachments) {
        if(attachments.length == 0)
            throw new IllegalArgumentException("Must supply atleast one attachment");
        for (ItemAttachment attachment : attachments) {
            registerRenderConfig(Objects.requireNonNull(attachment), config);
        }
    }

    @Override
    public List<AnimationElement> getElements() {
        return this.getWeaponModel().getDefinedElements();
    }

    private void registerAimAnimation(ItemScope attachment) {
        ResourceLocation location;
        if(attachment == null) {
            location = Pubgmc.getResource(this.getResourcePrefix() + "_aim");
        } else location = Pubgmc.getResource(this.getResourcePrefix() + "_aim_" + attachment.getRegistryName().getResourcePath());
        attachment2AimAnimationMap.put(attachment, location);
        ClientProxy.getAnimationLoader().registerEntry(location);
    }

    public final IRenderConfig getRenderConfig(ItemAttachment attachment) {
        return renderConfigs.get(attachment);
    }

    public void setRenderConfigsTempt(Map<ItemAttachment, IRenderConfig> map) {
        this.renderConfigs = map;
    }

    public Map<ItemAttachment, IRenderConfig> getRenderConfigs() {
        return renderConfigs;
    }
}