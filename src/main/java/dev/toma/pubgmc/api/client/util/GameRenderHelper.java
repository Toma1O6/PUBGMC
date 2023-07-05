package dev.toma.pubgmc.api.client.util;

import dev.toma.pubgmc.api.game.team.TeamGame;
import dev.toma.pubgmc.api.game.team.TeamManager;
import dev.toma.pubgmc.api.game.util.Team;
import dev.toma.pubgmc.util.helper.ImageUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GameRenderHelper {

    private static final int TEAM_PANEL_WIDTH = 60;

    public static void renderTeamOverlay(Minecraft minecraft, TeamGame<?> teamGame, int x, int y, boolean renderSoloTeam) {
        renderTeamOverlay(minecraft, teamGame, x, y, renderSoloTeam, false);
    }

    public static void renderTeamOverlay(Minecraft minecraft, TeamGame<?> teamGame, int x, int y, boolean renderSoloTeam, boolean offsetYByTeamSize) {
        Entity entity = minecraft.getRenderViewEntity();
        if (entity == null) {
            return;
        }
        TeamManager manager = teamGame.getTeamManager();
        Team team = manager.getEntityTeam(entity);
        if (team == null) {
            return;
        }
        if (!renderSoloTeam && team.getSize() == 1) {
            return;
        }
        if (offsetYByTeamSize) {
            y = y - team.getSize() * 15;
        }
        FontRenderer font = minecraft.fontRenderer;
        World world = minecraft.world;
        int index = 0;
        for (Team.Member member : team.getAllMembers().values()) {
            renderMemberInformation(font, world, team, member, x, y + 15 * index++);
        }
    }

    private static void renderMemberInformation(FontRenderer font, World world, Team team, Team.Member member, int x, int y) {
        boolean alive = team.isMember(member.getId());
        String username = team.getUsername(member.getId());
        ImageUtil.drawShape(x, y, x + TEAM_PANEL_WIDTH, y + 15, 0.0F, 0.0F, 0.0F, 0.4F);
        float health = 1.0F;
        if (member.getMemberType().isPlayer()) {
            EntityPlayer player = world.getPlayerEntityByUUID(member.getId());
            if (player != null) {
                health = player.getHealth() / player.getMaxHealth();
            }
        }
        ImageUtil.drawShape(x, y + 13, x + TEAM_PANEL_WIDTH, y + 15, 0.0F, 0.0F, 0.0F, 0.4F);
        ImageUtil.drawShape(x, y + 13, x + (int) (TEAM_PANEL_WIDTH * health), y + 15, 1.0F, 1.0F, 1.0F, 1.0F);
        font.drawString(username, x, y + 2, alive ? 0xFFFFFF : 0xFF0000);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
