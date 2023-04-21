package dev.toma.pubgmc.client.games;

import dev.toma.pubgmc.api.client.game.GameRenderer;
import dev.toma.pubgmc.api.client.util.GameAreaRenderer;
import dev.toma.pubgmc.api.game.DeathMessage;
import dev.toma.pubgmc.api.game.area.GameArea;
import dev.toma.pubgmc.common.games.game.battleroyale.BattleRoyaleGame;
import dev.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public final class BattleRoyaleGameRenderer implements GameRenderer<BattleRoyaleGame> {

    private static final ITextComponent ZONE_LABEL = new TextComponentTranslation("pubgmc.game_area.label");
    private static final ITextComponent SHRINKING = new TextComponentTranslation("pubgmc.game_area.status.shrinking");

    @Override
    public boolean renderHudOverlay(EntityPlayer player, BattleRoyaleGame game, ScaledResolution resolution, RenderGameOverlayEvent.ElementType elementType, float partialTicks) {
        if (elementType == RenderGameOverlayEvent.ElementType.ALL) {
            Minecraft minecraft = Minecraft.getMinecraft();
            FontRenderer font = minecraft.fontRenderer;
            // TODO render player count
            String zoneText = ZONE_LABEL.getFormattedText();
            int zoneTextWidth = font.getStringWidth(zoneText);
            font.drawStringWithShadow(zoneText, 5, 5, 0xFFFFFF);
            boolean shrinking = game.isZoneShrinking();
            int timeToShrinkStart = game.getRemainingTimeBeforeShrinking();
            if (shrinking) {
                font.drawStringWithShadow(" " + SHRINKING.getFormattedText(), 5 + zoneTextWidth, 5, 0xAA0000);
            } else if (timeToShrinkStart >= 0) {
                font.drawStringWithShadow(" " + PUBGMCUtil.formatTime(timeToShrinkStart), 5 + zoneTextWidth, 5, 0x00CC00);
            } else {
                font.drawStringWithShadow(" -", 5 + zoneTextWidth, 5, 0x00CC00);
            }
            DeathMessage[] deathMessages = game.getDeathMessageContainer().getDeathMessages();
            for (int i = 0; i < deathMessages.length; i++) {
                DeathMessage deathMessage = deathMessages[i];
                DeathMessage.Type deathMessageType = deathMessage.getDeathMessageType();
                int messageColor = deathMessageType.getColor();
                font.drawStringWithShadow(deathMessage.getWholeComponent().getFormattedText(), 10, 30 + i * 11, messageColor);
            }
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        }
        return false;
    }

    @Override
    public void renderInWorld(World world, BattleRoyaleGame game, double x, double y, double z, float partialTicks) {
        GameArea area = game.getZone();
        GameAreaRenderer.renderGameArea(area, 0x660033FF, x, y, z, partialTicks);
    }
}
