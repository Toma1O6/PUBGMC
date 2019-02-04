package com.toma.pubgmc.common.network;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.network.server.PacketAim;
import com.toma.pubgmc.common.network.server.PacketCraft;
import com.toma.pubgmc.common.network.server.PacketFiremode;
import com.toma.pubgmc.common.network.server.PacketHandleParachuteInputs;
import com.toma.pubgmc.common.network.server.PacketHandleVehicleInputs;
import com.toma.pubgmc.common.network.server.PacketNightVision;
import com.toma.pubgmc.common.network.server.PacketOpenGui;
import com.toma.pubgmc.common.network.server.PacketReload;
import com.toma.pubgmc.common.network.server.PacketReloading;
import com.toma.pubgmc.common.network.server.PacketSetScopeVariants;
import com.toma.pubgmc.common.network.server.PacketShoot;
import com.toma.pubgmc.common.network.server.PacketUpdateBoostValue;
import com.toma.pubgmc.common.network.sp.PacketClientCapabilitySync;
import com.toma.pubgmc.common.network.sp.PacketCreateNBT;
import com.toma.pubgmc.common.network.sp.PacketParticle;
import com.toma.pubgmc.common.network.sp.PacketReloadingSP;
import com.toma.pubgmc.common.network.sp.PacketSound;
import com.toma.pubgmc.common.network.sp.PacketSyncConfig;
import com.toma.pubgmc.common.network.sp.PacketUpdateAttachmentGUI;
import com.toma.pubgmc.common.network.sp.PacketUpdatePlayerData;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler 
{
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Pubgmc.MOD_ID);
	private static int ID = -1;
	
	/** 
	 *  Register packets here
	 */
	public static void initialize()
	{
		//server packets
		registerServer(PacketAim.class);
		registerServer(PacketReload.class);
		registerServer(PacketFiremode.class);
		registerServer(PacketNightVision.class);
		registerServer(PacketReloading.class);
		registerServer(PacketUpdateBoostValue.class);
		registerServer(PacketShoot.class);
		registerServer(PacketOpenGui.class);
		registerServer(PacketCraft.class);
		registerServer(PacketHandleParachuteInputs.class);
		registerServer(PacketHandleVehicleInputs.class);
		registerServer(PacketSetScopeVariants.class);
		
		//client packets
		registerClient(PacketSyncConfig.class);
		registerClient(PacketReloadingSP.class);
		registerClient(PacketUpdatePlayerData.class);
		registerClient(PacketSound.class);
		registerClient(PacketCreateNBT.class);
		registerClient(PacketUpdateAttachmentGUI.class);
		registerClient(PacketParticle.class);
		registerClient(PacketClientCapabilitySync.class);
	}
	
	public static void sendToClient(IMessage packet, EntityPlayerMP player)
	{
		INSTANCE.sendTo(packet, player);
	}
	
	public static void sendToAllClients(IMessage packet)
	{
		INSTANCE.sendToAll(packet);
	}
	
	public static void sendToClientsAround(IMessage packet, TargetPoint target)
	{
		INSTANCE.sendToAllAround(packet, target);
	}
	
	public static void sendToClientsAround(IMessage packet, int dimension, BlockPos pos, double range)
	{
		INSTANCE.sendToAllAround(packet, new TargetPoint(dimension, pos.getX(), pos.getY(), pos.getZ(), range));
	}
	
	public static void sendToClientAround(IMessage packet, int dimension, double x, double y, double z, double range)
	{
		INSTANCE.sendToAllAround(packet, new TargetPoint(dimension, x, y, z, range));
	}
	
	public static void sendToClientsAround(IMessage packet, EntityPlayerMP player, double range)
	{
		INSTANCE.sendToAllAround(packet, new TargetPoint(player.dimension, player.posX, player.posY, player.posZ, range));
	}
	
	public static void sendToDimension(IMessage packet, int dimension)
	{
		INSTANCE.sendToDimension(packet, dimension);
	}
	
	public static void sendToDimension(IMessage packet, EntityPlayerMP player)
	{
		INSTANCE.sendToDimension(packet, player.dimension);
	}
	
	public static void sendToServer(IMessage packet)
	{
		INSTANCE.sendToServer(packet);
	}
	
	public static void syncPlayerDataToClient(IPlayerData data, EntityPlayerMP player)
	{
		sendToClient(new PacketClientCapabilitySync(data), player);
	}
	
	private static void registerClient(Class packetClass)
	{
		INSTANCE.registerMessage(packetClass, packetClass, nextID(), Side.CLIENT);
	}
	
	private static void registerServer(Class packetClass)
	{
		INSTANCE.registerMessage(packetClass, packetClass, nextID(), Side.SERVER);
	}
	
	private static int nextID()
	{
		return ID++;
	}
}
