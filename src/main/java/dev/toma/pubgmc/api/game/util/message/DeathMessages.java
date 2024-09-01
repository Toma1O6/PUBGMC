package dev.toma.pubgmc.api.game.util.message;

import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.team.Team;
import dev.toma.pubgmc.api.game.team.TeamGame;
import dev.toma.pubgmc.api.game.team.TeamManager;
import dev.toma.pubgmc.api.game.team.TeamRelations;
import dev.toma.pubgmc.common.entity.controllable.EntityDriveable;
import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.init.DamageSourceGun;
import dev.toma.pubgmc.init.PMCDamageSources;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
            EntityCausedDeathMessage deathMessage = new EntityCausedDeathMessage(
                    GameHelper.getEntityDisplayName(killer),
                    GameHelper.getEntityDisplayName(victim),
                    tool
            );
            if (damageSource instanceof DamageSourceGun) {
                DamageSourceGun gunSource = (DamageSourceGun) damageSource;
                if (gunSource.wasHeadshot()) {
                    deathMessage.addAttribute(new TextComponentString(ConfigPMC.client.other.headshotCharacter.get()));
                }
            }
            deathMessage.addAffectedEntities(killer.getUniqueID(), victim.getUniqueID());
            return deathMessage;
        } else {
            SelfDeathMessage message = new SelfDeathMessage(GameHelper.getEntityDisplayName(victim), tool);
            message.addAffectedEntities(victim.getUniqueID());
            return message;
        }
    }

    public static ITextComponent getTool(DamageSource source) {
        Entity killer = source.getTrueSource();
        if (killer instanceof EntityLivingBase) {
            // TODO extract data from bullet for example for actual owner/weapon
            EntityLivingBase killerEntity = (EntityLivingBase) killer;
            Entity vehicle = killerEntity.getRidingEntity();
            if (vehicle instanceof EntityDriveable) {
                return GameHelper.getEntityDisplayName(vehicle); // TODO improve resolution
            }
            if (source instanceof DamageSourceGun) {
                DamageSourceGun gunSource = (DamageSourceGun) source;
                ItemStack itemStack = gunSource.getWeapon();
                if (!itemStack.isEmpty()) {
                    return new TextComponentTranslation(itemStack.getItem().getUnlocalizedName(itemStack) + ".name"); // TODO transfer headshot status
                }
            }
            ItemStack itemStack = killerEntity.getHeldItemMainhand();
            if (!itemStack.isEmpty()) {
                return new TextComponentTranslation(itemStack.getItem().getUnlocalizedName(itemStack)  + ".name");
            }
            return DeathMessage.ENTITY_GENERIC_DEATH;
        } else if (source == PMCDamageSources.ZONE) {
            return DeathMessage.GENERIC_BOUNDS_DEATH;
        }
        return DeathMessage.GENERIC_DEATH;
    }

    @SideOnly(Side.CLIENT)
    public static MessageRelation getRelationBetween(Game<?> game, DeathMessage message) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        List<UUID> entities = new ArrayList<>(message.getAffectedEntities());
        if (entities.isEmpty()) {
            return MessageRelation.NONE;
        }
        boolean isMe = entities.stream().anyMatch(uuid -> uuid.equals(player.getUniqueID()));
        UUID killer = entities.get(0);
        if (game instanceof TeamGame<?>) {
            TeamManager manager = ((TeamGame<?>) game).getTeamManager();
            UUID victim = entities.size() > 1 ? entities.get(1) : killer;
            Team team1 = manager.getEntityTeamByEntityId(killer);
            Team team2 = manager.getEntityTeamByEntityId(victim);
            TeamRelations relations = manager.getTeamRelationship(team1, team2);
            if (!relations.isFriendlyOrEnemy()) {
                return MessageRelation.NONE;
            }
            Team myTeam = manager.getEntityTeam(player);
            if (myTeam == null) {
                return MessageRelation.NONE;
            }
            TeamRelations victimTeamRelation = manager.getTeamRelationship(myTeam, team2);
            if (victimTeamRelation == TeamRelations.FRIENDLY) {
                return isMe ? MessageRelation.MY_DEATH : MessageRelation.FRIENDLY_DEATH;
            }
            TeamRelations killerTeamRelation = manager.getTeamRelationship(myTeam, team1);
            if (killerTeamRelation == TeamRelations.FRIENDLY) {
                return isMe ? MessageRelation.MY_KILL : MessageRelation.FRIENDLY_KILL;
            }
            return MessageRelation.NONE;
        } else {
            return isMe ? (killer.equals(player.getUniqueID()) ? MessageRelation.MY_KILL : MessageRelation.MY_DEATH) : MessageRelation.NONE;
        }
    }

    private DeathMessages() {
    }
}
