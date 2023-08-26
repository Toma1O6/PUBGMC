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

public class FFAGameConfiguration implements GameConfiguration {

    public static final String LOADOUT_UMP45 = "ffa/ump45";
    public static final String LOADOUT_VECTOR = "ffa/vector";
    public static final String LOADOUT_AKM = "ffa/akm";
    public static final String LOADOUT_M416 = "ffa/m416";
    public static final String LOADOUT_SLR = "ffa/slr";
    public static final String LOADOUT_M24 = "ffa/m24";

    public int entityCount = 8;
    public boolean allowAi = true;
    public int gameDuration = 12000;
    public int killTarget = 30;
    public int spawnProtectionTime = 60;
    public int healPerKill = 3;
    public String[] loadoutFiles = {
            LOADOUT_UMP45,
            LOADOUT_VECTOR,
            LOADOUT_AKM,
            LOADOUT_M416,
            LOADOUT_SLR,
            LOADOUT_M24
    };
    public final GameWorldConfiguration worldConfiguration = new GameWorldConfiguration();

    @Override
    public void performCorrections() {
        entityCount = Math.max(2, entityCount);
        gameDuration = Math.max(1200, gameDuration);
        killTarget = Math.max(-1, killTarget);
        spawnProtectionTime = Math.max(0, spawnProtectionTime);
        healPerKill = Math.max(0, healPerKill);
        worldConfiguration.correct();
    }

    public NBTTagCompound serialize() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("entityCount", entityCount);
        nbt.setInteger("gameDuration", gameDuration);
        nbt.setInteger("killTarget", killTarget);
        nbt.setBoolean("allowAi", allowAi);
        nbt.setInteger("spawnProtection", spawnProtectionTime);
        nbt.setInteger("healPerKill", healPerKill);
        NBTTagList loadouts = new NBTTagList();
        for (String loadout : loadoutFiles) {
            loadouts.appendTag(new NBTTagString(loadout));
        }
        nbt.setTag("loadouts", loadouts);
        nbt.setTag("worldCfg", worldConfiguration.serialize());
        return nbt;
    }

    public static FFAGameConfiguration deserialize(NBTTagCompound nbt) {
        FFAGameConfiguration cfg = new FFAGameConfiguration();
        cfg.entityCount = nbt.getInteger("entityCount");
        cfg.gameDuration = nbt.getInteger("gameDuration");
        cfg.killTarget = nbt.getInteger("killTarget");
        cfg.allowAi = nbt.getBoolean("allowAi");
        cfg.spawnProtectionTime = nbt.getInteger("spawnProtection");
        cfg.healPerKill = nbt.getInteger("healPerKill");
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
        object.addProperty("healPerKill", healPerKill);
        JsonArray loadouts = new JsonArray();
        for (String loadout : loadoutFiles) {
            loadouts.add(loadout);
        }
        object.add("loadouts", loadouts);
        object.add("worldConfiguration", worldConfiguration.jsonSerialize());
        return object;
    }

    public static FFAGameConfiguration jsonDeserialize(JsonObject object) {
        FFAGameConfiguration cfg = new FFAGameConfiguration();
        cfg.entityCount = JsonUtils.getInt(object, "entityCount", 8);
        cfg.allowAi = JsonUtils.getBoolean(object, "allowAi", true);
        cfg.gameDuration = JsonUtils.getInt(object, "gameDuration", 12000);
        cfg.killTarget = JsonUtils.getInt(object, "killTarget", 30);
        cfg.spawnProtectionTime = JsonUtils.getInt(object, "spawnProtection", 60);
        cfg.healPerKill = JsonUtils.getInt(object, "healPerKill", 3);
        JsonArray loadouts = JsonUtils.getJsonArray(object, "loadouts", new JsonArray());
        cfg.loadoutFiles = new String[loadouts.size()];
        for (int i = 0; i < loadouts.size(); i++) {
            cfg.loadoutFiles[i] = loadouts.get(i).getAsString();
        }
        cfg.worldConfiguration.jsonDeserialize(JsonUtils.getJsonObject(object, "worldConfiguration", new JsonObject()));
        return cfg;
    }
}
