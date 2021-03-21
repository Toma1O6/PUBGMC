package dev.toma.pubgmc.client.gui.animator;

import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;
import dev.toma.pubgmc.client.animation.AnimationLoader;
import dev.toma.pubgmc.client.animation.AnimationSpec;
import net.minecraft.util.ResourceLocation;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class AnimatorCache {
    public static AnimationProject project;
    public static Map<String, GuiAnimator.WrappedAnimationSpec> animations;

    public static void refreshAnimations(AnimationLoader loader, IPopupHandler handler) {
        animations = new TreeMap<>((o1, o2) -> {
            boolean flag0 = o1.contains(":");
            boolean flag1 = o2.contains(":");
            return flag0 && !flag1 ? -1 : !flag0 && flag1 ? 1 : 0;
        });
        for (Map.Entry<ResourceLocation, AnimationSpec> entry : loader.getAnimations().entrySet()) {
            String key = entry.getKey().toString();
            animations.put(key, new GuiAnimator.WrappedAnimationSpec(key, entry.getValue()));
        }
        File file = new File("./export/animations");
        loadAnimationFile(loader, file, handler);
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
                String name = file.getName().replaceFirst("[.][^.]+$", "");
                animations.put(name, new GuiAnimator.WrappedAnimationSpec(name, file.getParentFile(), spec, false));
            } catch (JsonParseException parseException) {
                handler.sendError(String.format("%s is corrupted - %s", file.getPath(), parseException.toString()));
            } catch (IOException exception) {
                // ignore
            }
        }
    }
}
