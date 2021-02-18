package dev.toma.pubgmc.util;

import java.util.HashMap;
import java.util.UUID;

public class FirerateCooldownTracker {

    private static final HashMap<UUID, FirerateData> DATA = new HashMap<>();

    public static FirerateData get(UUID uuid) {
        return DATA.computeIfAbsent(uuid, k -> new FirerateData());
    }

    public static void tick(UUID uuid) {
        get(uuid).tick();
    }

    public static int getValue(UUID uuid) {
        return get(uuid).cooldown;
    }

    public static void set(UUID uuid, int value) {
        get(uuid).cooldown = value;
    }

    public static class FirerateData {
        private int cooldown = 0;

        private void tick() {
            this.cooldown = this.cooldown > 0 ? this.cooldown - 1 : 0;
        }
    }
}
