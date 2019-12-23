package com.toma.pubgmc.content;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuiMainMenu extends GuiScreen {

    public static final Map<ResourceLocation, MapData[]> DATA = new HashMap<>();

    public GuiMainMenu() {

    }

    public static void createData(final Map<ResourceLocation, List<MapData>> data) {
        for(Map.Entry<ResourceLocation, List<MapData>> entry : data.entrySet()) {
            DATA.put(entry.getKey(), entry.getValue().toArray(new MapData[0]));
        }
    }
}
