package dev.toma.pubgmc.common.game.map;

import dev.toma.pubgmc.common.game.exception.InvalidMapNameException;
import dev.toma.pubgmc.common.game.exception.MapException;
import dev.toma.pubgmc.common.game.exception.MapNotFoundException;
import dev.toma.pubgmc.common.game.exception.MapRegistryException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class MapCollection implements INBTSerializable<NBTTagCompound> {

    private static final Pattern NAME_VALIDATOR = Pattern.compile("[a-zA-Z0-9-_]+");
    private Map<String, GameMap> maps;

    public MapCollection() {
        maps = new HashMap<>();
    }

    public GameMap getMapByName(String name) throws MapNotFoundException {
        if(!maps.containsKey(name)) throw new MapNotFoundException(name);
        return maps.get(name);
    }

    public void registerMap(GameMap map) throws MapException {
        String name = map.getMapName();
        if(!NAME_VALIDATOR.matcher(name).matches()) throw new InvalidMapNameException(name);
        if(maps.containsKey(name)) throw new MapRegistryException(String.format("Duplicate map key %s!", name));
        maps.put(name, map);
    }

    public void deleteMap(String name) throws MapNotFoundException {
        GameMap map = maps.get(name);
        if(map == null) throw new MapNotFoundException(name);
        maps.remove(name);
    }

    public void renameMap(String originalMapName, String newMapName) throws MapException {
        GameMap map = maps.get(originalMapName);
        if(map == null) throw new MapNotFoundException(originalMapName);
        if(!NAME_VALIDATOR.matcher(newMapName).matches()) throw new InvalidMapNameException(newMapName);
        maps.put(newMapName, map);
        maps.remove(originalMapName);
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return null;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {

    }
}
