package dev.toma.pubgmc.common.items.map;

import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.capability.GameDataProvider;
import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.api.game.map.GameMapPoint;
import dev.toma.pubgmc.common.items.PMCItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public abstract class MapPointItem extends PMCItem implements dev.toma.pubgmc.api.item.MapPointItem {

    public MapPointItem(String name) {
        super(name);
    }

    public abstract EnumActionResult processPoints(PointClickContext context);

    @Override
    public final EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return GameDataProvider.getGameData(worldIn).map(data -> {
            if (!player.isCreative())
                return EnumActionResult.FAIL;
            String activeMap = data.getActiveGameMapName();
            Map<GameMap, GameMapPoint> viablePoiList = new HashMap<>();
            BlockPos interactionPos = pos.up();
            for (Map.Entry<String, GameMap> entry : data.getRegisteredGameMaps().entrySet()) {
                if (entry.getKey().equals(activeMap)) {
                    continue;
                }
                GameMap map = entry.getValue();
                map.getPointAt(interactionPos).ifPresent(point -> viablePoiList.put(map, point));
            }
            if (!viablePoiList.isEmpty()) {
                return processPoints(new PointClickContext(viablePoiList, data, player, worldIn, interactionPos, hand, facing, new Vec3d(hitX, hitY, hitZ)));
            }
            return EnumActionResult.PASS;
        }).orElse(EnumActionResult.PASS);
    }

    public static final class PointClickContext {

        private final Map<GameMap, GameMapPoint> mapPoints;
        private final GameData data;
        private final EntityPlayer player;
        private final World world;
        private final BlockPos pos;
        private final EnumHand hand;
        private final EnumFacing facing;
        private final Vec3d hitVec;

        PointClickContext(Map<GameMap, GameMapPoint> map, GameData data, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, Vec3d hitVec) {
            this.mapPoints = map;
            this.data = data;
            this.player = player;
            this.world = world;
            this.pos = pos;
            this.hand = hand;
            this.facing = facing;
            this.hitVec = hitVec;
        }

        public Map<GameMap, GameMapPoint> getMapPoints() {
            return mapPoints;
        }

        public GameData getData() {
            return data;
        }

        public EntityPlayer getPlayer() {
            return player;
        }

        public World getWorld() {
            return world;
        }

        public BlockPos getPos() {
            return pos;
        }

        public EnumHand getHand() {
            return hand;
        }

        public EnumFacing getFacing() {
            return facing;
        }

        public Vec3d getHitVec() {
            return hitVec;
        }

        public boolean isServerCall() {
            return !world.isRemote;
        }
    }
}
