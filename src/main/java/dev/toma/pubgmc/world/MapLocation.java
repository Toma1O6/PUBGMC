package dev.toma.pubgmc.world;

import dev.toma.pubgmc.common.capability.game.IGameData;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.INBTSerializable;

public class MapLocation implements INBTSerializable<NBTTagCompound> {

    private String name;
    private BlockPos pos;

    public MapLocation(final String name, final BlockPos pos) {
        this.name = name;
        this.pos = pos;
    }

    public String name() {
        return name;
    }

    public BlockPos pos() {
        return pos;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("name", name);
        nbt.setTag("position", NBTUtil.createPosTag(pos));
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        name = nbt.getString("name");
        pos = NBTUtil.getPosFromTag(nbt.getCompoundTag("position"));
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof MapLocation) {
            MapLocation map = (MapLocation)obj;
            return this.name.equalsIgnoreCase(map.name) && pos.getX() == map.pos.getX() && pos.getZ() == map.pos.getZ();
        }
        return false;
    }

    public static MapLocation findLocation(String name, IGameData gameData) {
        for (MapLocation location : gameData.getSpawnLocations()) {
            if(location.name().equalsIgnoreCase(name)) {
                return location;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + ", Position [" + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + "]";
    }
}
