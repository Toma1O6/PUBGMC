package dev.toma.pubgmc.api.util;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class GameRuleStorage {

    private final Map<String, String> ruleValues = new HashMap<>();

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
        NBTTagCompound nbt = new NBTTagCompound();
        for (Map.Entry<String, String> entry : ruleValues.entrySet()) {
            nbt.setString(entry.getKey(), entry.getValue());
        }
        return nbt;
    }

    public void deserialize(NBTTagCompound nbt) {
        ruleValues.clear();
        Set<String> keys = nbt.getKeySet();
        for (String key : keys) {
            String value = nbt.getString(key);
            ruleValues.put(key, value);
        }
    }
}
