package dev.toma.pubgmc.client.games;

import dev.toma.pubgmc.api.client.game.GameRenderer;
import dev.toma.pubgmc.api.client.util.PlayzoneRenderer;
import dev.toma.pubgmc.api.client.util.TeamPanelRenderer;
import dev.toma.pubgmc.common.games.game.battleroyale.BattleRoyaleGame;
import dev.toma.pubgmc.common.games.playzone.DynamicPlayzone;
import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.config.client.CFG2DRatio;
import dev.toma.pubgmc.config.client.game.BattleRoyaleOverlays;
import dev.toma.pubgmc.util.PUBGMCUtil;
import dev.toma.pubgmc.util.helper.ImageUtil;
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
    public boolean renderHudOverlay(EntityPlayer player, BattleRoyaleGame game, ScaledResolution res, RenderGameOverlayEvent.ElementType elementType, float partialTicks) {
        Minecraft minecraft = Minecraft.getMinecraft();
        FontRenderer font = minecraft.fontRenderer;
        BattleRoyaleOverlays overlays = ConfigPMC.client.overlays.battleRoyaleOverlays;

        int screenWidth = res.getScaledWidth();
        int screenHeight = res.getScaledHeight();
        float centerX = screenWidth / 2f;
        float halfWidth = centerX;
        float centerY = screenHeight / 2f;
        float halfHeight = centerY;

        // Team Panel
        if (overlays.showTeamInformation.get()) {
            CFG2DRatio teamInfoPos = overlays.teamPanelPosition;
            float teamInfoX = centerX + halfWidth * (teamInfoPos.getX() - 0.95f); // default x -0.95 (←)
            float teamInfoY = centerY + halfHeight * (teamInfoPos.getY() + 0.8f); // default y 0.8 (↓)
            teamPanelRenderer.render(minecraft, game, teamInfoX, teamInfoY);
        }
        if (!game.isStarted()) {
            return false;
        }

        // Death Messages
        CFG2DRatio deathMessagePos = overlays.deathMessagesPos;
        float deathMessageX = centerX + halfWidth * (deathMessagePos.getX() + 0.4f); // default x +0.4 (→)
        float deathMessageY = centerY + halfHeight * (deathMessagePos.getY() - 0.95f); // default y -0.95 (↑)
        game.getDeathMessageContainer().render(font, game, deathMessageX, deathMessageY, 8);


        // Game Info
        CFG2DRatio gameInfoPos = overlays.gameInfoPanelPos;
        float gameInfoX = centerX + halfWidth * (gameInfoPos.getX() + 0.75f); // default x +0.75 (→)
        float gameInfoY = centerY + halfHeight * (gameInfoPos.getY() - 0.1f); // default y -0.1 (↑）

        // background
        float gameInfoBarLength = halfWidth * 0.23f;

        // {Zone}:
        String zoneText = ZONE_LABEL.getFormattedText();
        font.drawStringWithShadow(zoneText, gameInfoX, gameInfoY, 0xFFFFFF);
        int zoneTextWidth = font.getStringWidth(zoneText);
        // Zone: {isShrinking}
        boolean isShrinking = game.isZoneShrinking();
        int shrinkDelayRemain = game.getShrinkDelayRemain();
        if (isShrinking) { // Zone: {remaining shrinking time}
            int shrinkTimeTotal = game.getShrinkTimeTotal();
            int shrinkTimeRemain = game.getShrinkTimeRemain();
            float percentage = 1 - (float)shrinkTimeRemain / shrinkTimeTotal;
            int color = ConfigPMC.client.overlays.battleRoyaleOverlays.playzoneColor.getColor();
            int r = (color >> 16) & 0xFF;
            int g = (color >> 8) & 0xFF;
            int b = (color) & 0xFF;
            font.drawStringWithShadow(" " + PUBGMCUtil.formatTime(shrinkTimeRemain), gameInfoX + zoneTextWidth, gameInfoY, 0xCC0000);
            ImageUtil.drawShape(gameInfoX, gameInfoY + 20, gameInfoX + gameInfoBarLength, gameInfoY + 23, 0.197f, 0.197f, 0.197f, 0.1f); // #323232 50,50,50
            ImageUtil.drawShape(gameInfoX + gameInfoBarLength * percentage, gameInfoY + 20, gameInfoX + gameInfoBarLength, gameInfoY + 23, 0.95f, 0.95f, 0.95f, 0.8f); // #f2f2f2 242,242,242
            ImageUtil.drawShape(gameInfoX, gameInfoY + 20, gameInfoX + gameInfoBarLength * percentage, gameInfoY + 23, r/255.0f, g/255.0f, b/255.0f, 0.8f);
        } else if (shrinkDelayRemain >= 0) { // Zone: {time before shrink}
            int shrinkDelayTotal = game.getShrinkDelayTotal();
            float percentage = 1 - (float)shrinkDelayRemain / shrinkDelayTotal;
            DynamicPlayzone playzone = game.getPlayzone();
            DynamicPlayzone.ResizeTarget target = playzone.getTarget();
            boolean isWithin = target != null ? target.isWithinTargetArea(player) : playzone.isWithin(player);
            int textColor = !isWithin ? 0xEEEE00 : 0x00CC00;
            font.drawStringWithShadow(" " + PUBGMCUtil.formatTime(shrinkDelayRemain), gameInfoX + zoneTextWidth, gameInfoY, textColor);
            ImageUtil.drawShape(gameInfoX, gameInfoY + 20, gameInfoX + gameInfoBarLength, gameInfoY + 23, 0.197f, 0.197f, 0.197f, 0.3f); // #323232 50,50,50
            ImageUtil.drawShape(gameInfoX, gameInfoY + 20, gameInfoX + gameInfoBarLength * percentage, gameInfoY + 23, 0.95f, 0.95f, 0.95f, 0.8f); // #f2f2f2 242,242,242
        } else { // Zone: {initDelay}
            int initDelayTotal = game.getInitDelayTotal();
            int initDelayRemain = game.getInitDelayRemain();
            float percentage = 1 - (float) initDelayRemain / initDelayTotal;
            font.drawStringWithShadow(" " + PUBGMCUtil.formatTime(initDelayRemain), gameInfoX + zoneTextWidth, gameInfoY, 0x888888);
            ImageUtil.drawShape(gameInfoX, gameInfoY + 20, gameInfoX + gameInfoBarLength * percentage, gameInfoY + 23, 0.197f, 0.197f, 0.197f, 0.3f); // #323232 50,50,50
        }
        // {Alive}: {total}
        int playerCount = game.getAlivePlayerCount();
        String playersText = PLAYERS.getFormattedText();
        font.drawStringWithShadow(playersText, gameInfoX + 1, gameInfoY + 11, 0xFFFFFF);
        font.drawStringWithShadow(" " + playerCount, gameInfoX + 1 + font.getStringWidth(playersText), gameInfoY + 11, 0x00FFFF);

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
