package dev.toma.pubgmc.common.entity;

import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.capability.GameDataProvider;
import dev.toma.pubgmc.api.capability.SpecialEquipmentSlot;
import dev.toma.pubgmc.api.entity.EntityDebuffs;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.LivingGameEntity;
import dev.toma.pubgmc.api.game.loot.LootableContainer;
import dev.toma.pubgmc.api.game.mutator.GameMutators;
import dev.toma.pubgmc.api.inventory.SpecialInventoryProvider;
import dev.toma.pubgmc.api.item.SpecialInventoryItem;
import dev.toma.pubgmc.common.ai.EntityAIBeBlinded;
import dev.toma.pubgmc.common.ai.EntityAIGunAttack;
import dev.toma.pubgmc.common.ai.EntityAILightSensitiveNearestAttackableTarget;
import dev.toma.pubgmc.api.game.mutator.AIPlayerMutator;
import dev.toma.pubgmc.api.game.mutator.GameMutatorManager;
import dev.toma.pubgmc.common.entity.navigate.ExtendedRangeGroundNavigator;
import dev.toma.pubgmc.common.items.ItemAmmo;
import dev.toma.pubgmc.common.items.attachment.AttachmentType;
import dev.toma.pubgmc.common.items.attachment.ItemAttachment;
import dev.toma.pubgmc.common.items.guns.AmmoType;
import dev.toma.pubgmc.common.items.guns.GunAttachments;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.common.items.heal.ItemHealing;
import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.RandomBotNameGenerator;
import dev.toma.pubgmc.util.helper.GameHelper;
import dev.toma.pubgmc.util.helper.SerializationHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EntityAIPlayer extends EntityCreature implements LivingGameEntity, IEntityAdditionalSpawnData, SpecialInventoryProvider, EntityDebuffs {

    public static final String DEFAULT_LOADOUT = "default_loadout";
    private final InventoryBasic inventory = new InventoryBasic("container.aiPlayer", false, 9);
    private final InventoryBasic specialEquipment = new InventoryBasic("container.aiPlayer.equipment", false, 3);
    private int variant;
    private int blindTime;
    private int deafTime;
    private UUID gameId = GameHelper.DEFAULT_UUID;

    public EntityAIPlayer(World worldIn) {
        super(worldIn);
        this.preventEntitySpawning = true;
        this.enablePersistence();
        this.setSize(0.6F, 1.95F);
        this.variant = worldIn.rand.nextInt(4);
        ((PathNavigateGround) this.navigator).setBreakDoors(true);

        setRandomName(this);
    }

    public static void setRandomName(EntityAIPlayer aiPlayer) {
        aiPlayer.setCustomNameTag(RandomBotNameGenerator.generateBotName().getFormattedText());
        aiPlayer.setAlwaysRenderNameTag(false);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (ticksExisted % 20 == 0) {
            GameHelper.validateGameEntityStillValid(this);
        }
        if (blindTime > 0) {
            --blindTime;
        }
        if (deafTime > 0) {
            --deafTime;
        }
    }

    public void clearAI() {
        List<EntityAITasks.EntityAITaskEntry> taskEntries = new ArrayList<>(tasks.taskEntries);
        taskEntries.forEach(entry -> tasks.removeTask(entry.action));
        List<EntityAITasks.EntityAITaskEntry> targetTaskEntries = new ArrayList<>(targetTasks.taskEntries);
        targetTaskEntries.forEach(entry -> targetTasks.removeTask(entry.action));
    }

    @Override
    public boolean canPickUpLoot() {
        return true;
    }

    @Override
    public boolean isInRangeToRenderDist(double distance) {
        return true;
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL;
    }

    @Override
    protected boolean canDropLoot() {
        return false;
    }

    @Override
    protected void initEntityAI() {
        initAi();
    }

    @Override
    protected PathNavigate createNavigator(World worldIn) {
        return new ExtendedRangeGroundNavigator(this, worldIn, ConfigPMC.world().aiPathFindRange.get());
    }

    @SuppressWarnings("unchecked")
    private <G extends Game<?>> void initAi() {
        Game<?> game = GameDataProvider.getGameData(world).map(GameData::getCurrentGame).orElse(null);
        boolean useDefaultAi = true;
        if (game != null && game.isStarted()) {
            Optional<AIPlayerMutator<?>> mutatorOpt = GameMutatorManager.INSTANCE.getMutator(game.getGameType(), GameMutators.AI_TASKS);
            if (mutatorOpt.isPresent()) {
                AIPlayerMutator<G> mutator = (AIPlayerMutator<G>) mutatorOpt.get();
                mutator.apply((G) game, this);
                useDefaultAi = false;
            }
        }
        if (useDefaultAi) {
            addDefaultTasks(this);
            tasks.addTask(1, new EntityAIGunAttack(this));
            targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
            targetTasks.addTask(2, new EntityAILightSensitiveNearestAttackableTarget<>(this, EntityPlayer.class, true));
            targetTasks.addTask(3, new EntityAILightSensitiveNearestAttackableTarget<>(this, EntityAIPlayer.class, true));
        }
    }

    public static void addDefaultTasks(EntityAIPlayer ai) {
        ai.tasks.addTask(0, new EntityAIBeBlinded<>(ai));
        ai.tasks.addTask(0, new EntityAISwimming(ai));
        ai.tasks.addTask(4, new EntityAIOpenDoor(ai, false));
        ai.tasks.addTask(5, new EntityAIWanderAvoidWater(ai, 1.0));
        ai.tasks.addTask(8, new EntityAILookIdle(ai));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(128.0D);
        this.getEntityAttribute(SWIM_SPEED).setBaseValue(2.0);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("variant", this.variant);
        compound.setUniqueId("gameId", gameId);
        compound.setTag("inventory", SerializationHelper.inventoryToNbt(inventory));
        compound.setTag("equipmentInventory", SerializationHelper.inventoryToNbt(specialEquipment));
        compound.setInteger("blindTime", blindTime);
        compound.setInteger("deafTime", deafTime);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        variant = compound.getInteger("variant");
        gameId = compound.getUniqueId("gameId");
        SerializationHelper.inventoryFromNbt(inventory, compound.getTagList("inventory", Constants.NBT.TAG_COMPOUND));
        SerializationHelper.inventoryFromNbt(specialEquipment, compound.getTagList("equipmentInventory", Constants.NBT.TAG_COMPOUND));
        blindTime = compound.getInteger("blindTime");
        deafTime = compound.getInteger("deafTime");
    }

    @Override
    public void writeSpawnData(ByteBuf buffer) {
        buffer.writeInt(variant);
        NBTTagCompound nbtTag = new NBTTagCompound();
        nbtTag.setTag("inv", SerializationHelper.inventoryToNbt(specialEquipment));
        ByteBufUtils.writeTag(buffer, nbtTag);
        ByteBufUtils.writeUTF8String(buffer, gameId.toString());
    }

    @Override
    public void readSpawnData(ByteBuf additionalData) {
        variant = additionalData.readInt();
        NBTTagCompound nbtTag = ByteBufUtils.readTag(additionalData);
        SerializationHelper.inventoryFromNbt(specialEquipment, nbtTag.getTagList("inv", Constants.NBT.TAG_COMPOUND));
        gameId = UUID.fromString(ByteBufUtils.readUTF8String(additionalData));
    }

    public void clearInventory() {
        inventory.clear();
        specialEquipment.clear();
        for (EntityEquipmentSlot slot : EntityEquipmentSlot.values()) {
            setItemStackToSlot(slot, ItemStack.EMPTY);
        }
    }

    public IInventory getInventory() {
        return inventory;
    }

    public IInventory getSpecialEquipmentInventory() {
        return specialEquipment;
    }

    public int getVariant() {
        return variant;
    }

    public void loot(LootableContainer lootable) {
        AmmoType lootingAmmoType = null;
        for (int i = 0; i < lootable.getSize(); i++) {
            ItemStack stack = lootable.getItemStackInSlot(i);
            if (stack.isEmpty())
                continue;
            ItemStack weapon = getHeldItemMainhand();
            boolean hasWeapon = !weapon.isEmpty() && weapon.getItem() instanceof GunBase;
            if (weapon.isEmpty() && stack.getItem() instanceof GunBase) {
                if (stack.getItem() != PMCItems.FLARE_GUN) {
                    setItemStackToSlot(EntityEquipmentSlot.MAINHAND, stack.copy());
                    lootable.setItemStackToSlot(i, ItemStack.EMPTY);
                    lootingAmmoType = ((GunBase) stack.getItem()).getAmmoType();
                    continue;
                }
            }
            if (lootingAmmoType != null && stack.getItem() instanceof ItemAmmo && ((ItemAmmo) stack.getItem()).getAmmoType() == lootingAmmoType) {
                if (lootItem(stack)) {
                    lootable.setItemStackToSlot(i, ItemStack.EMPTY);
                    continue;
                }
            }
            if (stack.getItem() instanceof ItemArmor) {
                ItemArmor armor = (ItemArmor) stack.getItem();
                EntityEquipmentSlot equipmentSlot = armor.armorType;
                ItemStack equippedArmor = getItemStackFromSlot(equipmentSlot);
                if (equippedArmor.isEmpty()) {
                    setItemStackToSlot(equipmentSlot, stack.copy());
                    lootable.setItemStackToSlot(i, ItemStack.EMPTY);
                    continue;
                }
            }
            if (stack.getItem() instanceof SpecialInventoryItem) {
                SpecialInventoryItem item = (SpecialInventoryItem) stack.getItem();
                SpecialEquipmentSlot equipmentSlot = item.getSlotType();
                ItemStack equipped = specialEquipment.getStackInSlot(equipmentSlot.ordinal());
                if (equipped.isEmpty()) {
                    specialEquipment.setInventorySlotContents(equipmentSlot.ordinal(), stack.copy());
                    lootable.setItemStackToSlot(i, ItemStack.EMPTY);
                    continue;
                }
            }
            if (stack.getItem() instanceof ItemHealing) {
                if (lootItem(stack)) {
                    lootable.setItemStackToSlot(i, ItemStack.EMPTY);
                }
            }
            if (stack.getItem() instanceof ItemAttachment) {
                ItemAttachment attachment = (ItemAttachment) stack.getItem();
                if (hasWeapon) {
                    GunBase gun = (GunBase) weapon.getItem();
                    GunAttachments attachments = gun.getAttachments();
                    AttachmentType<?> type = attachment.getType();
                    if (attachments.supports(attachment) && !attachments.hasAttachment(weapon, type)) {
                        attachments.attach(weapon, attachment);
                        lootable.setItemStackToSlot(i, ItemStack.EMPTY);
                    }
                }
            }
        }
        SerializationHelper.syncEntity(this);
        lootable.onLootContentsChanged();
    }

    private boolean lootItem(ItemStack stack) {
        int slot = -1;
        if (stack.isEmpty())
            return false;
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack item = inventory.getStackInSlot(i);
            if (item.isEmpty()) {
                slot = i;
                break;
            }
        }
        if (slot != -1) {
            inventory.setInventorySlotContents(slot, stack.copy());
            return true;
        }
        return false;
    }

    public boolean hasNoWeapon() {
        return !(this.getHeldItemMainhand().getItem() instanceof GunBase);
    }

    @Override
    public UUID getCurrentGameId() {
        return gameId;
    }

    @Override
    public void assignGameId(UUID gameId) {
        this.gameId = gameId;
    }

    @Override
    public void onNewGameDetected(UUID newGameId) {
        setDead();
    }

    @Override
    public ItemStack getSpecialItemFromSlot(SpecialEquipmentSlot slot) {
        return specialEquipment.getStackInSlot(slot.ordinal());
    }

    @Override
    public void setSpecialItemToSlot(SpecialEquipmentSlot slot, ItemStack stack) {
        specialEquipment.setInventorySlotContents(slot.ordinal(), stack);
    }

    @Override
    public EntityLiving getLivingEntity() {
        return this;
    }

    @Override
    public boolean isBlind() {
        return blindTime > 0;
    }

    @Override
    public boolean isDeaf() {
        return deafTime > 0;
    }

    @Override
    public void clearBlindStatus() {
        setBlindTime(0);
    }

    @Override
    public void clearDeafStatus() {
        setDeafTime(0);
    }

    @Override
    public void setBlindTime(int time) {
        blindTime = time;
    }

    @Override
    public void setDeafTime(int time) {
        deafTime = time;
    }

    @Override
    public int getRemainingBlindTime() {
        return blindTime;
    }

    @Override
    public int getRemainingDeafTime() {
        return deafTime;
    }
}
