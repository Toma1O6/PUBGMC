package dev.toma.pubgmc.common.games.game.ffa;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.toma.pubgmc.api.game.GameConfiguration;
import dev.toma.pubgmc.api.game.GameWorldConfiguration;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.util.Constants;

public class FreeForAllGameConfiguration implements GameConfiguration {

    public int entityCount = 8;
    public boolean allowAi = true;
    public int gameDuration = 12000;
    public int killTarget = 30;
    public int spawnProtectionTime = 60;
    public String[] loadoutFiles = {
            "ffa/ump45",
            "ffa/vector",
            "ffa/akm",
            "ffa/m416",
            "ffa/slr",
            "ffa/m24"
    };
    public GameWorldConfiguration worldConfiguration = new GameWorldConfiguration();

    @Override
    public void performCorrections() {
        entityCount = Math.max(2, entityCount);
        gameDuration = Math.max(1200, gameDuration);
        killTarget = Math.max(-1, killTarget);
        spawnProtectionTime = Math.max(0, spawnProtectionTime);
        worldConfiguration.correct();
    }

    public NBTTagCompound serialize() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("entityCount", entityCount);
        nbt.setInteger("gameDuration", gameDuration);
        nbt.setInteger("killTarget", killTarget);
        nbt.setBoolean("allowAi", allowAi);
        nbt.setInteger("spawnProtection", spawnProtectionTime);
        NBTTagList loadouts = new NBTTagList();
        for (String loadout : loadoutFiles) {
            loadouts.appendTag(new NBTTagString(loadout));
        }
        nbt.setTag("loadouts", loadouts);
        nbt.setTag("worldCfg", worldConfiguration.serialize());
        return nbt;
    }

    public static FreeForAllGameConfiguration deserialize(NBTTagCompound nbt) {
        FreeForAllGameConfiguration cfg = new FreeForAllGameConfiguration();
        cfg.entityCount = nbt.getInteger("entityCount");
        cfg.gameDuration = nbt.getInteger("gameDuration");
        cfg.killTarget = nbt.getInteger("killTarget");
        cfg.allowAi = nbt.getBoolean("allowAi");
        cfg.spawnProtectionTime = nbt.getInteger("spawnProtection");
        NBTTagList loadouts = nbt.getTagList("loadouts", Constants.NBT.TAG_STRING);
        cfg.loadoutFiles = new String[loadouts.tagCount()];
        for (int i = 0; i < loadouts.tagCount(); i++) {
            cfg.loadoutFiles[i] = loadouts.getStringTagAt(i);
        }
        cfg.worldConfiguration.deserialize(nbt.getCompoundTag("worldCfg"));
        return cfg;
    }

    public JsonObject jsonSerialize() {
        JsonObject object = new JsonObject();
        object.addProperty("entityCount", entityCount);
        object.addProperty("allowAi", allowAi);
        object.addProperty("gameDuration", gameDuration);
        object.addProperty("killTarget", killTarget);
        object.addProperty("spawnProtection", spawnProtectionTime);
        JsonArray loadouts = new JsonArray();
        for (String loadout : loadoutFiles) {
            loadouts.add(loadout);
        }
        object.add("loadouts", loadouts);
        object.add("worldConfiguration", worldConfiguration.jsonSerialize());
        return object;
    }

    public static FreeForAllGameConfiguration jsonDeserialize(JsonObject object) {
        FreeForAllGameConfiguration cfg = new FreeForAllGameConfiguration();
        cfg.entityCount = JsonUtils.getInt(object, "entityCount", 8);
        cfg.allowAi = JsonUtils.getBoolean(object, "allowAi", true);
        cfg.gameDuration = JsonUtils.getInt(object, "gameDuration", 12000);
        cfg.killTarget = JsonUtils.getInt(object, "killTarget", 30);
        cfg.spawnProtectionTime = JsonUtils.getInt(object, "spawnProtection", 60);
        JsonArray loadouts = JsonUtils.getJsonArray(object, "loadouts", new JsonArray());
        cfg.loadoutFiles = new String[loadouts.size()];
        for (int i = 0; i < loadouts.size(); i++) {
            cfg.loadoutFiles[i] = loadouts.get(i).getAsString();
        }
        cfg.worldConfiguration.jsonDeserialize(JsonUtils.getJsonObject(object, "worldConfiguration", new JsonObject()));
        return cfg;
    }
}
