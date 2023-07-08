package dev.toma.pubgmc.config.client.component;

import dev.toma.configuration.api.client.component.ColorDisplayComponent;
import dev.toma.configuration.api.client.component.ConfigComponent;
import dev.toma.pubgmc.client.gui.widget.Widget;
import dev.toma.pubgmc.config.type.TextureType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureManager;

public class TextureComponent extends ConfigComponent<TextureType> {

    public TextureComponent(TextureType textureType, int x, int y, int width, int height) {
        super(textureType, x, y, width, height);
    }

    @Override
    public void drawComponent(FontRenderer font, int mouseX, int mouseY, float partialTicks, boolean hovered) {
        int color = configType.getColorForRender();
        float a = ((color >> 24) & 0xff) / 255F;
        float r = ((color >> 16) & 0xff) / 255F;
        float g = ((color >> 8) & 0xff) / 255F;
        float b = (color & 0xff) / 255F;
        Minecraft mc = Minecraft.getMinecraft();
        TextureManager textureManager = mc.getTextureManager();
        drawColorShape(x, y, x + width, y + height, 1.0F, 1.0F, 1.0F, 1.0F);
        textureManager.bindTexture(ColorDisplayComponent.COLOR_BACKGROUND);
        drawTexturedShape(x + 1, y + 1, x + width - 1, y + height - 1);
        textureManager.bindTexture(configType.get().getResource());
        Widget.drawTexturedColorShape(x, y, x + width, y + height, r, g, b, a);
    }
}
