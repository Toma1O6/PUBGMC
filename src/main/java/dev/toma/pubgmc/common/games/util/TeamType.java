package dev.toma.pubgmc.common.games.util;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

import java.util.Locale;

public enum TeamType {

    RED(TextFormatting.RED, 0xCC0000),
    BLUE(TextFormatting.BLUE, 0x4444CC);

    private final ITextComponent title;
    private final int color;

    TeamType(TextFormatting formatting, int color) {
        this.title = new TextComponentTranslation("label.pubgmc.team." + name().toLowerCase(Locale.ROOT));
        this.title.getStyle().setColor(formatting);
        this.color = color;
    }

    public TeamType getEnemy() {
        return this == RED ? BLUE : RED;
    }

    public ITextComponent getTitle() {
        return title;
    }

    public int getColor() {
        return color;
    }
}
