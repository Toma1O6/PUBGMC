package com.toma.pubgmc.config.common;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.util.INBTSerializable;

public final class CFGWeapons implements INBTSerializable<NBTTagCompound> {

    @Config.Name("P92")
    public CFGWeapon p92 = new CFGWeapon(4f, 7, 0.015f, 4);

    @Config.Name("P1911")
    public CFGWeapon p1911 = new CFGWeapon(5f, 7.25f, 0.01f, 5);

    @Config.Name("P18C")
    public CFGWeapon p18c = new CFGWeapon(4f, 7, 0.015f, 4);

    @Config.Name("R1895")
    public CFGWeapon r1895 = new CFGWeapon(8f, 7.5f, 0.01f, 5);

    @Config.Name("R45")
    public CFGWeapon r45 = new CFGWeapon(6f, 7.25f, 0.01f, 5);

    @Config.Name("Scorpion")
    public CFGWeapon scorpion = new CFGWeapon(4f, 7f, 0.015f, 4);

    @Config.Name("Deagle")
    public CFGWeapon deagle = new CFGWeapon(12.5f, 9f, 0.015f, 4);

    @Config.Name("Winchester-94")
    public CFGWeapon win94 = new CFGWeapon(10f, 12f, 0.008f, 7);

    @Config.Name("Sawed-off")
    public CFGWeapon sawedoff = new CFGWeapon(3f, 5f, 0.175f, 0);

    @Config.Name("S1897")
    public CFGWeapon s1897 = new CFGWeapon(4f, 5.5f, 0.175f, 0);

    @Config.Name("S686")
    public CFGWeapon s686 = new CFGWeapon(4f, 5.5f, 0.175f, 0);

    @Config.Name("S12K")
    public CFGWeapon s12k = new CFGWeapon(3.5f, 5.5f, 0.175f, 0);

    @Config.Name("Micro uzi")
    public CFGWeapon microuzi = new CFGWeapon(4f, 8f, 0.02f, 4);

    @Config.Name("UMP-45")
    public CFGWeapon ump45 = new CFGWeapon(5f, 8.5f, 0.02f, 5);

    @Config.Name("PP-19 Bizon")
    public CFGWeapon bizon = new CFGWeapon(4f, 8f, 0.035f, 4);

    @Config.Name("MP5K")
    public CFGWeapon mp5k = new CFGWeapon(4.0f, 8f, 0.035f, 4);

    @Config.Name("Vector")
    public CFGWeapon vector = new CFGWeapon(4f, 8f, 0.035f, 4);

    @Config.Name("Tommy-gun")
    public CFGWeapon tommygun = new CFGWeapon(5f, 8.5f, 0.02f, 5);

    @Config.Name("M16A4")
    public CFGWeapon m16a4 = new CFGWeapon(8f, 12f, 0.005f, 8);

    @Config.Name("M416")
    public CFGWeapon m416 = new CFGWeapon(8f, 12f, 0.0065f, 7);

    @Config.Name("SCAR-L")
    public CFGWeapon scarl = new CFGWeapon(8f, 11f, 0.007f, 7);

    @Config.Name("QBZ-95")
    public CFGWeapon qbz = new CFGWeapon(8f, 11f, 0.007f, 7);

    @Config.Name("G36C")
    public CFGWeapon g36c = new CFGWeapon(8f, 11f, 0.0065f, 7);

    @Config.Name("AUG")
    public CFGWeapon aug = new CFGWeapon(8f, 12f, 0.0065f, 7);

    @Config.Name("AKM")
    public CFGWeapon akm = new CFGWeapon(9.5f, 9f, 0.025f, 7);

    @Config.Name("Beryl M-762")
    public CFGWeapon m762 = new CFGWeapon(9f, 9.5f, 0.025f, 7);

    @Config.Name("MK-47 Mutant")
    public CFGWeapon mk47 = new CFGWeapon(9.5f, 9f, 0.025f, 7);

    @Config.Name("Groza")
    public CFGWeapon groza = new CFGWeapon(9.5f, 9f, 0.025f, 7);

    @Config.Name("M249")
    public CFGWeapon m249 = new CFGWeapon(8f, 11f, 0.0065f, 6);

    @Config.Name("DP-28")
    public CFGWeapon dp28 = new CFGWeapon(9.5f, 9f, 0.03f, 6);

    @Config.Name("VSS")
    public CFGWeapon vss = new CFGWeapon(6f, 7f, 0.035f, 2);

    @Config.Name("Mini-14")
    public CFGWeapon mini14 = new CFGWeapon(9f, 14f, 0.015f, 8);

    @Config.Name("QBU")
    public CFGWeapon qbu = new CFGWeapon(9f, 14f, 0.015f, 8);

    @Config.Name("SKS")
    public CFGWeapon sks = new CFGWeapon(10f, 10f, 0.035f, 7);

    @Config.Name("SLR")
    public CFGWeapon slr = new CFGWeapon(11f, 10f, 0.035f, 7);

    @Config.Name("MK-14 EBR")
    public CFGWeapon mk14 = new CFGWeapon(12.5f, 11f, 0.025f, 7);

    @Config.Name("Kar98k")
    public CFGWeapon kar98k = new CFGWeapon(18f, 11f, 0.04f, 8);

    @Config.Name("M24")
    public CFGWeapon m24 = new CFGWeapon(19f, 11.5f, 0.03f, 7);

    @Config.Name("AWM")
    public CFGWeapon awm = new CFGWeapon(24f, 17f, 0.005f, 10);

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
