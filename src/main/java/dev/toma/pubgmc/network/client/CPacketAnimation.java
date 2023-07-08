package dev.toma.pubgmc.network.client;

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

public class CPacketAnimation implements IMessage {

    boolean play;
    int index;

    public CPacketAnimation() {
    }

    public CPacketAnimation(boolean play, AnimationType<?> type) {
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

    public static class Handler implements IMessageHandler<CPacketAnimation, IMessage> {

        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(CPacketAnimation message, MessageContext ctx) {
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
