package dev.toma.pubgmc.api.game.util;

import dev.toma.pubgmc.util.helper.SerializationHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;

import java.util.Arrays;
import java.util.Objects;

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

    public DeathMessage[] getDeathMessages() {
        return Arrays.stream(renderingMessages)
                .filter(Objects::nonNull)
                .map(Value::getDeathMessage)
                .toArray(DeathMessage[]::new);
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
            nbt.setTag("message", deathMessage.serialize());
            return nbt;
        }

        private static Value deserialize(NBTTagCompound nbt) {
            int time = nbt.getInteger("time");
            DeathMessage deathMessage = DeathMessage.deserialize(nbt.getCompoundTag("message"));
            return new Value(time, deathMessage);
        }
    }
}
