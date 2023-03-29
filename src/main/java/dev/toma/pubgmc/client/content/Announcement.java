package dev.toma.pubgmc.client.content;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import dev.toma.pubgmc.client.gui.menu.GuiMenu;
import dev.toma.pubgmc.client.gui.widget.Widget;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Announcement extends MenuDisplayContent {

    final String title;
    final String text;
    final String url;

    public Announcement(String title, String link, String text) {
        this.title = title;
        this.text = text;
        this.url = link;
    }

    public static Announcement deserialize(JsonObject object, JsonDeserializationContext ctx) {
        String title = object.get("title").getAsString();
        String text = object.get("text").getAsString();
        String url = null;
        if (object.has("url")) {
            url = object.get("url").getAsString();
        }
        return new Announcement(title, url, text);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Widget createWidget(GuiMenu parent, int x, int y, int width, int height) {
        return new AnnouncementWidget(parent, x, y, width, height, this);
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public boolean hasLink() {
        return url != null && !url.isEmpty();
    }

    public String getUrl() {
        return url;
    }
}
