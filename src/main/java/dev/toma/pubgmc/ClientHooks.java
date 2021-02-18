package dev.toma.pubgmc;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;

public class ClientHooks {

    public static void player_setupModelAngles(ModelPlayer model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {

    }

    public static void player_preRenderCallback(RenderPlayer render, AbstractClientPlayer abstractClientPlayer, float partialTicks) {

    }
}
