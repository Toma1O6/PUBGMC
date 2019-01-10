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
		INSTANCE.registerMessage(PacketAim.class, PacketAim.class, nextID(), Side.SERVER);
		INSTANCE.registerMessage(PacketReload.class, PacketReload.class, nextID(), Side.SERVER);
		INSTANCE.registerMessage(PacketFiremode.class, PacketFiremode.class, nextID(), Side.SERVER);
		INSTANCE.registerMessage(PacketNightVision.class, PacketNightVision.class, nextID(), Side.SERVER);
		INSTANCE.registerMessage(PacketReloading.class, PacketReloading.class, nextID(), Side.SERVER);
		INSTANCE.registerMessage(PacketSyncConfig.class, PacketSyncConfig.class, nextID(), Side.CLIENT);
		INSTANCE.registerMessage(PacketUpdateBoostValue.class, PacketUpdateBoostValue.class, nextID(), Side.SERVER);
		INSTANCE.registerMessage(PacketReloadingSP.class, PacketReloadingSP.class, nextID(), Side.CLIENT);
		INSTANCE.registerMessage(PacketUpdatePlayerData.class, PacketUpdatePlayerData.class, nextID(), Side.CLIENT);
		INSTANCE.registerMessage(PacketShoot.class, PacketShoot.class, nextID(), Side.SERVER);
		INSTANCE.registerMessage(PacketOpenGui.class, PacketOpenGui.class, nextID(), Side.SERVER);
		INSTANCE.registerMessage(PacketSound.class, PacketSound.class, nextID(), Side.CLIENT);
		INSTANCE.registerMessage(PacketCreateNBT.class, PacketCreateNBT.class, nextID(), Side.CLIENT);
		INSTANCE.registerMessage(PacketCraft.class, PacketCraft.class, nextID(), Side.SERVER);
		INSTANCE.registerMessage(PacketHandleParachuteInputs.class, PacketHandleParachuteInputs.class, nextID(), Side.SERVER);
		INSTANCE.registerMessage(PacketHandleVehicleInputs.class, PacketHandleVehicleInputs.class, nextID(), Side.SERVER);
	}
	
	private static int nextID()
	{
		return ID++;
	}
}
