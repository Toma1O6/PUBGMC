package com.toma.pubgmc.api.util;

import com.toma.pubgmc.api.games.Game;
import com.toma.pubgmc.api.GamePlayerData;
import com.toma.pubgmc.api.teams.Team;
import com.toma.pubgmc.common.entity.bot.EntityAIPlayer;
import com.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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

    public ItemStack getStack() {
        return stack;
    }

    public EntityLivingBase getDeadEntity() {
        return deadEntity;
    }

    @Nullable
    public EntityLivingBase getSource() {
        return source;
    }

    @SideOnly(Side.CLIENT)
    public static int getIDFor(EntityLivingBase source, EntityLivingBase victim, Game game) {
        if(!(source instanceof EntityPlayer)) {
            return 0;
        }
        GamePlayerData srcData = game.getPlayerData().get(source.getUniqueID());
        if(srcData == null) return 0;
        GamePlayerData vcData = game.getPlayerData().get(victim.getUniqueID());
        if(vcData == null) {
            Minecraft mc = Minecraft.getMinecraft();
            if(mc.player.getName().equals(source.getName())) {
                return 1;
            }
            return 0;
        }
        if(victim instanceof EntityPlayer) {
            if(victim == Minecraft.getMinecraft().player) {
                return 2;
            }
            return 0;
        }
        return 0;
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
