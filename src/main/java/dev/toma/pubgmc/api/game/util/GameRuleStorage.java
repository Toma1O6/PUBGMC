package dev.toma.pubgmc.api.game.util;

import dev.toma.pubgmc.util.helper.SerializationHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class GameRuleStorage {

    public static final String FALSE = "false";
    public static final String TRUE = "true";

    public static final String NATURAL_REGENERATION = "naturalRegeneration";
    public static final String MOB_SPAWNING = "doMobSpawning";
    public static final String MOB_LOOT = "doMobLoot";
    public static final String SHOW_DEATH_MESSAGES = "showDeathMessages";

    private final Map<String, String> ruleValues = new HashMap<>();

    public static void applyDefaultGameRules(World world, GameRuleStorage storage) {
        applyDefaultGameRules(world, storage, false);
    }

    public static void applyDefaultGameRules(World world, GameRuleStorage storage, boolean deathMessagesChatDisplay) {
        storage.storeValueAndSet(world, GameRuleStorage.NATURAL_REGENERATION, GameRuleStorage.FALSE);
        // storage.storeValueAndSet(world, GameRuleStorage.MOB_SPAWNING, GameRuleStorage.FALSE);
        storage.storeValueAndSet(world, GameRuleStorage.MOB_LOOT, GameRuleStorage.FALSE);
        storage.storeValueAndSet(world, GameRuleStorage.SHOW_DEATH_MESSAGES, String.valueOf(deathMessagesChatDisplay));
    }

    public void storeValueAndSet(World world, String rule, String toSet) {
        GameRules rules = world.getGameRules();
        String value = rules.getString(rule);
        if (!value.isEmpty()) {
            if (ruleValues.putIfAbsent(rule, value) == null) {
                rules.setOrCreateGameRule(rule, toSet);
            }
        }
    }

    public void restoreGameRules(World world) {
        GameRules rules = world.getGameRules();
        ruleValues.forEach(rules::setOrCreateGameRule);
    }

    public NBTTagCompound serialize() {
        return SerializationHelper.mapToNbt(ruleValues, Function.identity(), NBTTagString::new);
    }

    public void deserialize(NBTTagCompound nbt) {
        SerializationHelper.mapFromNbt(ruleValues, nbt, Function.identity(), base -> ((NBTTagString) base).getString());
    }
}
