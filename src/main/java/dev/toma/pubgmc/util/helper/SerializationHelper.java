package dev.toma.pubgmc.util.helper;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SerializationHelper {

    public static JsonObject asObject(JsonElement json) {
        if (!json.isJsonObject()) {
            throw new JsonSyntaxException("Element is not a JSON object!");
        }
        return json.getAsJsonObject();
    }

    public static ItemStack readItemStack(JsonObject object) throws JsonParseException {
        ResourceLocation itemId = new ResourceLocation(JsonUtils.getString(object, "item"));
        if (!ForgeRegistries.ITEMS.containsKey(itemId)) {
            throw new JsonSyntaxException("Unknown item: " + itemId);
        }
        Item item = ForgeRegistries.ITEMS.getValue(itemId);
        int count = JsonUtils.getInt(object, "count", 1);
        int metadata = JsonUtils.getInt(object, "meta", 0);
        ItemStack stack = new ItemStack(item, count, metadata);
        if (object.has("nbt")) {
            try {
                NBTTagCompound nbtTagCompound = JsonToNBT.getTagFromJson(JsonUtils.getString(object, "nbt"));
                stack.setTagCompound(nbtTagCompound);
            } catch (NBTException e) {
                throw new JsonSyntaxException("Invalid NBT", e);
            }
        }
        return stack;
    }

    public static JsonObject writeItemStack(ItemStack stack) {
        JsonObject object = new JsonObject();
        object.addProperty("item", stack.getItem().getRegistryName().toString());
        int count = stack.getCount();
        if (count > 1) {
            object.addProperty("count", count);
        }
        int metadata = stack.getMetadata();
        if (metadata > 0) {
            object.addProperty("meta", metadata);
        }
        NBTTagCompound compound = stack.getTagCompound();
        if (compound != null) {
            object.addProperty("nbt", compound.toString());
        }
        return object;
    }
}