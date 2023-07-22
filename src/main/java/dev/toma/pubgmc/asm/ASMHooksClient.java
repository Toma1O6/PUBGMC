package dev.toma.pubgmc.asm;

import dev.toma.pubgmc.api.capability.*;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.mutator.GameMutators;
import dev.toma.pubgmc.api.item.NightVisionGoggles;
import dev.toma.pubgmc.client.event.ClientWorldTickEvent;
import dev.toma.pubgmc.client.layers.LayerBackpack;
import dev.toma.pubgmc.client.layers.LayerGhillie;
import dev.toma.pubgmc.client.layers.LayerNightVision;
import dev.toma.pubgmc.common.games.NoGame;
import dev.toma.pubgmc.api.game.mutator.GameMutatorManager;
import dev.toma.pubgmc.api.game.mutator.LightmapMutator;
import dev.toma.pubgmc.common.items.guns.GunBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Optional;

public class ASMHooksClient {

    private static ItemCameraTransforms.TransformType transformType = ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND;
    private static float renderTickTime;

    public static void model_setupModelAngles(ModelBiped model, Entity entity) {
        Minecraft mc = Minecraft.getMinecraft();
        Entity entity1 = mc.getRenderViewEntity();
        if (entity == entity1 && mc.gameSettings.thirdPersonView == 0) {
            return;
        }
        if (!(entity instanceof EntityPlayer))
            return;
        EntityPlayer player = (EntityPlayer) entity;
        IPlayerData data = PlayerDataProvider.get(player);
        if (data == null)
            return;
        boolean isHoldingWeapon = player.getHeldItemMainhand().getItem() instanceof GunBase;
        boolean isProne = data.isProne();
        boolean playerModel = model instanceof ModelPlayer;
        if (isProne) {
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
            if (playerModel) {
                ModelPlayer mp = (ModelPlayer) model;
                ModelBase.copyModelAngles(model.bipedRightArm, mp.bipedRightArmwear);
                ModelBase.copyModelAngles(model.bipedLeftArm, mp.bipedLeftArmwear);
            }
            player.limbSwing = 0.0F;
            player.limbSwingAmount = 0.0F;
        } else if (isHoldingWeapon) {
            boolean aiming = data.getAimInfo().isAiming();
            float f0;
            float f1;
            float f2;
            if (aiming) {
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
            if (playerModel) {
                ModelPlayer mp = (ModelPlayer) model;
                ModelBase.copyModelAngles(model.bipedRightArm, mp.bipedRightArmwear);
                ModelBase.copyModelAngles(model.bipedLeftArm, mp.bipedLeftArmwear);
            }
        }
    }

    public static void player_preRenderCallback(RenderPlayer render, AbstractClientPlayer abstractClientPlayer, float partialTicks) {
        IPlayerData data = PlayerDataProvider.get(abstractClientPlayer);
        if (data != null && data.isProne()) {
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.translate(0.0F, 0.9F, 0.12F);
        }
    }

    public static void player_constructRender(RenderPlayer renderPlayer, RenderManager manager, boolean useSmallArms) {
        renderPlayer.addLayer(new LayerGhillie<>(renderPlayer, PlayerDataProvider::get));
        renderPlayer.addLayer(new LayerBackpack<>(renderPlayer, PlayerDataProvider::get));
        renderPlayer.addLayer(new LayerNightVision<>(renderPlayer, PlayerDataProvider::get, player -> PlayerDataProvider.get(player).isNightVisionActive()));
    }

    public static void preRenderItem(ItemCameraTransforms.TransformType renderingType) {
        transformType = renderingType;
    }

    public static void updateLightmap(int[] lightmapColors) {
        Minecraft mc = Minecraft.getMinecraft();
        WorldClient client = mc.world;
        Game<?> game = GameDataProvider.getGameData(client).<Game<?>>map(GameData::getCurrentGame).orElse(NoGame.INSTANCE);
        Optional<LightmapMutator> mutatorOptional = GameMutatorManager.INSTANCE.getMutator(game.getGameType(), GameMutators.LIGHTMAP);
        if (!mutatorOptional.isPresent())
            return;
        LightmapMutator mutator = mutatorOptional.get();
        IPlayerData data = PlayerDataProvider.get(mc.player);
        if (data == null)
            return;
        ItemStack stack = data.getSpecialItemFromSlot(SpecialEquipmentSlot.NIGHT_VISION);
        float lightAmplifier = 1.0F;
        if (stack.getItem() instanceof NightVisionGoggles && data.isNightVisionActive()) {
            lightAmplifier = ((NightVisionGoggles) stack.getItem()).getBrightnessValue();
        }
        float gammaSetting = mutator.applyClientGamma(mc.gameSettings.gammaSetting);
        float sunLight = client.getSunBrightness(1.0F) + mutator.getGammaBoost() - gammaSetting * mutator.getGammaMultiplier();
        float amplifier = sunLight * lightAmplifier;
        for (int i = 0; i < 256; i++) {
            int value = lightmapColors[i];
            float r = ((value >> 16) & 255) / 255.0F;
            float g = ((value >> 8) & 255) / 255.0F;
            float b = (value & 255) / 255.0F;
            r = Math.min(1.0F, r * amplifier);
            g = Math.min(1.0F, g * amplifier);
            b = Math.min(1.0F, b * amplifier);
            lightmapColors[i] = 0xFF << 24 | ((int) (r * 255.0F)) << 16 | ((int) (g * 255.0F)) << 8 | ((int) (b * 255.0F));
        }
    }

    public static void dispatchPreWorldClientTickEvent(WorldClient worldClient) {
        MinecraftForge.EVENT_BUS.post(new ClientWorldTickEvent(TickEvent.Phase.START, worldClient));
    }

    public static void dispatchPostWorldClientTickEvent(WorldClient worldClient) {
        MinecraftForge.EVENT_BUS.post(new ClientWorldTickEvent(TickEvent.Phase.END, worldClient));
    }

    public static int getPlayerLimitForTabOverlayRender() {
        return 0; // Allows rendering of player list even when player is alone
    }

    public static ItemCameraTransforms.TransformType getTransformType() {
        return transformType;
    }

    public static void setRenderTickTime(float renderTickTime) {
        ASMHooksClient.renderTickTime = renderTickTime;
    }

    public static float getRenderTickTime() {
        return renderTickTime;
    }
}
