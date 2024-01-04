package dev.toma.pubgmc.client.games;

import dev.toma.pubgmc.api.client.game.GameRenderer;
import dev.toma.pubgmc.api.client.util.PlayzoneRenderer;
import dev.toma.pubgmc.api.game.playzone.Playzone;
import dev.toma.pubgmc.common.games.game.tournament.TournamentGame;
import dev.toma.pubgmc.common.games.game.tournament.TournamentGameConfiguration;
import dev.toma.pubgmc.common.games.game.tournament.TournamentMatch;
import dev.toma.pubgmc.common.games.util.TeamType;
import dev.toma.pubgmc.util.helper.ImageUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class TournamentGameRenderer implements GameRenderer<TournamentGame> {

    private final PlayzoneRenderer<Playzone> playzoneRenderer;

    private int debugLine;

    public TournamentGameRenderer() {
        this.playzoneRenderer = new PlayzoneRenderer<>();
        this.playzoneRenderer.setColor(0x44FFFFFF); // TODO config
    }

    public void renderHudText(EntityPlayer player, TournamentGame game, ScaledResolution resolution, float partialTicks) {
        this.debugLine = 0;
        FontRenderer font = Minecraft.getMinecraft().fontRenderer;
        renderDebugText(font, "GameState: " + game.getGameState());
        renderDebugText(font, "Timer: " + game.getEventTimer());
        TournamentMatch match = game.getActiveMatch();
        if (match != null) {
            TournamentGameConfiguration.MatchConfiguration configuration = game.getMatchConfig();
            renderDebugText(font, "Round: " + match.getRoundNumber() + "/" + configuration.matchCount);
            renderDebugText(font, "Match type: " + match.getMatchType());
            renderDebugText(font, "Match state: " + match.getMatchStatus());
            renderDebugText(font, "Match history: " + match.getMatchHistory());
            renderDebugText(font, "Team Red: " + match.getTeam(TeamType.RED));
            renderDebugText(font, "Team Blue: " + match.getTeam(TeamType.BLUE));
        }
    }

    @Override
    public boolean renderHudOverlay(EntityPlayer player, TournamentGame game, ScaledResolution resolution, RenderGameOverlayEvent.ElementType elementType, float partialTicks) {
        if (!game.isStarted())
            return false;
        if (elementType == RenderGameOverlayEvent.ElementType.ALL) {
            renderHudText(player, game, resolution, partialTicks);
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
