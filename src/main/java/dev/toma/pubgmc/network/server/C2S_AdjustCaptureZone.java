package dev.toma.pubgmc.network.server;

import dev.toma.pubgmc.api.capability.GameDataProvider;
import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.common.games.map.CaptureZonePoint;
import dev.toma.pubgmc.common.games.map.GameMapPoints;
import dev.toma.pubgmc.util.helper.PacketHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class C2S_AdjustCaptureZone implements IMessage {

    private String map;
    private BlockPos pos;
    private Vec3d left, right;
    private String label;

    public C2S_AdjustCaptureZone() {
    }

    public C2S_AdjustCaptureZone(String map, BlockPos pos, Vec3d left, Vec3d right, String label) {
        this.map = map;
        this.pos = pos;
        this.left = left;
        this.right = right;
        this.label = label;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, map);
        PacketHelper.writeBlockPos(pos, buf);
        buf.writeDouble(left.x);
        buf.writeDouble(left.y);
        buf.writeDouble(left.z);
        buf.writeDouble(right.x);
        buf.writeDouble(right.y);
        buf.writeDouble(right.z);
        ByteBufUtils.writeUTF8String(buf, label != null ? label : "");
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        map = ByteBufUtils.readUTF8String(buf);
        pos = PacketHelper.readBlockPos(buf);
        left = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
        right = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
        label = ByteBufUtils.readUTF8String(buf);
    }

    public static final class Handler implements IMessageHandler<C2S_AdjustCaptureZone, IMessage> {

        @Override
        public IMessage onMessage(C2S_AdjustCaptureZone message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;
            player.getServer().addScheduledTask(() -> {
                if (!player.isCreative())
                    return;
                GameDataProvider.getGameData(player.world).ifPresent(data -> {
                    GameMap gameMap = data.getGameMap(message.map);
                    if (gameMap == null)
                        return;
                    gameMap.getPointAt(message.pos).filter(point -> point.is(GameMapPoints.CAPTURE_ZONE))
                            .map(p -> (CaptureZonePoint) p)
                            .ifPresent(zone -> {
                                zone.setLabel(message.label.isEmpty() ? null : message.label);
                                zone.setDimensions(message.left, message.right);
                                data.sendGameDataToClients();
                            });
                });
            });
            return null;
        }
    }
}
