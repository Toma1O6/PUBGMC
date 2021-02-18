package dev.toma.pubgmc.content;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.File;

public class MapData {

    public final String displayName;
    public final String downloadLink;
    public boolean isDownloaded;
    public final boolean isNew;
    public final String version;
    public final String[] authors;
    public final String[] modes;

    private MapData(Builder builder, File[] files) {
        this.displayName = builder.name;
        this.downloadLink = builder.url;
        this.version = builder.version;
        this.authors = builder.authors;
        this.modes = builder.modes;
        this.isDownloaded = this.isDownloaded(files);
        this.isNew = builder.menuEffect;
    }

    public static MapData get(JsonObject object, File[] mapFiles) {
        return new Builder(mapFiles)
                .name(object.has("name") ? object.get("name").getAsString() : null)
                .url(object.has("url") ? object.get("url").getAsString() : null)
                .version(object.has("version") ? object.get("version").getAsString() : "Unknown")
                .authorList(object.has("author") ? convertToStringArray(object.getAsJsonArray("author")) : new String[]{"Unknown"})
                .modes(object.has("modes") ? convertToStringArray(object.getAsJsonArray("modes")) : null)
                .state(object.has("effect") ? object.get("effect").getAsBoolean() : false)
                .build();
    }

    public void finishDownload() {
        this.isDownloaded = true;
    }

    private static String[] convertToStringArray(JsonArray array) {
        String[] arr = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            arr[i] = array.get(i).getAsString();
        }
        return arr;
    }

    private boolean isDownloaded(File[] mapFiles) {
        for (File file : mapFiles) {
            if(file.isDirectory() && file.getName().equalsIgnoreCase(this.displayName)) {
                return true;
            }
        }
        return false;
    }

    private static class Builder {
        private String name, url, version, credits;
        private String[] authors, description, modes;
        private boolean valid = true;
        private boolean menuEffect = false;
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

        private Builder authorList(String[] authors) {
            this.authors = authors;
            return this;
        }

        private Builder modes(String[] modes) {
            this.validate(modes);
            this.modes = modes;
            return this;
        }

        private Builder state(boolean state) {
            this.menuEffect = state;
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
