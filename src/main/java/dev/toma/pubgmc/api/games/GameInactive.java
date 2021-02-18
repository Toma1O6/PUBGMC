package dev.toma.pubgmc.api.games;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.settings.EntityDeathManager;
import dev.toma.pubgmc.api.settings.GameBotManager;
import dev.toma.pubgmc.api.settings.GameManager;
import dev.toma.pubgmc.api.settings.TeamManager;
import dev.toma.pubgmc.api.teams.Team;
import dev.toma.pubgmc.world.BlueZone;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class GameInactive extends Game {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Pubgmc.MOD_ID, "textures/gui/game.png");

    public GameInactive(String name) {
        super(name, TEXTURE);
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

    @Override
    public void onGameObjectiveReached(World world, @Nullable Team team) {

    }
}
