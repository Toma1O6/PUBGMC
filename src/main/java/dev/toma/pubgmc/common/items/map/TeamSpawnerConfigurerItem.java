package dev.toma.pubgmc.common.items.map;

import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.api.game.map.GameMapPoint;
import dev.toma.pubgmc.client.games.screen.TeamSpawnerGui;
import dev.toma.pubgmc.common.games.map.GameMapPoints;
import dev.toma.pubgmc.common.games.map.TeamSpawnerPoint;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumActionResult;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TeamSpawnerConfigurerItem extends MapPointItem {

    public TeamSpawnerConfigurerItem(String name) {
        super(name);
    }

    @Override
    protected boolean filterPoint(GameMapPoint point) {
        return point.is(GameMapPoints.TEAM_SPAWNER);
    }

    @Override
    public EnumActionResult handlePoiClick(PointClickContext context) {
        GameMap map = context.getMap();
        GameMapPoint point = context.getPoint();
        if (this.filterPoint(point) && !context.isServerCall()) {
            openScreen(map, context.castPoint());
        }
        return EnumActionResult.FAIL;
    }

    @SideOnly(Side.CLIENT)
    private void openScreen(GameMap map, TeamSpawnerPoint point) {
        Minecraft.getMinecraft().displayGuiScreen(new TeamSpawnerGui(map, point));
    }
}
