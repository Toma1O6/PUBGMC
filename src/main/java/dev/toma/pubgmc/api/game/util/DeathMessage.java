package dev.toma.pubgmc.api.game.util;

import dev.toma.pubgmc.api.game.Team;
import dev.toma.pubgmc.api.game.TeamManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.UUID;

public final class DeathMessage {

    @Nullable
    private final UUID killerId;
    private final UUID victimId;
    @Nullable
    private final EntityLivingBase killer;
    private final EntityLivingBase victim;
    private final ITextComponent cause;
    private final ITextComponent combined;
    private Type type;

    public DeathMessage(@Nullable EntityLivingBase killer, EntityLivingBase victim, ITextComponent cause) {
        this.killer = killer;
        this.killerId = killer != null ? killer.getUniqueID() : null;
        this.victim = victim;
        this.victimId = victim.getUniqueID();
        this.cause = cause;

        this.combined = combine(killer != null ? killer.getDisplayName() : null, victim.getDisplayName(), cause);
    }

    private DeathMessage(@Nullable ITextComponent killerName, ITextComponent victimName, ITextComponent cause, @Nullable UUID killerId, UUID victimId) {
        this.killer = null;
        this.victim = null;
        this.cause = cause;
        this.killerId = killerId;
        this.victimId = victimId;
        this.combined = combine(killerName, victimName, cause);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public ITextComponent getWholeComponent() {
        return combined;
    }

    @Nullable
    public UUID getKillerId() {
        return killerId;
    }

    public UUID getVictimId() {
        return victimId;
    }

    @Override
    public String toString() {
        return combined.getFormattedText();
    }

    public NBTTagCompound serialize() {
        NBTTagCompound nbt = new NBTTagCompound();
        if (killer != null) {
            nbt.setString("killer", ITextComponent.Serializer.componentToJson(killer.getDisplayName()));
            nbt.setUniqueId("killerUUID", killerId);
        }
        nbt.setString("victim", ITextComponent.Serializer.componentToJson(victim.getDisplayName()));
        nbt.setString("cause", ITextComponent.Serializer.componentToJson(cause));
        nbt.setUniqueId("victimUUID", victimId);
        return nbt;
    }

    public static DeathMessage deserialize(NBTTagCompound nbt) {
        ITextComponent killer = null;
        UUID killerId = null;
        if (nbt.hasKey("killer")) {
            killer = ITextComponent.Serializer.jsonToComponent(nbt.getString("killer"));
            killerId = nbt.getUniqueId("killerUUID");
        }
        ITextComponent victim = ITextComponent.Serializer.jsonToComponent(nbt.getString("victim"));
        ITextComponent cause = ITextComponent.Serializer.jsonToComponent(nbt.getString("cause"));
        UUID victimId = nbt.getUniqueId("victimUUID");
        return new DeathMessage(killer, victim, cause, killerId, victimId);
    }

    private ITextComponent combine(@Nullable ITextComponent killer, ITextComponent victim, ITextComponent cause) {
        return killer != null
                ? new TextComponentString(String.format("%s [%s] %s", killer.getFormattedText(), cause.getFormattedText(), victim.getFormattedText()))
                : new TextComponentString(String.format("[%s] %s", cause.getFormattedText(), victim.getFormattedText()));
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

        public static Type getType(DeathMessage message, World world, TeamManager manager) {
            if (!world.isRemote)
                return NORMAL;
            return getClientType(message, manager);
        }

        @SideOnly(Side.CLIENT)
        private static Type getClientType(DeathMessage message, TeamManager manager) {
            UUID killer = message.killerId;
            UUID victim = message.victimId;
            Minecraft client = Minecraft.getMinecraft();
            if (victim.equals(client.player.getUniqueID())) {
                return FRIENDLY_DEATH;
            }
            if (Objects.equals(killer, client.player.getUniqueID())) {
                return FRIENDLY_KILL;
            }
            Team myTeam = manager.getEntityTeam(client.player);
            if (myTeam == null) {
                return NORMAL;
            }
            if (killer != null) {
                Team team = manager.getEntityTeamByEntityId(killer);
                if (myTeam.equals(team)) {
                    return FRIENDLY_KILL;
                }
            }
            Team victimTeam = manager.getEntityTeamByEntityId(victim);
            if (myTeam.equals(victimTeam)) {
                return FRIENDLY_DEATH;
            }
            return NORMAL;
        }
    }
}
