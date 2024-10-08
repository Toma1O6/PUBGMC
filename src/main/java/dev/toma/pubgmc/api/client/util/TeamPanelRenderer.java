package dev.toma.pubgmc.api.client.util;

import dev.toma.pubgmc.api.game.LivingGameEntity;
import dev.toma.pubgmc.api.game.team.Team;
import dev.toma.pubgmc.api.game.team.TeamGame;
import dev.toma.pubgmc.api.game.team.TeamManager;
import dev.toma.pubgmc.util.helper.ImageUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TeamPanelRenderer {

    private static final int ENTITY_ID_INTERVAL = 60;
    private static final int TEAM_PANEL_WIDTH = 60;
    private final Map<UUID, EntityLiving> entitiesById = new HashMap<>();

    private boolean renderSoloTeams = false;
    private boolean offsetsY = true;
    private boolean buildEntityIdMap = true;
    private int maxPlayerCount = 5;
    private int panelWidth = -1;

    public void setRenderSoloTeams(boolean renderSoloTeams) {
        this.renderSoloTeams = renderSoloTeams;
    }

    public void setOffsetsY(boolean offsetsY) {
        this.offsetsY = offsetsY;
    }

    public void shouldLoadRealAIHealth(boolean load) {
        this.buildEntityIdMap = load;
    }

    public void setMaxPlayerCount(int maxPlayerCount) {
        this.maxPlayerCount = maxPlayerCount;
    }

    public void render(Minecraft minecraft, TeamGame<?> teamGame, int x, int y) {
        Entity entity = minecraft.getRenderViewEntity();
        if (entity == null) {
            return;
        }
        TeamManager manager = teamGame.getTeamManager();
        Team team = manager.getEntityTeam(entity);
        if (team == null) {
            return;
        }
        int size = team.getSize();
        if (size <= 1 && !renderSoloTeams) {
            return;
        }
        if (!offsetsY) {
            y = y - Math.min(size, maxPlayerCount) * 15;
        }
        WorldClient client = minecraft.world;
        int index = 0;
        FontRenderer fontRenderer = minecraft.fontRenderer;
        Collection<Team.Member> members = team.getAllMembers().values();
        if (buildEntityIdMap && client.getTotalWorldTime() % ENTITY_ID_INTERVAL == 0L) {
            rebuildEntityIdMap(team, client);
            updatePanelWidth(team, members, fontRenderer);
        }
        if (panelWidth == -1) {
            updatePanelWidth(team, members, fontRenderer);
        }
        for (Team.Member member : members) {
            renderMemberInformation(fontRenderer, client, team, member, x, y + 15 * index++, panelWidth);
            if (index >= maxPlayerCount)
                break;
        }
    }

    private void renderMemberInformation(FontRenderer font, World world, Team team, Team.Member member, int x, int y, int width) {
        boolean alive = team.isMember(member.getId());
        ITextComponent username = team.getUsername(member.getId());
        ImageUtil.drawShape(x, y, x + width, y + 15, 0.0F, 0.0F, 0.0F, 0.4F);
        float health = 1.0F;
        switch (member.getMemberType()) {
            case AI:
                health = getHealthStatus(entitiesById.get(member.getId()));
                break;
            case PLAYER:
                health = getHealthStatus(world.getPlayerEntityByUUID(member.getId()));
                break;
        }
        ImageUtil.drawShape(x, y + 13, x + width, y + 15, 0.0F, 0.0F, 0.0F, 0.4F);
        ImageUtil.drawShape(x, y + 13, x + (int) (width * health), y + 15, 1.0F, 1.0F, 1.0F, 1.0F);
        font.drawString(username.getFormattedText(), x, y + 2, alive ? 0xFFFFFF : 0xFF0000);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private float getHealthStatus(@Nullable EntityLivingBase entity) {
        if (entity == null) {
            return 1.0F;
        }
        return entity.getHealth() / entity.getMaxHealth();
    }

    private void updatePanelWidth(Team team, Collection<Team.Member> members, FontRenderer font) {
        int i = TEAM_PANEL_WIDTH;
        for (Team.Member member : members) {
            ITextComponent username = team.getUsername(member.getId());
            int width = font.getStringWidth(username.getFormattedText());
            if (width > i) {
                i = width;
            }
        }
        this.panelWidth = i;
    }

    private void rebuildEntityIdMap(Team team, WorldClient client) {
        client.loadedEntityList.stream()
                .filter(entity -> entity instanceof LivingGameEntity)
                .map(entity -> (LivingGameEntity) entity)
                .forEach(livingGameEntity -> {
                    EntityLiving living = livingGameEntity.getLivingEntity();
                    UUID uuid = living.getUniqueID();
                    team.getMember(uuid).ifPresent(m -> entitiesById.put(uuid, living));
                });
    }
}
