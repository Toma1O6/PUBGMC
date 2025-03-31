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
    private int maxPlayerCount = 5; // may increase in future
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
        render(minecraft, teamGame, (float)x, (float)y);
    }

    public void render(Minecraft minecraft, TeamGame<?> teamGame, float x, float y) {
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
            y = y - Math.min(size, maxPlayerCount) * 13;
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
            renderMemberInformation(fontRenderer, client, team, member, x, y + 13 * index++, panelWidth);
            if (index >= maxPlayerCount)
                break;
        }
    }

    private void renderMemberInformation(FontRenderer font, World world, Team team, Team.Member member, int x, int y, int width) {
        renderMemberInformation(font, world, team, member, (float)x, (float)y, (float)width);
    }

    private void renderMemberInformation(FontRenderer font, World world, Team team, Team.Member member, float x, float y, float width) {
        boolean alive = team.isMember(member.getId());
        ITextComponent username = team.getUsername(member.getId());
        EntityLivingBase entity;
        if (member.getMemberType() == Team.MemberType.PLAYER) {
            entity = world.getPlayerEntityByUUID(member.getId());
        } else {
            entity = entitiesById.get(member.getId());
        }

        float healthLimit, absorptionHealth, normalHealth;
        if (entity != null) {
            healthLimit = entity.getMaxHealth();
            absorptionHealth = entity.getAbsorptionAmount();
            normalHealth = entity.getHealth();
        } else {
            healthLimit = 20.0f;
            absorptionHealth = 0f;
            normalHealth = 0f;
        }
        float healthLeft = normalHealth + absorptionHealth;
        float split75Left = healthLimit * 0.75f + absorptionHealth;
        boolean renderSplit75 = healthLeft < split75Left;
        // color
        float r, g, b, a;
        if (normalHealth < healthLimit * 0.25f) { // red
            r = 0.863f; g = 0.34f; b = 0.291f; a = 0.8f; // #dc564a 220,86,74
        } else if (normalHealth < healthLimit * 0.5f) { // yellow
            r = 0.98f; g = 0.895f; b = 0.648f; a = 0.8f; // #f9e4a5 249.228,165
        } else if (normalHealth < healthLimit) { // white
            r = 0.95f; g =0.95f; b = 0.95f; a = 0.8f; // #f2f2f2 242,242,242
        } else { // grey
            r = 0.648f; g = 0.648f; b = 0.648f; a = 0.8f; // #a5a5a5 165.165,165
        }

        // Transparent background
        ImageUtil.drawShape(x, y, x + width, y + 9, 0.0F, 0.0F, 0.0F, 0.2F);
        ImageUtil.drawShape(x, y + 9, x + width, y + 11, 0.197f, 0.197f, 0.197f, 0.3f); // #323232 50,50,50
        // health
        float percentage = healthLeft / healthLimit;
        ImageUtil.drawShape(x, y + 9, x + width * percentage, y + 11, r, g, b, a); // health bar
        // 75% health
        if (renderSplit75) {
            float splitPercentage = split75Left / healthLimit;
            ImageUtil.drawShape(x + width * percentage, y + 9, x + width * splitPercentage, y + 11, 0.346f, 0.346f, 0.346f, 0.3f); // #585858 88,88,88
        }
        // Entity name
        boolean renderShadow = false;
        font.drawString(username.getFormattedText(), x, y + 1, alive ? 0xFFFFFF : 0x585858, renderShadow);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private float getHealthStatus(@Nullable EntityLivingBase entity) {
        if (entity == null) {
            return 1.0F;
        }
        return (entity.getHealth() + entity.getAbsorptionAmount()) / entity.getMaxHealth();
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
