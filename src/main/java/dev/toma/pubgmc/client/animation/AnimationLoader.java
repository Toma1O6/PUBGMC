package dev.toma.pubgmc.client.animation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
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

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class AnimationLoader implements ISelectiveResourceReloadListener {

    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Vec3d.class, new Vec3dSerializer())
            .registerTypeHierarchyAdapter(KeyFrame.class, new KeyFrameSerializer())
            .registerTypeAdapter(AnimationSpec.class, new AnimationSpecSerializer())
            .create();
    private final List<ResourceLocation> paths = new ArrayList<>();
    private static final Logger log = LogManager.getLogger("Animation Loader");
    private final Map<ResourceLocation, AnimationSpec> animationSpecMap = new HashMap<>();

    public void registerEntries(ResourceLocation... locations) {
        for (ResourceLocation location : locations)
            registerEntry(location);
    }

    public void registerEntry(ResourceLocation location) {
        paths.add(location);
    }

    public AnimationSpec getAnimationSpecification(ResourceLocation location) {
        AnimationSpec spec = animationSpecMap.get(location);
        if(spec == null) {
            spec = AnimationSpec.EMPTY_SPEC;
            animationSpecMap.put(location, spec);
            log.error("Couldn't lookup animation for {} key, falling back to default animation", location);
        }
        return spec;
    }

    public Map<ResourceLocation, AnimationSpec> getAnimations() {
        return animationSpecMap;
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager, Predicate<IResourceType> resourcePredicate) {
        ProgressManager.ProgressBar bar = ProgressManager.push("Baking animations", paths.size());
        animationSpecMap.clear();
        for (ResourceLocation path : paths) {
            try {
                bar.step(path.toString());
                loadResource(resourceManager, new ResourceLocation(path.getResourceDomain(), String.format("animations/%s.json", path.getResourcePath())), path);
            } catch (Exception e) {
                log.error("Exception occurred while loading file {}: {}", path.toString(), e);
            }
        }
        ProgressManager.pop(bar);
    }

    public void loadResource(IResourceManager manager, ResourceLocation filePath, ResourceLocation oldPath) throws IOException {
        IResource resource = manager.getResource(filePath);
        JsonReader reader = new JsonReader(new InputStreamReader(resource.getInputStream()));
        try {
            AnimationSpec spec = load(reader);
            animationSpecMap.put(oldPath, spec);
        } catch (JsonParseException ex) {
            log.error("File {} has invalid syntax! {}", filePath, ex);
        }
    }

    public AnimationSpec load(JsonReader reader) throws JsonSyntaxException {
        return GSON.fromJson(reader, AnimationSpec.class);
    }
}
