package dev.toma.pubgmc.api.game;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiFunction;

public final class Team {

    private final Member owner;
    private final Map<UUID, Member> members;
    private final Map<UUID, Member> activeMembers;
    private final Map<UUID, String> usernames;

    public Team(Member owner) {
        this.owner = owner;
        this.members = new HashMap<>();
        this.activeMembers = new HashMap<>();
        this.usernames = new HashMap<>();
    }

    public UUID getTeamId() {
        return owner.getId();
    }

    public void addPlayer(EntityPlayer player) {
        UUID uuid = player.getUniqueID();
        Member member = new Member(uuid, MemberType.PLAYER);
        members.put(uuid, member);
        activeMembers.put(uuid, member);
    }

    public void addAI(Entity entity) {
        UUID uuid = entity.getUniqueID();
        Member member = new Member(uuid, MemberType.AI);
        members.put(uuid, member);
        activeMembers.put(uuid, member);
    }

    public void eliminate(UUID uuid) {
        activeMembers.remove(uuid);
    }

    public boolean isMember(UUID uuid) {
        return activeMembers.containsKey(uuid);
    }

    public Optional<Member> getMemberOptionally(UUID uuid) {
        return Optional.ofNullable(activeMembers.get(uuid));
    }

    public boolean isTeamEliminated() {
        return activeMembers.isEmpty();
    }

    public String getUsername(UUID memberId) {
        return usernames.getOrDefault(memberId, "<none>");
    }

    public Map<UUID, Member> getAllMembers() {
        return members;
    }

    private void saveUsername(Entity entity) {
        usernames.put(entity.getUniqueID(), entity.getName());
    }

    public static final class Member {

        private final UUID uuid;
        private final MemberType memberType;

        private Member(UUID uuid, MemberType memberType) {
            this.uuid = uuid;
            this.memberType = memberType;
        }

        public UUID getId() {
            return uuid;
        }

        public MemberType getMemberType() {
            return memberType;
        }
    }

    public enum MemberType {

        PLAYER(World::getPlayerEntityByUUID),
        AI(WorldServer::getEntityFromUuid);

        private final BiFunction<WorldServer, UUID, Entity> entityById;

        MemberType(BiFunction<WorldServer, UUID, Entity> entityById) {
            this.entityById = entityById;
        }

        public boolean isPlayer() {
            return this == PLAYER;
        }

        public EntityPlayer getPlayer(WorldServer server, UUID uuid) {
            if (this == AI) {
                throw new IllegalStateException("Cannot cast AI entity as player");
            }
            return (EntityPlayer) entityById.apply(server, uuid);
        }
    }
}
