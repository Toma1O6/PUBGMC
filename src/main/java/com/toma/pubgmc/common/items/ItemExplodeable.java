package com.toma.pubgmc.common.items;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.entity.throwables.EntityFragGrenade;
import com.toma.pubgmc.common.entity.throwables.EntityThrowableExplodeable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemExplodeable extends PMCItem {

    private final int maxFuse;
    private final ExplodeableItemAction explodeableItemAction;

    public ItemExplodeable(final String name, final int maxFuse, final ExplodeableItemAction action) {
        super(name);
        this.maxFuse = maxFuse;
        this.explodeableItemAction = action;
        this.setMaxStackSize(1);
    }

    public void startCooking(ItemStack stack, EntityPlayer player) {
        if(!stack.hasTagCompound()) {
            this.attachNBT(stack, player);
            stack.getTagCompound().setBoolean("isCooking", true);
            player.world.playSound(null, player.getPosition(), SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.MASTER, 1.0F, 1.0F);
        }
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        if(entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityLiving;
            if(!stack.hasTagCompound()) {
                this.attachNBT(stack, player);
            }
            this.explodeableItemAction.onRemoveFromInventory(stack, player.world, player, this.getFuseTime(stack), EntityThrowableExplodeable.EnumEntityThrowState.SHORT);
            if(!player.isCreative()) {
                stack.shrink(1);
            }
            player.playSound(SoundEvents.ENTITY_SNOWBALL_THROW, 1.0F, 1.0F);
        }
        return true;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if(!stack.hasTagCompound()) {
            this.attachNBT(stack, playerIn);
        }
        this.explodeableItemAction.onRemoveFromInventory(stack, worldIn, playerIn, this.getFuseTime(stack), EntityThrowableExplodeable.EnumEntityThrowState.LONG);
        stack.shrink(1);
        playerIn.playSound(SoundEvents.ENTITY_SNOWBALL_THROW, 1.0F, 1.0F);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(!(entityIn instanceof EntityPlayer)) return;
        EntityPlayer player = (EntityPlayer) entityIn;
        if(stack.hasTagCompound()) {
            if(this.isCooking(stack)) {
                int timeLeft = this.maxFuse - this.getFuseTime(stack);
                if(timeLeft < 0) {
                    this.explodeableItemAction.onRemoveFromInventory(stack, worldIn, player, timeLeft, EntityThrowableExplodeable.EnumEntityThrowState.FORCED);
                    stack.shrink(1);
                }
                stack.getTagCompound().setInteger("currentFuse", this.getFuseTime(stack) + 1);
            }
        } else this.attachNBT(stack, player);
    }

    private void attachNBT(ItemStack stack, EntityPlayer player) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("currentFuse", this.maxFuse);
        nbt.setBoolean("isCooking", false);
        nbt.setString("ownerID", player.getUniqueID().toString());
        stack.setTagCompound(nbt);
    }

    private boolean isCooking(ItemStack stack) {
        return stack.getTagCompound().getBoolean("isCooking");
    }

    private int getFuseTime(ItemStack stack) {
        return stack.getTagCompound().getInteger("currentFuse");
    }

    @FunctionalInterface
    public interface ExplodeableItemAction {

        void onRemoveFromInventory(ItemStack stack, World world, EntityPlayer player, int timeLeft, EntityThrowableExplodeable.EnumEntityThrowState state);
    }

    public static class Helper {

        public static void onFragRemoved(ItemStack stack, World world, EntityPlayer player, int timeLeft, EntityThrowableExplodeable.EnumEntityThrowState state) {
            if(failedNBTCheck(stack)) {
                Pubgmc.logger.fatal("Attempted to use {} with invalid NBT data!", stack.getItem().getClass());
                return;
            }
            if(!world.isRemote) {
                EntityFragGrenade grenade = new EntityFragGrenade(world, player, state, timeLeft);
                world.spawnEntity(grenade);
            }
        }

        public static void onSmokeRemoved(ItemStack stack, World world, EntityPlayer player, int timeLeft, EntityThrowableExplodeable.EnumEntityThrowState state) {
            if(failedNBTCheck(stack)) {
                Pubgmc.logger.fatal("Attempted to use {} with invalid NBT data!", stack.getItem().getClass());
                return;
            }
        }

        public static void onMolotovRemoved(ItemStack stack, World world, EntityPlayer player, int timeLeft, EntityThrowableExplodeable.EnumEntityThrowState state) {
            if(failedNBTCheck(stack)) {
                Pubgmc.logger.fatal("Attempted to use {} with invalid NBT data!", stack.getItem().getClass());
                return;
            }
        }

        public static void onFlashBangRemoved(ItemStack stack, World world, EntityPlayer player, int timeLeft, EntityThrowableExplodeable.EnumEntityThrowState state) {
            if(failedNBTCheck(stack)) {
                Pubgmc.logger.fatal("Attempted to use {} with invalid NBT data!", stack.getItem().getClass());
                return;
            }
        }

        private static boolean failedNBTCheck(ItemStack stack) {
            return !(stack.getItem() instanceof ItemExplodeable) || (stack.hasTagCompound() && !stack.getTagCompound().hasKey("ownerID"));
        }
    }
}
