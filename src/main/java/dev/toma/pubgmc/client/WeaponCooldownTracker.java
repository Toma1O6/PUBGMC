package dev.toma.pubgmc.client;

import dev.toma.pubgmc.common.items.guns.GunBase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WeaponCooldownTracker {

    private final Map<GunBase, Integer> map = new HashMap<>();

    public void tick(boolean paused) {
        if(!paused) {
            Iterator<Map.Entry<GunBase, Integer>> itr = map.entrySet().iterator();
            while (itr.hasNext()) {
                Map.Entry<GunBase, Integer> entry = itr.next();
                int i = entry.getValue() - 1;
                if(i <= 0) {
                    itr.remove();
                    continue;
                }
                map.put(entry.getKey(), i);
            }
        }
    }

    public boolean isOnCooldown(GunBase gunBase) {
        Integer i = map.get(gunBase);
        return i != null && i > 0;
    }

    public void add(GunBase gun) {
        if(map.put(gun, gun.getFireRate()) != null) {
            throw new IllegalStateException("Illegal cooldown insertion");
        }
    }
}
