package com.toma.pubgmc.content;

import com.toma.pubgmc.util.math.Vec2i;
import com.toma.pubgmc.util.math.Vec4i;
import net.minecraft.util.ResourceLocation;

public interface ImageButton {

    Vec2i getPosition();

    Vec2i getSize();

    ResourceLocation getTexture();

    Vec4i getTextureBounds();
}
