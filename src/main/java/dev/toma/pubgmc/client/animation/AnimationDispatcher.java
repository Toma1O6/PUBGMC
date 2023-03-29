package dev.toma.pubgmc.client.animation;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.client.animation.impl.*;
import dev.toma.pubgmc.client.renderer.item.gun.WeaponRenderer;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.proxy.ClientProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class AnimationDispatcher {

    public static void dispatchRecoilAnimationDefault(AnimationProcessor processor, AnimationType<TickableAnimation> type, EntityPlayer player) {
        float yaw = player.rotationYaw - player.prevRotationYaw;
        float pitch = player.rotationPitch - player.prevRotationPitch;
        float scale = Math.min(Math.abs(yaw * pitch) * 0.05F, 0.1F);
        processor.play(type, new MultiFrameAnimation(2, AnimationSpec.jump(AnimationElement.ITEM_AND_HANDS, 0.0, 0.0, scale, pitch, yaw, 0.0)));
    }

    public static void dispatchRecoilAnimationDefault(float yaw, float pitch, boolean aim) {
        float zModifier = aim ? 0.007F : 0.05F;
        float rotModifier = aim ? 0.4F : 1.2F;
        float scale = Math.min(Math.abs(yaw * pitch) * zModifier, 0.1F);
        AnimationProcessor.instance().play(AnimationType.RECOIL_ANIMATION_TYPE, new MultiFrameAnimation(2, AnimationSpec.jump(AnimationElement.ITEM_AND_HANDS, 0.0, 0.0, scale, pitch * rotModifier, yaw * rotModifier, 0.0)));
    }

    public static void dispatchAimAnimation(GunBase item, ItemStack stack) {
        AnimationSpec spec = ((WeaponRenderer) item.getTileEntityItemStackRenderer()).getAimAnimation(stack);
        AnimationProcessor.instance().play(AnimationType.AIM_ANIMATION_TYPE, new AimAnimation(spec));
    }

    public static void dispatchAimAnimationDefault(AnimationProcessor processor, AnimationType<AimAnimation> type, EntityPlayer player) {
        ItemStack stack = player.getHeldItemMainhand();
        if (stack.getItem() instanceof GunBase) {
            GunBase gun = (GunBase) stack.getItem();
            AnimationSpec spec = ((WeaponRenderer) gun.getTileEntityItemStackRenderer()).getAimAnimation(stack);
            processor.play(type, new AimAnimation(spec));
        }
    }

    public static void dispatchReloadAnimation(GunBase gun, ItemStack stack, EntityPlayer player) {
        WeaponRenderer renderer = (WeaponRenderer) gun.getTileEntityItemStackRenderer();
        AnimationSpec spec = renderer.getReloadAnimation(gun, stack);
        int animTime = gun.getReloader().getReloadAnimationTime(gun, stack, player);
        AnimationProcessor.instance().play(AnimationType.RELOAD_ANIMATION_TYPE, new MultiFrameAnimation(animTime, spec));
    }

    public static void dispatchReloadAnimationDefault(AnimationProcessor processor, AnimationType<MultiFrameAnimation> type, EntityPlayer player) {
        ItemStack stack = player.getHeldItemMainhand();
        if (stack.getItem() instanceof GunBase) {
            GunBase gun = (GunBase) stack.getItem();
            AnimationSpec spec = ((WeaponRenderer) gun.getTileEntityItemStackRenderer()).getReloadAnimation(gun, stack);
            int reloadTime = gun.getReloader().getReloadAnimationTime(gun, stack, player);
            processor.play(type, new MultiFrameAnimation(reloadTime, spec));
        }
    }

    public static void dispatchShootAnimation(GunBase gun) {
        AnimationSpec spec = ((WeaponRenderer) gun.getTileEntityItemStackRenderer()).getShootingAnimation();
        if (gun.getAction() != null) {
            AnimationProcessor.instance().schedule(AnimationType.SHOOT_ANIMATION_TYPE, new MultiFrameAnimation(30, spec), 10);
        } else {
            AnimationProcessor.instance().play(AnimationType.SHOOT_ANIMATION_TYPE, new MultiFrameAnimation(Math.min(gun.getFireRate(), 3), spec));
        }
    }

    public static void dispatchShootAnimationDefault(AnimationProcessor processor, AnimationType<MultiFrameAnimation> type, EntityPlayer player) {
        ItemStack stack = player.getHeldItemMainhand();
        if (stack.getItem() instanceof GunBase) {
            GunBase gun = (GunBase) stack.getItem();
            AnimationSpec spec = ((WeaponRenderer) gun.getTileEntityItemStackRenderer()).getShootingAnimation();
            processor.play(type, new MultiFrameAnimation(Math.min(gun.getFireRate(), 3), spec));
        }
    }

    public static void dispatchEquipAnimation(AnimationProcessor processor, AnimationType<EquipAnimation> type, EntityPlayer player) {
        ItemStack stack = player.getHeldItemMainhand();
        if (stack.getItem() instanceof GunBase) {
            GunBase gun = (GunBase) stack.getItem();
            GunBase.GunType gunType = gun.getGunType();
            ResourceLocation key = Pubgmc.getResource("equip_" + gunType.name().toLowerCase());
            AnimationSpec spec = ClientProxy.getAnimationLoader().getAnimationSpecification(key);
            processor.play(type, new EquipAnimation(spec));
        }
    }

    public static void dispatchHeldAnimation(AnimationProcessor processor, AnimationType<HeldAnimation> type, EntityPlayer player) {
        processor.play(type, new HeldAnimation());
    }
}
