package com.toma.pubgmc.content;

import net.minecraft.client.Minecraft;

public enum MapButtonState {

    NORMAL((x, y, w, h, mc) -> {
        // nothing :)
    }),

    DOWNLOADING((x, y, w, h, mc) -> {
        String s = "Downloading...";
        mc.fontRenderer.drawStringWithShadow(s, x + w - (mc.fontRenderer.getStringWidth(s) + 10), y + 15, 0xFFFFFFFF);
    }),

    EXTRACTING((x, y, w, h, mc) -> {
        String s = "Extracting ZIP...";
        mc.fontRenderer.drawStringWithShadow(s, x + w - (mc.fontRenderer.getStringWidth(s) + 10), y + 15, 0xFFFFFFFF);
    }),

    FAILED((x, y, w, h, mc) -> {
        String s = "Something went wrong! Check logs";
        mc.fontRenderer.drawStringWithShadow(s, x + w - (mc.fontRenderer.getStringWidth(s) + 10), y + 15, 0xFFFF0000);
    });

    private ButtonConsumer consumer;

    MapButtonState(final ButtonConsumer consumer) {
        this.consumer = consumer;
    }

    public void drawButtonOverlay(int x, int y, int w, int h, Minecraft mc) {
        this.consumer.apply(x, y, w, h, mc);
    }

    private interface ButtonConsumer {

        void apply(int x, int y, int w, int h, Minecraft mc);
    }
}
