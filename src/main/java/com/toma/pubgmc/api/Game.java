package com.toma.pubgmc.api;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.capability.IGameData;
import com.toma.pubgmc.init.GameRegistry;
import com.toma.pubgmc.world.BlueZone;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class Game implements INBTSerializable<NBTTagCompound> {

    public IGameData gameData;
    public BlueZone zone;
    public final ResourceLocation registryName;

    protected int gameTimer;
    protected List<EntityPlayer> joinedPlayers;
    private List<UUID> temporaryPlayerStorage;

    public Game(ResourceLocation registryName) {
        this.joinedPlayers = new ArrayList<>();
        this.registryName = registryName;
        GameRegistry.registerGame(registryName, this);
    }

    public abstract void populatePlayerList(World world);

    public abstract void onGameStart(World world);

    public abstract void onGameTick(World world);

    @Nonnull
    public abstract BlueZone initializeZone(World world);

    public void onPlayerDeath(EntityPlayer player) {

    }

    public boolean shouldUpdateTileEntities() {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void renderGameInfo(ScaledResolution res) {

    }

    public final boolean startGame(World world) {
        try {
            this.gameData = world.getCapability(IGameData.GameDataProvider.GAMEDATA, null);
            this.zone = this.initializeZone(world);
            this.populatePlayerList(world);
            this.onGameStart(world);
            gameData.setGame(this);
            gameTimer = 0;
        } catch (Exception e) {
            Pubgmc.logger.fatal(e.getMessage()  + " => aborting game start!");
            return false;
        }
        return true;
    }

    public final void tickGame(World world) {
        if(gameData.isPlaying()) {
            if(temporaryPlayerStorage != null) {
                temporaryPlayerStorage.forEach(uuid -> joinedPlayers.add(world.getPlayerEntityByUUID(uuid)));
                temporaryPlayerStorage = null;
                joinedPlayers = joinedPlayers.stream().filter(Objects::nonNull).collect(Collectors.toList());
            }
            ++gameTimer;
            this.onGameTick(world);
            this.zone.bluezoneTick(world);
        }
    }

    public final void stopGame(World world) {
        IGameData data = world.getCapability(IGameData.GameDataProvider.GAMEDATA, null);
        if(data != null && data.getLobby() != null) {
            BlockPos pos = data.getLobby().center;
            joinedPlayers.forEach(p -> p.attemptTeleport(pos.getX(), pos.getY() + 1, pos.getZ()));
        }
    }

    public final List<EntityPlayer> getJoinedPlayers() {
        return joinedPlayers;
    }

    public final int getGameTimer() {
        return gameTimer;
    }

    public void notifyAllPlayers(String message) {
        joinedPlayers.forEach(p -> p.sendMessage(new TextComponentString(message)));
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("timer", gameTimer);
        NBTTagList uuidList = new NBTTagList();
        joinedPlayers.forEach(p -> uuidList.appendTag(new NBTTagString(p.getUniqueID().toString())));
        nbt.setTag("players", uuidList);
        if(zone != null) {
            nbt.setTag("zone", zone.serializeNBT());
        }
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        temporaryPlayerStorage = new ArrayList<>();
        joinedPlayers.clear();
        gameTimer = nbt.getInteger("gameTimer");
        NBTTagList uuids = nbt.getTagList("players", Constants.NBT.TAG_STRING);
        uuids.forEach(tag -> temporaryPlayerStorage.add(UUID.fromString(((NBTTagString) tag).getString())));
        if(nbt.hasKey("zone")) {
            zone.deserializeNBT(nbt.getCompoundTag("zone"));
        }
    }
}
