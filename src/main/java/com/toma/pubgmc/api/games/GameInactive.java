package com.toma.pubgmc.api.games;

import com.toma.pubgmc.api.Game;
import com.toma.pubgmc.api.settings.EntityDeathManager;
import com.toma.pubgmc.api.settings.GameManager;
import com.toma.pubgmc.api.settings.TeamManager;
import com.toma.pubgmc.api.util.GameUtils;
import com.toma.pubgmc.api.settings.GameBotManager;
import com.toma.pubgmc.world.BlueZone;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class GameInactive extends Game {

    public final GameBotManager botManager = GameBotManager.Builder.create().disableBots().lootFactory(ai -> {}).botSpawner((world, game) -> null).addBotLogic(GameUtils::addBaseTasks).build();

    public GameInactive(String name) {
        super(name);
    }

    @Override
    public GameManager getGameManager() {
        return null;
    }

    @Override
    public GameBotManager getBotManager() {
        return null;
    }

    @Override
    public TeamManager getTeamManager() {
        return null;
    }

    @Override
    public EntityDeathManager getEntityDeathManager() {
        return null;
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
}
