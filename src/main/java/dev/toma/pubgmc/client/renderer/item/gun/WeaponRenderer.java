package dev.toma.pubgmc.client.renderer.item.gun;

import dev.toma.pubgmc.ClientHooks;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.renderer.item.IRenderConfig;
import dev.toma.pubgmc.client.renderer.item.attachment.AttachmentRenderer;
import dev.toma.pubgmc.common.items.attachment.AttachmentType;
import dev.toma.pubgmc.common.items.attachment.ItemAttachment;
import dev.toma.pubgmc.common.items.guns.GunBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class WeaponRenderer extends TileEntityItemStackRenderer {

    public static final ResourceLocation GUN_TEXTURES = Pubgmc.getResource("textures/weapon/gun_textures.png");
    public static final ResourceLocation ATTACHMENT_TEXTURES = Pubgmc.getResource("textures/weapon/attachment_textures.png");
    private Map<ItemAttachment, IRenderConfig> renderConfigs = new HashMap<>();

    public abstract ModelGun getWeaponModel();

    public abstract void registerAttachmentRenders();

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
        for (AttachmentType<?> type : AttachmentType.allTypes) {
            ItemAttachment attachment = gun.getAttachment(type, itemStackIn);
            if(attachment != null) {
                AttachmentRenderer<ItemAttachment> renderer = AttachmentRenderer.getRenderFor(attachment);
                if(renderer == null)
                    continue;
                manager.bindTexture(ATTACHMENT_TEXTURES);
                renderer.render(this, attachment);
            }
        }
    }

    public final void registerRenderConfig(ItemAttachment attachment, IRenderConfig config) {
        renderConfigs.put(attachment, config);
    }

    public final void registerRenderConfig(IRenderConfig config, ItemAttachment... attachments) {
        if(attachments.length == 0)
            throw new IllegalArgumentException("Must supply atleast one attachment");
        for (ItemAttachment attachment : attachments) {
            registerRenderConfig(Objects.requireNonNull(attachment), config);
        }
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