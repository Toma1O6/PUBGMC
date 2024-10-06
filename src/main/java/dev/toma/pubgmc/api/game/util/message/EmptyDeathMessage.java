package dev.toma.pubgmc.api.game.util.message;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

public class EmptyDeathMessage implements DeathMessage {

    public static final ITextComponent EMPTY = new TextComponentString("");
    public static final EmptyDeathMessage INSTANCE = new EmptyDeathMessage();

    private EmptyDeathMessage() {
    }

    @Override
    public ITextComponent getTextMessage() {
        return EMPTY;
    }

    @Override
    public void setRelation(MessageRelation relation) {
    }

    @Override
    public MessageRelation getRelation() {
        return MessageRelation.NONE;
    }

    @Override
    public void addAttribute(ITextComponent message) {
    }

    @Override
    public void addAffectedEntities(UUID... uuids) {
    }

    @Override
    public Set<UUID> getAffectedEntities() {
        return Collections.emptySet();
    }

    @Override
    public DeathMessageType<?> getType() {
        return DeathMessages.EMPTY;
    }

    public static final class Serializer implements DeathMessageType.MessageSerializer<EmptyDeathMessage> {

        @Override
        public NBTTagCompound serialize(EmptyDeathMessage message) {
            return new NBTTagCompound();
        }

        @Override
        public EmptyDeathMessage deserialize(NBTTagCompound message) {
            return INSTANCE;
        }
    }
}
