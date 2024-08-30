package dev.toma.pubgmc.api.game.util.message;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.ITextComponent;

public class SelfDeathMessage extends AbstractDeathMessage {

    private final ITextComponent victim;

    private ITextComponent composed;

    public SelfDeathMessage(ITextComponent victim, ITextComponent tool) {
        super(tool);
        this.victim = victim;
        this.composed = this.compose();
    }

    @Override
    public ITextComponent getTextMessage() {
        return this.composed;
    }

    @Override
    public void addAttribute(ITextComponent message) {
        super.addAttribute(message);
        this.composed = this.compose();
    }

    @Override
    public DeathMessageType<?> getType() {
        return DeathMessages.SELF;
    }

    protected ITextComponent compose() {
        return L_BRACKET.createCopy().appendSibling(this.composeTool()).appendSibling(R_BRACKET).appendSibling(SPACE).appendSibling(victim);
    }

    public static final class Serializer implements DeathMessageType.MessageSerializer<SelfDeathMessage> {

        @Override
        public NBTTagCompound serialize(SelfDeathMessage message) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("victim", ITextComponent.Serializer.componentToJson(message.victim));
            tag.setString("tool", ITextComponent.Serializer.componentToJson(message.getTool()));
            message.serializeCommon(tag);
            return tag;
        }

        @Override
        public SelfDeathMessage deserialize(NBTTagCompound message) {
            ITextComponent victim = ITextComponent.Serializer.jsonToComponent(message.getString("victim"));
            ITextComponent tool = ITextComponent.Serializer.jsonToComponent(message.getString("tool"));
            SelfDeathMessage deathMessage = new SelfDeathMessage(victim, tool);
            deathMessage.deserializeCommon(message);
            return deathMessage;
        }
    }
}
