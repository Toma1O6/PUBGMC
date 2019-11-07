package com.toma.pubgmc.util.helper;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.util.PUBGMCUtil;

import java.util.ArrayList;
import java.util.List;

public class LootHelper {

    public static final List<GunBase> AIRDROP_WEAPONS = new ArrayList<>(5);

    public static void init() {
        AIRDROP_WEAPONS.add(PMCRegistry.PMCItems.AUG);
        AIRDROP_WEAPONS.add(PMCRegistry.PMCItems.M249);
        AIRDROP_WEAPONS.add(PMCRegistry.PMCItems.GROZA);
        AIRDROP_WEAPONS.add(PMCRegistry.PMCItems.MK14);
        AIRDROP_WEAPONS.add(PMCRegistry.PMCItems.AWM);
    }

    public static GunBase getRandomWeapon(GunBase.GunType[] types, int rarityType) {
        List<GunBase> guns = getWeaponsOfType(types);
        filterGunsByRarity(guns, rarityType);
        return guns.get(Pubgmc.rng().nextInt(guns.size()));
    }

    private static List<GunBase> getWeaponsOfType(GunBase.GunType[] types) {
        List<GunBase> guns = new ArrayList<>();
        for(GunBase gun : GunBase.GUNS) {
            if(gun != PMCRegistry.PMCItems.FLARE_GUN && PUBGMCUtil.contains(gun.getGunType(), types)) {
                guns.add(gun);
            }
        }
        return guns;
    }

    private static List<GunBase> filterGunsByRarity(List<GunBase> list, int rarity) {
        if(AIRDROP_WEAPONS.isEmpty()) init();
        if(rarity == 1) return list;
        if(rarity == 0) list.removeAll(AIRDROP_WEAPONS);
        else if(rarity == 2) {
            list.removeIf(gunBase -> !AIRDROP_WEAPONS.contains(gunBase));
        }
        return list;
    }
}
