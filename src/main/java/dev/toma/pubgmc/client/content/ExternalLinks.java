package dev.toma.pubgmc.client.content;

import com.google.gson.*;
import net.minecraft.util.JsonUtils;

import java.lang.reflect.Type;

public final class ExternalLinks {

    private static final String DEF_DISCORD = "https://discord.gg/WEFYxwS8E3";
    private static final String DEF_PATREON = "https://www.patreon.com/tnt_team";
    private static final String DEF_HOMEPAGE = "https://www.curseforge.com/minecraft/mc-mods/pubgmc-mod";
    public static final ExternalLinks DEFAULT = new ExternalLinks(DEF_DISCORD, DEF_PATREON, DEF_HOMEPAGE);

    private final String discordInvite;
    private final String patreonPage;
    private final String homepage;

    private ExternalLinks(String discordInvite, String patreonPage, String homepage) {
        this.discordInvite = discordInvite;
        this.patreonPage = patreonPage;
        this.homepage = homepage;
    }

    public String getDiscordLink() {
        return discordInvite;
    }

    public String getPatreonLink() {
        return patreonPage;
    }

    public String getHomepageLink() {
        return homepage;
    }

    public static final class Deserializer implements JsonDeserializer<ExternalLinks> {

        @Override
        public ExternalLinks deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (!json.isJsonObject())
                throw new JsonParseException("Received invalid data - links must be defined inside JSON Object");
            JsonObject object = json.getAsJsonObject();
            String discord = JsonUtils.getString(object, "discord", DEF_DISCORD);
            String patreon = JsonUtils.getString(object, "patreon", DEF_PATREON);
            String homepage = JsonUtils.getString(object, "homepage", DEF_HOMEPAGE);
            return new ExternalLinks(discord, patreon, homepage);
        }
    }
}
