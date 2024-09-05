package dev.toma.pubgmc.network;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.capability.GameDataProvider;
import dev.toma.pubgmc.api.capability.IPlayerData;
import dev.toma.pubgmc.network.s2c.*;
import dev.toma.pubgmc.network.c2s.*;
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
        registerServerPacket(C2S_PacketSetProperty.Handler.class, C2S_PacketSetProperty.class);
        registerServerPacket(C2S_PacketFiremode.Handler.class, C2S_PacketFiremode.class);
        registerServerPacket(C2S_PacketShoot.Handler.class, C2S_PacketShoot.class);
        registerServerPacket(C2S_PacketProneStatus.Handler.class, C2S_PacketProneStatus.class);
        registerServerPacket(C2S_PacketUpdateWorkbench.Handler.class, C2S_PacketUpdateWorkbench.class);
        registerServerPacket(C2S_PacketCraftItem.Handler.class, C2S_PacketCraftItem.class);
        registerServerPacket(C2S_PacketControllableInput.Handler.class, C2S_PacketControllableInput.class);
        registerServerPacket(C2S_PacketOpenPlayerEquipment.Handler.class, C2S_PacketOpenPlayerEquipment.class);
        registerServerPacket(C2S_PacketSelectLoadout.Handler.class, C2S_PacketSelectLoadout.class);
        registerServerPacket(C2S_PacketAdjustTeamSpawner.Handler.class, C2S_PacketAdjustTeamSpawner.class);
        registerServerPacket(C2S_PacketAdjustCaptureZone.Handler.class, C2S_PacketAdjustCaptureZone.class);
        registerServerPacket(C2S_PacketAdjustPartialPlayZone.Handler.class, C2S_PacketAdjustPartialPlayZone.class);
        registerServerPacket(C2S_PacketAttachmentRequest.Handler.class, C2S_PacketAttachmentRequest.class);
        registerServerPacket(C2S_RequestGameMenuGUI.Handler.class, C2S_RequestGameMenuGUI.class);
        registerServerPacket(C2S_RequestDriveableEngineToggle.Handler.class, C2S_RequestDriveableEngineToggle.class);

        registerClientPacket(S2C_PacketPlaySoundWithDelay.Handler.class, S2C_PacketPlaySoundWithDelay.class);
        registerClientPacket(S2C_PacketMakeParticles.Handler.class, S2C_PacketMakeParticles.class);
        registerClientPacket(S2C_PacketSendPlayerCapability.Handler.class, S2C_PacketSendPlayerCapability.class);
        registerClientPacket(S2C_PacketSendTileEntityData.Handler.class, S2C_PacketSendTileEntityData.class);
        registerClientPacket(S2C_PacketReceiveServerConfig.Handler.class, S2C_PacketReceiveServerConfig.class);
        registerClientPacket(S2C_PacketNotifyRestoreConfig.Handler.class, S2C_PacketNotifyRestoreConfig.class);
        registerClientPacket(S2C_PacketSendEntityData.Handler.class, S2C_PacketSendEntityData.class);
        registerClientPacket(S2C_PacketAnimation.Handler.class, S2C_PacketAnimation.class);
        registerClientPacket(S2C_PacketSendGameData.Handler.class, S2C_PacketSendGameData.class);
        registerClientPacket(S2C_PacketLoadoutSelect.Handler.class, S2C_PacketLoadoutSelect.class);
        registerClientPacket(S2C_PacketReloadChunks.Handler.class, S2C_PacketReloadChunks.class);
        registerClientPacket(S2C_PacketSendExternalGuiEvent.Handler.class, S2C_PacketSendExternalGuiEvent.class);
        registerClientPacket(S2C_OpenGameMenuGUI.Handler.class, S2C_OpenGameMenuGUI.class);
        registerClientPacket(S2C_PacketSendPartyData.Handler.class, S2C_PacketSendPartyData.class);
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
        sendToClient(new S2C_PacketSendPlayerCapability(player.getUniqueID(), data.serializeNBT()), player);
    }

    public static void syncGameDataToClient(EntityPlayerMP player) {
        GameDataProvider.getGameData(player.world).ifPresent(data -> sendToClient(new S2C_PacketSendGameData(data.serializeNBT()), player));
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
