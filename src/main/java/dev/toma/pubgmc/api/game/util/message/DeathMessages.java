package dev.toma.pubgmc.api.game.util.message;

import dev.toma.pubgmc.common.entity.controllable.EntityVehicle;
import dev.toma.pubgmc.init.DamageSourceGun;
import dev.toma.pubgmc.init.PMCDamageSources;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;

public final class DeathMessages {

    public static final DeathMessageType<EmptyDeathMessage> EMPTY = new DeathMessageType<>("empty", new EmptyDeathMessage.Serializer());
    public static final DeathMessageType<SelfDeathMessage> SELF = new DeathMessageType<>("self", new SelfDeathMessage.Serializer());
    public static final DeathMessageType<EntityCausedDeathMessage> ENTITY = new DeathMessageType<>("entity", new EntityCausedDeathMessage.Serializer());

    public static DeathMessage createMessage(@Nullable EntityLivingBase victim, DamageSource damageSource) {
        if (victim == null) {
            return EmptyDeathMessage.INSTANCE;
        }
        ITextComponent tool = getTool(damageSource);
        if (damageSource.getTrueSource() instanceof EntityLivingBase) {
            EntityLivingBase killer = (EntityLivingBase) damageSource.getTrueSource();
            return new EntityCausedDeathMessage(killer.getDisplayName(), victim.getDisplayName(), tool);
        } else {
            return new SelfDeathMessage(victim.getDisplayName(), tool);
        }
    }

    public static ITextComponent getTool(DamageSource source) {
        Entity killer = source.getTrueSource();
        if (killer instanceof EntityLivingBase) {
            // TODO extract data from bullet for example for actual owner/weapon
            EntityLivingBase killerEntity = (EntityLivingBase) killer;
            Entity vehicle = killerEntity.getRidingEntity();
            if (vehicle instanceof EntityVehicle) {
                return vehicle.getDisplayName(); // TODO improve resolution
            }
            if (source instanceof DamageSourceGun) {
                DamageSourceGun gunSource = (DamageSourceGun) source;
                ItemStack itemStack = gunSource.getWeapon();
                if (!itemStack.isEmpty()) {
                    return itemStack.getTextComponent(); // TODO transfer headshot status
                }
            }
            ItemStack itemStack = killerEntity.getHeldItemMainhand();
            if (!itemStack.isEmpty()) {
                return itemStack.getTextComponent();
            }
            return DeathMessage.ENTITY_GENERIC_DEATH;
        } else if (source == PMCDamageSources.ZONE) {
            return DeathMessage.GENERIC_BOUNDS_DEATH;
        }
        return DeathMessage.GENERIC_DEATH;
    }

    public MessageRelation getRelationBetween(EntityLivingBase killer, EntityLivingBase victim) {
        return MessageRelation.NONE;
    }

    private DeathMessages() {
    }
}
