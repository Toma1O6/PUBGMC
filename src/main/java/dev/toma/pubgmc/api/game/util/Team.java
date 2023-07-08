package dev.toma.pubgmc.api.game.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.Constants;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public final class Team implements Iterable<Team.Member> {

    private final UUID teamId;
    private final Map<UUID, Member> members;
    private final Set<UUID> activeMembers;
    private final Map<UUID, String> usernames;
    private Member teamLeader;

    public Team(Entity entity) {
        this.teamLeader = Member.of(entity);
        this.teamId = teamLeader.uuid;
        this.members = new LinkedHashMap<>();
        this.activeMembers = new HashSet<>();
        this.usernames = new HashMap<>();
        add(entity);
    }

    public Team(Member member) {
        this.teamId = member.uuid;
        this.teamLeader = member;
        this.members = new HashMap<>();
        this.activeMembers = new HashSet<>();
        this.usernames = new HashMap<>();

        addMember(member);
    }

    private Team(UUID teamId, Member owner, Map<UUID, Member> members, Set<UUID> activeMembers, Map<UUID, String> usernames) {
        this.teamId = teamId;
        this.teamLeader = owner;
        this.members = members;
        this.activeMembers = activeMembers;
        this.usernames = usernames;
    }

    public void setTeamLeader(Member teamLeader) {
        this.teamLeader = teamLeader;
    }

    public UUID getTeamId() {
        return teamId;
    }

    public void add(Entity entity) {
        UUID uuid = entity.getUniqueID();
        Member member = Member.of(entity);
        members.put(uuid, member);
        activeMembers.add(uuid);
        saveUsername(entity);
    }

    public int getSize() {
        return members.size();
    }

    public Member getTeamLeader() {
        return teamLeader;
    }

    public void addMember(Member member) {
        this.members.put(member.uuid, member);
    }

    public void addActiveMember(UUID memberId) {
        this.activeMembers.add(memberId);
    }

    public void addUsername(UUID memberId, String username) {
        this.usernames.put(memberId, username);
    }

    public void eliminate(UUID uuid) {
        activeMembers.remove(uuid);
    }

    public boolean isMember(UUID uuid) {
        return activeMembers.contains(uuid);
    }

    public int getActiveMemberCount() {
        return activeMembers.size();
    }

    public Optional<Member> getMember(UUID memberUid) {
        return Optional.ofNullable(members.get(memberUid));
    }

    public Optional<Member> getActiveMember(UUID uuid) {
        return isMember(uuid) ? Optional.ofNullable(members.get(uuid)) : Optional.empty();
    }

    public Optional<Member> getAliveTeamMember() {
        Member lastValidMember = null;
        for (Member member : members.values()) {
            MemberType type = member.getMemberType();
            if (!isMember(member.uuid)) {
                continue;
            }
            if (lastValidMember == null || (lastValidMember.getMemberType() != MemberType.PLAYER && type.isPlayer())) {
                lastValidMember = member;
            }
        }
        return Optional.ofNullable(lastValidMember);
    }

    public boolean isTeamEliminated() {
        return activeMembers.isEmpty();
    }

    public String getUsername(UUID memberId) {
        return usernames.getOrDefault(memberId, "<none>");
    }

    public boolean isTeamLeader(UUID memberId) {
        return teamLeader.uuid.equals(memberId);
    }

    public boolean isTeamLeader(Entity entity) {
        return isTeamLeader(entity.getUniqueID());
    }

    public Map<UUID, Member> getAllMembers() {
        return members;
    }

    public Stream<Member> getActiveMemberStream() {
        return members.values().stream()
                .filter(member -> isMember(member.uuid));
    }

    // Useful for game AI despawning
    public void removeMemberById(UUID memberId) {
        if (teamLeader.uuid.equals(memberId) && members.size() > 1) {
            throw new UnsupportedOperationException("Cannot remove owner of non-empty team");
        }
        members.remove(memberId);
        usernames.remove(memberId);
        activeMembers.remove(memberId);
    }

    @Override
    public Iterator<Member> iterator() {
        return members.values().stream()
                .filter(member -> isMember(member.uuid))
                .iterator();
    }

    private void saveUsername(Entity entity) {
        usernames.put(entity.getUniqueID(), entity.getName());
    }

    public NBTTagCompound serialize() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setUniqueId("teamId", teamId);
        nbt.setUniqueId("teamLeader", teamLeader.uuid);
        NBTTagList members = new NBTTagList();
        this.members.values().forEach(member -> members.appendTag(member.serialize()));
        nbt.setTag("members", members);
        NBTTagList active = new NBTTagList();
        this.activeMembers.forEach(uuid -> active.appendTag(new NBTTagString(uuid.toString())));
        nbt.setTag("active", active);
        NBTTagCompound usernameTag = new NBTTagCompound();
        for (Map.Entry<UUID, String> entry : usernames.entrySet()) {
            usernameTag.setString(entry.getKey().toString(), entry.getValue());
        }
        nbt.setTag("usernames", usernameTag);
        return nbt;
    }

    public static Team deserialize(NBTTagCompound nbt) {
        UUID teamId = nbt.getUniqueId("teamId");
        UUID teamLeader = nbt.getUniqueId("teamLeader");
        NBTTagList memberList = nbt.getTagList("members", Constants.NBT.TAG_COMPOUND);
        Map<UUID, Member> memberMap = new HashMap<>();
        for (int i = 0; i < memberList.tagCount(); i++) {
            NBTTagCompound memberTag = memberList.getCompoundTagAt(i);
            Member member = Member.deserialize(memberTag);
            memberMap.put(member.uuid, member);
        }
        Member leader = memberMap.get(teamLeader);
        Set<UUID> activeMembers = new HashSet<>();
        NBTTagList activeList = nbt.getTagList("active", Constants.NBT.TAG_STRING);
        for (int i = 0; i < activeList.tagCount(); i++) {
            activeMembers.add(UUID.fromString(activeList.getStringTagAt(i)));
        }
        Map<UUID, String> usernameMap = new HashMap<>();
        NBTTagCompound usernamesTag = nbt.getCompoundTag("usernames");
        Set<String> ids = usernamesTag.getKeySet();
        for (String key : ids) {
            UUID memberId = UUID.fromString(key);
            String name = usernamesTag.getString(key);
            usernameMap.put(memberId, name);
        }
        return new Team(teamId, leader, memberMap, activeMembers, usernameMap);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return teamId.equals(team.teamId);
    }

    @Override
    public int hashCode() {
        return teamId.hashCode();
    }

    public static final class Member {

        private final UUID uuid;
        private final MemberType memberType;

        private Member(UUID uuid, MemberType memberType) {
            this.uuid = uuid;
            this.memberType = memberType;
        }

        public static Member of(Entity entity) {
            return new Member(entity.getUniqueID(), entity instanceof EntityPlayer ? MemberType.PLAYER : MemberType.AI);
        }

        public UUID getId() {
            return uuid;
        }

        public MemberType getMemberType() {
            return memberType;
        }

        public Entity getEntity(WorldServer server) {
            return memberType.isPlayer() ? server.getPlayerEntityByUUID(uuid) : server.getEntityFromUuid(uuid);
        }

        public EntityPlayer getPlayer(World world) {
            if (!memberType.isPlayer()) {
                throw new UnsupportedOperationException("Invalid member type");
            }
            return world.getPlayerEntityByUUID(uuid);
        }

        public NBTTagCompound serialize() {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setUniqueId("id", uuid);
            nbt.setInteger("type", memberType.ordinal());
            return nbt;
        }

        public static Member deserialize(NBTTagCompound nbt) {
            UUID uuid = nbt.getUniqueId("id");
            int type = nbt.getInteger("type");
            MemberType memberType = MemberType.values()[type % MemberType.values().length];
            return new Member(uuid, memberType);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Member member = (Member) o;
            return uuid.equals(member.uuid);
        }

        @Override
        public int hashCode() {
            return uuid.hashCode();
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
