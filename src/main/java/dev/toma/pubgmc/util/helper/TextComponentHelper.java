package dev.toma.pubgmc.util.helper;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

public class TextComponentHelper {

    public static final ITextComponent FALSE = new TextComponentTranslation("label.pubgmc.false");
    public static final ITextComponent TRUE = new TextComponentTranslation("label.pubgmc.true");
    public static final ITextComponent NO = new TextComponentTranslation("label.pubgmc.no");
    public static final ITextComponent YES = new TextComponentTranslation("label.pubgmc.yes");
    public static final ITextComponent UNDEFINED = new TextComponentTranslation("label.pubgmc.undefined");
    public static final ITextComponent CLICK_TO_TELEPORT = new TextComponentTranslation("label.pubgmc.click_to_teleport");

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
