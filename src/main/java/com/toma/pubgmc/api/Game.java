package com.toma.pubgmc.api;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.capability.IGameData;
import com.toma.pubgmc.init.GameRegistry;
import com.toma.pubgmc.world.BlueZone;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class Game implements INBTSerializable<NBTTagCompound> {

    public final IGameData gameData;
    public final World world;
    public final BlueZone zone;
    protected final List<EntityPlayer> joinedPlayers;

    public Game(World world, ResourceLocation registryName) {
        this.world = world;
        this.joinedPlayers = new ArrayList<>();
        gameData = world.getCapability(IGameData.GameDataProvider.GAMEDATA, null);
        zone = this.initializeZone(world);
        GameRegistry.registerGame(registryName, this);
    }

    public abstract void populatePlayerList();

    public abstract void onGameStart();

    public abstract void onGameTick();

    public abstract BlueZone initializeZone(World world);

    public void onPlayerDeath(EntityPlayer player) {

    }

    @SideOnly(Side.CLIENT)
    public void renderGameInfo() {

    }

    public boolean startGame() {
        try {
            this.populatePlayerList();
            this.onGameStart();
        } catch (Exception e) {
            Pubgmc.logger.fatal("Exception occured when starting game, aborting..");
            return false;
        }
        return true;
    }

    public void stopGame() {

    }

    public void sendMessageToAllPlayers(Predicate<EntityPlayer> predicate, String message) {
        TextComponentTranslation msg = new TextComponentTranslation(message);
        joinedPlayers.stream().filter(predicate).forEach(p -> p.sendMessage(msg));
    }
}
