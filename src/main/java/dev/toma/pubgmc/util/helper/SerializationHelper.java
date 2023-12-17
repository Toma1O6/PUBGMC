package dev.toma.pubgmc.util.helper;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import dev.toma.pubgmc.api.entity.SynchronizableEntity;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.s2c.S2C_PacketSendEntityData;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

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

    public static NBTTagList inventoryToNbt(IInventory inventory) {
        NBTTagList list = new NBTTagList();
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (!stack.isEmpty()) {
                NBTTagCompound slotNbt = new NBTTagCompound();
                slotNbt.setInteger("slotIndex", i);
                slotNbt.setTag("itemStack", stack.serializeNBT());
                list.appendTag(slotNbt);
            }
        }
        return list;
    }

    public static void inventoryFromNbt(IInventory inventory, NBTTagList list) {
        inventory.clear();
        for (int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound compound = list.getCompoundTagAt(i);
            int slotIndex = compound.getInteger("slotIndex");
            ItemStack stack = new ItemStack(compound.getCompoundTag("itemStack"));
            inventory.setInventorySlotContents(slotIndex, stack);
        }
    }

    public static <T extends Entity & SynchronizableEntity> void syncEntity(T entity) {
        PacketHandler.sendToAllTracking(new S2C_PacketSendEntityData(entity), entity);
    }

    public static <T> NBTTagList arrayToNbt(T[] array, Function<T, NBTBase> encoder) {
        NBTTagList list = new NBTTagList();
        for (T t : array) {
            if (t == null)
                continue;
            list.appendTag(encoder.apply(t));
        }
        return list;
    }

    public static <T> T[] arrayFromNbt(NBTTagList src, Function<Integer, T[]> arrayInstanceFn, Function<NBTBase, T> decoder) {
        T[] array = arrayInstanceFn.apply(src.tagCount());
        for (int i = 0; i < src.tagCount(); i++) {
            NBTBase base = src.get(i);
            array[i] = decoder.apply(base);
        }
        return array;
    }

    public static <T> void arrayFromNbt(NBTTagList src, T[] array, Function<NBTBase, T> decoder, @Nullable T fill) {
        Arrays.fill(array, fill);
        for (int i = 0; i < Math.min(array.length, src.tagCount()); i++) {
            array[i] = decoder.apply(src.get(i));
        }
    }

    public static <T> void arrayFromNbt(NBTTagList src, T[] array, Function<NBTBase, T> decoder) {
        arrayFromNbt(src, array, decoder, null);
    }

    public static <T> NBTTagList collectionToNbt(Collection<T> collection, Function<T, NBTBase> encoder) {
        NBTTagList list = new NBTTagList();
        collection.forEach(t -> list.appendTag(encoder.apply(t)));
        return list;
    }

    public static <T, C extends Collection<T>> C collectionFromNbt(Supplier<C> collectionProvider, NBTTagList src, Function<NBTBase, T> decoder) {
        C collection = collectionProvider.get();
        collectionFromNbt(collection, src, decoder);
        return collection;
    }

    public static <T> void collectionFromNbt(Collection<T> collection, NBTTagList src, Function<NBTBase, T> decoder) {
        collection.clear();
        src.forEach(nbtBase -> {
            T t = decoder.apply(nbtBase);
            if (t != null) {
                collection.add(t);
            }
        });
    }

    public static <K, V> NBTTagCompound mapToNbt(Map<K, V> map, Function<K, String> keyEncoder, Function<V, NBTBase> valueEncoder) {
        NBTTagCompound nbt = new NBTTagCompound();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            String key = keyEncoder.apply(entry.getKey());
            NBTBase base = valueEncoder.apply(entry.getValue());
            nbt.setTag(key, base);
        }
        return nbt;
    }

    public static <K, V, M extends Map<K, V>> M mapFromNbt(Supplier<M> mapProvider, NBTTagCompound src, Function<String, K> keyDecoder, Function<NBTBase, V> valueDecoder) {
        M map = mapProvider.get();
        mapFromNbt(map, src, keyDecoder, valueDecoder);
        return map;
    }

    public static <K, V> void mapFromNbt(Map<K, V> map, NBTTagCompound src, Function<String, K> keyDecoder, Function<NBTBase, V> valueDecoder) {
        map.clear();
        Set<String> keys = src.getKeySet();
        for (String string : keys) {
            K key = keyDecoder.apply(string);
            NBTBase nbtBase = src.getTag(string);
            V value = valueDecoder.apply(nbtBase);
            if (value != null) {
                map.put(key, value);
            }
        }
    }

    public static <E extends Enum<E>> E enumByIndex(int index, Class<E> type) {
        E[] vals = type.getEnumConstants();
        return vals[index % vals.length];
    }
}
