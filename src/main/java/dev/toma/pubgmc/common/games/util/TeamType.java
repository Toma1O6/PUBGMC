package dev.toma.pubgmc.common.games.util;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

public enum TeamType {

    RED(TextFormatting.RED),
    BLUE(TextFormatting.BLUE);

    private final ITextComponent title;

    TeamType(TextFormatting color) {
        this.title = new TextComponentTranslation("label.pubgmc.team." + name().toLowerCase());
        this.title.getStyle().setColor(color);
    }

    public ITextComponent getTitle() {
        return title;
    }
}
