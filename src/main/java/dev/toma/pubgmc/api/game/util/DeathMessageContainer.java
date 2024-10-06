package dev.toma.pubgmc.api.game.util;

import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.util.message.DeathMessage;
import dev.toma.pubgmc.api.game.util.message.DeathMessageType;
import dev.toma.pubgmc.api.game.util.message.DeathMessages;
import dev.toma.pubgmc.api.game.util.message.MessageRelation;
import dev.toma.pubgmc.util.helper.SerializationHelper;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DeathMessageContainer {

    private final int messageLifetime;
    private final Value[] renderingMessages;

    public DeathMessageContainer(int count, int lifetime) {
        this.messageLifetime = lifetime;
        this.renderingMessages = new Value[count];
    }

    public void push(DeathMessage message) {
        for (int i = renderingMessages.length - 2; i >= 0; --i) {
            renderingMessages[i + 1] = renderingMessages[i];
        }
        renderingMessages[0] = new Value(messageLifetime, message);
    }

    public void tick() {
        for (int i = 0; i < renderingMessages.length; i++) {
            Value value = renderingMessages[i];
            if (value == null)
                break;
            if (value.timeRemaining <= 0) {
                renderingMessages[i] = null;
                continue;
            }
            value.tick();
        }
    }

    @SideOnly(Side.CLIENT)
    public void render(FontRenderer font, Game<?> game, int x, int y, int spacing) {
        int index = 0;
        for (Value message : this.renderingMessages) {
            if (message == null)
                continue;
            DeathMessage deathMessage = message.deathMessage;
            MessageRelation relation = deathMessage.getRelation();
            if (!relation.isAssigned()) {
                relation = DeathMessages.getRelationBetween(game, deathMessage);
                deathMessage.setRelation(relation);
            }
            font.drawStringWithShadow(deathMessage.getTextMessage().getFormattedText(), x, y + index++ * spacing, relation.getTextColor());
        }
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public NBTTagCompound serialize() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setTag("messages", SerializationHelper.arrayToNbt(renderingMessages, Value::serialize));
        return compound;
    }

    public void deserialize(NBTTagCompound nbt) {
        SerializationHelper.arrayFromNbt(nbt.getTagList("messages", Constants.NBT.TAG_COMPOUND), renderingMessages, base -> Value.deserialize((NBTTagCompound) base));
    }

    private static final class Value {

        private int timeRemaining;
        private final DeathMessage deathMessage;

        private Value(int timeRemaining, DeathMessage deathMessage) {
            this.timeRemaining = timeRemaining;
            this.deathMessage = deathMessage;
        }

        public DeathMessage getDeathMessage() {
            return deathMessage;
        }

        private void tick() {
            --timeRemaining;
        }

        private NBTTagCompound serialize() {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setInteger("time", timeRemaining);
            nbt.setTag("deathMessage", DeathMessageType.serialize(this.deathMessage));
            return nbt;
        }

        private static Value deserialize(NBTTagCompound nbt) {
            int time = nbt.getInteger("time");
            DeathMessage deathMessage = DeathMessageType.deserialize(nbt.getCompoundTag("deathMessage"));
            return new Value(time, deathMessage);
        }
    }
}
