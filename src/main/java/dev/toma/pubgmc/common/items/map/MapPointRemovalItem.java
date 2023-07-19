package dev.toma.pubgmc.common.items.map;

import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.api.game.map.GameMapPoint;
import net.minecraft.util.EnumActionResult;

import java.util.Map;

public class MapPointRemovalItem extends MapPointItem {

    public MapPointRemovalItem(String name) {
        super(name);
    }

    @Override
    public EnumActionResult processPoints(PointClickContext context) {
        if (context.isServerCall()) {
            for (Map.Entry<GameMap, GameMapPoint> entry : context.getMapPoints().entrySet()) {
                GameMap map = entry.getKey();
                GameMapPoint point = entry.getValue();
                map.deletePoiAt(point.getPointPosition());
            }
            context.getData().sendGameDataToClients();
        }
        return EnumActionResult.SUCCESS;
    }
}
