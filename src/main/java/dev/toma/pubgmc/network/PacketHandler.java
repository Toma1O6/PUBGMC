package dev.toma.pubgmc.network;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.capability.GameDataProvider;
import dev.toma.pubgmc.api.capability.IPlayerData;
import dev.toma.pubgmc.network.client.*;
import dev.toma.pubgmc.network.server.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Pubgmc.MOD_ID);
    private static int ID = 0;

    public static void initialize() {
        registerServerPacket(SPacketSetProperty.Handler.class, SPacketSetProperty.class);
        registerServerPacket(SPacketFiremode.Handler.class, SPacketFiremode.class);
        registerServerPacket(PacketReloading.Handler.class, PacketReloading.class);
        registerServerPacket(PacketShoot.Handler.class, PacketShoot.class);
        registerServerPacket(PacketOpenGui.Handler.class, PacketOpenGui.class);
        registerServerPacket(PacketProne.Handler.class, PacketProne.class);
        registerServerPacket(PacketUpdateWorkbench.Handler.class, PacketUpdateWorkbench.class);
        registerServerPacket(PacketCraft.Handler.class, PacketCraft.class);
        registerServerPacket(PacketSaveConfig.Handler.class, PacketSaveConfig.class);
        registerServerPacket(SPacketControllableInput.Handler.class, SPacketControllableInput.class);
        registerServerPacket(C2S_PacketOpenPlayerEquipment.Handler.class, C2S_PacketOpenPlayerEquipment.class);
        registerServerPacket(C2S_SelectLoadout.Handler.class, C2S_SelectLoadout.class);
        registerServerPacket(C2S_AdjustTeamSpawner.Handler.class, C2S_AdjustTeamSpawner.class);
        registerServerPacket(C2S_AdjustCaptureZone.Handler.class, C2S_AdjustCaptureZone.class);
        registerServerPacket(C2S_AdjustPartialPlayZone.Handler.class, C2S_AdjustPartialPlayZone.class);

        registerClientPacket(PacketReloadingSP.Handler.class, PacketReloadingSP.class);
        registerClientPacket(PacketDelayedSound.Handler.class, PacketDelayedSound.class);
        registerClientPacket(PacketParticle.Handler.class, PacketParticle.class);
        registerClientPacket(PacketClientCapabilitySync.Handler.class, PacketClientCapabilitySync.class);
        registerClientPacket(PacketVehicleData.Handler.class, PacketVehicleData.class);
        registerClientPacket(PacketSyncTileEntity.Handler.class, PacketSyncTileEntity.class);
        registerClientPacket(PacketGetConfigFromServer.Handler.class, PacketGetConfigFromServer.class);
        registerClientPacket(PacketLoadConfig.Handler.class, PacketLoadConfig.class);
        registerClientPacket(PacketSyncEntity.Handler.class, PacketSyncEntity.class);
        registerClientPacket(CPacketAnimation.Handler.class, CPacketAnimation.class);
        registerClientPacket(S2C_SendGameData.Handler.class, S2C_SendGameData.class);
        registerClientPacket(S2C_PacketLoadoutSelect.Handler.class, S2C_PacketLoadoutSelect.class);
        registerClientPacket(S2C_ReloadChunks.Handler.class, S2C_ReloadChunks.class);
    }

    public static void sendToClient(IMessage packet, EntityPlayerMP player) {
        INSTANCE.sendTo(packet, player);
    }

    public static void sendToAllClients(IMessage packet) {
        INSTANCE.sendToAll(packet);
    }

    public static void sendToClientsAround(IMessage packet, TargetPoint target) {
        INSTANCE.sendToAllAround(packet, target);
    }

    public static void sendToAllTracking(IMessage packet, Entity entity) {
        INSTANCE.sendToAllTracking(packet, entity);
    }

    public static void sendToClientsAround(IMessage packet, int dimension, BlockPos pos, double range) {
        INSTANCE.sendToAllAround(packet, new TargetPoint(dimension, pos.getX(), pos.getY(), pos.getZ(), range));
    }

    public static void sendToClientsAround(IMessage packet, int dimension, double x, double y, double z, double range) {
        INSTANCE.sendToAllAround(packet, new TargetPoint(dimension, x, y, z, range));
    }

    public static void sendToClientsAround(IMessage packet, EntityPlayerMP player, double range) {
        INSTANCE.sendToAllAround(packet, new TargetPoint(player.dimension, player.posX, player.posY, player.posZ, range));
    }

    public static void sendToDimension(IMessage packet, int dimension) {
        INSTANCE.sendToDimension(packet, dimension);
    }

    public static void sendToDimension(IMessage packet, EntityPlayerMP player) {
        INSTANCE.sendToDimension(packet, player.dimension);
    }

    public static void sendToServer(IMessage packet) {
        INSTANCE.sendToServer(packet);
    }

    public static void syncPlayerDataToClient(IPlayerData data, EntityPlayerMP player) {
        sendToClient(new PacketClientCapabilitySync(player.getUniqueID(), data.serializeNBT()), player);
    }

    public static void syncGameDataToClient(EntityPlayerMP player) {
        GameDataProvider.getGameData(player.world).ifPresent(data -> sendToClient(new S2C_SendGameData(data.serializeNBT()), player));
    }

    private static <REQ extends IMessage, REPLY extends IMessage> void registerClientPacket(Class<? extends IMessageHandler<REQ, REPLY>> handler, Class<REQ> packet) {
        INSTANCE.registerMessage(handler, packet, nextID(), Side.CLIENT);
    }

    private static <REQ extends IMessage, REPLY extends IMessage> void registerServerPacket(Class<? extends IMessageHandler<REQ, REPLY>> handler, Class<REQ> packet) {
        INSTANCE.registerMessage(handler, packet, nextID(), Side.SERVER);
    }

    private static int nextID() {
        return ID++;
    }
}
