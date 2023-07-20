package dev.toma.pubgmc.client.games;

import dev.toma.pubgmc.api.client.game.GameRenderer;
import dev.toma.pubgmc.api.client.util.PlayzoneRenderer;
import dev.toma.pubgmc.api.client.util.ScoreboardRenderer;
import dev.toma.pubgmc.api.game.playzone.Playzone;
import dev.toma.pubgmc.api.game.util.PlayerPropertyHolder;
import dev.toma.pubgmc.api.properties.SharedProperties;
import dev.toma.pubgmc.common.games.game.domination.DominationGame;
import dev.toma.pubgmc.common.games.game.domination.DominationTeamManager;
import dev.toma.pubgmc.common.games.util.TeamType;
import dev.toma.pubgmc.util.PUBGMCUtil;
import dev.toma.pubgmc.util.helper.ImageUtil;
import dev.toma.pubgmc.util.helper.TextComponentHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import java.util.Comparator;

public class DominationGameRenderer implements GameRenderer<DominationGame> {

    private final ScoreboardRenderer<DominationGame> scoreboardRenderer;
    private final PlayzoneRenderer<Playzone> playzoneRenderer;

    public DominationGameRenderer() {
        this.scoreboardRenderer = ScoreboardRenderer.<DominationGame>create()
                .withSorting(SharedProperties.SCORE, Comparator.comparing(Integer::intValue), 0)
                .addRenderableColumn(TextComponentHelper.NAME.getFormattedText(), PlayerPropertyHolder::getUsername, col -> {
                    col.setTextColor((game, uuid) -> {
                        TeamType type = ((DominationTeamManager) game.getTeamManager()).getTeamType(uuid);
                        return type != null ? type.getColor() : 0xFFFFFF;
                    });
                })
                .addRenderableColumn(TextComponentHelper.SCORE.getFormattedText(), (propertyHolder, uuid) -> String.valueOf(propertyHolder.getProperty(uuid, SharedProperties.SCORE, 0)), col -> {
                    col.setRightAlignment(true);
                    col.setTextColor(0xFFFF00);
                })
                .addRenderableColumn(TextComponentHelper.KILLS.getFormattedText(), (propertyHolder, uuid) -> String.valueOf(propertyHolder.getProperty(uuid, SharedProperties.KILLS, 0)), col -> {
                    col.setRightAlignment(true);
                    col.setTextColor(0xFF0000);
                })
                .addRenderableColumn(TextComponentHelper.DEATHS.getFormattedText(), (propertyHolder, uuid) -> String.valueOf(propertyHolder.getProperty(uuid, SharedProperties.DEATHS, 0)), col -> {
                    col.setRightAlignment(true);
                    col.setTextColor(0xFF0000);
                })
                .addRenderableColumn(TextComponentHelper.CAPTURES.getFormattedText(), (propertyHolder, uuid) -> String.valueOf(propertyHolder.getProperty(uuid, SharedProperties.CAPTURES, 0)), col -> {
                    col.setRightAlignment(true);
                    col.setTextColor(0x00AAFF);
                })
                .build();
        this.scoreboardRenderer.setDrawGrid(true);
        this.scoreboardRenderer.setDisplayLimit(32);
        this.playzoneRenderer = new PlayzoneRenderer<>();
        this.playzoneRenderer.setColor(0x4439FF2B);
    }

    @Override
    public boolean renderHudOverlay(EntityPlayer player, DominationGame game, ScaledResolution resolution, RenderGameOverlayEvent.ElementType elementType, float partialTicks) {
        if (elementType == RenderGameOverlayEvent.ElementType.ALL) {
            if (!game.isStarted())
                return false;
            renderHudOverlay(game, resolution);
            return false;
        } else if (elementType == RenderGameOverlayEvent.ElementType.PLAYER_LIST) {
            renderTabOverlay(game, resolution);
            return true;
        }
        return false;
    }

    @Override
    public void renderInWorld(World world, DominationGame game, double x, double y, double z, float partialTicks) {
        if (!game.isStarted())
            return;
        playzoneRenderer.renderPlayzone(game.getPlayzone(), x, y, z, partialTicks);
    }

    private void renderTabOverlay(DominationGame game, ScaledResolution resolution) {
        int width = resolution.getScaledWidth();
        int height = resolution.getScaledHeight();
        ImageUtil.drawShape(0, 0, width, height, 0x88 << 24);
        scoreboardRenderer.renderScoreboard(game.getProperties(), game, resolution);
    }

    private void renderHudOverlay(DominationGame game, ScaledResolution resolution) {
        int infoWidth = 120;
        int infoHeight = 16;
        int halfX = infoWidth / 2;
        float left = (resolution.getScaledWidth() - infoWidth) / 2.0F;
        float top = resolution.getScaledHeight() - 50 - infoHeight;
        ImageUtil.drawShape(left, top, left + infoWidth, top + infoHeight, 0x66 << 24);
        int totalTickets = game.getConfiguration().totalTicketCount;
        int redTickets = game.getRedTicketCount();
        int blueTickets = game.getBlueTicketCount();
        float red = redTickets / (float) totalTickets;
        float blue = blueTickets / (float) totalTickets;
        ImageUtil.drawShape(left + halfX - 0.5F, top + 2, left + halfX + 0.5F, top + infoHeight - 2, 0x66AAAAAA);
        float rMin = left + 2;
        float rMax = left + halfX - 2;
        float rDiff = rMax - rMin;
        ImageUtil.drawGradient(rMin, top + infoHeight - 5, rMin + rDiff * red, top + infoHeight - 2, 0xFFFF0000, 0xFFAA0000);
        float bMin = left + halfX + 2;
        float bMax = left + infoWidth - 2;
        float bDiff = bMin - bMax;
        ImageUtil.drawGradient(bMin - (1.0F - blue) * bDiff, top + infoHeight - 5, bMax, top + infoHeight - 2, 0xFF0000FF, 0xFF0000AA);

        FontRenderer font = Minecraft.getMinecraft().fontRenderer;
        font.drawStringWithShadow(TeamType.RED.getTitle().getFormattedText(), left + 2, top + 2, 0xFFFFFF);
        String blueTitle = TeamType.BLUE.getTitle().getFormattedText();
        font.drawStringWithShadow(blueTitle, left + infoWidth - 2 - font.getStringWidth(blueTitle), top + 2, 0xFFFFFF);

        int timeRemaining = game.getTimeRemaining();
        String timeText = PUBGMCUtil.formatTime(timeRemaining);
        int textWidth = font.getStringWidth(timeText);
        font.drawString(timeText, resolution.getScaledWidth() - textWidth - 5, 5, 0xFFFFFF, true);
    }
}
