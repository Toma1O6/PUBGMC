package com.toma.pubgmc.api.interfaces;

import com.toma.pubgmc.api.games.Game;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface BotSpawner<T extends Game> {

    BlockPos getSpawnPosition(World world, T game);
}
