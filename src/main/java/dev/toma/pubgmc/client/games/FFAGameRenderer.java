package dev.toma.pubgmc.client.games;

import dev.toma.pubgmc.api.client.game.GameRenderer;
import dev.toma.pubgmc.api.client.util.PlayzoneRenderer;
import dev.toma.pubgmc.api.client.util.ScoreboardRenderer;
import dev.toma.pubgmc.api.game.playzone.Playzone;
import dev.toma.pubgmc.api.game.util.DeathMessage;
import dev.toma.pubgmc.api.game.util.PlayerPropertyHolder;
import dev.toma.pubgmc.api.properties.SharedProperties;
import dev.toma.pubgmc.common.games.game.ffa.FFAGame;
import dev.toma.pubgmc.util.PUBGMCUtil;
import dev.toma.pubgmc.util.helper.ImageUtil;
import dev.toma.pubgmc.util.helper.TextComponentHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import java.util.Comparator;

public class FFAGameRenderer implements GameRenderer<FFAGame> {

    private final ScoreboardRenderer<FFAGame> scoreboardRenderer;
    private final PlayzoneRenderer<Playzone> playzoneRenderer;

    public FFAGameRenderer() {
        this.scoreboardRenderer = ScoreboardRenderer.<FFAGame>create()
                .withSorting(SharedProperties.KILLS, Comparator.<Integer>comparingInt(val -> val).reversed(), 0)
                .addRenderableColumn(TextComponentHelper.NAME.getFormattedText(), PlayerPropertyHolder::getUsername)
                .addRenderableColumn(TextComponentHelper.KILLS.getFormattedText(), (propertyHolder, uuid) -> String.valueOf(propertyHolder.getProperty(uuid, SharedProperties.KILLS, 0)), col -> {
                    col.setRightAlignment(true);
                    col.setTextColor(0xFF4444);
                })
                .build();
        this.scoreboardRenderer.setDrawGrid(true);
        this.scoreboardRenderer.setMyScoreAlwaysRendered(true);
        this.scoreboardRenderer.setDisplayLimit(20);
        this.playzoneRenderer = new PlayzoneRenderer<>();
        this.playzoneRenderer.setColor(0x660033FF);
    }

    @Override
    public boolean renderHudOverlay(EntityPlayer player, FFAGame game, ScaledResolution resolution, RenderGameOverlayEvent.ElementType elementType, float partialTicks) {
        if (!game.isStarted())
            return false;
        if (elementType == RenderGameOverlayEvent.ElementType.PLAYER_LIST) {
            PlayerPropertyHolder properties = game.getProperties();
            ImageUtil.drawShape(0, 0, resolution.getScaledWidth(), resolution.getScaledHeight(), 0x88 << 24);
            scoreboardRenderer.renderScoreboard(properties, game, resolution);
            return true;
        } else if (elementType == RenderGameOverlayEvent.ElementType.ALL) {
            FontRenderer font = Minecraft.getMinecraft().fontRenderer;

            int timeRemaining = game.getTimeRemaining();
            String timeText = PUBGMCUtil.formatTime(timeRemaining);
            int textWidth = font.getStringWidth(timeText);
            font.drawString(timeText, resolution.getScaledWidth() - textWidth - 5, 5, 0xFFFFFF, true);

            DeathMessage[] deathMessages = game.getDeathMessageHolder().getDeathMessages();
            for (int i = 0; i < deathMessages.length; i++) {
                DeathMessage deathMessage = deathMessages[i];
                DeathMessage.Type type = deathMessage.getType();
                if (type == null) {
                    type = DeathMessage.Type.getSoloType(deathMessage, player.world);
                    deathMessage.setType(type);
                }
                font.drawStringWithShadow(deathMessage.getWholeComponent().getFormattedText(), 10, 10 + i * 10, type.getColor());
            }
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        }
        return false;
    }

    @Override
    public void renderInWorld(World world, FFAGame game, double x, double y, double z, float partialTicks) {
        if (!game.isStarted())
            return;
        Playzone playzone = game.getPlayzone();
        playzoneRenderer.renderPlayzone(playzone, x, y, z, partialTicks);
    }
}
