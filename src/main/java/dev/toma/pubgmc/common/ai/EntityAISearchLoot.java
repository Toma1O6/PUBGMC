package dev.toma.pubgmc.common.ai;

import dev.toma.pubgmc.api.game.loot.LootableContainer;
import dev.toma.pubgmc.api.game.playzone.Playzone;
import dev.toma.pubgmc.common.entity.EntityAIPlayer;
import dev.toma.pubgmc.util.helper.GameHelper;
import dev.toma.pubgmc.util.helper.InventoryHelper;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class EntityAISearchLoot extends EntityAIBase {

    private final EntityAIPlayer aiPlayer;
    private final int chance;
    private final float moveSpeed;
    private final Set<BlockPos> checkedLootSpawners = new HashSet<>();
    private final Supplier<Playzone> bounds;

    @Nullable
    private LootableContainer lootable;
    private boolean lootingCompleted;
    private int updateInterval;

    public EntityAISearchLoot(EntityAIPlayer entityAIPlayer, Supplier<Playzone> bounds, int chance, float moveSpeed) {
        this.aiPlayer = entityAIPlayer;
        this.bounds = bounds;
        this.chance = Math.max(1, chance);
        this.moveSpeed = moveSpeed;
        this.setMutexBits(1);
    }

    private boolean needsLoot() {
        ItemStack weapon = aiPlayer.getHeldItemMainhand();
        if (weapon.isEmpty()) {
            return true;
        }
        ItemStack helmet = aiPlayer.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        if (helmet.isEmpty()) {
            return true;
        }
        ItemStack armor = aiPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        if (armor.isEmpty()) {
            return true;
        }
        return aiPlayer.getHealth() < aiPlayer.getMaxHealth() && InventoryHelper.findHealingItemForAi(aiPlayer.getInventory()).isEmpty();
    }

    private LootableContainer findLootGeneratorWithinRange() {
        int dist = Integer.MAX_VALUE;
        LootableContainer closest = null;
        List<LootableContainer> containers = GameHelper.mergeTileEntitiesAndEntitiesByRule(aiPlayer.world, obj -> obj instanceof LootableContainer, obj -> (LootableContainer) obj)
                .collect(Collectors.toList());
        Playzone playzone = bounds.get();
        for (LootableContainer lootable : containers) {
            BlockPos pos = lootable.getWorldPosition();
            if (checkedLootSpawners.contains(pos) || (playzone != null && !playzone.isWithin(pos))) {
                continue;
            }
            int distance = (int) pos.distanceSq(aiPlayer.getPosition());
            if (distance < dist) {
                dist = distance;
                closest = lootable;
            }
        }
        return closest;
    }

    @Override
    public boolean shouldExecute() {
        int i = aiPlayer.getRNG().nextInt(chance);
        if (i == 0 && needsLoot()) {
            lootable = findLootGeneratorWithinRange();
            return lootable != null;
        }
        return false;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return !lootingCompleted || !this.aiPlayer.getNavigator().noPath();
    }

    @Override
    public void startExecuting() {
        if (lootable == null)
            return;
        BlockPos pos = lootable.getWorldPosition();
        this.aiPlayer.getNavigator().tryMoveToXYZ(pos.getX(), pos.getY() + 1, pos.getZ(), moveSpeed);
        if (moveSpeed > 1.0F) {
            this.aiPlayer.setSprinting(true);
        }
        updateInterval = 10;
    }

    @Override
    public void updateTask() {
        if (--updateInterval <= 0) {
            updateInterval = 10;
            BlockPos pos = lootable.getWorldPosition();
            if (pos.distanceSq(this.aiPlayer.getPosition()) > 4) {
                PathNavigate navigator = aiPlayer.getNavigator();
                navigator.tryMoveToXYZ(pos.getX(), pos.getY(), pos.getZ(), moveSpeed);
                if (navigator.noPath()) {
                    markSearched(pos);
                }
            } else {
                aiPlayer.loot(lootable);
                markSearched(pos);
            }
        }
    }

    @Override
    public void resetTask() {
        lootable = null;
        lootingCompleted = false;
        aiPlayer.setSprinting(false);
    }

    private void markSearched(BlockPos pos) {
        checkedLootSpawners.add(pos);
        aiPlayer.getNavigator().clearPath();
        lootingCompleted = true;
    }
}
