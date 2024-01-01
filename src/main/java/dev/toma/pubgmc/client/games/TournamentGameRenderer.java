package dev.toma.pubgmc.client.games;

import dev.toma.pubgmc.api.client.game.GameRenderer;
import dev.toma.pubgmc.api.client.util.PlayzoneRenderer;
import dev.toma.pubgmc.api.game.playzone.Playzone;
import dev.toma.pubgmc.common.games.game.tournament.TournamentGame;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class TournamentGameRenderer implements GameRenderer<TournamentGame> {

    private final PlayzoneRenderer<Playzone> playzoneRenderer;

    public TournamentGameRenderer() {
        this.playzoneRenderer = new PlayzoneRenderer<>();
        this.playzoneRenderer.setColor(0x44FFFFFF); // TODO config
    }

    public void renderHudText(EntityPlayer player, TournamentGame game, ScaledResolution resolution, float partialTicks) {
        FontRenderer font = Minecraft.getMinecraft().fontRenderer;
        font.drawString("GameState: " + game.getGameState(), 5, 5, 0xFFFFFF);
        font.drawString("Timer: " + game.getEventTimer(), 5, 17, 0xFFFFFF);
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
}
