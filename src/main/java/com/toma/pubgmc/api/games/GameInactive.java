package com.toma.pubgmc.api.games;

import com.toma.pubgmc.api.Game;
import com.toma.pubgmc.util.game.ZoneSettings;
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
    public void onGameTick(World world) {

    }

    @Override
    public BlueZone initializeZone(World world) {
        return new BlueZone(ZoneSettings.Builder.create().setStatic().damage(10.0F).speed(10.0F).build(), this.getGameData(world));
    }

    @Override
    public boolean shouldUpdateTileEntities() {
        return false;
    }
}
