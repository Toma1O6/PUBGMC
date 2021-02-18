package dev.toma.pubgmc.config.common;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;

public final class CFGWorld implements INBTSerializable<NBTTagCompound> {

    @Config.Name("Allow vehicles")
    public boolean vehicleSpawning = true;

    @Config.Name("Allow gun loot")
    public boolean enableGunLoot = true;

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

    @Config.Name("Default ghillie colors")
    public int[] defaultGhillieColors = {0x52D900, 0xD89000, 0xDBDBDB};

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound c = new NBTTagCompound();
        NBTTagList colors = new NBTTagList();
        c.setBoolean("vehicles", vehicleSpawning);
        c.setBoolean("gunLoot", enableGunLoot);
        c.setInteger("airdropLoot", airdropLoot.ordinal());
        c.setBoolean("guns", gunsEnabled);
        c.setInteger("planeHeight", planeHeight);
        c.setInteger("planeDelay", planeDelay);
        c.setBoolean("zoneNotif", titleZoneNotifications);
        for(int i = 0; i < defaultGhillieColors.length; i++) colors.appendTag(new NBTTagInt(defaultGhillieColors[i]));
        c.setTag("colors", colors);
        return c;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        vehicleSpawning = nbt.getBoolean("vehicles");
        enableGunLoot = nbt.getBoolean("gunLoot");
        airdropLoot = CFGEnumAirdropLoot.values()[nbt.getInteger("airdropLoot")];
        gunsEnabled = nbt.getBoolean("guns");
        planeHeight = nbt.getInteger("planeHeight");
        planeDelay = nbt.getInteger("planeDelay");
        titleZoneNotifications = nbt.getBoolean("zoneNotif");
        NBTTagList colors = nbt.getTagList("colors", Constants.NBT.TAG_INT);
        defaultGhillieColors = new int[colors.tagCount()];
        for(int i = 0; i < colors.tagCount(); i++) defaultGhillieColors[i] = colors.getIntAt(i);
    }
}
