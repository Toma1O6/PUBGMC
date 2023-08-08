package dev.toma.pubgmc.common.items.map;

import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.game.map.GameMapInstance;
import dev.toma.pubgmc.api.game.map.GameMapPoint;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MapPointRemovalItem extends MapPointItem {

    public MapPointRemovalItem(String name) {
        super(name);
    }

    @Override
    public EnumActionResult handlePoiClick(PointClickContext context) {
        if (context.isServerCall()) {
            GameMapInstance map = context.getMap();
            GameMapPoint point = context.getPoint();
            map.deletePoiAt(point.getPointPosition());
            context.getData().sendGameDataToClients();
        }
        return EnumActionResult.SUCCESS;
    }

    @Override
    public EnumActionResult handlePoiCreation(GameData data, World world, BlockPos pos, EntityPlayer player, EnumHand hand, GameMapInstance map) {
        return EnumActionResult.PASS;
    }
}
