package dev.toma.pubgmc.client.content;

import com.google.gson.*;
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
    private boolean changed;

    public ContentResult(MenuDisplayContent[] menuDisplayContents, String[] news, String[] vipPatrons) {
        this.menuDisplayContents = menuDisplayContents;
        this.news = news;
        this.vipPatrons = vipPatrons;
    }

    public void updateModifiable(ContentResult other) {
        this.changed = this.menuDisplayContents.length != other.menuDisplayContents.length || !Arrays.equals(news, other.news);
        this.menuDisplayContents = other.menuDisplayContents;
        this.news = other.news;
        Minecraft mc = Minecraft.getMinecraft();
        if(changed && mc.currentScreen instanceof RefreshListener) {
            synchronized (Minecraft.getMinecraft()) {
                ((RefreshListener) mc.currentScreen).onRefresh();
            }
        }
    }

    public boolean hasChanged() {
        return changed;
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

    public static class Deserializer implements JsonDeserializer<ContentResult> {

        @Override
        public ContentResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if(!json.isJsonObject()) throw new JsonParseException("Received invalid data - not a Json Object");
            JsonObject object = json.getAsJsonObject();
            JsonArray displayArray = JsonUtils.getJsonArray(object, "display");
            JsonArray newsArray = JsonUtils.getJsonArray(object, "mainMenuText");
            JsonArray mapDataArray = JsonUtils.getJsonArray(object, "map");
            JsonArray vipPatronArray = JsonUtils.getJsonArray(object, "vips");
            List<MenuDisplayContent> menuDisplayContentList = new ArrayList<>();
            for (JsonElement element : displayArray) {
                menuDisplayContentList.add(context.deserialize(element, MenuDisplayContent.class));
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
            return new ContentResult(menuDisplayContentList.toArray(new MenuDisplayContent[0]), stringList.toArray(new String[0]), vips);
        }
    }
}
