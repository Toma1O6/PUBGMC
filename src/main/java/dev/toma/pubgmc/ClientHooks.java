package dev.toma.pubgmc;

import dev.toma.pubgmc.client.layers.LayerBackpack;
import dev.toma.pubgmc.client.layers.LayerGhillie;
import dev.toma.pubgmc.common.capability.player.IPlayerData;
import dev.toma.pubgmc.common.capability.player.PlayerData;
import dev.toma.pubgmc.common.items.guns.GunBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class ClientHooks {

    private static ItemCameraTransforms.TransformType transformType = ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND;
    private static float renderTickTime;

    public static void model_setupModelAngles(ModelBiped model, Entity entity) {
        Minecraft mc = Minecraft.getMinecraft();
        Entity entity1 = mc.getRenderViewEntity();
        if(entity == entity1 && mc.gameSettings.thirdPersonView == 0) {
            return;
        }
        if(!(entity instanceof EntityPlayer))
            return;
        EntityPlayer player = (EntityPlayer) entity;
        IPlayerData data = PlayerData.get(player);
        if(data == null)
            return;
        boolean isHoldingWeapon = player.getHeldItemMainhand().getItem() instanceof GunBase;
        boolean isProne = data.isProne();
        boolean playerModel = model instanceof ModelPlayer;
        if(isProne) {
            float f0 = (float) Math.toRadians(180.0F);
            float f1 = (float) Math.toRadians(10.0F);
            float f2 = (float) Math.toRadians(-45.0F);
            model.bipedRightArm.rotateAngleX = f0;
            model.bipedLeftArm.rotateAngleX = f0;
            model.bipedRightArm.rotateAngleZ = -f1;
            model.bipedLeftArm.rotateAngleZ = f1;
            model.bipedRightLeg.rotateAngleZ = f1;
            model.bipedLeftLeg.rotateAngleZ = -f1;
            model.bipedHead.rotateAngleX = model.bipedHead.rotateAngleX + f2;
            model.bipedHeadwear.rotateAngleX = model.bipedHead.rotateAngleX;
            if(playerModel) {
                ModelPlayer mp = (ModelPlayer) model;
                ModelBase.copyModelAngles(model.bipedRightArm, mp.bipedRightArmwear);
                ModelBase.copyModelAngles(model.bipedLeftArm, mp.bipedLeftArmwear);
            }
            player.limbSwing = 0.0F;
            player.limbSwingAmount = 0.0F;
        } else if(isHoldingWeapon) {
            boolean aiming = data.isAiming();
            float f0;
            float f1;
            float f2;
            if(aiming) {
                f0 = (float) Math.toRadians(-90.0F);
                f1 = (float) Math.toRadians(-15.0F);
                f2 = (float) Math.toRadians(45.0F);
                model.bipedRightArm.rotateAngleX = model.bipedHead.rotateAngleX + f0;
                model.bipedRightArm.rotateAngleY = model.bipedHead.rotateAngleY;
                model.bipedRightArm.rotateAngleZ = -f1;
                model.bipedLeftArm.rotateAngleX = model.bipedHead.rotateAngleX + f0;
                model.bipedLeftArm.rotateAngleY = model.bipedHead.rotateAngleY + f2;
            } else {
                f0 = (float) Math.toRadians(-55.0F);
                f1 = (float) Math.toRadians(-40.0F);
                f2 = (float) Math.toRadians(60.0F);
                float f3 = (float) Math.toRadians(-60.0F);
                model.bipedRightArm.rotateAngleX = f0;
                model.bipedLeftArm.rotateAngleX = f3;
                model.bipedRightArm.rotateAngleY = f1;
                model.bipedLeftArm.rotateAngleY = f2;
            }
            if(playerModel) {
                ModelPlayer mp = (ModelPlayer) model;
                ModelBase.copyModelAngles(model.bipedRightArm, mp.bipedRightArmwear);
                ModelBase.copyModelAngles(model.bipedLeftArm, mp.bipedLeftArmwear);
            }
        }
    }

    public static void player_preRenderCallback(RenderPlayer render, AbstractClientPlayer abstractClientPlayer, float partialTicks) {
        IPlayerData data = PlayerData.get(abstractClientPlayer);
        if(data != null && data.isProne()) {
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.translate(0.0F, 0.9F, 0.12F);
        }
    }

    public static void player_constructRender(RenderPlayer renderPlayer, RenderManager manager, boolean useSmallArms) {
        renderPlayer.addLayer(new LayerGhillie(renderPlayer));
        renderPlayer.addLayer(new LayerBackpack(renderPlayer, entity -> {
            EntityPlayer player = (EntityPlayer) entity;
            return PlayerData.get(player);
        }));
    }

    public static void preRenderItem(ItemCameraTransforms.TransformType renderingType) {
         transformType = renderingType;
    }

    public static ItemCameraTransforms.TransformType getTransformType() {
        return transformType;
    }

    public static void setRenderTickTime(float renderTickTime) {
        ClientHooks.renderTickTime = renderTickTime;
    }

    public static float getRenderTickTime() {
        return renderTickTime;
    }
}
