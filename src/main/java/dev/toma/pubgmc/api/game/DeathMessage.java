package dev.toma.pubgmc.api.game;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Objects;

public final class DeathMessage {

    private Type type = Type.NORMAL;
    @Nullable
    private final EntityLivingBase killer;
    private final EntityLivingBase victim;
    private final ITextComponent cause;
    private final ITextComponent combined;
    private int color;

    public DeathMessage(@Nullable EntityLivingBase killer, EntityLivingBase victim, ITextComponent cause) {
        this.killer = killer;
        this.victim = victim;
        this.cause = cause;

        this.combined = killer != null
                ? new TextComponentString(String.format("%s [%s] %s", killer.getDisplayName().getFormattedText(), cause.getFormattedText(), victim.getDisplayName().getFormattedText()))
                : new TextComponentString(String.format("[%s] %s", cause.getFormattedText(), victim.getDisplayName().getFormattedText()));
    }

    public void setType(Type type) {
        this.type = Objects.requireNonNull(type);
    }

    public Type getDeathMessageType() {
        return type;
    }

    @Nullable
    public EntityLivingBase getKiller() {
        return killer;
    }

    public ITextComponent getCause() {
        return cause;
    }

    public EntityLivingBase getVictim() {
        return victim;
    }

    public ITextComponent getWholeComponent() {
        return combined;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    @Override
    public String toString() {
        return combined.getFormattedText();
    }

    public enum Type {

        FRIENDLY_KILL(0x00AAAA),
        FRIENDLY_DEATH(0xAA0000),
        NORMAL(0xFFFFFF);

        private final int color;

        Type(int color) {
            this.color = color;
        }

        public int getColor() {
            return color;
        }

        public static Type getType(DamageSource source, EntityLivingBase victim, World world, TeamManager manager) {
            if (!world.isRemote)
                return NORMAL;
            return getClientType(source, victim, manager);
        }

        @SideOnly(Side.CLIENT)
        private static Type getClientType(DamageSource source, EntityLivingBase victim, TeamManager manager) {
            Entity killer = source.getTrueSource();
            Minecraft client = Minecraft.getMinecraft();
            if (victim == client.player) {
                return FRIENDLY_DEATH;
            }
            if (killer == client.player) {
                return FRIENDLY_KILL;
            }
            Team myTeam = manager.getEntityTeam(client.player);
            if (myTeam == null) {
                return NORMAL;
            }
            if (killer != null) {
                Team team = manager.getEntityTeam(killer);
                if (myTeam.equals(team)) {
                    return FRIENDLY_KILL;
                }
            }
            Team victimTeam = manager.getEntityTeam(victim);
            if (myTeam.equals(victimTeam)) {
                return FRIENDLY_DEATH;
            }
            return NORMAL;
        }
    }
}
