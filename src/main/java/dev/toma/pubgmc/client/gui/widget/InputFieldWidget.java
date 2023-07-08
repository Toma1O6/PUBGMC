package dev.toma.pubgmc.client.gui.widget;

import dev.toma.configuration.internal.Ranged;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public abstract class InputFieldWidget<T> extends Widget {

    private final Callback<T> callback;
    private final Parser<T> parser;
    private String backgroundText = "Enter value";
    protected String text = "";
    protected boolean validState;

    public InputFieldWidget(int x, int y, int width, int height, String text, Callback<T> callback) {
        super(x, y, width, height);
        this.text = text;
        this.callback = callback;
        this.parser = this::parse;
    }

    public abstract T parse(String text);

    public abstract boolean isValid(char character);

    public abstract boolean isValid(String value);

    public abstract boolean isValidValue(T t);

    public void setBackgroundText(String backgroundText) {
        this.backgroundText = backgroundText;
    }

    @Override
    public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        drawColorShape(x, y, x + width, y + height, 1.0F, validState ? 1.0F : 0.0F, validState ? focused ? 0.0F : 1.0F : 0.0F, 1.0F);
        drawColorShape(x + 1, y + 1, x + width - 1, y + height - 1, 0.0F, 0.0F, 0.0F, 1.0F);
        if (text.isEmpty()) {
            mc.fontRenderer.drawString(backgroundText, x + 3, y + (height - mc.fontRenderer.FONT_HEIGHT) / 2, 0xAAAAAA);
        } else {
            mc.fontRenderer.drawString(text, x + 3, y + (height - mc.fontRenderer.FONT_HEIGHT) / 2, 0xFFFFFF);
        }
    }

    public void invalidate() {
        this.validState = false;
    }

    @Override
    public boolean isFocusable() {
        return true;
    }

    @Override
    public void onKeyPress(char character, int keycode) {
        if (keycode == 1) {
            unfocus();
        } else if (keycode == 14) {
            if (!text.isEmpty()) {
                text = text.substring(0, text.length() - 1);
                validate();
            }
        } else {
            if (isValid(character)) {
                FontRenderer renderer = Minecraft.getMinecraft().fontRenderer;
                String newText = text + character;
                if (renderer.getStringWidth(newText) < width - 6) {
                    text = newText;
                    validate();
                }
            }
        }
    }

    protected void validate() {
        validState = isValid(text);
        if (validState) {
            try {
                T t = parser.parse(text);
                if (isValidValue(t)) {
                    callback.onValueChanged(this, t);
                } else validState = false;
            } catch (Exception e) {
                validState = false;
            }
        }
    }

    public interface Callback<T> {
        void onValueChanged(InputFieldWidget<T> widget, T t);
    }

    public interface Parser<T> {
        T parse(String text) throws Exception;
    }

    public static class StringFieldWidget extends InputFieldWidget<String> {

        private Predicate<Character> characterValidator = character -> true;
        private Pattern pattern = null;

        public StringFieldWidget(int x, int y, int width, int height, String text, Callback<String> callback) {
            super(x, y, width, height, text, callback);
            validate();
        }

        public StringFieldWidget charValidator(Predicate<Character> predicate) {
            characterValidator = Objects.requireNonNull(predicate);
            return this;
        }

        public StringFieldWidget charValidator(Pattern pattern) {
            return charValidator(character -> Objects.requireNonNull(pattern).matcher(String.valueOf(character)).matches());
        }

        public StringFieldWidget pattern(Pattern pattern) {
            this.pattern = Objects.requireNonNull(pattern);
            return this;
        }

        @Override
        public String parse(String text) {
            return text;
        }

        @Override
        public boolean isValid(char character) {
            return characterValidator.test(character);
        }

        @Override
        public boolean isValid(String value) {
            return pattern == null || pattern.matcher(value).matches();
        }

        @Override
        public boolean isValidValue(String s) {
            return true;
        }
    }

    public static abstract class NumberField<N extends Number> extends InputFieldWidget<N> implements Ranged<N> {

        private N min;
        private N max;

        public NumberField(int x, int y, int width, int height, N text, N min, N max, Callback<N> callback) {
            super(x, y, width, height, String.valueOf(text), callback);
            this.min = min;
            this.max = max;
            validate();
        }

        @Override
        public N getMin() {
            return min;
        }

        @Override
        public N getMax() {
            return max;
        }
    }

    public static class IntegerFieldWidget extends NumberField<Integer> {

        private static final Pattern PATTERN = Pattern.compile("-?[0-9]+");

        public IntegerFieldWidget(int x, int y, int width, int height, int value, int min, int max, Callback<Integer> callback) {
            super(x, y, width, height, value, min, max, callback);
        }

        public IntegerFieldWidget(int x, int y, int width, int height, int value, Callback<Integer> callback) {
            this(x, y, width, height, value, Integer.MIN_VALUE, Integer.MAX_VALUE, callback);
        }

        @Override
        public Integer parse(String text) {
            return Integer.parseInt(text);
        }

        @Override
        public boolean isValid(char character) {
            return character == '-' || Character.isDigit(character);
        }

        @Override
        public boolean isValid(String value) {
            return PATTERN.matcher(value).matches();
        }

        @Override
        public boolean isInRange(Integer integer) {
            return integer >= getMin() && integer <= getMax();
        }

        @Override
        public boolean isValidValue(Integer integer) {
            return isInRange(integer);
        }
    }

    public static class DoubleFieldWidget extends NumberField<Double> {

        private static final Pattern PATTERN = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");

        public DoubleFieldWidget(int x, int y, int width, int height, double value, double min, double max, Callback<Double> callback) {
            super(x, y, width, height, value, min, max, callback);
        }

        public DoubleFieldWidget(int x, int y, int width, int height, double value, Callback<Double> callback) {
            this(x, y, width, height, value, -Double.MAX_VALUE, Double.MAX_VALUE, callback);
        }

        @Override
        public Double parse(String text) {
            return Double.parseDouble(text);
        }

        @Override
        public boolean isValid(char character) {
            return character == '.' || character == '-' || Character.isDigit(character);
        }

        @Override
        public boolean isValid(String value) {
            return PATTERN.matcher(value).matches();
        }

        @Override
        public boolean isInRange(Double aDouble) {
            return aDouble >= getMin() && aDouble <= getMax();
        }

        @Override
        public boolean isValidValue(Double aDouble) {
            return isInRange(aDouble);
        }
    }
}
