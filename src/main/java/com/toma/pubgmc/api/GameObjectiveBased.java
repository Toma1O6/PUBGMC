package com.toma.pubgmc.api;

import com.toma.pubgmc.api.objectives.types.GameArea;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public abstract class GameObjectiveBased extends Game {

    private Map<BlockPos, GameArea> objectives = new HashMap<>();

    public GameObjectiveBased(String name) {
        super(name);
    }

    public abstract boolean canAddObjective(BlockPos pos, GameArea objective);

    @Override
    public void onGameTick(World world) {

    }

    public final void addObjective(BlockPos pos, GameArea objective) {
        if(this.canAddObjective(pos, objective)) {
            this.objectives.put(pos, objective);
        }
    }

    public final Map<BlockPos, GameArea> getObjectives() {
        return objectives;
    }

    @Override
    public void writeDataToNBT(NBTTagCompound compound) {
        cachedObjectives.put(this.registryName, this.objectives);
    }

    @Override
    public void readDataFromNBT(NBTTagCompound compound) {
        Map<BlockPos, GameArea> map = cachedObjectives.get(this.registryName);
        this.objectives = map != null ? map : new HashMap<>();
    }
}
