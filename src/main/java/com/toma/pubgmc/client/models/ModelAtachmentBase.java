package com.toma.pubgmc.client.models;

import com.toma.pubgmc.Pubgmc;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;

public abstract class ModelAtachmentBase extends ModelBase {
    public void bindTextureLocation(String name) {
        ResourceLocation rl = new ResourceLocation(Pubgmc.MOD_ID + ":textures/weapons/" + name + ".png");
        Minecraft.getMinecraft().getTextureManager().bindTexture(rl);
    }

    public abstract void render();
}
