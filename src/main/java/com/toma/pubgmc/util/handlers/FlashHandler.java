package com.toma.pubgmc.util.handlers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.toma.pubgmc.common.entity.EntityFlashbang;
import com.toma.pubgmc.util.PUBGMCUtil;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;

public final class FlashHandler {

	public static final Map<UUID, Integer> FLASHED_PLAYERS = new HashMap<>();
	public static final int MAX_FLASH_RANGE = 12;
	
	public static void flashPlayer(EntityPlayer player, EntityFlashbang from) {
		UUID uuid = player.getUniqueID();
		if(FLASHED_PLAYERS.containsKey(uuid)) {
			FLASHED_PLAYERS.remove(uuid);
		}
		if(isInRangeToFlash(player, from)) {
			FLASHED_PLAYERS.put(uuid, getFlashAmountFor(player, from));
		}
	}
	
	public static int getFlashAmountFor(EntityPlayer player, EntityFlashbang flash) {
		float f0 = (float)PUBGMCUtil.getDistanceToBlockPos3D(player.getPosition(), flash.getPosition());
		return f0 > MAX_FLASH_RANGE ? 0 : (int)(100*(1-(f0/100)));
	}
	
	public static boolean isInRangeToFlash(EntityPlayer player, EntityFlashbang flash) {
		Vec3d playerPos = PUBGMCUtil.getPositionVec(player);
		Vec3d playerLook = PUBGMCUtil.multiply(player.getLookVec(), 100);
		Vec3d playerLookNegativeYaw = playerPos.add(playerLook.rotateYaw(-40f));
		Vec3d playerLookPositiveYaw = playerPos.add(playerLook.rotateYaw( 40f));
		Vec3d flashPos = PUBGMCUtil.getPositionVec(flash);
		double d0 = area(playerPos, playerLookNegativeYaw, playerLookPositiveYaw);
		double d1 = area(flashPos, playerLookNegativeYaw, playerLookPositiveYaw);
		double d2 = area(playerPos, flashPos, playerLookPositiveYaw);
		double d3 = area(playerPos, playerLookNegativeYaw, flashPos);
		System.out.println(d0);
		System.out.println(d1);
		System.out.println(d2);
		System.out.println(d3);
		System.out.println();
		return (d0 == d1 + d2 + d3);
	}
	
	private static double area(Vec3d yawNeg, Vec3d yawPos, Vec3d flash) {
		return Math.abs((yawNeg.x*(yawPos.z-flash.z) + yawPos.x*(flash.z-yawNeg.z) + flash.x*(yawNeg.z-yawPos.z))/2.0D);
	}
}
