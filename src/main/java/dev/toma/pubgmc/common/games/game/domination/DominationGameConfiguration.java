package dev.toma.pubgmc.common.games.game.domination;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.toma.pubgmc.api.game.GameWorldConfiguration;
import dev.toma.pubgmc.api.game.PartialZoneConfiguration;
import dev.toma.pubgmc.api.game.map.PartialZoneSelectorConfig;
import dev.toma.pubgmc.api.game.team.TeamGameConfiguration;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.util.Constants;

public final class DominationGameConfiguration implements TeamGameConfiguration, PartialZoneSelectorConfig {

    public static final String LOADOUT_UMP45 = "domination/ump45";
    public static final String LOADOUT_VECTOR = "domination/vector";
    public static final String LOADOUT_AKM = "domination/akm";
    public static final String LOADOUT_M416 = "domination/m416";
    public static final String LOADOUT_SLR = "domination/slr";
    public static final String LOADOUT_M24 = "domination/m24";

    public int playerCount = 16;
    public float captureSpeed = 0.0125F; // calculate with: x = 1 / (4 * <seconds to capture>)
    public int totalTicketCount = 10000;
    public int pointTicketLoss = 50;
    public int killTicketLoss = 15;
    public int gameDuration = 18000;
    public boolean allowAi = true;
    public boolean requirePointSuperiority = false;
    public String[] availableLoadouts = {
            LOADOUT_UMP45,
            LOADOUT_VECTOR,
            LOADOUT_AKM,
            LOADOUT_M416,
            LOADOUT_SLR,
            LOADOUT_M24
    };
    public final GameWorldConfiguration worldConfiguration = new GameWorldConfiguration();
    private final PartialZoneConfiguration zoneConfiguration = new PartialZoneConfiguration();

    @Override
    public PartialZoneConfiguration getZoneConfiguration() {
        return zoneConfiguration;
    }

    @Override
    public void performCorrections() {
        playerCount = Math.max(2, playerCount);
        captureSpeed = Math.max(0.0001F, captureSpeed);
        totalTicketCount = Math.max(10, totalTicketCount);
        pointTicketLoss = Math.max(1, pointTicketLoss);
        killTicketLoss = Math.max(0, killTicketLoss);
        gameDuration = Math.max(1200, gameDuration);
        worldConfiguration.correct();
    }

    public NBTTagCompound serialize() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("playerCount", playerCount);
        nbt.setFloat("captureSpeed", captureSpeed);
        nbt.setInteger("totalTickets", totalTicketCount);
        nbt.setInteger("pointTicketLoss", pointTicketLoss);
        nbt.setInteger("killTicketLoss", killTicketLoss);
        nbt.setInteger("gameDuration", gameDuration);
        nbt.setBoolean("allowAi", allowAi);
        nbt.setBoolean("requirePointSuperiority", requirePointSuperiority);
        NBTTagList loadouts = new NBTTagList();
        for (String loadout : availableLoadouts) {
            loadouts.appendTag(new NBTTagString(loadout));
        }
        nbt.setTag("loadouts", loadouts);
        nbt.setTag("worldCfg", worldConfiguration.serialize());
        nbt.setTag("zoneCfg", zoneConfiguration.serialize());
        return nbt;
    }

    public static DominationGameConfiguration deserialize(NBTTagCompound nbt) {
        DominationGameConfiguration cfg = new DominationGameConfiguration();
        cfg.playerCount = nbt.getInteger("playerCount");
        cfg.captureSpeed = nbt.getFloat("captureSpeed");
        cfg.totalTicketCount = nbt.getInteger("totalTickets");
        cfg.pointTicketLoss = nbt.getInteger("pointTicketLoss");
        cfg.killTicketLoss = nbt.getInteger("killTicketLoss");
        cfg.gameDuration = nbt.getInteger("gameDuration");
        cfg.allowAi = nbt.getBoolean("allowAi");
        cfg.requirePointSuperiority = nbt.getBoolean("requirePointSuperiority");
        NBTTagList loadouts = nbt.getTagList("loadouts", Constants.NBT.TAG_STRING);
        cfg.availableLoadouts = new String[loadouts.tagCount()];
        for (int i = 0; i < loadouts.tagCount(); i++) {
            cfg.availableLoadouts[i] = loadouts.getStringTagAt(i);
        }
        cfg.worldConfiguration.deserialize(nbt.getCompoundTag("worldCfg"));
        cfg.zoneConfiguration.deserialize(nbt.getCompoundTag("zoneCfg"));
        return cfg;
    }

    public JsonObject jsonSerialize() {
        JsonObject object = new JsonObject();
        object.addProperty("playerCount", playerCount);
        object.addProperty("captureSpeed", captureSpeed);
        object.addProperty("totalTickets", totalTicketCount);
        object.addProperty("pointTicketLoss", pointTicketLoss);
        object.addProperty("killTicketLoss", killTicketLoss);
        object.addProperty("gameDuration", gameDuration);
        object.addProperty("allowAi", allowAi);
        object.addProperty("requirePointSuperiority", requirePointSuperiority);
        JsonArray loadouts = new JsonArray();
        for (String loadout : availableLoadouts) {
            loadouts.add(loadout);
        }
        object.add("loadouts", loadouts);
        object.add("worldConfiguration", worldConfiguration.jsonSerialize());
        object.add("partialZoneConfiguration", zoneConfiguration.jsonSerialize());
        return object;
    }

    public static DominationGameConfiguration jsonDeserialize(JsonObject object) {
        DominationGameConfiguration cfg = new DominationGameConfiguration();
        cfg.playerCount = JsonUtils.getInt(object, "playerCount", 16);
        cfg.captureSpeed = JsonUtils.getFloat(object, "captureSpeed", 0.0125F);
        cfg.totalTicketCount = JsonUtils.getInt(object, "totalTickets", 10000);
        cfg.pointTicketLoss = JsonUtils.getInt(object, "pointTicketLoss", 50);
        cfg.killTicketLoss = JsonUtils.getInt(object, "killTicketLoss", 15);
        cfg.gameDuration = JsonUtils.getInt(object, "gameDuration", 18000);
        cfg.allowAi = JsonUtils.getBoolean(object, "allowAi", true);
        cfg.requirePointSuperiority = JsonUtils.getBoolean(object, "requirePointSuperiority", false);
        JsonArray loadouts = JsonUtils.getJsonArray(object, "loadouts", new JsonArray());
        cfg.availableLoadouts = new String[loadouts.size()];
        for (int i = 0; i < loadouts.size(); i++) {
            cfg.availableLoadouts[i] = loadouts.get(i).getAsString();
        }
        cfg.worldConfiguration.jsonDeserialize(JsonUtils.getJsonObject(object, "worldConfiguration", new JsonObject()));
        cfg.zoneConfiguration.jsonDeserialize(JsonUtils.getJsonObject(object, "partialZoneConfiguration", new JsonObject()));
        return cfg;
    }
}
