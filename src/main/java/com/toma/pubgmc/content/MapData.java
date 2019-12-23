package com.toma.pubgmc.content;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.File;

public class MapData {

    public final String displayName;
    public final String downloadLink;
    public final boolean isDownloaded;
    public final String version;
    public final String credits;
    public final String[] authors;
    public final String[] description;
    public final String[] modes;

    private MapData(Builder builder, File[] files) {
        this.displayName = builder.name;
        this.downloadLink = builder.url;
        this.version = builder.version;
        this.credits = builder.credits;
        this.authors = builder.authors;
        this.description = builder.description;
        this.modes = builder.modes;
        this.isDownloaded = this.isDownloaded(files);
    }

    public static MapData get(JsonObject object, File[] mapFiles) {
        return new Builder(mapFiles)
                .name(object.has("name") ? object.get("name").toString() : null)
                .url(object.has("url") ? object.get("url").toString() : null)
                .version(object.has("version") ? object.get("version").toString() : "Unknown")
                .credits(object.has("credits") ? object.get("credits").toString() : "")
                .authorList(object.has("author") ? convertToStringArray(object.getAsJsonArray("author")) : new String[]{"Unknown"})
                .description(object.has("description") ? convertToStringArray(object.getAsJsonArray("description")) : new String[]{"No description provided", "Ask game author to provide more info"})
                .modes(object.has("modes") ? convertToStringArray(object.getAsJsonArray("modes")) : null)
                .build();
    }

    private static String[] convertToStringArray(JsonArray array) {
        String[] arr = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            arr[i] = array.get(i).toString();
        }
        return arr;
    }

    private boolean isDownloaded(File[] mapFiles) {
        for (File file : mapFiles) {
            if(file.getName().equalsIgnoreCase(this.displayName)) {
                return true;
            }
        }
        return false;
    }

    private static class Builder {
        private String name, url, version, credits;
        private String[] authors, description, modes;
        private boolean valid = true;
        private final File[] files;

        public Builder(final File[] files) {
            this.files = files;
        }

        private Builder name(String name) {
            this.validate(name);
            this.name = name;
            return this;
        }

        private Builder url(String url) {
            this.validate(url);
            this.url = url;
            return this;
        }

        private Builder version(String version) {
            this.version = version;
            return this;
        }

        private Builder credits(String credits) {
            this.credits = credits;
            return this;
        }

        private Builder authorList(String[] authors) {
            this.authors = authors;
            return this;
        }

        private Builder description(String[] description) {
            this.description = description;
            return this;
        }

        private Builder modes(String[] modes) {
            this.validate(modes);
            this.modes = modes;
            return this;
        }

        private MapData build() {
            return this.valid ? new MapData(this, files) : null;
        }

        private void validate(String s) {
            this.valid = s == null || s.isEmpty() ? false : this.valid;
        }

        private void validate(String[] s) {
            this.valid = s == null || s.length < 1 ? false : this.valid;
        }
    }
}
