package dev.toma.pubgmc.api.game.util;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public enum CaptureStatus {

    NEW,
    CAPTURED,
    CAPTURING,
    BLOCKED;

    private final ITextComponent title;

    CaptureStatus() {
        this.title = new TextComponentTranslation("label.pubgmc.capture_status." + name().toLowerCase());
    }

    public ITextComponent getTitle() {
        return title;
    }
}
