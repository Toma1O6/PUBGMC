package dev.toma.pubgmc.common.entity.bot.ai;

import dev.toma.pubgmc.api.game.loot.LootableContainer;
import dev.toma.pubgmc.common.entity.bot.EntityAIPlayer;
import dev.toma.pubgmc.util.helper.GameHelper;
import dev.toma.pubgmc.util.helper.InventorySearchHelper;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EntityAISearchLoot extends EntityAIBase {

    private final EntityAIPlayer aiPlayer;
    private final int chance;
    private final Set<BlockPos> checkedLootSpawners = new HashSet<>();

    @Nullable
    private LootableContainer lootable;

    public EntityAISearchLoot(EntityAIPlayer entityAIPlayer, int chance) {
        this.aiPlayer = entityAIPlayer;
        this.chance = Math.max(1, chance);
        this.setMutexBits(1);
    }

    @Override
    public boolean isInterruptible() {
        return true;
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
        return aiPlayer.getHealth() < aiPlayer.getMaxHealth() && InventorySearchHelper.findHealingItem(aiPlayer.getInventory()).isEmpty();
    }

    private LootableContainer findLootGeneratorWithinRange() {
        int dist = Integer.MAX_VALUE;
        LootableContainer closest = null;
        List<LootableContainer> containers = GameHelper.mergeTileEntitiesAndEntitiesByRule(aiPlayer.world, obj -> obj instanceof LootableContainer, obj -> (LootableContainer) obj)
                .collect(Collectors.toList());
        for (LootableContainer lootable : containers) {
            BlockPos pos = lootable.getWorldPosition();
            if (checkedLootSpawners.contains(pos)) {
                continue;
            }
            int distance = (int) pos.distanceSq(aiPlayer.getPosition());
            if (distance < dist) {
                dist = distance;
                closest =  lootable;
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
        return !this.aiPlayer.getNavigator().noPath();
    }

    @Override
    public void startExecuting() {
        if(lootable == null)
            return;
        BlockPos pos = lootable.getWorldPosition();
        this.aiPlayer.getNavigator().tryMoveToXYZ(pos.getX(), pos.getY() + 1, pos.getZ(), 1.0D);
    }

    @Override
    public void updateTask() {
        BlockPos pos = lootable.getWorldPosition();
        if(pos.distanceSq(this.aiPlayer.getPosition()) > 4) {
            if (aiPlayer.getNavigator().noPath()) {
                checkedLootSpawners.add(pos);
                return;
            }
            this.aiPlayer.getNavigator().tryMoveToXYZ(pos.getX(), pos.getY(), pos.getZ(), 1.0D);
        } else {
            aiPlayer.loot(lootable);
            checkedLootSpawners.add(pos);
            aiPlayer.getNavigator().clearPath();
        }
    }

    @Override
    public void resetTask() {
        lootable = null;
    }
}
