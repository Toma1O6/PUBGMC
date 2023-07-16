package dev.toma.pubgmc.network.client;

import dev.toma.pubgmc.api.game.loadout.EntityLoadout;
import dev.toma.pubgmc.api.game.loadout.LoadoutManager;
import dev.toma.pubgmc.client.games.screen.FFALoadoutScreen;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class S2C_PacketFFAGui implements IMessage {

    private final List<EntityLoadout> list;

    public S2C_PacketFFAGui() {
        this(new ArrayList<>());
    }

    public S2C_PacketFFAGui(List<EntityLoadout> list) {
        this.list = list;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(list.size());
        list.forEach(loadout -> {
            String string = LoadoutManager.loadoutToString(loadout);
            ByteBufUtils.writeUTF8String(buf, string);
        });
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        list.clear();
        int count = buf.readInt();
        for (int i = 0; i < count; i++) {
            String string = ByteBufUtils.readUTF8String(buf);
            EntityLoadout loadout = LoadoutManager.loadoutFromString(string);
            list.add(loadout);
        }
    }

    public static final class Handler implements IMessageHandler<S2C_PacketFFAGui, IMessage> {

        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(S2C_PacketFFAGui message, MessageContext ctx) {
            Minecraft minecraft = Minecraft.getMinecraft();
            minecraft.addScheduledTask(() -> minecraft.displayGuiScreen(new FFALoadoutScreen(message.list)));
            return null;
        }
    }
}
