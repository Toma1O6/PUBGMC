package dev.toma.pubgmc.common.items;

import dev.toma.pubgmc.PMCTabs;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.common.entity.throwables.*;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

public class ItemExplodeable extends PMCItem implements MainHandOnly {

    private final int maxFuse;
    private final ExplodeableItemAction explodeableItemAction;
    private List<String> description;

    public ItemExplodeable(final String name, final int maxFuse, final ExplodeableItemAction action) {
        super(name);
        this.maxFuse = maxFuse;
        this.explodeableItemAction = action;
        this.setMaxStackSize(1);
        this.setCreativeTab(PMCTabs.TAB_GUNS);
    }

    public ItemExplodeable addAditionalDescription(String... description) {
        this.description = Arrays.asList(description);
        return this;
    }

    @Override
    public void dropItemFromInvalidSlot(ItemStack stack, EntityPlayer player) {
        if (isCooking(stack)) {
            getExplodeableItemAction().onRemoveFromInventory(stack, player.world, player, maxFuse - getFuseTime(stack), EntityThrowableExplodeable.EnumEntityThrowState.FORCED);
        } else {
            EntityItem item = new EntityItem(player.world, player.posX, player.posY + player.getEyeHeight(), player.posZ, stack.copy());
            Vec3d vec = player.getLookVec();
            item.motionX = vec.x * 0.3;
            item.motionY = vec.y * 0.3;
            item.motionZ = vec.z * 0.3;
            item.setPickupDelay(30);
            player.world.spawnEntity(item);
        }
        player.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, ItemStack.EMPTY);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }

    public boolean isCooking(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().getBoolean("isCooking");
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityLiving;
            if (!stack.hasTagCompound()) {
                this.attachNBT(stack, player);
            }
            this.explodeableItemAction.onRemoveFromInventory(stack, player.world, player, this.maxFuse - this.getFuseTime(stack), EntityThrowableExplodeable.EnumEntityThrowState.SHORT);
            player.inventory.removeStackFromSlot(player.inventory.currentItem);
            player.playSound(SoundEvents.ENTITY_SNOWBALL_THROW, 1.0F, 1.0F);
        }
        return true;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (!worldIn.isRemote) {
            if (!stack.hasTagCompound()) {
                this.attachNBT(stack, playerIn);
            }
            if (this.maxFuse > 0 && !this.isCooking(stack)) {
                stack.getTagCompound().setBoolean("isCooking", true);
                worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.MASTER, 1.0F, 1.0F);
                return super.onItemRightClick(worldIn, playerIn, handIn);
            }
            this.explodeableItemAction.onRemoveFromInventory(stack, worldIn, playerIn, this.maxFuse - this.getFuseTime(stack), EntityThrowableExplodeable.EnumEntityThrowState.LONG);
            playerIn.inventory.removeStackFromSlot(playerIn.inventory.currentItem);
            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.MASTER, 1.0F, 1.0F);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!(entityIn instanceof EntityPlayer)) return;
        if (!worldIn.isRemote) {
            if (this.isCooking(stack)) {
                int timeLeft = this.maxFuse - this.getFuseTime(stack);
                if (timeLeft < 0) {
                    this.explodeableItemAction.onRemoveFromInventory(stack, worldIn, (EntityPlayer) entityIn, timeLeft, EntityThrowableExplodeable.EnumEntityThrowState.FORCED);
                    ((EntityPlayer) entityIn).inventory.removeStackFromSlot(itemSlot);
                    return;
                }
                stack.getTagCompound().setInteger("currentFuse", this.getFuseTime(stack) + 1);
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format("label.pubgmc.throwable.right_click"));
        tooltip.add(I18n.format("label.pubgmc.throwable.left_click"));
        if (maxFuse > 0) tooltip.add(I18n.format("label.pubgmc.throwable.fuse", this.maxFuse));
        if (description != null) {
            tooltip.addAll(description);
        }
    }

    public ExplodeableItemAction getExplodeableItemAction() {
        return explodeableItemAction;
    }

    public int getMaxFuse() {
        return maxFuse;
    }

    private void attachNBT(ItemStack stack, EntityPlayer player) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("currentFuse", 0);
        nbt.setBoolean("isCooking", false);
        nbt.setString("ownerID", player.getUniqueID().toString());
        stack.setTagCompound(nbt);
    }

    public int getFuseTime(ItemStack stack) {
        return stack.getTagCompound().getInteger("currentFuse");
    }

    @FunctionalInterface
    public interface ExplodeableItemAction {

        void onRemoveFromInventory(ItemStack stack, World world, EntityPlayer player, int timeLeft, EntityThrowableExplodeable.EnumEntityThrowState state);
    }

    public static class Helper {

        public static void onFragRemoved(ItemStack stack, World world, EntityPlayer player, int timeLeft, EntityThrowableExplodeable.EnumEntityThrowState state) {
            if (validateUsage(stack)) {
                if (!world.isRemote) {
                    EntityFragGrenade grenade = new EntityFragGrenade(world, player, state, timeLeft);
                    world.spawnEntity(grenade);
                }
            }
        }

        public static void onSmokeRemoved(ItemStack stack, World world, EntityPlayer player, int timeLeft, EntityThrowableExplodeable.EnumEntityThrowState state) {
            if (validateUsage(stack)) {
                if (!world.isRemote) {
                    EntitySmokeGrenade smokeGrenade = new EntitySmokeGrenade(world, player, state, timeLeft);
                    world.spawnEntity(smokeGrenade);
                }
            }
        }

        public static void onMolotovRemoved(ItemStack stack, World world, EntityPlayer player, int timeLeft, EntityThrowableExplodeable.EnumEntityThrowState state) {
            if (validateUsage(stack)) {
                if (!world.isRemote) {
                    world.spawnEntity(new EntityMolotov(world, player, state));
                }
            }
        }

        public static void onFlashBangRemoved(ItemStack stack, World world, EntityPlayer player, int timeLeft, EntityThrowableExplodeable.EnumEntityThrowState state) {
            if (validateUsage(stack)) {
                if (!world.isRemote)
                    world.spawnEntity(new EntityFlashBang(world, player, state, timeLeft));
            }
        }

        private static boolean validateUsage(ItemStack stack) {
            boolean canUse = !(stack.getItem() instanceof ItemExplodeable) || (stack.hasTagCompound() && !stack.getTagCompound().hasKey("ownerID"));
            if (!canUse) {
                Pubgmc.logger.fatal("Attempted to use {} with invalid NBT data!", stack.getItem().getClass());
                return false;
            }
            return true;
        }
    }
}
