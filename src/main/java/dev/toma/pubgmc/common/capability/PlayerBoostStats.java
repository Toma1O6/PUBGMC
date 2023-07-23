package dev.toma.pubgmc.common.capability;

import dev.toma.pubgmc.DevUtil;
import dev.toma.pubgmc.api.capability.BoostStats;
import dev.toma.pubgmc.api.capability.IPlayerData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class PlayerBoostStats implements BoostStats {

    final IPlayerData data;
    int level;
    float saturation;

    public PlayerBoostStats(IPlayerData data) {
        this.data = data;
    }

    @Override
    public void onTick() {
        EntityPlayer player = data.getPlayer();
        World world = player.world;
        if (!world.isRemote && level > 10) {
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 20, 0, false, false));
        }
        if ((saturation = Math.max(0.0F, saturation - 0.002F)) <= 0.0F && level > 0) {
            if (!world.isRemote) {
                player.heal(level > 10 ? 2.0F : 1.0F);
                --level;
                saturation = 1.0F;
                data.sync();
            }
        }
    }

    @Override
    public void add(int level) {
        this.level = DevUtil.wrap(this.level + level, 0, 20);
    }

    @Override
    public int getBoostLevel() {
        return level;
    }

    @Override
    public void setBoostLevel(int level) {
        this.level = level;
    }

    @Override
    public float getSaturation() {
        return saturation;
    }

    @Override
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
