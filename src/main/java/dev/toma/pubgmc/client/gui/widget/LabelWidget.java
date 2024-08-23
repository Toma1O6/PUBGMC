package dev.toma.pubgmc.client.gui.widget;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.ITextComponent;

public final class LabelWidget extends Widget {

    private final FontRenderer font;
    private final ITextComponent label;

    private int textColor = 0xFFFFFF;
    private boolean textShadow = false;

    public LabelWidget(FontRenderer font, int x, int y, int width, int height, ITextComponent label) {
        super(x, y, width, height);
        this.font = font;
        this.label = label;
    }

    public static LabelWidget centered(FontRenderer font, int x, int y, int width, int height, ITextComponent label) {
        int textWidth = font.getStringWidth(label.getFormattedText());
        return new LabelWidget(font, x + (width - textWidth) / 2, y + (height - font.FONT_HEIGHT) / 2, textWidth, font.FONT_HEIGHT, label);
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public void setTextShadow(boolean textShadow) {
        this.textShadow = textShadow;
    }

    @Override
    public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (!this.isActive())
            return;
        this.font.drawString(this.label.getFormattedText(), x, y, textColor, textShadow);
    }
}
