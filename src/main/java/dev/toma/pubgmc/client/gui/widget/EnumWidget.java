package dev.toma.pubgmc.client.gui.widget;

import java.util.function.Function;

public class EnumWidget<E extends Enum<E>> extends VanillaButtonWidget {

    private final Function<E, String> textProvider;
    private E value;

    public EnumWidget(int x, int y, int width, int height, E value, Function<E, String> textProvider) {
        super(x, y, width, height, textProvider.apply(value), null);
        this.value = value;
        this.textProvider = textProvider;
    }

    public E getValue() {
        return value;
    }

    @Override
    public void onClick(int mouseX, int mouseY, int button) {
        E[] values = value.getDeclaringClass().getEnumConstants();
        int next = (value.ordinal() + 1) % values.length;
        value = values[next];
        text = textProvider.apply(value);
    }
}
