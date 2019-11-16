package com.toma.pubgmc.util.game.loot;

import com.google.common.base.Preconditions;
import com.toma.pubgmc.common.capability.IWorldData;
import com.toma.pubgmc.common.items.guns.GunBase;

public class LootOptions {

    public final boolean isSpecialLoot;
    public final boolean genAmmo;
    public final boolean randomAmmoGen;
    public final double chanceModifier;
    public final GunBase.GunType[] validWeaponTypes;

    public LootOptions(final Builder builder) {
        builder.verify();
        isSpecialLoot = builder.drops;
        genAmmo = builder.ammo;
        randomAmmoGen = builder.randAmmo;
        chanceModifier = builder.chance;
        validWeaponTypes = builder.types;
    }

    public static LootOptions getDefault() {
        return new LootOptions(Builder.get().ammo(true, false).types(GunBase.GunType.values()));
    }

    public static LootOptions getCurrent(IWorldData worldData) {
        return new LootOptions(Builder.get()
                .specialLoot(worldData.hasAirdropWeapons())
                .ammo(worldData.isAmmoLootEnabled(), worldData.isRandomAmmoCountEnabled())
                .chance(worldData.getLootChanceMultiplier())
                .types(worldData.getWeaponList().toArray(new GunBase.GunType[0])));
    }

    public static class Builder {

        private boolean drops = false, ammo = false, randAmmo = false;
        private double chance = 0.0D;
        private GunBase.GunType[] types = new GunBase.GunType[0];

        private Builder() {}

        public static Builder get() {
            return new Builder();
        }

        public Builder setSpecialLootAllowed() {
            return this.specialLoot(true);
        }

        public Builder specialLoot(boolean special) {
            this.drops = special;
            return this;
        }

        public Builder ammo(boolean generateAmmo, boolean randomAmmoAmount) {
            this.ammo = generateAmmo;
            this.randAmmo = randomAmmoAmount;
            return this;
        }

        public Builder chance(double chance) {
            this.chance = chance;
            return this;
        }

        public Builder types(GunBase.GunType[] gunTypes) {
            this.types = gunTypes;
            return this;
        }

        public void verify() {
            Preconditions.checkNotNull(types);
        }
    }
}
