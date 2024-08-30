package dev.toma.pubgmc.api.game.util.message;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public interface DeathMessage {

    ITextComponent SPACE = new TextComponentString(" ");
    ITextComponent L_BRACKET = new TextComponentString("[");
    ITextComponent R_BRACKET = new TextComponentString("]");

    ITextComponent GENERIC_DEATH = new TextComponentTranslation("label.pubgmc.death_message.generic");
    ITextComponent ENTITY_GENERIC_DEATH = new TextComponentTranslation("label.pubgmc.death_message.generic.entity");
    ITextComponent GENERIC_BOUNDS_DEATH = new TextComponentTranslation("label.pubgmc.death_message.zone");

    ITextComponent getTextMessage();

    void setRelation(MessageRelation relation);

    MessageRelation getRelation();

    void setAttribute(String attribute, String value);

    String getAttribute(String attribute);

    DeathMessageType<?> getType();
}
