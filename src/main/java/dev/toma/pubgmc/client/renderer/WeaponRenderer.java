package dev.toma.pubgmc.client.renderer;

import dev.toma.pubgmc.ClientHooks;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.client.models.ModelGun;
import dev.toma.pubgmc.common.items.attachment.AttachmentType;
import dev.toma.pubgmc.common.items.attachment.ItemAttachment;
import dev.toma.pubgmc.common.items.guns.GunBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public abstract class WeaponRenderer extends TileEntityItemStackRenderer {

    public static final ResourceLocation GUN_TEXTURES = Pubgmc.getResource("textures/weapon/gun_textures.png");
    public static final ResourceLocation ATTACHMENT_TEXTURES = Pubgmc.getResource("textures/weapon/attachment_textures.png");

    private final Map<ItemAttachment, IRenderConfig> renderConfig = new HashMap<>();

    public abstract ModelGun getWeaponModel();

    public abstract void registerAttachmentRenders();

    public void preRender(ItemCameraTransforms.TransformType transformType) {
    }

    public final void registerRenderConfig(ItemAttachment attachment, IRenderConfig config) {
        renderConfig.put(attachment, config);
    }

    public final void renderAttachment(ItemAttachment attachment) {
        IRenderConfig config = renderConfig.get(attachment);
        if(config != null)
            config.applyTransforms();
        attachment.getTileEntityItemStackRenderer().renderByItem(ItemStack.EMPTY);
    }

    @Override
    public final void renderByItem(ItemStack itemStackIn) {
        TextureManager manager = Minecraft.getMinecraft().getTextureManager();
        ItemCameraTransforms.TransformType transformType = ClientHooks.getTransformType();
        this.preRender(transformType);
        manager.bindTexture(GUN_TEXTURES);
        this.getWeaponModel().render(itemStackIn);

        GunBase gun = (GunBase) itemStackIn.getItem();
        for (AttachmentType<?> type : AttachmentType.allTypes) {
            ItemAttachment attachment = gun.getAttachment(type, itemStackIn);
            if(attachment != null && hasCustomTEISR(attachment)) {
                manager.bindTexture(ATTACHMENT_TEXTURES);
                renderAttachment(attachment);
            }
        }
    }

    public boolean hasCustomTEISR(Item item) {
        return item.getTileEntityItemStackRenderer() != TileEntityItemStackRenderer.instance;
    }

    public interface IRenderConfig {

        void applyTransforms();
    }
}
