package com.toma.pubgmc.config.common;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.util.INBTSerializable;

public final class CFGWorld implements INBTSerializable<NBTTagCompound> {

    @Config.Name("Allow vehicles")
    public boolean vehicleSpawning = true;

    @Config.Name("Allow gun loot")
    public boolean enableGunLoot = true;

    @Config.Name("Airdrop range")
    @Config.Comment({"If there are no players in specified radius around the drop, it will despawn","Set this to -1 to disable despawning"})
    @Config.RangeInt(min = -1, max = 200)
    public int airdropRange = 40;

    @Config.Name("Airdrop loot")
    public CFGEnumAirdropLoot airdropLoot = CFGEnumAirdropLoot.ALL;

    @Config.Name("Allow guns")
    @Config.Comment("You can use this to disable all guns, it will no longer shoot")
    public boolean gunsEnabled = true;

    @Config.Name("Plane path height")
    @Config.RangeInt(min = 10, max = 255)
    public int planeHeight = 150;

    @Config.Name("Plane start delay")
    @Config.Comment("How many seconds plane will wait in starting position when game starts")
    public int planeDelay = 5;

    @Config.Name("Title zone notification")
    @Config.Comment("If true you will receive zone shrink notification through title instead of chat")
    public boolean titleZoneNotifications = true;

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound c = new NBTTagCompound();
        c.setBoolean("vehicles", vehicleSpawning);
        c.setBoolean("gunLoot", enableGunLoot);
        c.setInteger("airdropRange", airdropRange);
        c.setInteger("airdropLoot", airdropLoot.ordinal());
        c.setBoolean("guns", gunsEnabled);
        c.setInteger("planeHeight", planeHeight);
        c.setInteger("planeDelay", planeDelay);
        c.setBoolean("zoneNotif", titleZoneNotifications);
        return c;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        vehicleSpawning = nbt.getBoolean("vehicles");
        enableGunLoot = nbt.getBoolean("gunLoot");
        airdropRange = nbt.getInteger("airdropRange");
        airdropLoot = CFGEnumAirdropLoot.values()[nbt.getInteger("airdropLoot")];
        gunsEnabled = nbt.getBoolean("guns");
        planeHeight = nbt.getInteger("planeHeight");
        planeDelay = nbt.getInteger("planeDelay");
        titleZoneNotifications = nbt.getBoolean("zoneNotif");
    }
}
