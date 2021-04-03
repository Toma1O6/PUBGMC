package dev.toma.pubgmc.client.animation.impl;

import dev.toma.pubgmc.ClientHooks;
import dev.toma.pubgmc.DevUtil;
import dev.toma.pubgmc.client.animation.AnimationElement;
import dev.toma.pubgmc.client.animation.AnimationProcessor;
import dev.toma.pubgmc.client.animation.interfaces.Animation;
import dev.toma.pubgmc.client.animation.interfaces.KeyFrame;
import dev.toma.pubgmc.common.capability.player.IPlayerData;
import dev.toma.pubgmc.common.capability.player.PlayerData;
import dev.toma.pubgmc.common.items.guns.GunBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class HeldAnimation implements Animation {

    final KeyFrame bigWeaponSprintFrame = KeyFrame.rotate(1.0F, new Vec3d(0.1, 0.0, -0.3), new Vec3d(-15.0, 60.0, 0.0));
    float progress, progressPrev, progressSmooth;

    @Override
    public void animateElement(AnimationElement element) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = mc.player;
        if(player != null) {
            ItemStack stack = player.getHeldItemMainhand();
            if(!(stack.getItem() instanceof GunBase)) {
                return;
            }
            if(element == AnimationElement.ITEM_AND_HANDS) {
                float partialTicks = ClientHooks.getRenderTickTime();
                AnimationProcessor.processKeyFrame(bigWeaponSprintFrame, progressSmooth);
                IPlayerData data = PlayerData.get(player);
                float bobbingScale = 0.4F;
                if(data.getAimInfo().isAiming()) {
                    bobbingScale = 0.1F;
                }
                float f = player.distanceWalkedModified - player.prevDistanceWalkedModified;
                float f1 = -(player.distanceWalkedModified + f * partialTicks);
                float f2 = player.prevCameraYaw + (player.cameraYaw - player.prevCameraYaw) * partialTicks;
                GlStateManager.translate(MathHelper.sin(f1 * (float)Math.PI) * f2 * bobbingScale, -Math.abs(MathHelper.cos(f1 * (float)Math.PI) * f2) * bobbingScale, 0.0F);
            }
        }
    }

    @Override
    public void tick() {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = mc.player;
        progressPrev = progress;
        if(player != null && player.isSprinting()) {
            progress = Math.min(1.0F, progress + 0.1F);
        } else {
            progress = Math.max(0.0F, progress - 0.1F);
        }
    }

    @Override
    public void renderTick(float partialTicks) {
        progressSmooth = DevUtil.lerp(progress, progressPrev, partialTicks);
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }
}
