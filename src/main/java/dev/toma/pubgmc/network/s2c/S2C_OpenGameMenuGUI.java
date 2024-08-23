package dev.toma.pubgmc.network.s2c;

import dev.toma.pubgmc.client.gui.game.GameGui;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class S2C_OpenGameMenuGUI implements IMessage {

    private boolean isAdmin;

    public S2C_OpenGameMenuGUI() {
    }

    public S2C_OpenGameMenuGUI(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(isAdmin);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        isAdmin = buf.readBoolean();
    }

    public static class Handler implements IMessageHandler<S2C_OpenGameMenuGUI, IMessage> {

        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(S2C_OpenGameMenuGUI message, MessageContext ctx) {
            Minecraft minecraft = Minecraft.getMinecraft();
            minecraft.addScheduledTask(() -> {
                GameGui gameGui = new GameGui(message.isAdmin);
                minecraft.displayGuiScreen(gameGui);
            });
            return null;
        }
    }
}
