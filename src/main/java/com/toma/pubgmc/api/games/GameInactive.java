package com.toma.pubgmc.api.games;

import com.toma.pubgmc.api.Game;
import com.toma.pubgmc.world.BlueZone;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

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
    public void onPlayerKilled(EntityPlayer player, @Nullable EntityLivingBase entityLivingBase, ItemStack gun, boolean headshot) {
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
