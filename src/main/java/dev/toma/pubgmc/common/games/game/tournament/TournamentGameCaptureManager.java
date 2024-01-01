package dev.toma.pubgmc.common.games.game.tournament;

import dev.toma.pubgmc.api.game.team.TeamManager;
import dev.toma.pubgmc.api.game.util.Team;
import dev.toma.pubgmc.common.games.map.CaptureZonePoint;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;

import java.util.UUID;

public final class TournamentGameCaptureManager {

    private final CaptureZonePoint captureZone;

    private Team capturingTeam;
    private int capturingTime;

    public TournamentGameCaptureManager(CaptureZonePoint captureZone) {
        this.captureZone = captureZone;
    }

    public NBTTagCompound serialize() {
        NBTTagCompound nbt = new NBTTagCompound();
        if (capturingTeam != null) {
            nbt.setUniqueId("capturingTeam", capturingTeam.getTeamId());
            nbt.setInteger("captureTime", capturingTime);
        }
        return nbt;
    }

    public void deserialize(NBTTagCompound nbt, TeamManager manager) {
        capturingTime = nbt.getInteger("captureTime");
        if (nbt.hasKey("capturingTeamMost", Constants.NBT.TAG_LONG)) {
            UUID captureTeamId = nbt.getUniqueId("capturingTeam");
            capturingTeam = manager.getTeamById(captureTeamId);
        }
    }
}
