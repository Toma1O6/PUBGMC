package com.toma.pubgmc.common.network;

import com.toma.pubgmc.Pubgmc;
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
import com.toma.pubgmc.common.network.sp.PacketCreateNBT;
import com.toma.pubgmc.common.network.sp.PacketReloadingSP;
import com.toma.pubgmc.common.network.sp.PacketSound;
import com.toma.pubgmc.common.network.sp.PacketSyncConfig;
import com.toma.pubgmc.common.network.sp.PacketUpdatePlayerData;

import net.minecraftforge.fml.common.network.NetworkRegistry;
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
