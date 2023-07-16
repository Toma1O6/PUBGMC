package dev.toma.pubgmc.network.server;

import dev.toma.pubgmc.api.capability.GameDataProvider;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.common.games.game.ffa.FFAGame;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class C2S_SelectFFALoadout implements IMessage {

    private int loadout;

    public C2S_SelectFFALoadout() {
    }

    public C2S_SelectFFALoadout(int loadout) {
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

    public static final class Handler implements IMessageHandler<C2S_SelectFFALoadout, IMessage> {

        @Override
        public IMessage onMessage(C2S_SelectFFALoadout message, MessageContext ctx) {
            EntityPlayerMP sender = ctx.getServerHandler().player;
            sender.getServer().addScheduledTask(() -> GameDataProvider.getGameData(sender.world).ifPresent(gameData -> {
                Game<?> game = gameData.getCurrentGame();
                if (game instanceof FFAGame) {
                    ((FFAGame) game).selectLoadout(sender.getUniqueID(), message.loadout, sender.world);
                }
            }));
            return null;
        }
    }
}
