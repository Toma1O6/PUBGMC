package com.toma.pubgmc.api;

import com.toma.pubgmc.common.capability.IGameData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class Game implements INBTSerializable<NBTTagCompound> {

    public final IGameData gameData;
    public final World world;
    private final List<EntityPlayer> joinedPlayers;

    public Game(World world) {
        this.world = world;
        this.joinedPlayers = new ArrayList<>();
        gameData = world.getCapability(IGameData.GameDataProvider.GAMEDATA, null);
    }

    public abstract void populatePlayerList();

    public abstract void onGameStart();

    public void startGame() {
        this.populatePlayerList();
        this.onGameStart();
    }

    public void stopGame() {

    }

    public void sendMessageToAllPlayers(Predicate<EntityPlayer> predicate, String message) {
        TextComponentTranslation msg = new TextComponentTranslation(message);
        joinedPlayers.stream().filter(predicate).forEach(p -> p.sendMessage(msg));
    }
}
