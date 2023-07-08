package dev.toma.pubgmc.util.helper;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

public class TextComponentHelper {

    public static final ITextComponent FALSE = new TextComponentTranslation("label.pubgmc.false");
    public static final ITextComponent TRUE = new TextComponentTranslation("label.pubgmc.true");
    public static final ITextComponent NO = new TextComponentTranslation("label.pubgmc.no");
    public static final ITextComponent YES = new TextComponentTranslation("label.pubgmc.yes");
    public static final ITextComponent ACCEPT = new TextComponentTranslation("label.pubgmc.accept");
    public static final ITextComponent CONFIRM = new TextComponentTranslation("label.pubgmc.confirm");
    public static final ITextComponent IGNORE = new TextComponentTranslation("label.pubgmc.ignore");
    public static final ITextComponent DECLINE = new TextComponentTranslation("label.pubgmc.decline");
    public static final ITextComponent UNDEFINED = new TextComponentTranslation("label.pubgmc.undefined");
    public static final ITextComponent CLICK_TO_TELEPORT = new TextComponentTranslation("label.pubgmc.click_to_teleport");
    public static final ITextComponent GENERIC_DEATH = new TextComponentTranslation("label.pubgmc.death_message.generic");
    public static final ITextComponent GENERIC_DEATH_BY_ENTITY = new TextComponentTranslation("label.pubgmc.death_message.generic.entity");
    public static final ITextComponent GENERIC_ZONE = new TextComponentTranslation("label.pubgmc.death_message.zone");
    public static final ITextComponent GAME_WON = applyColor(new TextComponentTranslation("pubgmc.game.won.label"), TextFormatting.GREEN);

    public static <T extends ITextComponent> T applyColor(T component, TextFormatting color) {
        component.getStyle().setColor(color);
        return component;
    }

    public static ITextComponent getBooleanLocalization(boolean value, boolean useYesNoLabel) {
        return useYesNoLabel
                ? value ? YES : NO
                : value ? TRUE : FALSE;
    }
}
