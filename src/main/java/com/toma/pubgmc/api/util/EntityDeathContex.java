package com.toma.pubgmc.api.util;

import com.toma.pubgmc.common.entity.bot.EntityAIPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityDeathContex {

    private final boolean isBot;
    private final Entity deadEntity;
    @Nullable
    private final Entity source;

    public EntityDeathContex(final @Nonnull Entity deadEntity, final @Nullable Entity source) {
        this.deadEntity = deadEntity;
        this.source = source;
        this.isBot = deadEntity instanceof EntityAIPlayer;
    }

    public void sendToSource(String message) {
        if(this.hasSource()) {
            this.source.sendMessage(new TextComponentString(message));
        }
    }

    public void sendToVictim(String message) {
        this.deadEntity.sendMessage(new TextComponentString(message));
    }

    public void sendToOthers(String message) {
        TextComponentString component = new TextComponentString(message);
        this.deadEntity.getEntityWorld().playerEntities.stream().filter(player -> player != source).forEach(player -> player.sendMessage(component));
    }

    public boolean hasSource() {
        return this.source != null;
    }

    public double getDistanceFromSource() {
        double x = deadEntity.posX - source.posX;
        double y = deadEntity.posY - source.posY;
        double z = deadEntity.posZ - source.posZ;
        return Math.sqrt(x * x + y * y + z * z);
    }

    public boolean isBot() {
        return isBot;
    }

    public Entity getDeadEntity() {
        return deadEntity;
    }

    @Nullable
    public Entity getSource() {
        return source;
    }

    public static EntityDeathContex getDeathContex(LivingDeathEvent event) {
        return new EntityDeathContex(event.getEntity(), event.getSource().getTrueSource());
    }
}
