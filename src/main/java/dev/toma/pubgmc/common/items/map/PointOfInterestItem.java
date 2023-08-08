package dev.toma.pubgmc.common.items.map;

import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.game.map.GameMapInstance;
import dev.toma.pubgmc.common.games.map.PointOfInterestPoint;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PointOfInterestItem extends MapPointItem {

    public PointOfInterestItem(String name) {
        super(name);
    }

    @Override
    public EnumActionResult handlePoiCreation(GameData data, World world, BlockPos pos, EntityPlayer player, EnumHand hand, GameMapInstance map) {
        PointOfInterestPoint point = new PointOfInterestPoint(pos);
        map.setMapPoint(pos, point);
        data.sendGameDataToClients();
        return EnumActionResult.SUCCESS;
    }

    @Override
    public EnumActionResult handlePoiClick(PointClickContext context) {
        return EnumActionResult.PASS;
    }
}
