package dev.toma.pubgmc.client.games;

import dev.toma.pubgmc.api.client.game.GameRenderer;
import dev.toma.pubgmc.api.client.util.PlayzoneRenderer;
import dev.toma.pubgmc.api.game.playzone.Playzone;
import dev.toma.pubgmc.api.game.team.TeamManager;
import dev.toma.pubgmc.api.game.util.Team;
import dev.toma.pubgmc.common.games.game.tournament.TournamentGame;
import dev.toma.pubgmc.common.games.game.tournament.TournamentGameCaptureManager;
import dev.toma.pubgmc.common.games.game.tournament.TournamentGameConfiguration;
import dev.toma.pubgmc.common.games.game.tournament.TournamentMatch;
import dev.toma.pubgmc.common.games.util.TeamType;
import dev.toma.pubgmc.util.helper.ImageUtil;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class TournamentGameRenderer implements GameRenderer<TournamentGame> {

    private final PlayzoneRenderer<Playzone> playzoneRenderer;

    private int debugLine;

    public TournamentGameRenderer() {
        this.playzoneRenderer = new PlayzoneRenderer<>();
        this.playzoneRenderer.setColor(0x44FFFFFF); // TODO config
    }

    public void renderScoreboard(EntityPlayer player, TournamentGame game, ScaledResolution resolution, float partialTicks) {
        TeamManager manager = game.getTeamManager();
        Team team = manager.getEntityTeam(player);
        TournamentMatch match = game.getActiveMatch();

        int width = resolution.getScaledWidth();
        int height = resolution.getScaledHeight();
        ImageUtil.drawShape(0, 0, width, height, 0x88 << 24);
        if (match != null && team != null && match.containsTeam(team)) {
            // Render match rounds and player scores
        } else {
            // Render match scores and planned matches
        }
    }

    public void renderTeamList(EntityPlayer player, TournamentGame game, ScaledResolution resolution, float partialTicks) {
        int width = resolution.getScaledWidth();
        int height = resolution.getScaledHeight();
        FontRenderer font = Minecraft.getMinecraft().fontRenderer;
        TournamentGameConfiguration configuration = game.getConfiguration();
        TeamManager manager = game.getTeamManager();

        ImageUtil.drawShape(0, 0, width, height, 0x88 << 24);
        Collection<Team> teams = manager.getTeams();
        int teamWidth = 120;
        int teamHeight = teams.stream().mapToInt(team -> 15 + 10 * team.getSize()).sum();
        int left = (width - teamWidth) / 2;
        int top = (height - teamHeight) / 2;
        int topOffset = 0;
        for (Team team : teams) {
            ImageUtil.drawShape(left, top + topOffset, left + teamWidth, top + topOffset + 2, 0xFFFFFFFF);
            String teamName = team.getUsername(team.getTeamId());
            if (teamName == null) {
                teamName = "Team";
            }
            font.drawString(TextFormatting.BOLD + teamName, left, top + topOffset + 4, 0xFFFFFF);
            topOffset += 15;
            Set<UUID> memberIds = team.getAllMembers().keySet();
            for (UUID memberId : memberIds) {
                String username = team.getUsername(memberId);
                font.drawString(username, left + 5, top + topOffset, 0xFFFFFF);
                topOffset += 10;
            }
        }
        ImageUtil.drawShape(left, top + topOffset, left + teamWidth, top + topOffset + 2, 0xFFFFFFFF);
    }

    public void renderHudText(EntityPlayer player, TournamentGame game, ScaledResolution resolution, float partialTicks) {
        this.debugLine = 0;
        FontRenderer font = Minecraft.getMinecraft().fontRenderer;
        renderDebugText(font, "§eGameState:§r§a " + game.getGameState());
        renderDebugText(font, "§eTimings:§r§b " + game.getTickTimer() + "/" + game.getEventTimer());
        TournamentMatch match = game.getActiveMatch();
        if (match != null) {
            TournamentGameConfiguration.MatchConfiguration configuration = game.getMatchConfig();
            renderDebugText(font, "§eRound:§r§b " + match.getRoundNumber() + "/" + configuration.matchCount);
            renderDebugText(font, "§eMatch type:§r§a " + match.getMatchType());
            renderDebugText(font, "§eMatch state:§r§a " + match.getMatchStatus());
            renderDebugText(font, "§eMatch history:§r§5 " + match.getMatchHistory());
            renderDebugText(font, "§eTeam §r§cRed§r§e:§r " + match.getTeam(TeamType.RED));
            renderDebugText(font, "§eTeam §r§9Blue§r§e:§r " + match.getTeam(TeamType.BLUE));

            TournamentGameCaptureManager manager = game.getCaptureManager();
            if (configuration.hasCaptureZoneEnabled()) {
                renderDebugText(font, "§eCapture Manager:§r");
                renderDebugText(font, "  §eCapture Zone:§r§a " + manager.getCaptureZone());
                renderDebugText(font, "  §eCapture Team:§r " + manager.getCapturingTeam());
                renderDebugText(font, "  §eCapture Timings:§r§b " + manager.getCapturingTime() + "/" + configuration.captureTime);
            }
        }
        renderDebugText(font, "§eScoreboard:§r");
        Object2IntMap<Team> scoreboard = game.getTeamScores();
        for (Map.Entry<Team, Integer> entry : scoreboard.entrySet()) {
            String text = String.format("  %s => §r§b%d§r", entry.getKey(), entry.getValue());
            renderDebugText(font, text);
        }
    }

    @Override
    public boolean renderHudOverlay(EntityPlayer player, TournamentGame game, ScaledResolution resolution, RenderGameOverlayEvent.ElementType elementType, float partialTicks) {
        if (!game.isStarted()) {
            if (elementType == RenderGameOverlayEvent.ElementType.PLAYER_LIST) {
                renderTeamList(player, game, resolution, partialTicks);
                return true;
            }
            return false;
        }
        if (elementType == RenderGameOverlayEvent.ElementType.ALL) {
            renderHudText(player, game, resolution, partialTicks);
        } else if (elementType == RenderGameOverlayEvent.ElementType.PLAYER_LIST) {
            renderScoreboard(player, game, resolution, partialTicks);
            return true;
        }
        return false;
    }

    @Override
    public void renderInWorld(World world, TournamentGame game, double x, double y, double z, float partialTicks) {
        Playzone playzone = game.getPlayzone();
        if (playzone != null) {
            playzoneRenderer.renderPlayzone(playzone, x, y, z, partialTicks);
        }
    }

    private void renderDebugText(FontRenderer font, String text) {
        ImageUtil.drawShape(4, 4 + debugLine * 10, 6 + font.getStringWidth(text), 5 + debugLine * 10 + font.FONT_HEIGHT, 0x66444444);
        font.drawString(text, 5, 5 + debugLine++ * 10, 0xFFFFFF);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
