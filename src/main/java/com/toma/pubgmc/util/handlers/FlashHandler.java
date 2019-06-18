package com.toma.pubgmc.util.handlers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.toma.pubgmc.common.entity.EntityFlashbang;
import com.toma.pubgmc.util.PUBGMCUtil;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;

public class FlashHandler {

	private static final FlashHandler INSTANCE = new FlashHandler();
	public static final Map<UUID, Integer> FLASHED_PLAYERS = new HashMap<>();
	public static final int MAX_FLASH_RANGE = 12;
	
	public static FlashHandler instance() {
		return INSTANCE;
	}
	
	public void flashPlayer(EntityPlayer player, EntityFlashbang from) {
		UUID uuid = player.getUniqueID();
		if(FLASHED_PLAYERS.containsKey(uuid)) {
			FLASHED_PLAYERS.remove(uuid);
		}
		if(isPlayerLookingAtFlash(player, from)) {
			FLASHED_PLAYERS.put(uuid, getFlashAmountFor(player, from));
		}
	}
	
	public int getFlashAmountFor(EntityPlayer player, EntityFlashbang flash) {
		float f0 = (float)PUBGMCUtil.getDistanceToBlockPos3D(player.getPosition(), flash.getPosition());
		return f0 > MAX_FLASH_RANGE ? 0 : (int)(100*(1-(f0/100)));
	}
	
	public boolean isPlayerLookingAtFlash(EntityPlayer player, EntityFlashbang flash) {
		if(PUBGMCUtil.getDistanceToBlockPos3D(player.getPosition(), flash.getPosition()) > MAX_FLASH_RANGE) {
			return false;
		}
		
		Vec3d flashVec = PUBGMCUtil.getPositionVec(flash);
		Vec3d playerVec = PUBGMCUtil.getPositionVec(player);
		
		double angle = Math.atan2(player.posZ-flash.posZ, player.posX-flash.posX);
		return true;
	}
}
