package com.toma.pubgmc.api.games;

import com.toma.pubgmc.api.Game;
import com.toma.pubgmc.world.BlueZone;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GameInactive extends Game {

    public GameInactive(ResourceLocation location) {
        super(location);
    }

    @Override
    public void onGameStart(World world) {
        throw new IllegalStateException("Cannot start this type of game!");
    }

    @Override
    public void populatePlayerList(World world) {
    }

    @Override
    public void onGameStopped(World world, Game game) {
    }

    @Override
    public void onGameTick(World world) {

    }

    @Override
    public BlueZone initializeZone(World world) {
        return new BlueZone();
    }

    @Override
    public boolean shouldUpdateTileEntities() {
        return false;
    }
}
