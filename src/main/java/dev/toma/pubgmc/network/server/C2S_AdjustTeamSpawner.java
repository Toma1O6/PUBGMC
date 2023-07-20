package dev.toma.pubgmc.network.server;

import dev.toma.pubgmc.api.capability.GameDataProvider;
import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.common.games.map.GameMapPoints;
import dev.toma.pubgmc.common.games.map.TeamSpawnerPoint;
import dev.toma.pubgmc.common.games.util.TeamType;
import dev.toma.pubgmc.util.helper.PacketHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class C2S_AdjustTeamSpawner implements IMessage {

    private String map;
    private BlockPos pos;
    private TeamType type;

    public C2S_AdjustTeamSpawner() {
    }

    public C2S_AdjustTeamSpawner(String map, BlockPos pos, TeamType type) {
        this.map = map;
        this.pos = pos;
        this.type = type;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, map);
        PacketHelper.writeBlockPos(pos, buf);
        buf.writeInt(type.ordinal());
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        map = ByteBufUtils.readUTF8String(buf);
        pos = PacketHelper.readBlockPos(buf);
        type = TeamType.values()[buf.readInt()];
    }

    public static final class Handler implements IMessageHandler<C2S_AdjustTeamSpawner, IMessage> {

        @Override
        public IMessage onMessage(C2S_AdjustTeamSpawner message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;
            player.getServer().addScheduledTask(() -> {
                if (!player.isCreative())
                    return;
                GameDataProvider.getGameData(player.world).ifPresent(data -> {
                    GameMap gameMap = data.getGameMap(message.map);
                    if (gameMap == null)
                        return;
                    gameMap.getPointAt(message.pos).filter(point -> point.is(GameMapPoints.TEAM_SPAWNER))
                            .map(p -> (TeamSpawnerPoint) p)
                            .ifPresent(spawner -> {
                                spawner.setTeamType(message.type);
                                data.sendGameDataToClients();
                            });
                });
            });
            return null;
        }
    }
}
