package dev.toma.pubgmc.client.gui.game;

import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.capability.GameDataProvider;
import dev.toma.pubgmc.api.capability.PartyData;
import dev.toma.pubgmc.api.capability.PartyDataProvider;
import dev.toma.pubgmc.api.client.DataListenerGui;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.groups.Group;
import dev.toma.pubgmc.api.game.groups.GroupManager;
import dev.toma.pubgmc.api.game.groups.SingleplayerGameGroupManager;
import dev.toma.pubgmc.api.game.team.TeamGame;
import dev.toma.pubgmc.client.gui.menu.GuiWidgets;
import dev.toma.pubgmc.client.gui.widget.ButtonWidget;
import dev.toma.pubgmc.client.gui.widget.LabelWidget;
import dev.toma.pubgmc.common.games.NoGame;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.UUID;

public class GameGui extends GuiWidgets implements DataListenerGui {

    private static final int TEAM_PANEL_WIDTH = 130;

    private static final ITextComponent TEAM_TITLE = new TextComponentTranslation("label.pubgmc.game_ui.team_title");
    private static final ITextComponent CREATE_TEAM_LABEL = new TextComponentTranslation("label.pubgmc.game_ui.create_team");
    private static final ITextComponent INVITE_MEMBER_LABEL = new TextComponentTranslation("label.pubgmc.game_ui.invite_member");
    private static final ITextComponent DISBAND_TEAM_LABEL = new TextComponentTranslation("label.pubgmc.game_ui.disband_team");

    private final boolean isAdmin;
    private GameData gameData;
    private PartyData partyData;

    public GameGui(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    private void initAdminGui() {
        this.initPartyWidgets();
    }

    private void initUserGui() {
        this.initPartyWidgets();
    }

    private void initPartyWidgets() {
        UUID myUUID = mc.player.getUniqueID();
        GroupManager groupManager = GroupManager.getInContext(mc.world);
        Group group = groupManager.getGroupForPlayer(myUUID);

        LabelWidget label = this.addWidget(LabelWidget.centered(fontRenderer, width - TEAM_PANEL_WIDTH, 0, TEAM_PANEL_WIDTH, 15, TEAM_TITLE));
        label.setTextShadow(true);

        int widgetsLeft = width - TEAM_PANEL_WIDTH + 5;
        int widgetsWidth = TEAM_PANEL_WIDTH - 10;
        if (group != null) {
            // Display party info, members and so on
            boolean isLeader = myUUID.equals(group.getLeader());
            // Invite button
            int halfWidth = widgetsWidth / 2 - 10;
            ButtonWidget inviteButton = this.addWidget(new ButtonWidget(widgetsLeft, 15, halfWidth, 20, INVITE_MEMBER_LABEL.getFormattedText(), this::onInviteMember));
            inviteButton.setActive(false); // TODO set based on current player count
            // Disband button
            ButtonWidget disbandButton = this.addWidget(new ButtonWidget(widgetsLeft + halfWidth + 5, 15, halfWidth, 20, DISBAND_TEAM_LABEL.getFormattedText(), this::onDisbandTeam));
            disbandButton.setActive(false); // TODO set based on current game state

            int top = 15;
            if (isLeader) {
                top += 25;

            }

            // Settings
            if (groupManager instanceof PartyData) {
                ButtonWidget settingsButton = this.addWidget(new ButtonWidget(widgetsLeft, height - 25, widgetsWidth, 20, I18n.format("mco.configure.world.settings.title"), this::onOpenPartySettings));
                settingsButton.setActive(isLeader);
            }
        } else {
            // Create party button
            ButtonWidget createTeamButton = this.addWidget(new ButtonWidget(widgetsLeft, 15, widgetsWidth, 20, CREATE_TEAM_LABEL.getFormattedText(), this::onCreateTeam));
            createTeamButton.setActive(groupManager.canCreateTeam(myUUID));
        }
    }

    private void renderBackground(int mouseX, int mouseY, float partialTicks) {
        drawRect(width - TEAM_PANEL_WIDTH, 0, width, height, 0x66 << 24); // Teams background
        drawVerticalLine(width - TEAM_PANEL_WIDTH - 1, -1, height, 0xFFFFFFFF); // Teams separator
    }

    @Override
    public void init() {
        this.gameData = GameDataProvider.getGameData(mc.world).orElse(null);
        if (this.gameData == null)
            return;
        this.partyData = PartyDataProvider.getPartyData(mc.world).orElse(null);
        if (this.partyData == null)
            return;

        if (isAdmin)
            initAdminGui();
        else
            initUserGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.renderBackground(mouseX, mouseY, partialTicks);
        this.drawWidgets(mc, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    private void onCreateTeam(ButtonWidget widget, int mouseX, int mouseY, int button) {

    }

    private void onInviteMember(ButtonWidget widget, int mouseX, int mouseY, int button) {

    }

    private void onDisbandTeam(ButtonWidget widget, int mouseX, int mouseY, int button) {

    }

    private void onOpenPartySettings(ButtonWidget widget, int mouseX, int mouseY, int button) {

    }

    @Override
    public void onGameDataReceived(GameData data) {
        this.initGui();
    }

    @Override
    public void onPartyDataReceived(PartyData data) {
        this.initGui();
    }

    static {
        Style bold = new Style();
        bold.setBold(true);

        TEAM_TITLE.setStyle(bold);
    }
}
