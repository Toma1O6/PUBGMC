package dev.toma.pubgmc.network.s2c;

import dev.toma.pubgmc.client.animation.AnimationProcessor;
import dev.toma.pubgmc.client.animation.AnimationType;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;

public class S2C_PacketAnimation implements IMessage {

    boolean play;
    int index;

    public S2C_PacketAnimation() {
    }

    public S2C_PacketAnimation(boolean play, AnimationType<?> type) {
        this.play = play;
        this.index = Objects.requireNonNull(type).getIndex();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(play);
        buf.writeInt(index);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        play = buf.readBoolean();
        index = buf.readInt();
    }

    public static class Handler implements IMessageHandler<S2C_PacketAnimation, IMessage> {

        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(S2C_PacketAnimation message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                AnimationType<?> type = AnimationType.getFromID(message.index);
                AnimationProcessor processor = AnimationProcessor.instance();
                if (message.play) {
                    processor.play(type);
                } else {
                    processor.stop(type);
                }
            });
            return null;
        }
    }
}
