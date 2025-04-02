package dev.toma.pubgmc.config.common;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.ConfigPlugin;
import dev.toma.configuration.api.type.DoubleType;
import dev.toma.configuration.api.type.ObjectType;
import dev.toma.configuration.api.util.NumberDisplayType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public final class CFGWeapons extends ObjectType implements INBTSerializable<NBTTagCompound> {

    final ConfigPlugin plugin;
    public DoubleType globalVerticalRecoil;
    public DoubleType globalHorizontalRecoil;
    public DoubleType crouchRecoilScale;
    public DoubleType proneRecoilScale;
    public AttachmentsConfig attachmentsConfig;
    public CFGWeapon p92;
    public CFGWeapon p1911;
    public CFGWeapon p18c;
    public CFGWeapon r1895;
    public CFGWeapon r45;
    public CFGWeapon scorpion;
    public CFGWeapon deagle;
    public CFGWeapon win94;
    public CFGWeapon sawedoff;
    public CFGWeapon s1897;
    public CFGWeapon s686;
    public CFGWeapon s12k;
    public CFGWeapon microuzi;
    public CFGWeapon ump45;
    public CFGWeapon bizon;
    public CFGWeapon mp5k;
    public CFGWeapon vector;
    public CFGWeapon tommygun;
    public CFGWeapon m16a4;
    public CFGWeapon m416;
    public CFGWeapon scarl;
    public CFGWeapon qbz;
    public CFGWeapon g36c;
    public CFGWeapon aug;
    public CFGWeapon akm;
    public CFGWeapon m762;
    public CFGWeapon mk47;
    public CFGWeapon groza;
    public CFGWeapon m249;
    public CFGWeapon dp28;
    public CFGWeapon vss;
    public CFGWeapon mini14;
    public CFGWeapon qbu;
    public CFGWeapon sks;
    public CFGWeapon slr;
    public CFGWeapon mk14;
    public CFGWeapon kar98k;
    public CFGWeapon m24;
    public CFGWeapon awm;

    public CFGWeapons(ConfigPlugin plugin) {
        super("Weapons");
        this.plugin = plugin;
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        globalVerticalRecoil = configCreator.createDouble("Global Vertical Recoil", 1.0, 0.0, 10.0);
        globalHorizontalRecoil = configCreator.createDouble("Global Horizontal Recoil", 1.0, 0.0, 10.0);
        crouchRecoilScale = configCreator.createDouble("Crouched Recoil Scale", 0.74, 0.0, 1.0).setDisplay(NumberDisplayType.TEXT_FIELD_SLIDER);
        proneRecoilScale = configCreator.createDouble("Prone Recoil Scale", 0.65, 0.0, 1.0).setDisplay(NumberDisplayType.TEXT_FIELD_SLIDER);
        attachmentsConfig = configCreator.createObject(new AttachmentsConfig(plugin), plugin);
        p92 = configCreator.createObject(new CFGWeapon("P92", 7.04f, 14.25f, 0.051f, 0,0,0.121f,0), plugin);
        p1911 = configCreator.createObject(new CFGWeapon("P1911", 8.45f, 9.375f, 0.078f, 1,0,0.071f,0.847f), plugin);
        p18c = configCreator.createObject(new CFGWeapon("P18C", 3.85f, 14.063f, 0.052f, 0,0,0.124f,0), plugin);
        r1895 = configCreator.createObject(new CFGWeapon("R1895", 12.8f, 12.375f, 0.059f, 1,0,0.056f,8.333f), plugin);
        r45 = configCreator.createObject(new CFGWeapon("R45", 13.0f, 12.375f, 0.059f, 1,0,0.057f,8.453f), plugin);
        scorpion = configCreator.createObject(new CFGWeapon("Scorpion", 3.11f, 13.125f, 0.055f, 1,2,0.112f,2.176f), plugin);
        deagle = configCreator.createObject(new CFGWeapon("Deagle", 11.38f, 16.875f, 0.043f, 0,2,0.484f,7.967f), plugin);
        win94 = configCreator.createObject(new CFGWeapon("Wincherster-94", 13.2f, 28.5f, 0.027f, 0,4,0.1f,11.884f), plugin);
        sawedoff = configCreator.createObject(new CFGWeapon("Sawed-off", 4.2f, 12.375f, 0.058f, 0,0,0.666f,0), plugin);
        s1897 = configCreator.createObject(new CFGWeapon("S1897", 5.2f, 13.5f, 0.053f, 0,1,0.975f,0), plugin);
        s686 = configCreator.createObject(new CFGWeapon("S686", 5.2f, 13.875f, 0.052f, 0,0,0.949f,0), plugin);
        s12k = configCreator.createObject(new CFGWeapon("S12K", 4.8f, 13.125f, 0.055f, 0,0,0.797f,0), plugin);
        microuzi = configCreator.createObject(new CFGWeapon("Micro-uzi", 5.2f, 13.125f, 0.057f, 1,2,0.124f,4.16f), plugin);
        ump45 = configCreator.createObject(new CFGWeapon("UMP-45", 8.4f, 13.5f, 0.055f, 1,5,0.13f,7.139f), plugin);
        bizon = configCreator.createObject(new CFGWeapon("PP-19 Bizon", 7.6f, 15.3f, 0.048f, 1,3,0.155f,6.076f), plugin);
        mp5k = configCreator.createObject(new CFGWeapon("MP5-k", 6.8f, 15.3f, 0.048f, 1,3,0.132f,5.426f), plugin);
        vector = configCreator.createObject(new CFGWeapon("Vector 9mm", 5.71f, 13.125f, 0.057f, 1,3,0.146f,4.559f), plugin);
        tommygun = configCreator.createObject(new CFGWeapon("Tommy gun", 8.0f, 10.5f, 0.072f, 1,8,0.112f,6.788f), plugin);
        m16a4 = configCreator.createObject(new CFGWeapon("M16A4", 8.6f, 34.125f, 0.021f, 1,2,0.206f,6.436f), plugin);
        m416 = configCreator.createObject(new CFGWeapon("M416", 8.0f, 33.0f, 0.022f, 1,1,0.222f,6.0f), plugin);
        scarl = configCreator.createObject(new CFGWeapon("Scar-L", 8.4f, 32.625f, 0.022f, 1,2,0.168f,6.291f), plugin);
        qbz = configCreator.createObject(new CFGWeapon("QBZ", 8.4f, 34.875f, 0.021f, 1,2,0.18f,6.295f), plugin);
        g36c = configCreator.createObject(new CFGWeapon("G36C", 8.2f, 33.0f, 0.022f, 1,2,0.167f,6.145f), plugin);
        aug = configCreator.createObject(new CFGWeapon("AUG A3", 8.8f, 34.688f, 0.021f, 1,2,0.189f,6.595f), plugin);
        akm = configCreator.createObject(new CFGWeapon("AKM", 8.6f, 26.813f, 0.027f, 1,2,0.24f,6.438f), plugin);
        m762 = configCreator.createObject(new CFGWeapon("Beryl M762", 8.8f, 27.75f, 0.026f, 1,2,0.261f,6.6f), plugin);
        mk47 = configCreator.createObject(new CFGWeapon("MK-47 Mutant", 9.8f, 29.25f, 0.025f, 1,3,0.238f,7.345f), plugin);
        groza = configCreator.createObject(new CFGWeapon("Groza", 9.6f, 26.813f, 0.027f, 1,3,0.159f,7.186f), plugin);
        m249 = configCreator.createObject(new CFGWeapon("M249", 8.2f, 34.313f, 0.021f, 1,2,0.204f,6.56f), plugin);
        dp28 = configCreator.createObject(new CFGWeapon("DP-28", 9.51f, 31.5f, 0.023f, 1,1,0.311f,7.121f), plugin);
        vss = configCreator.createObject(new CFGWeapon("VSS", 8.6f, 12.375f, 0.067f, 3,3,0.149f,7.4f), plugin);
        mini14 = configCreator.createObject(new CFGWeapon("Mini-14", 9.6f, 37.125f, 0.02f, 1,3,0.194f,7.97f), plugin);
        qbu = configCreator.createObject(new CFGWeapon("QBU", 9.6f, 37.125f, 0.02f, 1,2,0.191f,7.971f), plugin);
        sks = configCreator.createObject(new CFGWeapon("SKS", 10.6f, 30.0f, 0.025f, 1,3,0.152f,8.799f), plugin);
        slr = configCreator.createObject(new CFGWeapon("SLR", 11.2f, 31.5f, 0.024f, 1,4,0.142f,9.518f), plugin);
        mk14 = configCreator.createObject(new CFGWeapon("MK-14 EBR", 12.0f, 37.125f, 0.02f, 1,3,0.182f,10.193f), plugin);
        kar98k = configCreator.createObject(new CFGWeapon("Kar98k", 15.8f, 28.5f, 0.027f, 2,3,0.118f,14.219f), plugin);
        m24 = configCreator.createObject(new CFGWeapon("M24", 15.0f, 29.625f, 0.026f, 2,4,0.099f,13.503f), plugin);
        awm = configCreator.createObject(new CFGWeapon("AWM", 21.0f, 35.775f, 0.021f, 1,4,0.144f,18.901f), plugin);
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound c = new NBTTagCompound();
        c.setDouble("vertical", globalVerticalRecoil.get());
        c.setDouble("horizontal", globalHorizontalRecoil.get());
        c.setDouble("crouched", crouchRecoilScale.get());
        c.setDouble("prone", proneRecoilScale.get());
        c.setTag("attachments", attachmentsConfig.serializeNBT());
        writeToNBT("p92", p92, c);
        writeToNBT("p1911", p1911, c);
        writeToNBT("p18c", p18c, c);
        writeToNBT("r1895", r1895, c);
        writeToNBT("r45", r45, c);
        writeToNBT("scorpion", scorpion, c);
        writeToNBT("deagle", deagle, c);
        writeToNBT("win94", win94, c);
        writeToNBT("sawedoff", sawedoff, c);
        writeToNBT("s1897", s1897, c);
        writeToNBT("s686", s686, c);
        writeToNBT("s12k", s12k, c);
        writeToNBT("microuzi", microuzi, c);
        writeToNBT("vector", vector, c);
        writeToNBT("bizon", bizon, c);
        writeToNBT("mp5k", mp5k, c);
        writeToNBT("ump45", ump45, c);
        writeToNBT("tommygun", tommygun, c);
        writeToNBT("m164a", m16a4, c);
        writeToNBT("m416", m416, c);
        writeToNBT("scarl", scarl, c);
        writeToNBT("qbz", qbz, c);
        writeToNBT("g36c", g36c, c);
        writeToNBT("aug", aug, c);
        writeToNBT("akm", akm, c);
        writeToNBT("m762", m762, c);
        writeToNBT("mk47", mk47, c);
        writeToNBT("groza", groza, c);
        writeToNBT("dp28", dp28, c);
        writeToNBT("m249", m249, c);
        writeToNBT("vss", vss, c);
        writeToNBT("mini14", mini14, c);
        writeToNBT("qbu", qbu, c);
        writeToNBT("sks", sks, c);
        writeToNBT("slr", slr, c);
        writeToNBT("mk14", mk14, c);
        writeToNBT("kar98k", kar98k, c);
        writeToNBT("m24", m24, c);
        writeToNBT("awm", awm, c);
        return c;
    }

    @Override
    public void deserializeNBT(NBTTagCompound c) {
        globalVerticalRecoil.set(c.getDouble("vertical"));
        globalHorizontalRecoil.set(c.getDouble("horizontal"));
        crouchRecoilScale.set(c.getDouble("crouched"));
        proneRecoilScale.set(c.getDouble("prone"));
        attachmentsConfig.deserializeNBT(c.getCompoundTag("attachments"));
        readFromNBT("p92", p92, c);
        readFromNBT("p1911", p1911, c);
        readFromNBT("p18c", p18c, c);
        readFromNBT("r1895", r1895, c);
        readFromNBT("r45", r45, c);
        readFromNBT("scorpion", scorpion, c);
        readFromNBT("deagle", deagle, c);
        readFromNBT("win94", win94, c);
        readFromNBT("sawedoff", sawedoff, c);
        readFromNBT("s1897", s1897, c);
        readFromNBT("s686", s686, c);
        readFromNBT("s12k", s12k, c);
        readFromNBT("microuzi", microuzi, c);
        readFromNBT("vector", vector, c);
        readFromNBT("bizon", bizon, c);
        readFromNBT("mp5k", mp5k, c);
        readFromNBT("ump45", ump45, c);
        readFromNBT("tommygun", tommygun, c);
        readFromNBT("m164a", m16a4, c);
        readFromNBT("m416", m416, c);
        readFromNBT("scarl", scarl, c);
        readFromNBT("qbz", qbz, c);
        readFromNBT("g36c", g36c, c);
        readFromNBT("aug", aug, c);
        readFromNBT("akm", akm, c);
        readFromNBT("m762", m762, c);
        readFromNBT("mk47", mk47, c);
        readFromNBT("groza", groza, c);
        readFromNBT("dp28", dp28, c);
        readFromNBT("m249", m249, c);
        readFromNBT("vss", vss, c);
        readFromNBT("mini14", mini14, c);
        readFromNBT("qbu", qbu, c);
        readFromNBT("sks", sks, c);
        readFromNBT("slr", slr, c);
        readFromNBT("mk14", mk14, c);
        readFromNBT("kar98k", kar98k, c);
        readFromNBT("m24", m24, c);
        readFromNBT("awm", awm, c);
    }

    private void writeToNBT(String name, CFGWeapon w, NBTTagCompound nbt) {
        nbt.setTag(name, w.serializeNBT());
    }

    private void readFromNBT(String name, CFGWeapon weapon, NBTTagCompound c) {
        weapon.deserializeNBT(c.getCompoundTag(name));
    }
}
