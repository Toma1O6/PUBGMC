package dev.toma.pubgmc.config.common;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.ConfigPlugin;
import dev.toma.configuration.api.type.ObjectType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public final class CFGWeapons extends ObjectType implements INBTSerializable<NBTTagCompound> {

    final ConfigPlugin plugin;
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
        p92 = configCreator.createObject(new CFGWeapon("P92", 4f, 11, 0.015f, 4), plugin);
        p1911 = configCreator.createObject(new CFGWeapon("P1911", 5f, 12, 0.01f, 5), plugin);
        p18c = configCreator.createObject(new CFGWeapon("P18C", 4f, 11, 0.015f, 4), plugin);
        r1895 = configCreator.createObject(new CFGWeapon("R1895", 8f, 14, 0.01f, 5), plugin);
        r45 = configCreator.createObject(new CFGWeapon("R45", 6f, 11, 0.01f, 5), plugin);
        scorpion = configCreator.createObject(new CFGWeapon("Scorpion", 4f, 11, 0.015f, 4), plugin);
        deagle = configCreator.createObject(new CFGWeapon("Deagle", 12.5f, 14, 0.015f, 4), plugin);
        win94 = configCreator.createObject(new CFGWeapon("Wincherster-94", 10f, 16, 0.008f, 7), plugin);
        sawedoff = configCreator.createObject(new CFGWeapon("Sawed-off", 3f, 8, 0.175f, 3), plugin);
        s1897 = configCreator.createObject(new CFGWeapon("S1897", 4f, 8, 0.175f, 3), plugin);
        s686 = configCreator.createObject(new CFGWeapon("S686", 4f, 8, 0.175f, 3), plugin);
        s12k = configCreator.createObject(new CFGWeapon("S12K", 3.5f, 8, 0.175f, 3), plugin);
        microuzi = configCreator.createObject(new CFGWeapon("Micro-uzi", 4f, 11, 0.02f, 4), plugin);
        ump45 = configCreator.createObject(new CFGWeapon("UMP-45", 5f, 12, 0.02f, 5), plugin);
        bizon = configCreator.createObject(new CFGWeapon("PP-19 Bizon", 4f, 11, 0.035f, 4), plugin);
        mp5k = configCreator.createObject(new CFGWeapon("MP5-k", 4.0f, 11, 0.035f, 4), plugin);
        vector = configCreator.createObject(new CFGWeapon("Vector 9mm", 4f, 11, 0.035f, 4), plugin);
        tommygun = configCreator.createObject(new CFGWeapon("Tommy gun", 5f, 12, 0.02f, 5), plugin);
        m16a4 = configCreator.createObject(new CFGWeapon("M16A4", 8f, 18, 0.005f, 8), plugin);
        m416 = configCreator.createObject(new CFGWeapon("M416", 8f, 19, 0.0065f, 7), plugin);
        scarl = configCreator.createObject(new CFGWeapon("Scar-L", 8f, 18, 0.007f, 7), plugin);
        qbz = configCreator.createObject(new CFGWeapon("QBZ", 8f, 18, 0.007f, 7), plugin);
        g36c = configCreator.createObject(new CFGWeapon("G36C", 8f, 18, 0.0065f, 7), plugin);
        aug = configCreator.createObject(new CFGWeapon("AUG A3", 8f, 18, 0.0065f, 7), plugin);
        akm = configCreator.createObject(new CFGWeapon("AKM", 9.5f, 16, 0.025f, 7), plugin);
        m762 = configCreator.createObject(new CFGWeapon("Beryl M762", 9f, 16, 0.025f, 7), plugin);
        mk47 = configCreator.createObject(new CFGWeapon("MK-47 Mutant", 9.5f, 17, 0.025f, 7), plugin);
        groza = configCreator.createObject(new CFGWeapon("Groza", 9.5f, 16, 0.025f, 7), plugin);
        m249 = configCreator.createObject(new CFGWeapon("M249", 8f, 18, 0.0065f, 6), plugin);
        dp28 = configCreator.createObject(new CFGWeapon("DP-28", 9.5f, 16, 0.03f, 6), plugin);
        vss = configCreator.createObject(new CFGWeapon("VSS", 6f, 8, 0.035f, 2), plugin);
        mini14 = configCreator.createObject(new CFGWeapon("Mini-14", 9f, 20, 0.015f, 8), plugin);
        qbu = configCreator.createObject(new CFGWeapon("QBU", 9f, 19, 0.015f, 8), plugin);
        sks = configCreator.createObject(new CFGWeapon("SKS", 10f, 17.5F, 0.035f, 7), plugin);
        slr = configCreator.createObject(new CFGWeapon("SLR", 11f, 17, 0.035f, 7), plugin);
        mk14 = configCreator.createObject(new CFGWeapon("MK-14 EBR", 12.5f, 18, 0.025f, 7), plugin);
        kar98k = configCreator.createObject(new CFGWeapon("Kar98k", 18f, 19, 0.04f, 8), plugin);
        m24 = configCreator.createObject(new CFGWeapon("M24", 19f, 19.5F, 0.03f, 7), plugin);
        awm = configCreator.createObject(new CFGWeapon("AWM", 24f, 23, 0.005f, 10), plugin);
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound c = new NBTTagCompound();
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
