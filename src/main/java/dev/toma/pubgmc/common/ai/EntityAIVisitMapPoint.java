package dev.toma.pubgmc.common.ai;

import dev.toma.pubgmc.api.capability.GameDataProvider;
import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.api.game.map.GameMapPoint;
import dev.toma.pubgmc.api.game.map.GameMapPointType;
import dev.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;

import java.util.*;
import java.util.stream.Collectors;

public class EntityAIVisitMapPoint<P extends GameMapPoint> extends EntityAIBase {

    private final EntityLiving livingEntity;
    private final GameMapPointType<P> pointType;
    private final double moveSpeed;

    private final Set<BlockPos> visitedPositions = new HashSet<>();
    private BlockPos target;
    private int delay = 10;

    public EntityAIVisitMapPoint(EntityLiving entity, GameMapPointType<P> pointType, double moveSpeed) {
        this.livingEntity = entity;
        this.pointType = pointType;
        this.moveSpeed = moveSpeed;

        setMutexBits(1);
    }

    @Override
    public boolean shouldExecute() {
        List<BlockPos> positions = getPositionsForVisiting();
        if (!positions.isEmpty()) {
            target = PUBGMCUtil.getClosestPosition(positions, livingEntity.getPosition());
            return target != null;
        }
        return false;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return target != null && !livingEntity.getNavigator().noPath();
    }

    @Override
    public void startExecuting() {
        livingEntity.getNavigator().tryMoveToXYZ(target.getX() + 0.5, target.getY(), target.getZ() + 0.5, moveSpeed);
        if (moveSpeed > 1) {
            livingEntity.setSprinting(true);
        }
    }

    @Override
    public void resetTask() {
        livingEntity.setSprinting(false);
    }

    @Override
    public void updateTask() {
        if (--delay <= 0) {
            delay = 10;
            livingEntity.getNavigator().tryMoveToXYZ(target.getX() + 0.5, target.getY(), target.getZ() + 0.5, moveSpeed);
            if (livingEntity.getDistanceSq(target) < 16) {
                visitedPositions.add(target);
                livingEntity.getNavigator().clearPath();
                target = null;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private List<BlockPos> getPositionsForVisiting() {
        return (List<BlockPos>) GameDataProvider.getGameData(livingEntity.world).map(data -> {
            String currentMap = data.getActiveGameMapName();
            if (currentMap == null) {
                return Collections.emptyList();
            }
            GameMap map = data.getGameMap(currentMap);
            if (map == null) {
                return Collections.emptyList();
            }
            return map.getPoints(pointType).stream().map(GameMapPoint::getPointPosition)
                    .filter(pos -> !visitedPositions.contains(pos))
                    .collect(Collectors.toList());
        }).orElse(Collections.emptyList());
    }
}
