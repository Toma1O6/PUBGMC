package dev.toma.pubgmc.client.animation.impl;

import dev.toma.pubgmc.DevUtil;
import dev.toma.pubgmc.api.capability.AimInfo;
import dev.toma.pubgmc.api.capability.PlayerDataProvider;
import dev.toma.pubgmc.client.animation.AnimationElement;
import dev.toma.pubgmc.client.animation.AnimationProcessor;
import dev.toma.pubgmc.client.animation.AnimationSpec;
import dev.toma.pubgmc.client.animation.interfaces.Animation;
import net.minecraft.client.Minecraft;

public class AimAnimation implements Animation {

    final AnimationSpec spec;
    final AimInfo info;
    float progress, progressPrev, progressSmooth;

    public AimAnimation(AnimationSpec spec) {
        this.spec = spec;
        this.info = PlayerDataProvider.get(Minecraft.getMinecraft().player).getAimInfo();
    }

    @Override
    public void animateElement(AnimationElement element) {
        spec.getDefs(element).ifPresent(list -> AnimationProcessor.processKeyFrame(list.get(0), progressSmooth));
    }

    @Override
    public void tick() {
        this.progressPrev = this.progress;
        this.progress = info.getProgress();
    }

    @Override
    public void renderTick(float partialTicks) {
        this.progressSmooth = DevUtil.lerp(progress, progressPrev, partialTicks);
    }

    @Override
    public boolean shouldRemove() {
        return info == null;
    }
}
