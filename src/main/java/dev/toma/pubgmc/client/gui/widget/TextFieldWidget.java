package dev.toma.pubgmc.client.gui.widget;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ChatAllowedCharacters;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class TextFieldWidget extends Widget {

    String text;
    String ghostText = "Sample text";
    int maxLength;
    boolean hasError = false;
    Predicate<Character> inputValidator = character -> true;
    Consumer<TextFieldWidget> inputCallback = w -> {};

    public TextFieldWidget(int x, int y, int width, int height, String initialValue, int maxLength) {
        super(x, y, width, height);
        this.text = initialValue == null ? "" : initialValue;
        this.maxLength = maxLength;
    }

    public TextFieldWidget ghostText(String ghostText) {
        this.ghostText = ghostText;
        return this;
    }

    public TextFieldWidget withValidator(Predicate<Character> inputValidator) {
        this.inputValidator = inputValidator;
        return this;
    }

    public void withCallback(Consumer<TextFieldWidget> callback) {
        this.inputCallback = callback;
    }

    public String getText() {
        return text;
    }

    public void setErrorStatus(boolean errorStatus) {
        this.hasError = errorStatus;
    }

    public boolean isInvalidInput() {
        return hasError;
    }

    @Override
    public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        drawColorShape(x, y, x + width, y + height, 1.0F, hasError ? 0.0F : 1.0F, hasError ? 0.0F : (focused ? 0.0F : 1.0F), 1.0F);
        drawColorShape(x + 1, y + 1, x + width - 1, y + height - 1, 0.0F, 0.0F, 0.0F, 1.0F);
        FontRenderer renderer = mc.fontRenderer;
        if (text.isEmpty()) {
            renderer.drawString(ghostText, x + 3, y + (height - renderer.FONT_HEIGHT) / 2.0F, 0x999999, false);
        } else {
            renderer.drawString(text, x + 3, y + (height - renderer.FONT_HEIGHT) / 2.0F, hasError ? 0xFF0000 : 0xFFFFFF, false);
        }
    }

    @Override
    public void onKeyPress(char character, int keycode) {
        if (keycode == 1) {
            unfocus();
        } else if (keycode == 14) {
            if (!text.isEmpty()) {
                text = text.substring(0, text.length() - 1);
                callback();
            }
        } else {
            if (text.length() < maxLength - 1) {
                if (inputValidator.test(character)) {
                    text += ChatAllowedCharacters.filterAllowedCharacters(String.valueOf(character));
                    callback();
                }
            }
        }
    }

    @Override
    public boolean isFocusable() {
        return true;
    }

    private void callback() {
        if (inputCallback != null)
            inputCallback.accept(this);
    }
}
