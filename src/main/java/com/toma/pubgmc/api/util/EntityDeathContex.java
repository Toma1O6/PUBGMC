package com.toma.pubgmc.api.util;

import com.toma.pubgmc.api.Game;
import com.toma.pubgmc.api.settings.EntityDeathManager;
import com.toma.pubgmc.api.teams.Team;
import com.toma.pubgmc.common.entity.bot.EntityAIPlayer;
import com.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityDeathContex {

    private final boolean isBot;
    private final EntityLivingBase deadEntity;
    @Nullable
    private final EntityLivingBase source;
    private final boolean isTeamKill;
    private final ItemStack stack;

    public EntityDeathContex(final @Nonnull EntityLivingBase deadEntity, final @Nullable EntityLivingBase source, final Game game) {
        this.deadEntity = deadEntity;
        this.source = source;
        this.isBot = deadEntity instanceof EntityAIPlayer;
        this.isTeamKill = source != null && source instanceof EntityPlayer && deadEntity instanceof EntityPlayer ? this.checkSameTeams((EntityPlayer) deadEntity, (EntityPlayer) source, game) : false;
        this.stack = source != null ? source.getHeldItemMainhand() : ItemStack.EMPTY;
    }

    /**
     * Not safe to call directly, you need to check for LivingBase entities!
     */
    public static EntityDeathContex getDeathContex(LivingDeathEvent event, Game game) {
        return new EntityDeathContex((EntityLivingBase) event.getEntity(), (EntityLivingBase) event.getSource().getTrueSource(), game);
    }

    public void sendDeathMessages(EntityDeathManager manager) {
        this.sendToSource(manager.getSourceNotification().apply(this));
        this.sendToVictim(manager.getVictimNotification().apply(this));
        this.sendToOthers(manager.getOthersNotification().apply(this));
    }

    protected void sendToSource(String message) {
        if (this.hasSource() && this.source instanceof EntityPlayer) {
            ((EntityPlayer) this.source).sendStatusMessage(new TextComponentString(message), true);
        }
    }

    protected void sendToVictim(String message) {
        this.deadEntity.sendMessage(new TextComponentString(message));
    }

    protected void sendToOthers(String message) {
        TextComponentString component = new TextComponentString(message);
        for (EntityPlayer player : this.deadEntity.world.playerEntities) {
            if (!player.getUniqueID().equals(this.deadEntity.getUniqueID()) && (source == null || !player.getUniqueID().equals(this.source.getUniqueID()))) {
                player.sendMessage(component);
            }
        }
    }

    public boolean hasSource() {
        return this.source != null;
    }

    public double getDistanceFromSource() {
        if (source == null) {
            return -1;
        }
        double x = deadEntity.posX - source.posX;
        double y = deadEntity.posY - source.posY;
        double z = deadEntity.posZ - source.posZ;
        return Math.sqrt(x * x + y * y + z * z);
    }

    public boolean isBot() {
        return isBot;
    }

    public EntityLivingBase getDeadEntity() {
        return deadEntity;
    }

    @Nullable
    public EntityLivingBase getSource() {
        return source;
    }

    private boolean checkSameTeams(EntityPlayer player0, EntityPlayer player1, Game game) {
        Team team0 = this.getTeamFor(player0, game);
        Team team1 = this.getTeamFor(player1, game);
        return team0 != null && team1 != null && team0 == team1;
    }

    private Team getTeamFor(EntityPlayer player, Game game) {
        for (Team team : game.getTeamList()) {
            if (PUBGMCUtil.contains(player.getUniqueID(), team.players)) {
                return team;
            }
        }
        return null;
    }
}
