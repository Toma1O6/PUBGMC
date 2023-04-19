package dev.toma.pubgmc.client.games;

import dev.toma.pubgmc.api.client.game.GameRenderer;
import dev.toma.pubgmc.api.client.util.GameAreaRenderer;
import dev.toma.pubgmc.api.game.area.GameArea;
import dev.toma.pubgmc.common.games.game.battleroyale.BattleRoyaleGame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public final class BattleRoyaleGameRenderer implements GameRenderer<BattleRoyaleGame> {

    @Override
    public boolean renderHudOverlay(EntityPlayer player, BattleRoyaleGame game, ScaledResolution resolution, RenderGameOverlayEvent.ElementType elementType, float partialTicks) {
        if (elementType == RenderGameOverlayEvent.ElementType.ALL) {

        }
        return false;
    }

    @Override
    public void renderInWorld(World world, BattleRoyaleGame game, double x, double y, double z, float partialTicks) {
        GameArea area = game.getZone();
        GameAreaRenderer.renderGameArea(area, 0x660033FF, x, y, z, partialTicks);
    }
}
