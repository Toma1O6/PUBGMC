package dev.toma.pubgmc.client.gui.animator;

import dev.toma.pubgmc.DevUtil;
import dev.toma.pubgmc.client.animation.AnimationElement;
import dev.toma.pubgmc.client.animation.AnimationLoader;
import dev.toma.pubgmc.client.animation.AnimationSpec;
import dev.toma.pubgmc.client.animation.interfaces.KeyFrame;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnimationProject {

    Map<AnimationElement, List<MutableKeyFrame>> animation;
    String name = "";
    File workingFile;
    boolean isSaved;
    float animationProgress;
    int length = 40;

    public AnimationProject(GuiAnimator.WrappedAnimationSpec wrappedSpec) {
        this.animation = convertToMutable(wrappedSpec.spec);
        this.name = wrappedSpec.name;
        this.workingFile = wrappedSpec.dir;
        this.isSaved = false;
    }

    public AnimationProject(AnimationSpec spec) {
        this.animation = convertToMutable(spec);
        this.isSaved = true;
        this.workingFile = new File("./export/animations");
    }

    AnimationProject() {
        this(new AnimationSpec(new HashMap<>()));
    }

    private static Map<AnimationElement, List<MutableKeyFrame>> convertToMutable(AnimationSpec spec) {
        Map<AnimationElement, List<MutableKeyFrame>> def = new HashMap<>();
        for (Map.Entry<AnimationElement, List<KeyFrame>> entry : spec.getFrameDefs().entrySet()) {
            List<MutableKeyFrame> list = entry.getValue().stream()
                    .map(MutableKeyFrame::fromImmutable)
                    .sorted((o1, o2) -> Float.compare(o1.endpoint, o2.endpoint))
                    .collect(Collectors.toList());
            def.put(entry.getKey(), list);
        }
        return def;
    }

    public void setAnimationProgress(float animationProgress) {
        this.animationProgress = animationProgress;
    }

    public AnimationProject named(String name) {
        this.name = name;
        return this;
    }

    public void markModified() {
        isSaved = false;
    }

    public void save() {
        try {
            File file = new File(workingFile, name + ".json");
            if (file.exists()) {
                AnimationSpec spec = new AnimationSpec(toImmutable());
                String content = AnimationLoader.GSON.toJson(spec, AnimationSpec.class).replaceAll("\\s", "");
                FileWriter writer = new FileWriter(file);
                writer.write(content);
                writer.close();
                isSaved = true;
            }
        } catch (IOException ex) {
            //
        }
    }

    public void saveAs(String path) {
        try {
            if (!workingFile.exists())
                workingFile.mkdirs();
            File file = new File(workingFile, path + ".json");
            if (!file.exists())
                file.createNewFile();
            AnimationSpec spec = new AnimationSpec(toImmutable());
            String content = AnimationLoader.GSON.toJson(spec, AnimationSpec.class).replaceAll("\\s", "");
            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.close();
            isSaved = true;
        } catch (IOException ex) {
            //
        }
    }

    public void add(AnimationElement element, MutableKeyFrame frame) {
        List<MutableKeyFrame> list = animation.computeIfAbsent(element, element1 -> new ArrayList<>());
        list.add(frame);
        list.sort((o1, o2) -> Float.compare(o1.endpoint, o2.endpoint));
        markModified();
    }

    public void remove(AnimationElement element, MutableKeyFrame frame) {
        List<MutableKeyFrame> frameList = animation.get(element);
        if (frameList != null) {
            frameList.remove(frame);
        }
        if (frameList.isEmpty())
            animation.remove(element);
        markModified();
    }

    public Map<AnimationElement, List<MutableKeyFrame>> getAnimation() {
        return animation;
    }

    public Map<AnimationElement, List<KeyFrame>> toImmutable() {
        return DevUtil.make(new HashMap<>(), map -> {
            for (Map.Entry<AnimationElement, List<MutableKeyFrame>> entry : animation.entrySet()) {
                List<KeyFrame> frames = new ArrayList<>(entry.getValue());
                map.put(entry.getKey(), frames);
            }
        });
    }

    public void addLength(int amount) {
        setLength(length + amount);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = DevUtil.wrap(length, 1, 1200);
    }
}
