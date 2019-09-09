package com.toma.pubgmc.common.network;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.network.server.*;
import com.toma.pubgmc.common.network.sp.*;
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
    private static int ID = -1;

    /**
     * Register packets here
     */
    public static void initialize() {
        registerServerPacket(PacketAim.class);
        registerServerPacket(PacketReload.class);
        registerServerPacket(PacketFiremode.class);
        registerServerPacket(PacketNightVision.class);
        registerServerPacket(PacketReloading.class);
        registerServerPacket(PacketUpdateBoostValue.class);
        registerServerPacket(PacketShoot.class);
        registerServerPacket(PacketOpenGui.class);
        registerServerPacket(PacketHandleParachuteInputs.class);
        registerServerPacket(PacketHandleVehicleInput.class);
        registerServerPacket(PacketSetScopeVariants.class);
        registerServerPacket(PacketTeleportPlayer.class);
        registerServerPacket(PacketChooseLocation.class);
        registerServerPacket(PacketProne.Handler.class, PacketProne.class);
        registerServerPacket(PacketUpdateWorkbench.Handler.class, PacketUpdateWorkbench.class);
        registerServerPacket(PacketCraft.Handler.class, PacketCraft.class);
        registerServerPacket(PacketSaveConfig.Handler.class, PacketSaveConfig.class);
        registerServerPacket(PacketUpdateLootData.Handler.class, PacketUpdateLootData.class);
        registerClientPacket(PacketReloadingSP.class);
        registerClientPacket(PacketDelayedSound.class);
        registerClientPacket(PacketCreateNBT.class);
        registerClientPacket(PacketParticle.Handler.class, PacketParticle.class);
        registerClientPacket(PacketClientCapabilitySync.class);
        registerClientPacket(PacketUpdatePlayerRotation.class);
        registerClientPacket(PacketVehicleData.class);
        registerClientPacket(PacketSyncTileEntity.Handler.class, PacketSyncTileEntity.class);
        registerClientPacket(PacketUpdateFlashStatus.Handler.class, PacketUpdateFlashStatus.class);
        registerClientPacket(PacketGetConfigFromServer.Handler.class, PacketGetConfigFromServer.class);
        registerClientPacket(PacketLoadConfig.Handler.class, PacketLoadConfig.class);
        registerClientPacket(PacketDisplayLootSetupGui.Handler.class, PacketDisplayLootSetupGui.class);
        registerClientPacket(PacketSyncGameData.Handler.class, PacketSyncGameData.class);
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

    /**
     * Registers packet for Server -> Client
     *
     * @param packetClass
     */
    private static void registerClientPacket(Class packetClass) {
        INSTANCE.registerMessage(packetClass, packetClass, nextID(), Side.CLIENT);
    }

    private static <REQ extends IMessage, REPLY extends IMessage> void registerClientPacket(Class<? extends IMessageHandler<REQ, REPLY>> handler, Class<REQ> packet) {
        INSTANCE.registerMessage(handler, packet, nextID(), Side.CLIENT);
    }

    private static <REQ extends IMessage, REPLY extends IMessage> void registerServerPacket(Class<? extends IMessageHandler<REQ, REPLY>> handler, Class<REQ> packet) {
        INSTANCE.registerMessage(handler, packet, nextID(), Side.SERVER);
    }

    /**
     * Registers packet for Client -> Server
     *
     * @param packetClass
     */
    private static void registerServerPacket(Class packetClass) {
        INSTANCE.registerMessage(packetClass, packetClass, nextID(), Side.SERVER);
    }

    private static int nextID() {
        return ID++;
    }
}
