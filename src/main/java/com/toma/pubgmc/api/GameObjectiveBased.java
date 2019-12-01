package com.toma.pubgmc.api;

import com.toma.pubgmc.api.objectives.types.GameArea;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class GameObjectiveBased extends Game {

    private Map<BlockPos, GameArea> objectives = new HashMap<>();

    public GameObjectiveBased(String name) {
        super(name);
    }

    public abstract GameArea createNewObjective(World world, BlockPos pos);

    @Override
    public void onGameTick(World world) {

    }

    public final void addObjective(BlockPos pos, GameArea objective) {
        UUID objectiveID = UUID.randomUUID();
        this.objectives.put(pos, objective);
    }

    public final Map<BlockPos, GameArea> getObjectives() {
        return objectives;
    }

    @Override
    public void writeDataToNBT(NBTTagCompound compound) {
        NBTTagList keys = new NBTTagList();
        NBTTagList data = new NBTTagList();
        for(Map.Entry<BlockPos, GameArea> entry : objectives.entrySet()) {
            keys.appendTag(NBTUtil.createPosTag(entry.getKey()));
            data.appendTag(GameArea.createNBT(entry.getValue()));
        }
        compound.setTag("keys", keys);
        compound.setTag("data", data);
    }

    @Override
    public void readDataFromNBT(NBTTagCompound compound) {
        this.objectives = new HashMap<>();
        NBTTagList keys = compound.getTagList("keys", Constants.NBT.TAG_COMPOUND);
        NBTTagList data = compound.getTagList("data", Constants.NBT.TAG_COMPOUND);
        for(int i = 0; i < keys.tagCount(); i++) {
            this.objectives.put(NBTUtil.getPosFromTag(keys.getCompoundTagAt(i)), GameArea.getFromNBT(data.getCompoundTagAt(i)));
        }
    }
}
