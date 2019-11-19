package com.toma.pubgmc.api.games;

import com.toma.pubgmc.api.Game;
import com.toma.pubgmc.common.entity.bot.EntityAIPlayer;
import com.toma.pubgmc.world.BlueZone;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class GameInactive extends Game {

    public GameInactive(String name) {
        super(name);
    }

    @Override
    public void onGameStart(World world) {
        throw new IllegalStateException("Cannot start this type of game!");
    }

    @Override
    public void populatePlayerList(World world) {
    }

    @Override
    public void onGameStopped(World world) {
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

    @Override
    public void writeDataToNBT(NBTTagCompound compound) {
    }

    @Override
    public void readDataFromNBT(NBTTagCompound compound) {
    }

    @Override
    public boolean shouldCreateDeathCrate() {
        return false;
    }

    @Override
    public boolean canSpawnBots() {
        return false;
    }

    @Override
    public Consumer<EntityAIPlayer> getLootDistributor() {
        return bot -> {};
    }
}
