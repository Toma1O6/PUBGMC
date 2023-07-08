package dev.toma.pubgmc.client.content;

import com.google.common.collect.Maps;
import com.google.gson.*;
import dev.toma.pubgmc.client.gui.menu.GuiMenu;
import dev.toma.pubgmc.client.gui.widget.Widget;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.Type;
import java.util.Map;

public abstract class MenuDisplayContent {

    private static final Map<String, EventTypeDeserializer<?>> DESERIALIZERS = Maps.newHashMap();

    public static void registerDeserializers() {
        addDeserializer("event", CommunityEvent::deserialize);
        addDeserializer("announcement", Announcement::deserialize);
    }

    @SideOnly(Side.CLIENT)
    public abstract Widget createWidget(GuiMenu parent, int x, int y, int width, int height);

    public static class Deserializer implements JsonDeserializer<MenuDisplayContent> {

        @Override
        public MenuDisplayContent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (!json.isJsonObject())
                throw new JsonParseException("Couldn't parse display content, expected JsonObject");
            JsonObject object = json.getAsJsonObject();
            String type = JsonUtils.getString(object, "type");
            EventTypeDeserializer<?> deserializer = DESERIALIZERS.get(type);
            if (deserializer == null) throw new JsonParseException("Unknown event type: " + type);
            return deserializer.deserialize(object, context);
        }
    }

    static <C extends MenuDisplayContent> void addDeserializer(String key, EventTypeDeserializer<C> deserializer) {
        EventTypeDeserializer<?> value = DESERIALIZERS.put(key, deserializer);
        if (value != null) {
            throw new IllegalStateException("Duplicate deserializer key: " + key);
        }
    }
}
