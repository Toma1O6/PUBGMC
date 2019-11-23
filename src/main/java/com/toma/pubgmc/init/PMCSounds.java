package com.toma.pubgmc.init;

import com.toma.pubgmc.Pubgmc;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class PMCSounds {

    /**
     * ========================================[SOUNDS]========================================================
     **/
    public static SoundEvent gun_noammo, bullet_whizz;

    //reloading
    public static SoundEvent reload_p92, reload_p1911, reload_r1895, reload_r45, reload_p18c, reload_scorpion, reload_deagle, reload_win94, reload_flare;
    public static SoundEvent reload_sawedoff, reload_s1897, reload_s686, reload_s12k;
    public static SoundEvent reload_microuzi, reload_ump9, reload_vector, reload_tommygun, reload_bizon, reload_mp5k;
    public static SoundEvent reload_m16a4, reload_m416, reload_scarl, reload_g36c, reload_qbz, reload_aug, reload_akm, reload_m762, reload_mk47, reload_groza;
    public static SoundEvent reload_dp28, reload_m249;
    public static SoundEvent reload_vss, reload_mini14, reload_qbu, reload_sks, reload_slr, reload_mk14;
    public static SoundEvent reload_kar98k, reload_kar98k_single, reload_m24, reload_awm;

    //pistols
    public static SoundEvent gun_p92, gun_p92_silenced;
    public static SoundEvent gun_p1911, gun_p1911_silenced;
    public static SoundEvent gun_p18c, gun_p18c_silenced;
    public static SoundEvent gun_r1895, gun_r1895_silenced;
    public static SoundEvent gun_r45;
    public static SoundEvent gun_scorpion, gun_scorpion_silenced;
    public static SoundEvent gun_deagle;
    public static SoundEvent gun_flare;

    //shotguns
    public static SoundEvent gun_sawed_off, gun_s1897, gun_s686, gun_s12k;

    //smgs
    public static SoundEvent gun_micro_uzi, gun_micro_uzi_silenced;
    public static SoundEvent gun_ump9, gun_ump9_silenced;
    public static SoundEvent gun_vector, gun_vector_silenced;
    public static SoundEvent gun_tommy_gun, gun_tommy_gun_silenced;
    public static SoundEvent gun_bizon, gun_bizon_silenced;
    public static SoundEvent gun_mp5k, gun_mp5k_silenced;

    //ars
    public static SoundEvent gun_m16a4, gun_m16a4_silenced;
    public static SoundEvent gun_m416, gun_m416_silenced;
    public static SoundEvent gun_scarl, gun_scarl_silenced;
    public static SoundEvent gun_g36c, gun_g36c_silenced;
    public static SoundEvent gun_qbz, gun_qbz_silenced;
    public static SoundEvent gun_aug, gun_aug_silenced;
    public static SoundEvent gun_akm, gun_akm_silenced;
    public static SoundEvent gun_m762, gun_m762_silenced;
    public static SoundEvent gun_mk47, gun_mk47_silenced;
    public static SoundEvent gun_groza, gun_groza_silenced;

    //dmrs
    public static SoundEvent gun_mini14, gun_mini14_silenced;
    public static SoundEvent gun_qbu, gun_qbu_silenced;
    public static SoundEvent gun_sks, gun_sks_silenced;
    public static SoundEvent gun_slr, gun_slr_silenced;
    public static SoundEvent gun_mk14, gun_mk14_silenced;
    public static SoundEvent gun_vss;

    //srs
    public static SoundEvent gun_kar98k, gun_kar98k_silenced;
    public static SoundEvent gun_m24, gun_m24_silenced;
    public static SoundEvent gun_awm, gun_awm_silenced;

    //other
    public static SoundEvent gun_win94, gun_m249, gun_dp28;
    public static SoundEvent airdrop_plane_fly_by;
    public static SoundEvent chute_open, chute_land;
    public static SoundEvent bolt_win94, bolt_kar98k, bolt_m24, bolt_awm;

    //vehicle
    public static SoundEvent vehicleIdle;
    public static SoundEvent uaz;
    public static SoundEvent flash, flash_short;

    /**
     * ========================================[UTIL]==========================================================
     **/

    public static void registerSounds() {
        gun_p92 = registerSound("gun.p92");
        gun_p92_silenced = registerSound("gun.p92.silenced");
        gun_p1911 = registerSound("gun.p1911");
        gun_p1911_silenced = registerSound("gun.p1911.silenced");
        gun_p18c = registerSound("gun.p18c");
        gun_p18c_silenced = registerSound("gun.p18c.silenced");
        gun_r1895 = registerSound("gun.r1895");
        gun_r1895_silenced = registerSound("gun.r1895.silenced");
        gun_r45 = registerSound("gun.r45");
        gun_scorpion = registerSound("gun.scorpion");
        gun_scorpion_silenced = registerSound("gun.scorpion.silenced");
        gun_deagle = registerSound("gun.deagle");
        gun_flare = registerSound("gun.flare");

        gun_win94 = registerSound("gun.win94");
        gun_m249 = registerSound("gun.m249");
        gun_dp28 = registerSound("gun.dp28");

        gun_sawed_off = registerSound("gun.sawed_off");
        gun_s1897 = registerSound("gun.s1897");
        gun_s686 = registerSound("gun.s686");
        gun_s12k = registerSound("gun.s12k");

        gun_micro_uzi = registerSound("gun.micro_uzi");
        gun_micro_uzi_silenced = registerSound("gun.micro_uzi.silenced");
        gun_ump9 = registerSound("gun.ump9");
        gun_ump9_silenced = registerSound("gun.ump9.silenced");
        gun_vector = registerSound("gun.vector");
        gun_vector_silenced = registerSound("gun.vector.silenced");
        gun_tommy_gun = registerSound("gun.tommy_gun");
        gun_tommy_gun_silenced = registerSound("gun.tommy_gun.silenced");
        gun_bizon = registerSound("gun.bizon");
        gun_bizon_silenced = registerSound("gun.bizon.silenced");
        gun_mp5k = registerSound("gun.mp5k");
        gun_mp5k_silenced = registerSound("gun.mp5k.silenced");

        gun_m16a4 = registerSound("gun.m16a4");
        gun_m16a4_silenced = registerSound("gun.m16a4.silenced");
        gun_m416 = registerSound("gun.m416");
        gun_m416_silenced = registerSound("gun.m416.silenced");
        gun_scarl = registerSound("gun.scarl");
        gun_scarl_silenced = registerSound("gun.scarl.silenced");
        gun_g36c = registerSound("gun.g36c");
        gun_g36c_silenced = registerSound("gun.g36c.silenced");
        gun_qbz = registerSound("gun.qbz");
        gun_qbz_silenced = registerSound("gun.qbz.silenced");
        gun_aug = registerSound("gun.aug");
        gun_aug_silenced = registerSound("gun.aug.silenced");
        gun_akm = registerSound("gun.akm");
        gun_akm_silenced = registerSound("gun.akm.silenced");
        gun_m762 = registerSound("gun.m762");
        gun_m762_silenced = registerSound("gun.m762.silenced");
        gun_mk47 = registerSound("gun.mk47");
        gun_mk47_silenced = registerSound("gun.mk47.silenced");
        gun_groza = registerSound("gun.groza");
        gun_groza_silenced = registerSound("gun.groza.silenced");
        gun_mini14 = registerSound("gun.mini14");
        gun_mini14_silenced = registerSound("gun.mini14.silenced");
        gun_qbu = registerSound("gun.qbu");
        gun_qbu_silenced = registerSound("gun.qbu.silenced");
        gun_sks = registerSound("gun.sks");
        gun_sks_silenced = registerSound("gun.sks.silenced");
        gun_slr = registerSound("gun.slr");
        gun_slr_silenced = registerSound("gun.slr.silenced");
        gun_mk14 = registerSound("gun.mk14");
        gun_mk14_silenced = registerSound("gun.mk14.silenced");
        gun_vss = registerSound("gun.vss");

        gun_kar98k = registerSound("gun.kar98k");
        gun_kar98k_silenced = registerSound("gun.kar98k.silenced");
        gun_m24 = registerSound("gun.m24");
        gun_m24_silenced = registerSound("gun.m24.silenced");
        gun_awm = registerSound("gun.awm");
        gun_awm_silenced = registerSound("gun.awm.silenced");

        reload_flare = registerSound("reload.flare");
        reload_p92 = registerSound("reload.p92");
        reload_p1911 = registerSound("reload.p1911");
        reload_p18c = registerSound("reload.p18c");
        reload_r1895 = registerSound("reload.r1895");
        reload_r45 = registerSound("reload.r45");
        reload_scorpion = registerSound("reload.scorpion");
        reload_deagle = registerSound("reload.deagle");
        reload_win94 = registerSound("reload.win94");
        reload_sawedoff = registerSound("reload.sawedoff");
        reload_s1897 = registerSound("reload.s1897");
        reload_s686 = registerSound("reload.s686");
        reload_s12k = registerSound("reload.s12k");
        reload_microuzi = registerSound("reload.microuzi");
        reload_ump9 = registerSound("reload.ump9");
        reload_vector = registerSound("reload.vector");
        reload_tommygun = registerSound("reload.tommygun");
        reload_bizon = registerSound("reload.bizon");
        reload_mp5k = registerSound("reload.mp5k");
        reload_m16a4 = registerSound("reload.m16a4");
        reload_m416 = registerSound("reload.m416");
        reload_scarl = registerSound("reload.scarl");
        reload_g36c = registerSound("reload.g36c");
        reload_qbz = registerSound("reload.qbz");
        reload_aug = registerSound("reload.aug");
        reload_akm = registerSound("reload.akm");
        reload_m762 = registerSound("reload.m762");
        reload_mk47 = registerSound("reload.mk47");
        reload_groza = registerSound("reload.groza");
        reload_dp28 = registerSound("reload.dp28");
        reload_m249 = registerSound("reload.m249");
        reload_vss = registerSound("reload.vss");
        reload_mini14 = registerSound("reload.mini14");
        reload_qbu = registerSound("reload.qbu");
        reload_sks = registerSound("reload.sks");
        reload_slr = registerSound("reload.slr");
        reload_mk14 = registerSound("reload.mk14");
        reload_kar98k = registerSound("reload.kar98k");
        reload_kar98k_single = registerSound("reload.kar98k.single");
        reload_m24 = registerSound("reload.m24");
        reload_awm = registerSound("reload.awm");

        bolt_win94 = registerSound("bolt.win94");
        bolt_kar98k = registerSound("bolt.kar98k");
        bolt_m24 = registerSound("bolt.m24");
        bolt_awm = registerSound("bolt.awm");

        gun_noammo = registerSound("gun.noammo");
        bullet_whizz = registerSound("bullet.whizz");
        airdrop_plane_fly_by = registerSound("plane.fly_by");
        chute_open = registerSound("chute.open");
        chute_land = registerSound("chute.land");

        vehicleIdle = registerSound("vehicle.idle");
        uaz = registerSound("vehicle.uaz");
        flash = registerSound("flash.full");
        flash_short = registerSound("flash.short");
    }

    private static SoundEvent registerSound(String name) {
        ResourceLocation r = new ResourceLocation(Pubgmc.MOD_ID, name);
        SoundEvent e = new SoundEvent(r);
        e.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(e);
        return e;
    }


}
