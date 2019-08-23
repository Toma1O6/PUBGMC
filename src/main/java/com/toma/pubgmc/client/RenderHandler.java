package com.toma.pubgmc.client;

import com.toma.pubgmc.client.models.ModelGhillie;
import com.toma.pubgmc.client.models.vehicles.ModelPlane;
import com.toma.pubgmc.client.util.ModelHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderHandler {

    private final ModelGhillie ghillie = new ModelGhillie();
    private final ResourceLocation texture = new ResourceLocation("");

    @SubscribeEvent
    public void renderPlayerPost(RenderPlayerEvent.Post e) {
        EntityPlayer player = e.getEntityPlayer();
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        ModelPlayer model = e.getRenderer().getMainModel();
        GlStateManager.pushMatrix();
        ModelHelper.transformAllModifications();
        ghillie.render(model);
        GlStateManager.popMatrix();
    }
}
