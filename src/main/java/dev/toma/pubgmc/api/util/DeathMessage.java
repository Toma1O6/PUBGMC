package dev.toma.pubgmc.api.util;

import dev.toma.pubgmc.api.games.Game;
import dev.toma.pubgmc.api.GamePlayerData;
import dev.toma.pubgmc.api.settings.EntityDeathManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.UUID;

public class DeathMessage {

    @Nullable
    private final EntityLivingBase source;
    private final EntityLivingBase victim;
    private final int distance;
    private final String data;
    private final String deathMsg;
    private int ticksLeft;

    public DeathMessage(EntityDeathManager manager, EntityDeathContex ctx) {
        this.source = ctx.getSource();
        this.victim = ctx.getDeadEntity();
        this.distance = (int) ctx.getDistanceFromSource();
        this.data = ctx.getStack().isEmpty() ? "?" : ctx.getStack().getDisplayName();
        this.deathMsg = ctx.hasSource() ? ctx.getSource().getName() + " [" + data + "] " + ctx.getDeadEntity().getName() : "[DEATH] " + ctx.getDeadEntity().getName();
        this.ticksLeft = manager.getDeathMessageRenderLength();
    }

    public void tick(int index, Game game) {
        --ticksLeft;
        if(ticksLeft <= 0) {
            game.displayedDeathMessages[index] = null;
        }
    }

    @Nullable
    public EntityLivingBase source() {
        return source;
    }

    public EntityLivingBase victim() {
        return victim;
    }

    @SideOnly(Side.CLIENT)
    public void draw(Game game, Minecraft mc, ScaledResolution res, int x, int y) {
        EnumViewContex ctx = this.getContex(mc, game);
        float diff = ticksLeft <= 20 ? ticksLeft / 20.0F : 0.0F;
        int alpha = (int)(0xFF * diff) << 24;
        switch (ctx) {
            case NORMAL: default: {
                int color = 0xFFFFFF;
                int additional = (int)(0xFF * diff) << 24;
                mc.fontRenderer.drawStringWithShadow(this.deathMsg, x, y, 0xFFFFFF + alpha);
                break;
            }
            case SRC: {
                mc.fontRenderer.drawStringWithShadow(this.deathMsg + " [" + this.distance + "m]", x, y, 0x00DDDD + alpha);
                break;
            }
            case VCM: {
                mc.fontRenderer.drawStringWithShadow(this.deathMsg, x, y, 0xBB0000 + alpha);
                break;
            }
        }
    }

    @SideOnly(Side.CLIENT)
    private EnumViewContex getContex(Minecraft mc, Game game) {
        GamePlayerData srcData = this.source() == null ? null : game.getPlayerData().get(this.source().getUniqueID());
        GamePlayerData vcmData = game.getPlayerData().get(this.victim().getUniqueID());
        UUID uuid = mc.player.getUniqueID();
        if(srcData != null) {
            GamePlayerData localData = game.getPlayerData().get(uuid);
            if(uuid.equals(this.source().getUniqueID()) || (localData != null && localData.getTeam() == srcData.getTeam())) {
                return EnumViewContex.SRC;
            }
        }
        if(vcmData != null) {
            GamePlayerData localData = game.getPlayerData().get(uuid);
            if(uuid.equals(this.victim().getUniqueID()) || (localData != null && localData.getTeam() == vcmData.getTeam())) {
                return EnumViewContex.VCM;
            }
        }
        return EnumViewContex.NORMAL;
    }

    private enum EnumViewContex {
        SRC,
        VCM,
        NORMAL
    }
}
