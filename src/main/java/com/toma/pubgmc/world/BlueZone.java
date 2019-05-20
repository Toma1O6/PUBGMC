package com.toma.pubgmc.world;

import com.toma.pubgmc.common.capability.IGameData;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlueZone 
{
	private static final BlueZone INSTANCE = new BlueZone();
	private int phase;
	private BlueZone lastZone;
	
	public void create(IGameData data) {
		this.reset();
		BlockPos pos = data.getMapCenter();
		// TODO: create zone center within map border
	}
	
	public void reset() {
		this.phase = 0;
	}
	
	public void next() {
		lastZone = this;
		++phase;
		// TODO gen new zone center, increase damage, recalculate movement
	}
	
	public BlockPos getCorner1() {
		// TODO
		return null;
	}
	
	public BlockPos getCorner2() {
		// TODO
		return null;
	}
	
	public boolean isInsideZone(World world, BlockPos pos) {
		if(world.isBlockLoaded(pos)) {
			return doesPositionFit(pos.getX(), this.getCorner1().getX(), this.getCorner2().getX()) 
				&& doesPositionFit(pos.getZ(), this.getCorner1().getZ(), this.getCorner2().getZ());
		}
		return false;
	}
	
	public static BlueZone instance() {
		return INSTANCE;
	}
	
	private static boolean doesPositionFit(int coordinate, int corner1, int corner2) {
		if(corner1 >= corner2) {
			if(coordinate <= corner1 && coordinate >= corner2) {
				return true;
			}
			return false;
		} else {
			if(coordinate >= corner1 && coordinate <= corner2) {
				return true;
			}
			return false;
		}
	}
}
