package dev.toma.pubgmc.common.games.map;

import dev.toma.pubgmc.api.game.map.GameMapInstance;
import dev.toma.pubgmc.api.game.map.GameMapPointSerializer;
import dev.toma.pubgmc.api.game.map.GameMapPointType;
import dev.toma.pubgmc.common.games.util.TeamType;
import dev.toma.pubgmc.util.helper.SerializationHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TeamSpawnerPoint extends SpawnerPoint {

    private TeamType teamType;

    public TeamSpawnerPoint(BlockPos pointPosition, TeamType teamType) {
        super(pointPosition);
        this.teamType = teamType;
    }

    public void setTeamType(TeamType teamType) {
        this.teamType = teamType;
    }

    public TeamType getTeamType() {
        return teamType;
    }

    @Override
    public GameMapPointType<?> getType() {
        return GameMapPoints.TEAM_SPAWNER;
    }

    public static final class Serializer implements GameMapPointSerializer<TeamSpawnerPoint> {

        @Override
        public NBTTagCompound serializePointData(TeamSpawnerPoint point) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setInteger("teamType", point.teamType.ordinal());
            return nbt;
        }

        @Override
        public TeamSpawnerPoint deserializePointData(BlockPos pointPosition, NBTTagCompound nbt, GameMapInstance parent) {
            TeamType type = SerializationHelper.enumByIndex(nbt.getInteger("teamType"), TeamType.class);
            return new TeamSpawnerPoint(pointPosition, type);
        }

        @Override
        public TeamSpawnerPoint createDefaultInstance(BlockPos pos, World world, GameMapInstance map) {
            return new TeamSpawnerPoint(pos, TeamType.RED);
        }
    }
}
