package dev.toma.pubgmc.api.client.game.minimap;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.util.helper.ImageUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Locale;

public final class Minimap {

    private final ResourceLocation icon;
    private final GameMap map;

    public Minimap(ResourceLocation icon, GameMap map) {
        this.icon = icon;
        this.map = map;
    }

    public void renderBackground(double x1, double y1, double width, double height) {
        Minecraft minecraft = Minecraft.getMinecraft();
        ImageUtil.drawCustomSizedImage(minecraft, icon, x1, y1, width, height, false);
    }

    @Nullable
    public static Minimap forGameMap(GameMap map) {
        ResourceLocation mapIcon = new ResourceLocation(Pubgmc.MOD_ID, "textures/map/" + map.getMapName().toLowerCase(Locale.ROOT) + ".png");
        Minecraft minecraft = Minecraft.getMinecraft();
        try (IResource resource = minecraft.getResourceManager().getResource(mapIcon)) {
            return new Minimap(mapIcon, map);
        } catch (IOException e) {
            return null;
        }
    }
}
