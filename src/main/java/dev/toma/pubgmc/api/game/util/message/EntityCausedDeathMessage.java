package dev.toma.pubgmc.api.game.util.message;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.ITextComponent;

public final class EntityCausedDeathMessage implements DeathMessage {

    private final ITextComponent source;
    private final ITextComponent victim;
    private final ITextComponent tool;

    private final ITextComponent composedMessage;
    private MessageRelation relation = MessageRelation.NONE;

    public EntityCausedDeathMessage(ITextComponent source, ITextComponent victim, ITextComponent tool) {
        this.source = source;
        this.victim = victim;
        this.tool = tool;

        this.composedMessage = this.compose(source, victim, tool);
    }

    @Override
    public ITextComponent getTextMessage() {
        return this.composedMessage;
    }

    @Override
    public void setRelation(MessageRelation relation) {
        this.relation = relation;
    }

    @Override
    public MessageRelation getRelation() {
        return relation;
    }

    @Override
    public void setAttribute(String attribute, String value) {

    }

    @Override
    public String getAttribute(String attribute) {
        return null;
    }

    @Override
    public DeathMessageType<?> getType() {
        return DeathMessages.ENTITY;
    }

    private ITextComponent compose(ITextComponent source, ITextComponent victim, ITextComponent tool) {
        return source.createCopy().appendSibling(SPACE).appendSibling(L_BRACKET).appendSibling(tool).appendSibling(R_BRACKET).appendSibling(SPACE).appendSibling(victim);
    }

    public static final class Serializer implements DeathMessageType.MessageSerializer<EntityCausedDeathMessage> {

        @Override
        public NBTTagCompound serialize(EntityCausedDeathMessage message) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("source", ITextComponent.Serializer.componentToJson(message.source));
            tag.setString("victim", ITextComponent.Serializer.componentToJson(message.victim));
            tag.setString("tool", ITextComponent.Serializer.componentToJson(message.tool));
            tag.setInteger("relation", message.getRelation().ordinal());
            return tag;
        }

        @Override
        public EntityCausedDeathMessage deserialize(NBTTagCompound message) {
            ITextComponent source = ITextComponent.Serializer.jsonToComponent(message.getString("source"));
            ITextComponent victim = ITextComponent.Serializer.jsonToComponent(message.getString("victim"));
            ITextComponent tool = ITextComponent.Serializer.jsonToComponent(message.getString("tool"));
            EntityCausedDeathMessage deathMessage = new EntityCausedDeathMessage(source, victim, tool);
            deathMessage.setRelation(MessageRelation.values()[message.getInteger("relation")]);
            return deathMessage;
        }
    }
}
