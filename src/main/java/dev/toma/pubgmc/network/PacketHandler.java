package dev.toma.pubgmc.network;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.common.capability.player.IPlayerData;
import dev.toma.pubgmc.network.server.*;
import dev.toma.pubgmc.network.sp.*;
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
        registerServerPacket(PacketServerAction.Handler.class, PacketServerAction.class);
        registerServerPacket(PacketFiremode.Handler.class, PacketFiremode.class);
        registerServerPacket(PacketReloading.Handler.class, PacketReloading.class);
        registerServerPacket(PacketShoot.Handler.class, PacketShoot.class);
        registerServerPacket(PacketOpenGui.Handler.class, PacketOpenGui.class);
        registerServerPacket(PacketSetScopeVariants.Handler.class, PacketSetScopeVariants.class);
        registerServerPacket(PacketChooseLocation.Handler.class, PacketChooseLocation.class);
        registerServerPacket(PacketProne.Handler.class, PacketProne.class);
        registerServerPacket(PacketUpdateWorkbench.Handler.class, PacketUpdateWorkbench.class);
        registerServerPacket(PacketCraft.Handler.class, PacketCraft.class);
        registerServerPacket(PacketSaveConfig.Handler.class, PacketSaveConfig.class);
        registerServerPacket(PacketUpdateLootData.Handler.class, PacketUpdateLootData.class);
        registerServerPacket(SPacketControllableInput.Handler.class, SPacketControllableInput.class);

        registerClientPacket(PacketReloadingSP.Handler.class, PacketReloadingSP.class);
        registerClientPacket(PacketDelayedSound.Handler.class, PacketDelayedSound.class);
        registerClientPacket(PacketCreateNBT.Handler.class, PacketCreateNBT.class);
        registerClientPacket(PacketParticle.Handler.class, PacketParticle.class);
        registerClientPacket(PacketClientCapabilitySync.Handler.class, PacketClientCapabilitySync.class);
        registerClientPacket(PacketUpdatePlayerRotation.Handler.class, PacketUpdatePlayerRotation.class);
        registerClientPacket(PacketVehicleData.Handler.class, PacketVehicleData.class);
        registerClientPacket(PacketSyncTileEntity.Handler.class, PacketSyncTileEntity.class);
        registerClientPacket(PacketUpdateFlashStatus.Handler.class, PacketUpdateFlashStatus.class);
        registerClientPacket(PacketGetConfigFromServer.Handler.class, PacketGetConfigFromServer.class);
        registerClientPacket(PacketLoadConfig.Handler.class, PacketLoadConfig.class);
        registerClientPacket(PacketDisplayLootSetupGui.Handler.class, PacketDisplayLootSetupGui.class);
        registerClientPacket(PacketSyncGameData.Handler.class, PacketSyncGameData.class);
        registerClientPacket(PacketSyncEntity.Handler.class, PacketSyncEntity.class);
        registerClientPacket(PacketOpenObjectiveGui.Handler.class, PacketOpenObjectiveGui.class);
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
        sendToClient(new PacketClientCapabilitySync(player, data.serializeNBT()), player);
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
