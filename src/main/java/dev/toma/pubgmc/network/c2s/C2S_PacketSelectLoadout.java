package dev.toma.pubgmc.network.c2s;

import dev.toma.pubgmc.api.capability.GameDataProvider;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.LoadoutHandler;
import dev.toma.pubgmc.api.game.loadout.GameLoadoutManager;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class C2S_PacketSelectLoadout implements IMessage {

    private int loadout;

    public C2S_PacketSelectLoadout() {
    }

    public C2S_PacketSelectLoadout(int loadout) {
        this.loadout = loadout;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(loadout);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        loadout = buf.readInt();
    }

    public static final class Handler implements IMessageHandler<C2S_PacketSelectLoadout, IMessage> {

        @Override
        public IMessage onMessage(C2S_PacketSelectLoadout message, MessageContext ctx) {
            EntityPlayerMP sender = ctx.getServerHandler().player;
            sender.getServer().addScheduledTask(() -> GameDataProvider.getGameData(sender.world).ifPresent(gameData -> {
                Game<?> game = gameData.getCurrentGame();
                if (game instanceof LoadoutHandler) {
                    LoadoutHandler handler = (LoadoutHandler) game;
                    GameLoadoutManager loadoutManager = handler.getLoadoutManager();
                    boolean noLoadout = !loadoutManager.hasSelectedLoadout(sender.getUniqueID());
                    loadoutManager.selectLoadout(sender.getUniqueID(), message.loadout, sender.world);
                    if (loadoutManager.shouldApplyLoadoutAfterSelection(sender) && game.isStarted() && noLoadout) {
                        loadoutManager.applyLoadout(sender);
                    }
                }
            }));
            return null;
        }
    }
}
