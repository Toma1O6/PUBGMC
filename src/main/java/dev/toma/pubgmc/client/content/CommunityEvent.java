package dev.toma.pubgmc.client.content;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.toma.pubgmc.client.gui.menu.GuiMenu;
import dev.toma.pubgmc.client.gui.widget.Widget;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Objects;

public class CommunityEvent extends MenuDisplayContent {

    private final String name;
    private final LocalDateTime dateTime;
    private final String host;
    @Nullable
    private final String[] description;
    @Nullable
    private String ip;
    private final DateComparationContext ctx;

    private CommunityEvent(@Nullable String name, LocalDateTime dateTime, @Nullable String host, @Nullable String[] description, @Nullable String ip) {
        this.name = name != null && !name.isEmpty() ? name : "Unnamed event";
        this.dateTime = Objects.requireNonNull(dateTime, "Undefined event schedule");
        this.host = host != null && !host.isEmpty() ? host : "Unkown";
        this.description = description;
        this.ip = ip;
        this.ctx = DateComparationContext.from(LocalDateTime.now(Clock.systemUTC()), dateTime);
    }

    public static CommunityEvent deserialize(JsonObject object, JsonDeserializationContext ctx) {
        String name = object.get("name").getAsString();
        String host = object.get("host").getAsString();
        JsonArray description = object.getAsJsonArray("description");
        int pos = 0;
        String[] strings = new String[description.size()];
        for (JsonElement element : description) {
            strings[pos] = element.getAsString();
            ++pos;
        }
        LocalDateTime dateTime = LocalDateTime.parse(object.get("date").getAsString());
        String ip = object.has("ip") ? object.get("ip").getAsString() : null;
        return new CommunityEvent(name, dateTime, host, strings, ip);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Widget createWidget(GuiMenu parent, int x, int y, int width, int height) {
        return new EventWidget(parent, x, y, width, height, this);
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getScheduledDateTime() {
        return dateTime;
    }

    public String getHost() {
        return host;
    }

    @Nullable
    public String[] getDescription() {
        return description;
    }

    public boolean hasDescription() {
        return description != null && description.length > 0;
    }

    public boolean isLive() {
        return ip != null && !ip.isEmpty();
    }

    @Nullable
    public String getIP() {
        return ip;
    }

    public void setIp(@Nullable String ip) {
        this.ip = ip;
    }

    public DateComparationContext getComparationContext() {
        return ctx;
    }
}
