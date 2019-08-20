package com.toma.pubgmc.client.util;

import net.minecraft.client.renderer.GlStateManager;

public class ModelTransformationHelper {
    public static ModelTransformationHelper instance;
    private static ModelHelper d = ModelHelper.instance;

    public static void defaultPistolTransform() {
        GlStateManager.scale(0.1, 0.1, 0.1);
        GlStateManager.rotate(180, 0, 1, 0);
        GlStateManager.rotate(180, 1, 0, 0);
        GlStateManager.translate(-5, -10, 6);
        GlStateManager.scale(0.3, 0.3, 0.3);
    }

    public static void defaultShotgunTransform() {
        defaultPistolTransform();
        GlStateManager.scale(0.5999999, 0.5999999, 0.5999999);
        GlStateManager.translate(2.0, 7.0, 0.0);
    }

    public static void defaultSMGTransform() {
        defaultPistolTransform();
        GlStateManager.translate(0.0, 5.0, 0.0);
    }

    public static void defaultARTransform() {
        defaultPistolTransform();
        GlStateManager.scale(0.6999999, 0.6999999, 0.6999999);
        GlStateManager.translate(0.0, 11.0, 0.0);
    }

    public static void defaultSRTransform() {
        defaultARTransform();
    }

    public static void defaultPistolSilencerTransform() {
        GlStateManager.scale(0.03, 0.03, 0.025);
        GlStateManager.translate(17.63, -2.0, -19.33);
    }

    public static void defaultPistolRedDotTransform() {
        GlStateManager.rotate(180, 0, 1, 0);
        GlStateManager.rotate(180, 1, 0, 0);
        GlStateManager.scale(0.00999994, 0.00999994, 0.00999994);
        GlStateManager.translate(-50, -96.20001, 42.0);
    }

    public static void defaultHoloTransform() {
        GlStateManager.rotate(180, 0, 1, 0);
        GlStateManager.rotate(180, 1, 0, 0);
        GlStateManager.scale(0.02999994, 0.02999994, 0.02999994);
        GlStateManager.translate(-16.6, -48.44, 20.0);
    }

    public static void default2XTransform() {
        GlStateManager.rotate(180, 0, 1, 0);
        GlStateManager.rotate(180, 1, 0, 0);
        GlStateManager.scale(0.01999991, 0.01999991, 0.01999991);
        GlStateManager.translate(-24.800003, -61.95, 24.0);
    }

    public static void default4XTransform() {
        GlStateManager.rotate(180, 0, 1, 0);
        GlStateManager.rotate(180, 1, 0, 0);
        GlStateManager.scale(0.014f, 0.014f, 0.014f);
        GlStateManager.translate(-35.600006, -83.64998, 37.0);
    }

    public static void default8XTransform() {
        GlStateManager.rotate(180, 0, 1, 0);
        GlStateManager.rotate(180, 1, 0, 0);
        GlStateManager.scale(0.02, 0.02, 0.02);
        GlStateManager.translate(-24.85, -56.15, 12.0);
    }

    public static void default15XTransform() {
        GlStateManager.rotate(180, 0, 1, 0);
        GlStateManager.rotate(180, 1, 0, 0);
        GlStateManager.scale(0.02, 0.02, 0.02);
        GlStateManager.translate(-25.0, -68.0, 10.0);
    }

    public static void silencerSMGTransform() {
        GlStateManager.scale(0.02999994, 0.02999994, 0.02999994);
        GlStateManager.translate(16.6, 1.0, 0.0);
    }

    public static void silencerARTransform() {
        GlStateManager.scale(0.01999994, 0.01999994, 0.016);
        GlStateManager.translate(24.800018, 7.8999996, -67.899994);
    }

    public static void silencerSRTransform() {
        GlStateManager.scale(0.017, 0.017, 0.014);
        GlStateManager.translate(29.25, 14.25, -103.0);
    }

    public static void verticalGripTransform() {
        GlStateManager.rotate(180, 0, 1, 0);
        GlStateManager.rotate(180, 1, 0, 0);
        GlStateManager.scale(0.02, 0.02, 0.02);
        GlStateManager.translate(-25.0, -26.1, -12.0);
    }

    public static void angledGripTransform() {
        GlStateManager.rotate(180, 1, 0, 0);
        GlStateManager.rotate(180, 0, 1, 0);
        GlStateManager.scale(0.02, 0.02, 0.02);
        GlStateManager.translate(-24.85, -37.95, -12.0);
    }
}
