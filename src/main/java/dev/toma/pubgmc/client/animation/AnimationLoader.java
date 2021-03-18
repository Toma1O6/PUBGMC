package dev.toma.pubgmc.client.animation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;
import dev.toma.pubgmc.client.animation.interfaces.KeyFrame;
import dev.toma.pubgmc.client.animation.serializers.AnimationSpecSerializer;
import dev.toma.pubgmc.client.animation.serializers.KeyFrameSerializer;
import dev.toma.pubgmc.client.animation.serializers.Vec3dSerializer;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.resource.IResourceType;
import net.minecraftforge.client.resource.ISelectiveResourceReloadListener;
import net.minecraftforge.fml.common.ProgressManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class AnimationLoader implements ISelectiveResourceReloadListener {

    private static final Logger log = LogManager.getLogger("Animation Loader");
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Vec3d.class, new Vec3dSerializer())
            .registerTypeAdapter(KeyFrame.class, new KeyFrameSerializer())
            .registerTypeAdapter(AnimationSpec.class, new AnimationSpecSerializer())
            .create();
    private List<ResourceLocation> paths = new ArrayList<>();
    private final Map<ResourceLocation, AnimationSpec> animationSpecMap = new HashMap<>();

    public void registerEntries(ResourceLocation... locations) {
        for (ResourceLocation location : locations)
            registerEntry(location);
    }

    public void registerEntry(ResourceLocation location) {
        paths.add(location);
    }

    public AnimationSpec getAnimationSpecification(ResourceLocation location) {
        return animationSpecMap.get(location);
    }

    public AnimationSpec load(File file) {
        try {
            JsonReader reader = new JsonReader(new FileReader(file));
            AnimationSpec spec = GSON.fromJson(reader, AnimationSpec.class);
            reader.close();
            return spec;
        } catch (IOException e) {
            log.fatal("Exception occurred while parsing animation spec file: ", e);
            return null;
        }
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager, Predicate<IResourceType> resourcePredicate) {
        ProgressManager.ProgressBar bar = ProgressManager.push("Animation loader:", paths.size());
        for (ResourceLocation path : paths) {
            try {
                bar.step(path.toString());
                loadResource(resourceManager, new ResourceLocation(path.getResourceDomain(), String.format("animations/%s.json", path.getResourcePath())));
            } catch (Exception e) {
                log.error("Exception occurred while loading file {}: {}", path.toString(), e);
            }
        }
        paths = null;
        ProgressManager.pop(bar);
    }

    public void loadResource(IResourceManager manager, ResourceLocation path) throws IOException {
        IResource resource = manager.getResource(path);
        JsonReader reader = new JsonReader(new InputStreamReader(resource.getInputStream()));
        try {
            animationSpecMap.put(path, GSON.fromJson(reader, AnimationSpec.class));
        } catch (JsonParseException ex) {
            log.error("File {} has invalid syntax! {}", path, ex);
        }
    }
}
