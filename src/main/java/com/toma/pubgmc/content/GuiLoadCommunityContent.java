package com.toma.pubgmc.content;

import com.google.common.io.ByteStreams;
import com.toma.pubgmc.Pubgmc;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GuiLoadCommunityContent extends GuiScreen {

    private boolean hasCheckedData = false;
    private URL dataURL;
    private static final ResourceLocation TEXTURE = new ResourceLocation("");
    private String jsonData;
    private File[] mapFiles;
    private ProgressStatus status;

    public GuiLoadCommunityContent() {
        try {
            this.dataURL = new URL("https://raw.githubusercontent.com/Toma1O6/mod-utilities/master/pmc-community-data.json");
        } catch (MalformedURLException e) {
            this.safeLaunch();
            e.printStackTrace();
        }
    }

    @Override
    public void initGui() {
        if(!hasCheckedData) {
            this.hasCheckedData = true;
            this.updateStatus(ProgressStatus.INITIALIZING);
            File file = new File(mc.mcDataDir, "pubgmc content");
            boolean flag = true;
            if(!file.exists()) {
                try {
                    flag = file.createNewFile();
                } catch (Exception e) {
                    flag = false;
                }
            }
            if(!flag) {
                this.safeLaunch();
                return;
            }
            this.mapFiles = file.listFiles();
            this.loadCommunityMaps();
        }
    }

    public void loadCommunityMaps() {
        // TODO - improve
        new Thread("Community content") {
            @SuppressWarnings("UnstableApiUsage")
            @Override
            public void run() {
                try {
                    Pubgmc.logger.info("Checking {} for community map data", dataURL);
                    updateStatus(ProgressStatus.CONNETING);
                    InputStream stream = this.openStream(dataURL);
                    String commData = new String(ByteStreams.toByteArray(stream), StandardCharsets.UTF_8);
                    stream.close();
                    updateStatus(ProgressStatus.PROCESSING);
                    this.process(commData);
                    updateStatus(ProgressStatus.READY);
                } catch (Exception e) {
                    updateStatus(ProgressStatus.FAILED);
                    e.printStackTrace();
                }
            }

            private void process(String data) {
                this.parse(data);

            }

            private void parse(String data) {

            }

            private InputStream openStream(URL url) throws IOException {
                return url.openConnection().getInputStream();
            }
        }.start();
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0: break;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.color(1f, 1f, 1f, 1f);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder builder = tessellator.getBuffer();
        float c = 0.25f;
        builder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        builder.pos(0, mc.displayHeight, 0).color(c, c, c, 1.0F).endVertex();
        builder.pos(mc.displayWidth, mc.displayHeight, 0).color(c, c, c, 1.0F).endVertex();
        builder.pos(mc.displayWidth, 0, 0).color(c, c, c, 1.0F).endVertex();
        builder.pos(0, 0, 0).color(c, c, c, 1.0F).endVertex();
        tessellator.draw();
        int w = (int)(this.width / 1.25F);
        int h = 20;
        int x = this.width / 2 - w / 2;
        int y = this.height - 50;
        builder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        builder.pos(x - 1, y + h + 1, 0).color(0F, 0F, 0F, 1).endVertex();
        builder.pos(x + w + 1, y + h + 1, 0).color(0F, 0F, 0F, 1).endVertex();
        builder.pos(x + w + 1, y - 1, 0).color(0F, 0F, 0F, 1).endVertex();
        builder.pos(x - 1, y - 1, 0).color(0F, 0F, 0F, 1).endVertex();
        tessellator.draw();
        builder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        builder.pos(x, y + h, 0).color(0.2F, 0.8F, 0.0F, 1.0F).endVertex();
        builder.pos(x + w, y + h, 0).color(0.8F, 0.0F, 0.0F, 1.0F).endVertex();
        builder.pos(x + w, y, 0).color(0.8F, 0.0F, 0.0F, 1.0F).endVertex();
        builder.pos(x, y, 0).color(0.2F, 0.8F, 0.0F, 1.0F).endVertex();
        tessellator.draw();
    }

    public synchronized void updateStatus(ProgressStatus status) {
        this.status = status;
        // TODO - update button
    }

    public void safeLaunch() {
        Pubgmc.logger.fatal("Exception occurred in custom menu!");
        this.updateStatus(ProgressStatus.FAILED);
    }

    private enum ProgressStatus {
        INITIALIZING("Initializing"),
        CONNETING("Connecting"),
        PROCESSING("Processing"),
        READY("Ready", 0x33FF33, true),
        FAILED("Failed", 0xFF0000, true);

        private final String displayName;
        private final int color;
        private boolean flag;

        ProgressStatus(String displayName) {
            this(displayName, 0, false);
        }

        ProgressStatus(String displayName, int color, boolean flag) {
            this.displayName = displayName;
            this.color = color;
            this.flag = flag;
        }

        public String getDisplayName() {
            return displayName;
        }

        public int getColor() {
            return color;
        }
    }
}
