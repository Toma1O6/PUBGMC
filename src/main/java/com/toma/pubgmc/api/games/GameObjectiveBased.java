package com.toma.pubgmc.api.games;

import com.toma.pubgmc.api.objectives.types.GameArea;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public abstract class GameObjectiveBased extends Game {

    private Map<BlockPos, GameArea> objectives = new HashMap<>();

    public GameObjectiveBased(String name, ResourceLocation texture) {
        super(name, texture);
    }

    public abstract boolean addObjective(World world, BlockPos pos, GameArea area);

    @Override
    public void onGameTick(World world) {

    }

    public final void createObjective(World world, BlockPos pos, GameArea area) {
        if(this.addObjective(world, pos, area)) {
            this.getObjectives().put(pos, area);
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
