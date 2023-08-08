package dev.toma.pubgmc.network.server;

import dev.toma.pubgmc.api.capability.GameDataProvider;
import dev.toma.pubgmc.api.game.map.GameMapInstance;
import dev.toma.pubgmc.api.util.Position2;
import dev.toma.pubgmc.common.games.map.GameMapPoints;
import dev.toma.pubgmc.common.games.map.PartialPlayAreaPoint;
import dev.toma.pubgmc.util.helper.PacketHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class C2S_AdjustPartialPlayZone implements IMessage {

    private String map;
    private BlockPos pos;
    private Position2 min, max;
    private String label;

    public C2S_AdjustPartialPlayZone() {
    }

    public C2S_AdjustPartialPlayZone(String map, BlockPos pos, Position2 min, Position2 max, String label) {
        this.map = map;
        this.pos = pos;
        this.min = min;
        this.max = max;
        this.label = label;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, map);
        PacketHelper.writeBlockPos(pos, buf);
        buf.writeDouble(min.getX());
        buf.writeDouble(min.getZ());
        buf.writeDouble(max.getX());
        buf.writeDouble(max.getZ());
        ByteBufUtils.writeUTF8String(buf, label != null ? label : "");
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        map = ByteBufUtils.readUTF8String(buf);
        pos = PacketHelper.readBlockPos(buf);
        min = new Position2(buf.readDouble(), buf.readDouble());
        max = new Position2(buf.readDouble(), buf.readDouble());
        label = ByteBufUtils.readUTF8String(buf);
    }

    public static final class Handler implements IMessageHandler<C2S_AdjustPartialPlayZone, IMessage> {

        @Override
        public IMessage onMessage(C2S_AdjustPartialPlayZone message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;
            player.getServer().addScheduledTask(() -> {
                if (!player.isCreative())
                    return;
                GameDataProvider.getGameData(player.world).ifPresent(data -> {
                    GameMapInstance gameMap = data.getGameMap(message.map);
                    if (gameMap == null)
                        return;
                    gameMap.getPointAt(message.pos).filter(point -> point.is(GameMapPoints.PARTIAL_PLAY_AREA))
                            .map(p -> (PartialPlayAreaPoint) p)
                            .ifPresent(zone -> {
                                zone.setName(message.label);
                                zone.setMin(message.min);
                                zone.setMax(message.max);
                                data.sendGameDataToClients();
                            });
                });
            });
            return null;
        }
    }
}
