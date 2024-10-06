package dev.toma.pubgmc.api.game.util.message;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.ITextComponent;

public final class EntityCausedDeathMessage extends AbstractDeathMessage {

    private final ITextComponent source;
    private final ITextComponent victim;

    private ITextComponent composedMessage;

    public EntityCausedDeathMessage(ITextComponent source, ITextComponent victim, ITextComponent tool) {
        super(tool);
        this.source = source;
        this.victim = victim;

        this.composedMessage = this.compose(source, victim);
    }

    @Override
    public void addAttribute(ITextComponent message) {
        super.addAttribute(message);
        this.composedMessage = compose(this.source, this.victim);
    }

    @Override
    public ITextComponent getTextMessage() {
        return this.composedMessage;
    }

    @Override
    public DeathMessageType<?> getType() {
        return DeathMessages.ENTITY;
    }

    private ITextComponent compose(ITextComponent source, ITextComponent victim) {
        return source.createCopy().appendSibling(SPACE).appendSibling(L_BRACKET).appendSibling(this.composeTool()).appendSibling(R_BRACKET).appendSibling(SPACE).appendSibling(victim);
    }

    private void refresh() {
        this.composedMessage = this.compose(this.source, this.victim);
    }

    public static final class Serializer implements DeathMessageType.MessageSerializer<EntityCausedDeathMessage> {

        @Override
        public NBTTagCompound serialize(EntityCausedDeathMessage message) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("source", ITextComponent.Serializer.componentToJson(message.source));
            tag.setString("victim", ITextComponent.Serializer.componentToJson(message.victim));
            tag.setString("tool", ITextComponent.Serializer.componentToJson(message.getTool()));
            message.serializeCommon(tag);
            return tag;
        }

        @Override
        public EntityCausedDeathMessage deserialize(NBTTagCompound message) {
            ITextComponent source = ITextComponent.Serializer.jsonToComponent(message.getString("source"));
            ITextComponent victim = ITextComponent.Serializer.jsonToComponent(message.getString("victim"));
            ITextComponent tool = ITextComponent.Serializer.jsonToComponent(message.getString("tool"));
            EntityCausedDeathMessage deathMessage = new EntityCausedDeathMessage(source, victim, tool);
            deathMessage.deserializeCommon(message);
            deathMessage.refresh();
            return deathMessage;
        }
    }
}
