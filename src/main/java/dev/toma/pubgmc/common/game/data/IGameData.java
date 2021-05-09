package dev.toma.pubgmc.common.game.data;

import dev.toma.pubgmc.common.game.AbstractGame;
import dev.toma.pubgmc.common.game.GameLaunchConfig;
import dev.toma.pubgmc.common.game.map.Area3D;
import dev.toma.pubgmc.common.game.map.GameMap;
import dev.toma.pubgmc.common.game.map.MapCollection;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.List;
import java.util.Map;

public interface IGameData extends INBTSerializable<NBTTagCompound> {

    void tickGames();

    void launchGame(GameLaunchConfig<?> config);

    Area3D getGlobalLobby();

    Map<Long, AbstractGame> getGames();

    MapCollection getMaps();

    World getWorld();
}
