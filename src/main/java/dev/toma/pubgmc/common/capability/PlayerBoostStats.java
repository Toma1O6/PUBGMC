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
    private int boost = 0;
    private int maxBoost = 300;
    private int boostDropCooldown = 0;
    private int healCooldown = 0;
    private int effectCooldown = 0;
    private boolean showEffectParticles = false; // may add to config

    public PlayerBoostStats(IPlayerData data) {
        this.data = data;
    }

    @Override
    public void onTick() {
        EntityPlayer player = data.getPlayer();
        World world = player.world;
        int boostLevel = getBoostLevel();
        // heal
        if (healCooldown <= 0) {
            if (!world.isRemote) {
                switch (boostLevel) {
                    case 1: { // 1% hp
                        player.heal(0.2f); break;
                    }
                    case 2: { // 2% hp
                        player.heal(0.4f); break;
                    }
                    case 3: { // 3% hp
                        player.heal(0.6f); break;
                    }
                    case 4: { // 4% hp
                        player.heal(0.8f); break;
                    }
                }
                healCooldown += 160;
                data.sync();
            }
        } else if (!world.isRemote) {
            --healCooldown;
            data.sync();
        }
        // effect
        if (effectCooldown <= 0) {
            if (!world.isRemote) {
                switch (boostLevel) {
                    case 3: {
                        player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 30, 0, false, showEffectParticles)); // 20% speed
                        break;
                    }
                    case 4: {
                        player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 30, 1, false, showEffectParticles)); // 40% speed
                        player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 30, 0, false, showEffectParticles));
                        break;
                    }
                }
                effectCooldown += 20;
                data.sync();
            }
        } else if (!world.isRemote){
            --effectCooldown;
            data.sync();
        }
        // boost
        if (boostDropCooldown <= 0) {
            if (!world.isRemote) {
                --boost;
                boostDropCooldown = 20;
                data.sync();
            }
        } else if (!world.isRemote){
            --boostDropCooldown;
            data.sync();
        }
    }

    @Override
    public void addBoost(int amount) {
        int preLevel = getBoostLevel();
        if (preLevel == 0) {
            reset();
        }
        this.boost = DevUtil.wrap(this.boost + amount, 0, maxBoost);
        int currentLevel = getBoostLevel();
        if (preLevel != currentLevel) { // immediately apply new (higher) effect
            effectCooldown = 0;
        }
    }

    @Override
    public int getBoost() {
        return boost;
    }

    @Override
    public int getBoostLimit() {
        return maxBoost;
    }

    @Override
    public int getBoostLevel() {
        if (boost >= 270) { // 90-100%
            return 4;
        } else if (boost >= 180) { // 60-89%
            return 3;
        } else if (boost >= 60) { // 20-59%
            return 2;
        } else if (boost > 0) { // 1-19%
            return 1;
        } else { // 0%
            return 0;
        }
    }

    @Override
    public float getLevelPercentage(int level) {
        float percentage;
        switch (level) {
            case 1: {
                percentage = 0.2f; break;
            }
            case 2: {
                percentage = 0.6f; break;
            }
            case 3: {
                percentage = 0.9f; break;
            }
            case 4: {
                percentage = 1.0f; break;
            }
            default: {
                percentage = 0.0f; break;
            }
        }
        return percentage;
    }

    @Override
    public void setBoost(float percentage) {
        int preLevel = getBoostLevel();
        if (preLevel == 0) {
            reset();
        }
        this.boost = (int) (maxBoost * percentage);
        int currentLevel = getBoostLevel();
        if (preLevel != currentLevel) { // immediately apply new (higher) effect
            effectCooldown = 0;
        }
    }

    @Override
    public void reset() {
        this.boost = 0;
        this.healCooldown = 0; // immediately heal when first use
        this.effectCooldown = 0;
        this.boostDropCooldown = 20; // prevent immediate drop for first use
    }

    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("boost", boost);
        nbt.setInteger("healCooldown", healCooldown);
        nbt.setInteger("effectCooldown", effectCooldown);
        nbt.setInteger("boostDropCooldown", boostDropCooldown);
        return nbt;
    }

    public void deserializeNBT(NBTTagCompound nbt) {
        boost = nbt.getInteger("boost");
        healCooldown = nbt.getInteger("healCooldown");
        effectCooldown = nbt.getInteger("effectCooldown");
        boostDropCooldown = nbt.getInteger("boostDropCooldown");
    }
}
