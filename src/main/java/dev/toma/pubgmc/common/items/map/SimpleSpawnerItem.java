package dev.toma.pubgmc.common.items.map;

import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.game.map.GameMapInstance;
import dev.toma.pubgmc.common.games.map.SpawnerPoint;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SimpleSpawnerItem extends MapPointItem {

    public SimpleSpawnerItem(String name) {
        super(name);
    }

    @Override
    public EnumActionResult handlePoiCreation(GameData data, World world, BlockPos pos, EntityPlayer player, EnumHand hand, GameMapInstance map) {
        SpawnerPoint point = new SpawnerPoint(pos);
        map.setMapPoint(pos, point);
        data.sendGameDataToClients();
        return EnumActionResult.SUCCESS;
    }

    @Override
    public EnumActionResult handlePoiClick(PointClickContext context) {
        return EnumActionResult.PASS;
    }
}
