package dev.toma.pubgmc.common.ai;

import dev.toma.pubgmc.api.item.AIHealingItem;
import dev.toma.pubgmc.util.helper.InventoryHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import java.util.function.Function;

public class EntityAIHeal<T extends EntityLiving> extends EntityAIBase {

    private final T entity;
    private final float healthThreshold;
    private final Function<T, IInventory> invProvider;

    private ItemStack oldMainhandItem = ItemStack.EMPTY;
    private int timeRemaining;
    private boolean completed;

    public EntityAIHeal(T entity, float healthThreshold, Function<T, IInventory> invProvider) {
        this.entity = entity;
        this.healthThreshold = healthThreshold;
        this.invProvider = invProvider;
    }

    @Override
    public boolean shouldExecute() {
        float health = entity.getHealth();
        IInventory inventory = invProvider.apply(entity);
        return entity.getAttackTarget() == null && health <= healthThreshold && !InventoryHelper.findHealingItemForAi(inventory).isEmpty();
    }

    @Override
    public boolean shouldContinueExecuting() {
        return entity.getAttackTarget() == null && !completed;
    }

    @Override
    public void startExecuting() {
        IInventory inventory = invProvider.apply(entity);
        ItemStack stack = InventoryHelper.findHealingItemForAi(inventory);
        if (!stack.isEmpty() && stack.getItem() instanceof AIHealingItem) {
            AIHealingItem healingItem = (AIHealingItem) stack.getItem();
            ItemStack mainHand = entity.getHeldItemMainhand();
            oldMainhandItem = entity.getHeldItemMainhand().copy();
            if (!oldMainhandItem.isEmpty()) {
                InventoryHelper.store(inventory, mainHand.copy());
            }
            oldMainhandItem = mainHand.copy();
            entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, stack.copy());
            stack.setCount(0);
            timeRemaining = healingItem.getUseTime();
        }
    }

    @Override
    public void resetTask() {
        IInventory inventory = invProvider.apply(entity);
        ItemStack prev = ItemStack.EMPTY;
        if (!oldMainhandItem.isEmpty()) {
            prev = InventoryHelper.findExact(oldMainhandItem, inventory);
        }
        ItemStack held = entity.getHeldItemMainhand();
        if (!held.isEmpty()) {
            InventoryHelper.store(inventory, held.copy());
        }
        entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, prev.copy());
        prev.setCount(0);
        completed = false;
        oldMainhandItem = ItemStack.EMPTY;
    }

    @Override
    public void updateTask() {
        entity.getNavigator().clearPath();
        if (--timeRemaining <= 0) {
            ItemStack stack = entity.getHeldItemMainhand();
            if (stack.getItem() instanceof AIHealingItem) {
                float healthGain = ((AIHealingItem) stack.getItem()).getAIHealAmount();
                entity.heal(healthGain);
                stack.shrink(1);
            }
            completed = true;
        }
    }
}
