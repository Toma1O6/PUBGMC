package com.toma.pubgmc.content;

import com.google.common.io.ByteStreams;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GuiLoadCommunityContent extends GuiScreen {

    private static final Logger log = LogManager.getLogger("PMC Community Content");
    private static URL dataURL;
    static {
        try {
            dataURL = new URL("https://raw.githubusercontent.com/Toma1O6/mod-utilities/master/pmc-community-data.json");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private static final ResourceLocation TEXTURE = new ResourceLocation("");
    private String jsonData;

    public GuiLoadCommunityContent() {
        this.loadCommunityMaps();
    }

    public void loadCommunityMaps() {
        // TODO - improve
        new Thread("PUBGMC - Community content") {
            @SuppressWarnings("UnstableApiUsage")
            @Override
            public void run() {
                try {
                    log.info("Checking {} for community map data", dataURL);
                    // TODO: update - connecting
                    InputStream stream = this.openStream(dataURL);
                    String commData = new String(ByteStreams.toByteArray(stream), StandardCharsets.UTF_8);
                    stream.close();
                    // TODO: update - processing
                    this.parse(commData);
                    // TODO: update - finished
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
