package dev.toma.pubgmc.client.gui.widget;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.util.function.BooleanSupplier;

public class CheckboxWidget extends Widget {

    final String text;
    final String renderText;
    final ClickCallback clickCallback;
    boolean selected;
    BooleanSupplier lightTheme = () -> false;

    public CheckboxWidget(int x, int y, int width, int height, String text, ClickCallback clickCallback) {
        this(x, y, width, height, text, clickCallback, 8);
    }

    public CheckboxWidget(int x, int y, int width, int height, String text, ClickCallback clickCallback, int characterCount) {
        super(x, y, width, height);
        FontRenderer renderer = Minecraft.getMinecraft().fontRenderer;
        int tw = renderer.getStringWidth(text);
        if (tw > width - height - 5) {
            this.renderText = text.substring(0, Math.min(characterCount, text.length())) + "...";
        } else {
            this.renderText = text;
        }
        this.text = text;
        this.clickCallback = clickCallback;
    }

    public CheckboxWidget lightThemeSupplier(BooleanSupplier supplier) {
        this.lightTheme = supplier;
        return this;
    }

    public CheckboxWidget initialState(boolean state) {
        this.selected = state;
        return this;
    }

    public void prepareText(String text) {

    }

    @Override
    public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        boolean light = lightTheme.getAsBoolean();
        int x2 = Math.min(x + width, x + height);
        drawColorShape(x, y, x2, y + height, 1.0F, 1.0F, 1.0F, 1.0F);
        drawColorShape(x + 1, y + 1, x2 - 1, y + height - 1, 0.0F, 0.0F, 0.0F, 1.0F);
        if (selected) {
            drawColorShape(x + 3, y + 3, x2 - 3, y + height - 3, 1.0F, 1.0F, 1.0F, 1.0F);
        }
        FontRenderer fr = mc.fontRenderer;
        fr.drawString(renderText, x2 + 3, y + (height - fr.FONT_HEIGHT) / 2f, light ? 0x333333 : 0xFFFFFF, false);
        if (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height) {
            int tw = fr.getStringWidth(text);
            drawColorShape(x + width / 2 - 5, y - 11, x + width / 2 + tw + 5, y, 0.0F, 0.0F, 0.0F, 1.0F);
            fr.drawStringWithShadow(text, x + width / 2.0F, y - 9.5F, 0xFFFFFF);
        }
    }

    @Override
    public void onClick(int mouseX, int mouseY, int button) {
        selected = !selected;
        clickCallback.clicked(selected, mouseX, mouseY, this);
    }

    public boolean isSelected() {
        return selected;
    }

    @Override
    public boolean isMouseOver(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= Math.min(x + width, x + height) && mouseY >= y && mouseY <= y + height;
    }

    public interface ClickCallback {
        void clicked(boolean state, int mouseX, int mouseY, CheckboxWidget widget);
    }
}
