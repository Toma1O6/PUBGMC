package dev.toma.pubgmc.api.game.util.message;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.ITextComponent;

public class SelfDeathMessage implements DeathMessage {

    private final ITextComponent victim;
    private final ITextComponent tool;

    private final ITextComponent composed;
    private MessageRelation relation = MessageRelation.NONE;

    public SelfDeathMessage(ITextComponent victim, ITextComponent tool) {
        this.victim = victim;
        this.tool = tool;

        this.composed = L_BRACKET.createCopy().appendSibling(tool).appendSibling(R_BRACKET).appendSibling(SPACE).appendSibling(victim);
    }

    @Override
    public ITextComponent getTextMessage() {
        return this.composed;
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
        return DeathMessages.SELF;
    }

    public static final class Serializer implements DeathMessageType.MessageSerializer<SelfDeathMessage> {

        @Override
        public NBTTagCompound serialize(SelfDeathMessage message) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("victim", ITextComponent.Serializer.componentToJson(message.victim));
            tag.setString("tool", ITextComponent.Serializer.componentToJson(message.tool));
            tag.setInteger("relation", message.relation.ordinal());
            return tag;
        }

        @Override
        public SelfDeathMessage deserialize(NBTTagCompound message) {
            ITextComponent victim = ITextComponent.Serializer.jsonToComponent(message.getString("victim"));
            ITextComponent tool = ITextComponent.Serializer.jsonToComponent(message.getString("tool"));
            SelfDeathMessage deathMessage = new SelfDeathMessage(victim, tool);
            deathMessage.setRelation(MessageRelation.values()[message.getInteger("relation")]);
            return deathMessage;
        }
    }
}
