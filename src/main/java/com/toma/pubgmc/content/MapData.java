package com.toma.pubgmc.content;

import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class MapData {

    public String fileName;
    public String displayName;
    public String downloadLink;
    public boolean isDownloaded;
    @Nullable
    public ResourceLocation icon;
    public String version;
    public String credits;
    public String[] authors;
    public String[] description;
}
