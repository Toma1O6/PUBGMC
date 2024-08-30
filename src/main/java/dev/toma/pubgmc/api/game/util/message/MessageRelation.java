package dev.toma.pubgmc.api.game.util.message;

public enum MessageRelation {

    NONE(0xFFFFFF),
    MY_KILL(0x00AAFF),
    MY_DEATH(0xFF0000),
    FRIENDLY_KILL(0x00AAAA),
    FRIENDLY_DEATH(0xAA0000);

    private final int textColor;

    MessageRelation(int textColor) {
        this.textColor = textColor;
    }

    public int getTextColor() {
        return textColor;
    }
}
