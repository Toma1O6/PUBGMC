package com.toma.pubgmc.api.games;

import com.toma.pubgmc.api.Game;
import com.toma.pubgmc.util.game.ZoneSettings;
import com.toma.pubgmc.world.BlueZone;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class GameDeathmatch extends Game {

    public GameDeathmatch(ResourceLocation location) {
        super(location);
    }

    @Override
    public void populatePlayerList(World world) {
        world.playerEntities.forEach(player -> this.getJoinedPlayers().add(player.getUniqueID()));
    }

    @Nonnull
    @Override
    public BlueZone initializeZone(World world) {
        ZoneSettings settings = ZoneSettings.Builder.create().damage(1.0F).setStatic().build();
        return new BlueZone(this.getGameData(world), settings);
    }

    @Override
    public void onGameStart(World world) {

    }

    @Override
    public void onGameTick(World world) {

    }

    @Override
    public void onGameStopped(World world, Game game) {

    }

    @Override
    public void onPlayerKilled(EntityPlayer player, @Nullable EntityLivingBase entityLivingBase, ItemStack gun, boolean headshot) {

    }

    @Nullable
    @Override
    public CommandException onGameStartCommandExecuted(ICommandSender sender, MinecraftServer server, String[] additionalArgs) {
        if(additionalArgs.length == 0) {
            return new CommandException("Additional arguments needed! Use /game start gunType...");
        }
        return null;
    }

    @Override
    public void writeDataToNBT(NBTTagCompound compound) {

    }

    @Override
    public void readDataFromNBT(NBTTagCompound compound) {

    }
}
