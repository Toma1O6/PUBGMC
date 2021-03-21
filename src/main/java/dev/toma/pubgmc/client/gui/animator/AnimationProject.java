package dev.toma.pubgmc.client.gui.animator;

import dev.toma.pubgmc.client.animation.AnimationElement;
import dev.toma.pubgmc.client.animation.interfaces.KeyFrame;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimationProject {

    Map<AnimationElement, List<KeyFrame>> animation;
    String name;
    File workingFile;
    boolean isSaved;

    AnimationProject() {
        animation = new HashMap<>();
    }

    public void save() {
        if(workingFile == null) {

        }
    }

    public void saveAs(String path) {

    }
}
