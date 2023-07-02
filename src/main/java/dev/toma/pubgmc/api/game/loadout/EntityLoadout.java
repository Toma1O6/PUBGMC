package dev.toma.pubgmc.api.game.loadout;

import com.google.gson.*;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.data.loot.LootGenerationContext;
import dev.toma.pubgmc.data.loot.LootProvider;
import dev.toma.pubgmc.data.loot.LootProviderType;
import dev.toma.pubgmc.data.loot.NoLootProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import scala.collection.script.NoLo;

import javax.annotation.Nullable;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

public final class EntityLoadout {

    public static final EntityLoadout EMPTY = new Builder().build();
    @Nullable
    private final String name;
    private final ItemStack icon;
    private final LootProvider weaponProvider;
    private final LootProvider armorProvider;
    private final LootProvider specialEquipmentProvider;
    private final LootProvider generalLootProvider;

    private EntityLoadout(String name, ItemStack icon, LootProvider weaponProvider, LootProvider armorProvider, LootProvider specialEquipmentProvider, LootProvider generalLootProvider) {
        this.name = name;
        this.icon = icon;
        this.weaponProvider = weaponProvider;
        this.armorProvider = armorProvider;
        this.specialEquipmentProvider = specialEquipmentProvider;
        this.generalLootProvider = generalLootProvider;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public List<ItemStack> getWeapons(LootGenerationContext context) {
        return weaponProvider.generateItems(context);
    }

    public List<ItemStack> getArmor(LootGenerationContext context) {
        return armorProvider.generateItems(context);
    }

    public List<ItemStack> getSpecialEquipment(LootGenerationContext context) {
        return specialEquipmentProvider.generateItems(context);
    }

    public List<ItemStack> getGeneralLoot(LootGenerationContext context) {
        return generalLootProvider.generateItems(context);
    }

    public static final class Adapter implements JsonSerializer<EntityLoadout>, JsonDeserializer<EntityLoadout> {

        @Override
        public JsonElement serialize(EntityLoadout src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject object = new JsonObject();
            if (src.name != null) {
                object.addProperty("name", src.name);
            }
            if (!src.icon.isEmpty()) {
                JsonObject icon = new JsonObject();
                icon.addProperty("item", src.icon.getItem().getRegistryName().toString());
                icon.addProperty("count", src.icon.getCount());
                icon.addProperty("metadata", src.icon.getMetadata());
                NBTTagCompound nbt = src.icon.getTagCompound();
                if (nbt != null) {
                    icon.addProperty("nbt", nbt.toString());
                }
                object.add("icon", icon);
            }
            if (src.weaponProvider != null) {
                object.add("weaponLoot", LootProviderType.serialize(src.weaponProvider));
            }
            if (src.armorProvider != null) {
                object.add("armorLoot", LootProviderType.serialize(src.armorProvider));
            }
            if (src.specialEquipmentProvider != null) {
                object.add("specialEquipmentLoot", LootProviderType.serialize(src.specialEquipmentProvider));
            }
            if (src.generalLootProvider != null) {
                object.add("generalLoot", LootProviderType.serialize(src.generalLootProvider));
            }
            return object;
        }

        @Override
        public EntityLoadout deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject object = json.getAsJsonObject();
            String name = null;
            ItemStack icon = ItemStack.EMPTY;
            LootProvider weaponProvider = NoLootProvider.EMPTY;
            LootProvider armorProvider = NoLootProvider.EMPTY;
            LootProvider specialEquipmentProvider = NoLootProvider.EMPTY;
            LootProvider generalLootProvider = NoLootProvider.EMPTY;
            if (object.has("name")) {
                name = JsonUtils.getString(object, "name");
            }
            if (object.has("icon")) {
                JsonObject iconJson = JsonUtils.getJsonObject(object, "icon");
                ResourceLocation itemId = new ResourceLocation(JsonUtils.getString(iconJson, "item"));
                int count = JsonUtils.getInt(iconJson, "count", 1);
                int metadata = JsonUtils.getInt(iconJson, "metadata", 0);
                ItemStack stack = new ItemStack(ForgeRegistries.ITEMS.getValue(itemId), count, metadata);
                if (iconJson.has("nbt")) {
                    try {
                        NBTTagCompound nbt = JsonToNBT.getTagFromJson(JsonUtils.getString(iconJson, "nbt"));
                        stack.setTagCompound(nbt);
                    } catch (NBTException e) {
                        Pubgmc.logger.error("Error while attempting to parse {} to NBT: {}", JsonUtils.getString(iconJson, "nbt"), e);
                    }
                }
                icon = stack;
            }
            if (object.has("weaponLoot")) {
                weaponProvider = LootProviderType.parse(JsonUtils.getJsonObject(object, "weaponLoot"));
            }
            if (object.has("armorLoot")) {
                armorProvider = LootProviderType.parse(JsonUtils.getJsonObject(object, "armorLoot"));
            }
            if (object.has("specialEquipmentLoot")) {
                specialEquipmentProvider = LootProviderType.parse(JsonUtils.getJsonObject(object, "specialEquipmentLoot"));
            }
            if (object.has("generalLoot")) {
                generalLootProvider = LootProviderType.parse(JsonUtils.getJsonObject(object, "generalLoot"));
            }
            return new EntityLoadout(name, icon, weaponProvider, armorProvider, specialEquipmentProvider, generalLootProvider);
        }
    }

    public static final class Builder {

        private String name;
        private ItemStack icon = ItemStack.EMPTY;
        private LootProvider weapon = NoLootProvider.EMPTY;
        private LootProvider armor = NoLootProvider.EMPTY;
        private LootProvider special = NoLootProvider.EMPTY;
        private LootProvider general = NoLootProvider.EMPTY;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withIcon(ItemStack icon) {
            this.icon = icon;
            return this;
        }

        public Builder withWeaponProvider(LootProvider weapon) {
            this.weapon = weapon;
            return this;
        }

        public Builder withArmorProvider(LootProvider armor) {
            this.armor = armor;
            return this;
        }

        public Builder withSpecialEquipmentProvider(LootProvider special) {
            this.special = special;
            return this;
        }

        public Builder withGeneralLootProvider(LootProvider general) {
            this.general = general;
            return this;
        }

        public EntityLoadout build() {
            return new EntityLoadout(
                    name,
                    Objects.requireNonNull(icon, "Icon cannot be null"),
                    Objects.requireNonNull(weapon, "Weapon loot provider cannot be null"),
                    Objects.requireNonNull(armor, "Armor loot provider cannot be null"),
                    Objects.requireNonNull(special, "Special loot provider cannot be null"),
                    Objects.requireNonNull(general, "General loot provider cannot be null")
            );
        }
    }
}
