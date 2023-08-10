package dev.toma.pubgmc.client.games;

import dev.toma.pubgmc.api.client.game.GameRenderer;
import dev.toma.pubgmc.api.client.util.PlayzoneRenderer;
import dev.toma.pubgmc.api.client.util.ScoreboardRenderer;
import dev.toma.pubgmc.api.game.CaptureZones;
import dev.toma.pubgmc.api.game.playzone.Playzone;
import dev.toma.pubgmc.api.game.util.DeathMessage;
import dev.toma.pubgmc.api.game.util.PlayerPropertyHolder;
import dev.toma.pubgmc.api.properties.SharedProperties;
import dev.toma.pubgmc.client.renderer.poi.CaptureZoneRenderer;
import dev.toma.pubgmc.common.games.game.domination.DominationCapturePointManager;
import dev.toma.pubgmc.common.games.game.domination.DominationGame;
import dev.toma.pubgmc.common.games.game.domination.DominationTeamManager;
import dev.toma.pubgmc.common.games.util.TeamType;
import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.config.client.CFG2DCoords;
import dev.toma.pubgmc.config.client.game.DominationOverlays;
import dev.toma.pubgmc.util.PUBGMCUtil;
import dev.toma.pubgmc.util.helper.ImageUtil;
import dev.toma.pubgmc.util.helper.TextComponentHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import java.util.*;

public class DominationGameRenderer implements GameRenderer<DominationGame> {

    private final ScoreboardRenderer<DominationGame> scoreboardRenderer;
    private final PlayzoneRenderer<Playzone> playzoneRenderer;

    @SuppressWarnings("unchecked")
    public DominationGameRenderer() {
        this.scoreboardRenderer = ScoreboardRenderer.<DominationGame>create()
                .withSorting(SharedProperties.SCORE, Comparator.comparing(Integer::intValue).reversed(), (sorter, holder, game) -> {
                    ScoreboardRenderer.Sorter<UUID, DominationGame> sort = (ScoreboardRenderer.Sorter<UUID, DominationGame>) sorter;
                    List<UUID> red = holder.getSortedOwners(sort.getType(), sort.getComparator(), sort.getDefaultValue(), uuid -> isFromTeam(uuid, game, TeamType.RED));
                    List<UUID> blue = holder.getSortedOwners(sort.getType(), sort.getComparator(), sort.getDefaultValue(), uuid -> isFromTeam(uuid, game, TeamType.BLUE));
                    red.addAll(blue);
                    return red;
                }, 0)
                .addRenderableColumn(TextComponentHelper.NAME.getFormattedText(), PlayerPropertyHolder::getUsername, col -> col.setTextColor((game, uuid) -> {
                    TeamType type = game.getTeamManager().getTeamType(uuid);
                    return type != null ? type.getColor() : 0xFFFFFF;
                }))
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
        this.playzoneRenderer.setColor(ConfigPMC.client.overlays.dominationOverlays.playzoneColor.getColor());
    }

    @Override
    public boolean renderHudOverlay(EntityPlayer player, DominationGame game, ScaledResolution resolution, RenderGameOverlayEvent.ElementType elementType, float partialTicks) {
        if (elementType == RenderGameOverlayEvent.ElementType.ALL) {
            if (!game.isStarted())
                return false;
            renderHudOverlay(player, game, resolution);
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

    private void renderHudOverlay(EntityPlayer player, DominationGame game, ScaledResolution resolution) {
        DominationOverlays overlays = ConfigPMC.client.overlays.dominationOverlays;
        FontRenderer font = Minecraft.getMinecraft().fontRenderer;

        if (overlays.showTicketStatus.get()) {
            int infoWidth = 120;
            int infoHeight = 16;
            int halfX = infoWidth / 2;
            CFG2DCoords ticketsPos = overlays.ticketPanel;
            float left = ticketsPos.getX() + (resolution.getScaledWidth() - infoWidth) / 2.0F;
            float top = ticketsPos.getY() + resolution.getScaledHeight() - 50 - infoHeight;
            ImageUtil.drawShape(left, top, left + infoWidth, top + infoHeight, 0x66 << 24);
            int totalTickets = game.getConfiguration().totalTicketCount;
            int redTickets = Math.max(0, game.getRedTicketCount());
            int blueTickets = Math.max(0, game.getBlueTicketCount());
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

            float scale = 0.50F;
            GlStateManager.pushMatrix();
            {
                String displayedText = String.valueOf(redTickets);
                GlStateManager.translate(left + halfX - font.getStringWidth(displayedText) / 2f - 2, top + 5, 0);
                GlStateManager.scale(scale, scale, scale);
                font.drawString(displayedText, 0, 0, 0xAA3333, true);
            }
            GlStateManager.popMatrix();

            GlStateManager.pushMatrix();
            {
                String displayedText = String.valueOf(blueTickets);
                GlStateManager.translate(left + halfX + 2, top + 5, 0);
                GlStateManager.scale(scale, scale, scale);
                font.drawString(displayedText, 0, 0, 0x4444FF, true);
            }
            GlStateManager.popMatrix();

            font.drawStringWithShadow(TeamType.RED.getTitle().getFormattedText(), left + 2, top + 2, 0xFFFFFF);
            String blueTitle = TeamType.BLUE.getTitle().getFormattedText();
            font.drawStringWithShadow(blueTitle, left + infoWidth - 2 - font.getStringWidth(blueTitle), top + 2, 0xFFFFFF);
        }

        CFG2DCoords timePos = overlays.timePanel;
        int timeRemaining = game.getTimeRemaining();
        String timeText = PUBGMCUtil.formatTime(timeRemaining);
        int textWidth = font.getStringWidth(timeText);
        font.drawString(timeText, timePos.getX() + resolution.getScaledWidth() - textWidth - 5, timePos.getY() + 5, 0xFFFFFF, true);

        DeathMessage[] deathMessages = game.getDeathMessageHolder().getDeathMessages();
        CFG2DCoords dmPos = overlays.deathMessagesPanel;
        for (int i = 0; i < deathMessages.length; i++) {
            DeathMessage deathMessage = deathMessages[i];
            DeathMessage.Type type = deathMessage.getType();
            if (type == null) {
                type = DeathMessage.Type.getType(deathMessage, player.world, game.getTeamManager());
                deathMessage.setType(type);
            }
            font.drawStringWithShadow(deathMessage.getWholeComponent().getFormattedText(), dmPos.getX() + 10, dmPos.getY() + 10 + i * 10, type.getColor());
        }
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        if (overlays.showCaptureZoneStatus.get()) {
            renderPointInfo(player, game, resolution, overlays.captureZonePanel);
        }
    }

    private void renderPointInfo(EntityPlayer player, DominationGame game, ScaledResolution resolution, CFG2DCoords offsets) {
        DominationCapturePointManager manager = game.getPointManager();
        DominationCapturePointManager.Tracker tracker = manager.getEntityCaptureData(player);
        FontRenderer font = Minecraft.getMinecraft().fontRenderer;
        if (tracker == null) {
            int backgroundWidth = getPointOverlayWidth(manager, font);
            float left = offsets.getX() + (resolution.getScaledWidth() - backgroundWidth) / 2f;
            float top = offsets.getY();
            int offset = 5;
            List<DominationCapturePointManager.Tracker> list = new ArrayList<>(manager.getAllPointData());
            list.sort(Comparator.comparing(tr -> {
                String label = tr.getPoint().getLabel();
                return label != null ? label : "";
            }));
            for (DominationCapturePointManager.Tracker poiTracker : list) {
                String label = poiTracker.getPoint().getLabel();
                if (label != null && !label.isEmpty()) {
                    String text = label.toUpperCase(Locale.ROOT);
                    int width = font.getStringWidth(text) + 2;
                    CaptureZoneRenderer.drawPointLabel(poiTracker.getCaptureData(), font, text, left + offset, top + 4F, left + offset + width, top + 14F, 1.0F);
                    offset += width + 5;
                }
            }
        } else {
            CaptureZones.CaptureData data = tracker.getCaptureData();
            int width = 120;
            int height = 25;
            float left = offsets.getX() + (resolution.getScaledWidth() - width) / 2.0F;
            float top = offsets.getY() + 5.0F;
            ImageUtil.drawShape(left, top, left + width, top + height, 0x66 << 24);
            ImageUtil.drawShape(left + 2, top + height - 7, left + width - 2, top + height - 2, 0xFF << 24 | data.getBackground());
            if (data.getCaptureProgress() > 0.0F) {
                float right = left + (width - 2) * data.getCaptureProgress();
                ImageUtil.drawShape(left + 2, top + height - 7, right, top + height - 2, 0xFF << 24 | data.getForeground());
            }
            StringBuilder text = new StringBuilder();
            String label = tracker.getPoint().getLabel();
            if (label != null) {
                text.append(label.toUpperCase(Locale.ROOT)).append(": ");
            }
            text.append(data.getStatus().getTitle().getFormattedText());
            font.drawStringWithShadow(TextFormatting.UNDERLINE + text.toString(), left + 5, top + 5, 0xFFFFFF);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    private int getPointOverlayWidth(DominationCapturePointManager manager, FontRenderer font) {
        int total = 0;
        int count = 0;
        for (DominationCapturePointManager.Tracker tracker : manager.getAllPointData()) {
            String label = tracker.getPoint().getLabel();
            if (label != null && !label.isEmpty()) {
                total += font.getStringWidth(label.toUpperCase(Locale.ROOT));
                ++count;
            }
        }
        return 5 + total + count * 5;
    }

    private boolean isFromTeam(UUID uuid, DominationGame game, TeamType type) {
        DominationTeamManager manager = game.getTeamManager();
        return manager.getTeamType(uuid) == type;
    }
}
