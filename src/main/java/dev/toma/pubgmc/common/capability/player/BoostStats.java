package dev.toma.pubgmc.common.capability.player;

import dev.toma.pubgmc.DevUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.util.INBTSerializable;

public class BoostStats implements INBTSerializable<NBTTagCompound> {

    final IPlayerData data;
    int level;
    float saturation;

    public BoostStats(IPlayerData data) {
        this.data = data;
    }

    public void onTick(EntityPlayer player) {
        World world = player.world;
        if(!world.isRemote) {
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 20, 0, false, false));
        }
        if((saturation = Math.max(0.0F, saturation - 0.002F)) <= 0.0F && level > 0) {
            if(!world.isRemote) {
                player.heal(level > 10 ? 2.0F : 1.0F);
                --level;
                saturation = 1.0F;
                data.sync();
            }
        }
    }

    public void add(int level) {
        this.level = DevUtil.wrap(this.level + level, 0, 20);
        this.saturation = 0.0F;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public float getSaturation() {
        return saturation;
    }

    public boolean isEmpty() {
        return level == 0 && saturation == 0.0F;
    }

    public void reset() {
        this.level = 0;
        this.saturation = 0.0F;
    }

    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("level", level);
        nbt.setFloat("saturation", saturation);
        return nbt;
    }

    public void deserializeNBT(NBTTagCompound nbt) {
        level = nbt.getInteger("level");
        saturation = nbt.getFloat("saturation");
    }
}
