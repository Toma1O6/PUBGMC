package dev.toma.pubgmc.client.games;

import dev.toma.pubgmc.api.client.game.GameRenderer;
import dev.toma.pubgmc.api.client.util.PlayzoneRenderer;
import dev.toma.pubgmc.api.client.util.TeamPanelRenderer;
import dev.toma.pubgmc.common.games.game.battleroyale.BattleRoyaleGame;
import dev.toma.pubgmc.common.games.playzone.DynamicPlayzone;
import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.config.client.CFG2DCoords;
import dev.toma.pubgmc.config.client.game.BattleRoyaleOverlays;
import dev.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public final class BattleRoyaleGameRenderer implements GameRenderer<BattleRoyaleGame> {

    private static final ITextComponent ZONE_LABEL = new TextComponentTranslation("pubgmc.playzone.label");
    private static final ITextComponent PLAYERS = new TextComponentTranslation("pubgmc.game.label.alive_players");

    private final TeamPanelRenderer teamPanelRenderer;
    private final PlayzoneRenderer<DynamicPlayzone> playzoneRenderer;

    public BattleRoyaleGameRenderer() {
        this.teamPanelRenderer = new TeamPanelRenderer();
        this.teamPanelRenderer.setRenderSoloTeams(false);
        this.teamPanelRenderer.setOffsetsY(false);
        this.teamPanelRenderer.shouldLoadRealAIHealth(true);
        this.playzoneRenderer = new PlayzoneRenderer<>();
        this.playzoneRenderer.setColor(ConfigPMC.client.overlays.battleRoyaleOverlays.playzoneColor.getColor());
    }

    @Override
    public boolean renderHudOverlay(EntityPlayer player, BattleRoyaleGame game, ScaledResolution resolution, RenderGameOverlayEvent.ElementType elementType, float partialTicks) {
        if (elementType == RenderGameOverlayEvent.ElementType.ALL) {
            Minecraft minecraft = Minecraft.getMinecraft();
            FontRenderer font = minecraft.fontRenderer;
            BattleRoyaleOverlays overlays = ConfigPMC.client.overlays.battleRoyaleOverlays;
            if (overlays.showTeamInformation.get()) {
                CFG2DCoords pos = overlays.teamPanelPosition;
                teamPanelRenderer.render(minecraft, game, pos.getX(), pos.getY() + resolution.getScaledHeight() - 50);
            }
            if (game.isStarted()) {
                CFG2DCoords infoPos = overlays.gameInfoPanelPosition;
                String zoneText = ZONE_LABEL.getFormattedText();
                int zoneTextWidth = font.getStringWidth(zoneText);
                font.drawStringWithShadow(zoneText, infoPos.getX() + 5, infoPos.getY() + 5, 0xFFFFFF);
                boolean shrinking = game.isZoneShrinking();
                int timeToShrinkStart = game.getRemainingTimeBeforeShrinking();
                if (shrinking) {
                    int timeToShrinkFinish = game.getRemainingTimeBeforeShrinkComplete();
                    font.drawStringWithShadow(" " + PUBGMCUtil.formatTime(timeToShrinkFinish), infoPos.getX() + 5 + zoneTextWidth, infoPos.getY() + 5, 0xCC0000);
                } else if (timeToShrinkStart >= 0) {
                    DynamicPlayzone playzone = game.getPlayzone();
                    DynamicPlayzone.ResizeTarget target = playzone.getTarget();
                    boolean isWithin = target != null ? target.isWithinTargetArea(player.posX, player.posZ) : playzone.isWithin(player);
                    int textColor = !isWithin ? 0xEEEE00 : 0x00CC00;
                    font.drawStringWithShadow(" " + PUBGMCUtil.formatTime(timeToShrinkStart), infoPos.getX() + 5 + zoneTextWidth, infoPos.getY() + 5, textColor);
                } else {
                    font.drawStringWithShadow(" -", infoPos.getX() + 5 + zoneTextWidth, infoPos.getY() + 5, 0x00CC00);
                }
                int playerCount = game.getAlivePlayerCount();
                String playersText = PLAYERS.getFormattedText();
                font.drawStringWithShadow(playersText, infoPos.getX() + 5, infoPos.getY() + 16, 0xFFFFFF);
                font.drawStringWithShadow(" " + playerCount, infoPos.getX() + 5 + font.getStringWidth(playersText), infoPos.getY() + 16, 0x00FFFF);
                CFG2DCoords dmPos = overlays.deathMessagesPosition;
                game.getDeathMessageContainer().render(font, game, dmPos.getX() + 10, dmPos.getY() + 35, 11);
            }
        }
        return false;
    }

    @Override
    public void renderInWorld(World world, BattleRoyaleGame game, double x, double y, double z, float partialTicks) {
        if (!game.isStarted())
            return;
        DynamicPlayzone playzone = game.getPlayzone();
        playzoneRenderer.renderPlayzone(playzone, x, y, z, partialTicks);
    }
}
