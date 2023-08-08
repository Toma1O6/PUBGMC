package dev.toma.pubgmc.api.game;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.util.Constants;

public final class PartialZoneConfiguration {

    public boolean enabled = false;
    public String[] availableSubMaps = {};

    public NBTTagCompound serialize() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setBoolean("enabled", enabled);
        NBTTagList list = new NBTTagList();
        for (String map : availableSubMaps) {
            list.appendTag(new NBTTagString(map));
        }
        nbt.setTag("availableSubMaps", list);
        return nbt;
    }

    public void deserialize(NBTTagCompound nbt) {
        enabled = nbt.getBoolean("enabled");
        NBTTagList list = nbt.getTagList("availableSubMaps", Constants.NBT.TAG_STRING);
        availableSubMaps = new String[list.tagCount()];
        for (int i = 0; i < list.tagCount(); i++) {
            availableSubMaps[i] = list.getStringTagAt(i);
        }
    }

    public JsonObject jsonSerialize() {
        JsonObject object = new JsonObject();
        object.addProperty("enabled", enabled);
        JsonArray array = new JsonArray();
        for (String map : availableSubMaps) {
            array.add(map);
        }
        object.add("availableSubMaps", array);
        return object;
    }

    public void jsonDeserialize(JsonObject object) {
        enabled = JsonUtils.getBoolean(object, "enabled", false);
        JsonArray subMaps = JsonUtils.getJsonArray(object, "availableSubMaps", new JsonArray());
        availableSubMaps = new String[subMaps.size()];
        for (int i = 0; i < subMaps.size(); i++) {
            availableSubMaps[i] = subMaps.get(i).getAsString();
        }
    }
}
