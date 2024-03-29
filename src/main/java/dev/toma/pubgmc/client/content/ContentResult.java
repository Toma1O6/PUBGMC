package dev.toma.pubgmc.client.content;

import com.google.gson.*;
import dev.toma.pubgmc.Pubgmc;
import net.minecraft.client.Minecraft;
import net.minecraft.util.JsonUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContentResult {

    private MenuDisplayContent[] menuDisplayContents;
    private String[] news;
    private String[] vipPatrons;
    private ExternalLinks externalLinks;
    private int version;
    private boolean changed;

    public ContentResult(MenuDisplayContent[] menuDisplayContents, String[] news, String[] vipPatrons, int version, ExternalLinks externalLinks) {
        this.menuDisplayContents = menuDisplayContents;
        this.news = news;
        this.vipPatrons = vipPatrons;
        this.version = version;
        this.externalLinks = externalLinks;
    }

    public void updateModifiable(ContentResult other) {
        this.changed = this.menuDisplayContents.length != other.menuDisplayContents.length || !Arrays.equals(news, other.news) || !Arrays.equals(vipPatrons, other.vipPatrons);
        this.menuDisplayContents = other.menuDisplayContents;
        this.news = other.news;
        this.vipPatrons = other.vipPatrons;
        this.version = other.version;
        this.externalLinks = other.externalLinks;
        Minecraft mc = Minecraft.getMinecraft();
        if (changed && mc.currentScreen instanceof RefreshListener) {
            synchronized (Minecraft.getMinecraft()) {
                ((RefreshListener) mc.currentScreen).onRefresh();
            }
        }
    }

    public boolean hasChanged() {
        return changed;
    }

    public boolean isSupportedVersion() {
        return version == Pubgmc.CONTENT_DATA_VERSION;
    }

    public MenuDisplayContent[] getMenuDisplayContents() {
        return menuDisplayContents;
    }

    public String[] getNews() {
        return news;
    }

    public String[] getVipPatrons() {
        return vipPatrons;
    }

    public ExternalLinks getExternalLinks() {
        return externalLinks;
    }

    public static class Deserializer implements JsonDeserializer<ContentResult> {

        @Override
        public ContentResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (!json.isJsonObject()) throw new JsonParseException("Received invalid data - not a Json Object");
            JsonObject object = json.getAsJsonObject();
            int version = JsonUtils.getInt(object, "version", 1);
            if (version == Pubgmc.CONTENT_DATA_VERSION) {
                JsonArray displayArray = JsonUtils.getJsonArray(object, "display");
                JsonArray newsArray = JsonUtils.getJsonArray(object, "mainMenuText");
                JsonArray mapDataArray = JsonUtils.getJsonArray(object, "map");
                JsonArray vipPatronArray = JsonUtils.getJsonArray(object, "vips");
                List<MenuDisplayContent> menuDisplayContentList = new ArrayList<>();
                for (JsonElement element : displayArray) {
                    try {
                        menuDisplayContentList.add(context.deserialize(element, MenuDisplayContent.class));
                    } catch (JsonParseException ex) {
                        ContentManager.log.error("Skipping event loading: {}", ex.getMessage());
                    }
                }
                List<String> stringList = new ArrayList<>();
                for (JsonElement element : newsArray) {
                    stringList.add(element.getAsString());
                }
                /*List<MapData> mapDataList = new ArrayList<>();
                for (JsonElement element : mapDataArray) {
                    mapDataList.add(context.deserialize(element, MapData.class));
                }*/
                String[] vips = new String[vipPatronArray.size()];
                for (int i = 0; i < vipPatronArray.size(); i++) {
                    vips[i] = vipPatronArray.get(i).getAsString();
                }
                ExternalLinks extLinks = context.deserialize(JsonUtils.getJsonObject(object, "urls"), ExternalLinks.class);
                return new ContentResult(menuDisplayContentList.toArray(new MenuDisplayContent[0]), stringList.toArray(new String[0]), vips, version, extLinks);
            }
            return new ContentResult(new MenuDisplayContent[0], new String[0], new String[0], version, ExternalLinks.DEFAULT);
        }
    }
}
