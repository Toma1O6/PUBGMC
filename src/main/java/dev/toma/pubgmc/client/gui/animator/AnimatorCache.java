package dev.toma.pubgmc.client.gui.animator;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonReader;
import dev.toma.pubgmc.client.animation.AnimationLoader;
import dev.toma.pubgmc.client.animation.AnimationSpec;
import net.minecraft.util.ResourceLocation;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AnimatorCache {

    public static AnimationProject project = new AnimationProject();
    public static Map<String, GuiAnimator.WrappedAnimationSpec> animations;

    public static void refreshAnimations(AnimationLoader loader, IPopupHandler handler) {
        animations = new TreeMap<>(AnimatorCache::compareResourceLocations);
        for (Map.Entry<ResourceLocation, AnimationSpec> entry : loader.getAnimations().entrySet()) {
            String key = entry.getKey().toString();
            animations.put(key, new GuiAnimator.WrappedAnimationSpec(key, entry.getValue()));
        }
        File file = new File("./export/animations");
        loadAnimationFile(loader, file, handler);
        //onLoadedTemp();
    }

    // TODO remove
    public static void onLoadedTemp() {
        String name = "kar98k";
        int baseReloadTime = 24;
        int setupTime = 10;
        AnimationSpec start = animations.get("kar_start").spec;
        AnimationSpec bullet = animations.get("kar_bullet").spec;
        AnimationSpec end = animations.get("kar_end").spec;
        int repeats = 5;
        for (int i = 0; i < repeats; i++) {
            int bulletsInserted = i + 1;
            int reloadTime = baseReloadTime * bulletsInserted;
            double half = (setupTime / 2.0) / reloadTime;
            List<GuiConnectAnimations.ConnectionSpec> specList = new ArrayList<>();
            specList.add(new GuiConnectAnimations.ConnectionSpec((float) half, 1, start));
            specList.add(new GuiConnectAnimations.ConnectionSpec(1.0F - (float) (half), bulletsInserted, bullet));
            specList.add(new GuiConnectAnimations.ConnectionSpec(1.0F, 1, end));
            AnimationProject tempProject = new AnimationProject(GuiConnectAnimations.convert(specList, false));
            tempProject.saveAs(name + "_reload_" + (repeats - bulletsInserted));
        }
    }

    static void loadAnimationFile(AnimationLoader loader, File file, IPopupHandler handler) {
        if(file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files)
                loadAnimationFile(loader, f, handler);
            return;
        }
        String path = file.getPath();
        int lastIndex = path.lastIndexOf('.');
        String extension = lastIndex == -1 ? "" : path.substring(lastIndex);
        if(extension.equals(".json")) {
            try {
                JsonReader reader = new JsonReader(new FileReader(file));
                AnimationSpec spec = loader.load(reader);
                if(spec == null)
                    throw new JsonParseException("Unable to parse");
                String name = file.getName().replaceFirst("[.][^.]+$", "");
                animations.put(name, new GuiAnimator.WrappedAnimationSpec(name, file.getParentFile(), spec, false));
            } catch (JsonParseException parseException) {
                handler.sendError(String.format("%s is corrupted - %s", file.getAbsolutePath(), parseException.toString()));
            } catch (IOException exception) {
                handler.sendError(String.format("Error reading file %s - %s", file.getAbsolutePath(), exception.toString()));
            }
        }
    }

    static int compareResourceLocations(String s1, String s2) {
        if(Objects.equals(s1, s2))
            return 0;
        boolean flag0 = s1 != null && s1.contains(":");
        boolean flag1 = s2 != null && s2.contains(":");
        return s1 != null && s2 != null && flag0 == flag1 ? s1.compareTo(s2) : flag0 && !flag1 ? 1 : -1;
    }
}
