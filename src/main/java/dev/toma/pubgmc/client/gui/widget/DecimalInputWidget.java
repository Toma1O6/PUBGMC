package dev.toma.pubgmc.client.gui.widget;

import dev.toma.pubgmc.client.gui.animator.MutableKeyFrame;
import net.minecraft.client.Minecraft;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.function.Supplier;
import java.util.regex.Pattern;

public class DecimalInputWidget extends Widget {

    static final DecimalFormat NUMBER_FORMAT;
    static final Pattern DECIMAL_PATTERN = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");
    static final Pattern CHARACTER_PATTERN = Pattern.compile("[0-9.-]");
    Supplier<Double> supplier;
    MutableKeyFrame frame;
    String num;
    boolean valid;
    final Setter setter;

    public DecimalInputWidget(int x, int y, int width, int height, MutableKeyFrame frame, Supplier<Double> supplier, Setter setter) {
        super(x, y, width, height);
        this.supplier = supplier;
        this.setter = setter;
        setFrame(frame);
    }

    public void setFrame(MutableKeyFrame frame) {
        double d = frame != null ? supplier.get() : 0.0;
        this.frame = frame;
        this.num = NUMBER_FORMAT.format(d);
        validateAndSet();
    }

    @Override
    public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (valid) {
            if (focused) {
                drawColorShape(x, y, x + width, y + height, 1.0F, 1.0F, 0.0F, 1.0F);
            } else {
                drawColorShape(x, y, x + width, y + height, 1.0F, 1.0F, 1.0F, 1.0F);
            }
        } else {
            drawColorShape(x, y, x + width, y + height, 0.8F, 0.0F, 0.0F, 1.0F);
        }
        drawColorShape(x + 1, y + 1, x + width - 1, y + height - 1, 0.0F, 0.0F, 0.0F, 1.0F);
        mc.fontRenderer.drawString(num, x + 3, y + (height - mc.fontRenderer.FONT_HEIGHT) / 2.0F, 0xFFFFFF, false);
    }

    @Override
    public boolean handleClicked(int mouseX, int mouseY, int button) {
        return super.handleClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean isFocusable() {
        return true;
    }

    @Override
    public void onKeyPress(char character, int keycode) {
        if (keycode == 14 && !num.isEmpty()) {
            num = num.substring(0, num.length() - 1);
            validateAndSet();
        } else {
            if (CHARACTER_PATTERN.matcher(String.valueOf(character)).matches()) {
                int i = num.replaceAll("[-.]", "").length();
                if (i < 6) {
                    num += character;
                    validateAndSet();
                }
            }
        }
    }

    void validateAndSet() {
        try {
            if (DECIMAL_PATTERN.matcher(num).matches()) {
                double d = Double.parseDouble(num);
                if (frame != null) setter.set(frame, d);
                valid = true;
            } else
                valid = false;
        } catch (NumberFormatException nfe) {
            valid = false;
        }
    }

    static {
        NUMBER_FORMAT = new DecimalFormat("#.####");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        NUMBER_FORMAT.setDecimalFormatSymbols(symbols);
    }

    public interface Setter {
        void set(MutableKeyFrame frame, double value);
    }
}
