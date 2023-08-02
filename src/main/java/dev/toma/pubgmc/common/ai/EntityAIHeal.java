package dev.toma.pubgmc.common.ai;

import dev.toma.pubgmc.api.item.HealingItem;
import dev.toma.pubgmc.util.helper.InventoryHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import java.util.function.Function;
import java.util.function.Predicate;

public class EntityAIHeal<T extends EntityLiving> extends EntityAIBase {

    private final T entity;
    private final Predicate<T> healCondition;
    private final Function<T, IInventory> invProvider;

    private ItemStack oldMainhandItem = ItemStack.EMPTY;
    private int timeRemaining;
    private boolean completed;

    public EntityAIHeal(T entity, float healthThreshold, Function<T, IInventory> invProvider) {
        this(entity, e -> e.getHealth() < healthThreshold, invProvider);
    }

    public EntityAIHeal(T entity, Predicate<T> healCondition, Function<T, IInventory> invProvider) {
        this.entity = entity;
        this.healCondition = healCondition;
        this.invProvider = invProvider;
    }

    @Override
    public boolean shouldExecute() {
        IInventory inventory = invProvider.apply(entity);
        if (entity.getAttackTarget() == null && healCondition.test(entity)) {
            ItemStack medStack = InventoryHelper.findHealingItemForAi(inventory);
            if (medStack.isEmpty()) {
                return false;
            }
            HealingItem healingItem = (HealingItem) medStack.getItem();
            return healingItem.canHeal(entity, medStack);
        }
        return false;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return entity.getAttackTarget() == null && !completed;
    }

    @Override
    public void startExecuting() {
        IInventory inventory = invProvider.apply(entity);
        ItemStack stack = InventoryHelper.findHealingItemForAi(inventory);
        if (!stack.isEmpty() && stack.getItem() instanceof HealingItem) {
            HealingItem healingItem = (HealingItem) stack.getItem();
            ItemStack mainHand = entity.getHeldItemMainhand();
            oldMainhandItem = entity.getHeldItemMainhand().copy();
            if (!oldMainhandItem.isEmpty()) {
                InventoryHelper.store(inventory, mainHand.copy());
            }
            oldMainhandItem = mainHand.copy();
            entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, stack.copy());
            timeRemaining = healingItem.getUsageTime(stack);
            stack.setCount(0);
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
            if (stack.getItem() instanceof HealingItem) {
                ((HealingItem) stack.getItem()).heal(entity, stack, entity.world);
            }
            completed = true;
        }
    }
}
