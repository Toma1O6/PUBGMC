package dev.toma.pubgmc.common.items.guns;

import com.google.common.base.Preconditions;
import dev.toma.pubgmc.client.renderer.item.gun.WeaponRenderer;
import dev.toma.pubgmc.common.items.attachment.ScopeZoom;
import dev.toma.pubgmc.common.items.guns.GunBase.Firemode;
import dev.toma.pubgmc.common.items.guns.GunBase.GunType;
import net.minecraft.util.SoundEvent;

import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Supplier;

public class GunBuilder {

    final Function<GunBuilder, GunBase> buildFunc;
    final String name;
    int reloadTime;
    int firerate;
    int maxAmmo;
    int exMaxAmmo;
    int burstAmount;
    float vertical;
    float horizontal;
    float volumeNormal;
    float volumeSilenced;
    boolean airdropOnly;
    GunType weaponType;
    AmmoType ammoType;
    Firemode defFiremode;
    Function<Firemode, Firemode> firemodeSwitchFunc;
    SoundEvent shootNormal;
    SoundEvent shootSilenced;
    SoundEvent reloadSound;
    WeaponStats weaponStats;
    GunAttachments attachments;
    Supplier<SoundEvent> action;
    ScopeZoom customScope;
    Supplier<Callable<WeaponRenderer>> renderer;
    IReloader reloader;

    private GunBuilder(String name, Function<GunBuilder, GunBase> buildFunc) {
        this.name = name;
        this.buildFunc = buildFunc;
    }

    public static GunBuilder create(String regName) {
        return create(regName, GunBase::new);
    }

    public static GunBuilder create(String regName, Function<GunBuilder, GunBase> buildFunc) {
        return new GunBuilder(regName, buildFunc);
    }

    public GunBuilder stats(WeaponStats weaponStats) {
        this.weaponStats = weaponStats;
        return this;
    }

    public GunBuilder recoil(float vertical, float horizontal) {
        this.vertical = vertical;
        this.horizontal = horizontal;
        return this;
    }

    public GunBuilder reload(IReloader reloader, int reloadTime, SoundEvent reloadSound) {
        this.reloader = reloader;
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
        return this.ammo(ammoType, maxAmmo, maxAmmo);
    }

    public GunBuilder firemode(Firemode firemode) {
        return this.firemode(firemode, Function.identity());
    }

    public GunBuilder firemode(Firemode defaultFiremode, Function<Firemode, Firemode> firemodeSwitchFunc) {
        this.defFiremode = defaultFiremode;
        this.firemodeSwitchFunc = firemodeSwitchFunc;
        return this;
    }

    public GunBuilder burstAmount(int burstAmount) {
        this.burstAmount = burstAmount;
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
        this.airdropOnly = true;
        return this;
    }

    public GunBuilder builtInScope(ScopeZoom scopeZoomConfig) {
        this.customScope = scopeZoomConfig;
        return this;
    }

    public GunAttachments.Builder attachments() {
        return new GunAttachments.Builder(this);
    }

    public GunBuilder setAttachments(GunAttachments attachments) {
        this.attachments = attachments;
        return this;
    }

    public GunBuilder renderer(Supplier<Callable<WeaponRenderer>> renderer) {
        this.renderer = renderer;
        return this;
    }

    public Supplier<Callable<WeaponRenderer>> getRenderer() {
        return renderer;
    }

    public GunBase build() {
        checkNotNull(weaponStats);
        validateFloat(vertical, 0.0f, 10f);
        validateFloat(horizontal, 0.0f, 10f);
        checkNotNull(reloader);
        validateInt(reloadTime, 1, 150);
        validateInt(firerate, 1, 150);
        checkNotNull(ammoType);
        validateInt(maxAmmo, 1, 200);
        validateInt(exMaxAmmo, 1, 200);
        checkNotNull(defFiremode);
        checkNotNull(firemodeSwitchFunc);
        checkNotNull(weaponType);
        checkNotNull(shootNormal);
        checkNotNull(shootSilenced);
        validateFloat(volumeNormal, 1f, 40f);
        validateFloat(volumeSilenced, 1f, 30f);
        checkNotNull(reloadSound);
        if (attachments == null)
            attachments().build();
        validateInt(burstAmount, 0, 3);
        if (action != null) {
            Preconditions.checkNotNull(action.get(), "Action cannot return null sound event");
        }
        return buildFunc.apply(this);
    }

    private static <T> void checkNotNull(T obj) throws NullPointerException {
        if (obj == null) {
            throw new NullPointerException();
        }
    }

    private static void validateFloat(float floatToValidate, float min, float max) throws IllegalArgumentException {
        if (floatToValidate < min || floatToValidate > max) {
            throw new IllegalArgumentException("Float value must be in <" + min + ";" + max + "> range! Got: " + floatToValidate);
        }
    }

    private static void validateDouble(double doubleToValidate, double min, double max) throws IllegalArgumentException {
        if (doubleToValidate < min || doubleToValidate > max) {
            throw new IllegalArgumentException("Double value must be in <" + min + ";" + max + "> range! Got: " + doubleToValidate);
        }
    }

    private static void validateInt(int intToValidate, int min, int max) throws IllegalArgumentException {
        if (intToValidate < min || intToValidate > max) {
            throw new IllegalArgumentException("Int value must be in <" + min + ";" + max + "> range! Got: " + intToValidate);
        }
    }
}
