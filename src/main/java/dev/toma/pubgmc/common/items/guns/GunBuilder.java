package dev.toma.pubgmc.common.items.guns;

import com.google.common.base.Preconditions;
import dev.toma.pubgmc.common.items.guns.GunBase.Firemode;
import dev.toma.pubgmc.common.items.guns.GunBase.GunType;
import dev.toma.pubgmc.common.items.guns.GunBase.ReloadType;
import dev.toma.pubgmc.config.common.CFGWeapon;
import dev.toma.pubgmc.util.game.loot.LootManager;
import dev.toma.pubgmc.util.game.loot.LootType;
import net.minecraft.util.SoundEvent;

import java.util.function.Supplier;

public class GunBuilder {
    String name;
    int reloadTime, firerate, maxAmmo, exMaxAmmo;
    float vertical, horizontal, volumeNormal, volumeSilenced;
    boolean twoRoundBurst, airdrop;
    GunType weaponType;
    ReloadType reloadType;
    AmmoType ammoType;
    Firemode defFiremode;
    Firemode[] validFiremodes;
    SoundEvent reloadSound, shootNormal, shootSilenced;
    CFGWeapon cfgStats;
    Supplier<SoundEvent> action;

    private GunBuilder() {
    }

    /**
     * Initialize new builder object
     *
     * @param regName - the item registry name
     * @return new builder instance
     */
    public static GunBuilder create(String regName) {
        GunBuilder builder = new GunBuilder();
        builder.name = regName;
        builder.twoRoundBurst = false;
        return builder;
    }

    private static <T> boolean isObjectInsideGroup(T obj, T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            Object o = arr[i];
            if (obj.equals(o)) {
                return true;
            }
        }
        return false;
    }

    private static <T> T checkNotNull(T obj) throws NullPointerException {
        if (obj == null) {
            throw new NullPointerException(obj + " cannot be null!");
        }
        return obj;
    }

    private static float validateFloat(float floatToValidate, float min, float max) throws IllegalArgumentException {
        if (floatToValidate < min || floatToValidate > max) {
            throw new IllegalArgumentException("Float value must be in <" + min + ";" + max + "> range! Got: " + floatToValidate);
        }

        return floatToValidate;
    }

    private static double validateDouble(double doubleToValidate, double min, double max) throws IllegalArgumentException {
        if (doubleToValidate < min || doubleToValidate > max) {
            throw new IllegalArgumentException("Double value must be in <" + min + ";" + max + "> range! Got: " + doubleToValidate);
        }
        return doubleToValidate;
    }

    private static int validateInt(int intToValidate, int min, int max) throws IllegalArgumentException {
        if (intToValidate < min || intToValidate > max) {
            throw new IllegalArgumentException("Int value must be in <" + min + ";" + max + "> range! Got: " + intToValidate);
        }
        return intToValidate;
    }

    public GunBuilder stats(CFGWeapon cfg) {
        this.cfgStats = cfg;
        return this;
    }

    public GunBuilder recoil(float vertical, float horizontal) {
        this.vertical = vertical;
        this.horizontal = horizontal;
        return this;
    }

    public GunBuilder reload(ReloadType typeOfReload, int reloadTime, SoundEvent reloadSound) {
        this.reloadType = typeOfReload;
        this.reloadTime = reloadTime;
        this.reloadSound = reloadSound;
        return this;
    }

    public GunBuilder firerate(int firerate) {
        this.firerate = firerate;
        return this;
    }

    public GunBuilder ammo(AmmoType ammoType, int maxAmmo, int exMaxAmmo) {
        this.ammoType = ammoType;
        this.maxAmmo = maxAmmo;
        this.exMaxAmmo = exMaxAmmo;
        return this;
    }

    public GunBuilder ammo(AmmoType ammoType, int maxAmmo) {
        this.ammoType = ammoType;
        this.maxAmmo = maxAmmo;
        this.exMaxAmmo = maxAmmo;
        return this;
    }

    public GunBuilder firemode(Firemode defaultFiremode, Firemode... validFiremodes) {
        this.defFiremode = defaultFiremode;
        this.validFiremodes = validFiremodes;
        return this;
    }

    public GunBuilder setTwoRoundBurst() {
        this.twoRoundBurst = true;
        return this;
    }

    public GunBuilder weaponType(GunType type) {
        this.weaponType = type;
        return this;
    }

    public GunBuilder sound(SoundEvent shootNormal, float volume, SoundEvent shootSilenced, float silencedVolume) {
        this.shootNormal = shootNormal;
        this.volumeNormal = volume;
        this.shootSilenced = shootSilenced;
        this.volumeSilenced = silencedVolume;
        return this;
    }

    public GunBuilder sound(SoundEvent normal, float volume) {
        this.shootNormal = normal;
        this.volumeNormal = volume;
        this.shootSilenced = normal;
        this.volumeSilenced = volume;
        return this;
    }

    public GunBuilder addBoltAction(Supplier<SoundEvent> action) {
        this.action = action;
        return this;
    }

    public GunBuilder airdropOnly() {
        this.airdrop = true;
        return this;
    }

    /**
     * <i><u>In order to successfully build new Gun object you need to call:</u></i>
     * <b>
     * <li> firerate
     * <li> recoil
     * <li> reload
     * <li> ammo
     * <li> firemode
     * <li> weaponType
     * <li> sound
     * </b>
     *
     * @return new GunBase object with required parameters
     */
    public GunBase build() {
        cfgStats = checkNotNull(cfgStats);
        vertical = validateFloat(vertical, 0.1f, 10f);
        horizontal = validateFloat(horizontal, 0.1f, 10f);
        reloadType = checkNotNull(reloadType);
        reloadTime = validateInt(reloadTime, 1, 150);
        firerate = validateInt(firerate, 1, 150);
        ammoType = checkNotNull(ammoType);
        maxAmmo = validateInt(maxAmmo, 1, 100);
        exMaxAmmo = validateInt(exMaxAmmo, 1, 100);
        defFiremode = checkNotNull(defFiremode);
        validFiremodes = checkNotNull(validFiremodes);
        weaponType = checkNotNull(weaponType);
        shootNormal = checkNotNull(shootNormal);
        shootSilenced = checkNotNull(shootSilenced);
        volumeNormal = validateFloat(volumeNormal, 1f, 40f);
        volumeSilenced = validateFloat(volumeSilenced, 1f, 30f);
        reloadSound = checkNotNull(reloadSound);

        if(action != null) {
            Preconditions.checkNotNull(action.get(), "Cannot add IBoltAction with null soundevent");
        }

        if (twoRoundBurst) {
            if (!isObjectInsideGroup(Firemode.BURST, validFiremodes)) {
                throw new NullPointerException("Two round is registered, but BURST firemode isn't valid for the weapon " + name);
            }
        }

        GunBase gun = new GunBase(this.name);
        LootManager.register(LootType.GUN, new LootManager.LootEntry(gun, weaponType.getWeight(), airdrop));
        gun.setStats(cfgStats);
        gun.setVerticalRecoil(vertical);
        gun.setHorizontalRecoil(horizontal);
        gun.setReloadType(reloadType);
        gun.setReloadTime(reloadTime);
        gun.setFireRate(firerate);
        gun.setAmmoType(ammoType);
        gun.setMaxAmmo(maxAmmo, exMaxAmmo);
        gun.setFiremode(defFiremode);
        gun.setValidFiremodes(validFiremodes);
        gun.setHasTwoRoundBurst(twoRoundBurst);
        gun.setGunType(weaponType);
        gun.setGunSound(shootNormal);
        gun.setGunSoundVolume(volumeNormal);
        gun.setGunSilencedSound(shootSilenced);
        gun.setGunSilencedSoundVolume(volumeSilenced);
        gun.setReloadSound(reloadSound);
        gun.action = action;
        return gun;
    }
}
