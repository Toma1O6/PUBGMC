package com.toma.pubgmc.api.games;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.api.Game;
import com.toma.pubgmc.common.entity.EntityPlane;
import com.toma.pubgmc.util.game.ZoneSettings;
import com.toma.pubgmc.world.BlueZone;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class GameBattleRoyale extends Game {

    private int gameTimer;

    public GameBattleRoyale(World world) {
        super(world, new ResourceLocation(Pubgmc.MOD_ID, "battleroyale"));
    }

    @Override
    public BlueZone initializeZone(World world) {
        ZoneSettings settings = ZoneSettings.Builder.create()
                .damage(0.01f)
                .speed(0.01f)
                .build();
        return new BlueZone(settings, this);
    }

    @Override
    public void onGameStart() {
        if(!world.isRemote) {
            EntityPlane plane = new EntityPlane(world, gameData);
            int joined = 0;
            Iterator<EntityPlayer> iterator = joinedPlayers.iterator();
            while(iterator.hasNext()) {
                EntityPlayer player = iterator.next();
                boolean flag = iterator.hasNext();
                ++joined;
                plane.pendingPlayers.add(player.getUniqueID());
                if(joined >= 31) {
                    world.spawnEntity(plane);
                    for(UUID uuid : plane.pendingPlayers) {
                        EntityPlayer pending = world.getPlayerEntityByUUID(uuid);
                        if(pending != null) {
                            pending.startRiding(plane);
                        }
                    }
                    plane.pendingPlayers = null;
                    if(flag)
                        plane = new EntityPlane(world, gameData);
                }
            }
        }
    }

    @Override
    public void populatePlayerList() {
        world.playerEntities.stream()
                .filter(player -> !player.isCreative() && !player.isSpectator())
                .forEach(player -> joinedPlayers.add(player));
    }

    @Override
    public void onGameTick() {
        ++this.gameTimer;
    }
}
