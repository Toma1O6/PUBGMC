package dev.toma.pubgmc.content;

import com.google.common.io.ByteStreams;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.init.GameRegistry;
import dev.toma.pubgmc.util.DynamicArray;
import dev.toma.pubgmc.util.helper.ImageUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class GuiLoadCommunityContent extends GuiScreen {

    public static boolean compatMode = false;
    public static File contentFolder;

    private boolean hasCheckedData = false;
    private DynamicArray<Message> messages = new DynamicArray<>(10);
    private URL dataURL;
    private static final ResourceLocation TEXTURE = new ResourceLocation("");
    private String jsonData;
    private File[] mapFiles;

    public GuiLoadCommunityContent() {
        try {
            this.dataURL = new URL("https://raw.githubusercontent.com/Toma1O6/mod-utilities/master/pmc-community-data.json");
        } catch (MalformedURLException e) {
            this.safeLaunch();
            e.printStackTrace();
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
    }

    @Override
    public void initGui() {
        GuiButton btn = new GuiButton(0, this.width - 110, this.height - 30, 100, 20, "Continue");
        btn.enabled = dev.toma.pubgmc.content.GuiMainMenu.DATA != null || compatMode;
        this.addButton(btn);
        if(!hasCheckedData) {
            this.hasCheckedData = true;
            contentFolder = new File(mc.mcDataDir, "pubgmc-content");
            boolean flag = true;
            if(!contentFolder.exists()) {
                try {
                    flag = contentFolder.mkdirs();
                } catch (Exception e) {
                    flag = false;
                }
            }
            if(!flag) {
                this.safeLaunch();
                return;
            }
            this.mapFiles = contentFolder.listFiles();
            sendStatusUpdate("Initializing");
            this.loadCommunityMaps();
        }
    }

    public void loadCommunityMaps() {
        new Thread("Community content") {
            @SuppressWarnings("UnstableApiUsage")
            @Override
            public void run() {
                try {
                    Pubgmc.logger.info("Checking {} for community map data", dataURL);
                    sendStatusUpdate("Connecting");
                    InputStream stream = this.openStream(dataURL);
                    sendStatusUpdate("Connected");
                    String commData = new String(ByteStreams.toByteArray(stream), StandardCharsets.UTF_8);
                    stream.close();
                    this.process(commData);
                } catch (Exception e) {
                    compatMode = true;
                    sendStatusUpdate("Failed");
                    e.printStackTrace();
                    GuiLoadCommunityContent gui = GuiLoadCommunityContent.this;
                    gui.buttonList.get(0).enabled = true;
                    gui.addButton(new GuiButton(1, gui.width - 220, gui.height - 30, 100, 20, "Retry"));
                }
            }

            private JsonObject parse(String data) {
                JsonParser parser = new JsonParser();
                return (JsonObject) parser.parse(data);
            }

            private InputStream openStream(URL url) throws IOException {
                return url.openConnection().getInputStream();
            }

            private void process(String data) {
                sendStatusUpdate("Parsing");
                JsonObject object = this.parse(data);
                JsonArray maps = object.getAsJsonArray("maps");
                sendStatusUpdate("Processing");
                Map<ResourceLocation, List<MapData>> map = new HashMap<>();
                for(int i = 0; i < maps.size(); i++) {
                    JsonObject mapObj = (JsonObject) maps.get(i);
                    MapData mapData = MapData.get(mapObj, mapFiles);
                    if(mapData == null) {
                        Pubgmc.logger.warn("Invalid map entry, ignoring...");
                        continue;
                    }
                    ResourceLocation[] gamemodes = this.getGamemodes(mapData.modes);
                    for(ResourceLocation rl : gamemodes) {
                        map.computeIfAbsent(rl, k -> new ArrayList<>());
                        map.get(rl).add(mapData);
                        addScreenMessage(new Message()
                                .addMessageComponent(new MessageComponent("Loaded map"))
                                .addMessageComponent(new MessageComponent(mapData.displayName, TextFormatting.AQUA))
                                .addMessageComponent(new MessageComponent("for mode", TextFormatting.WHITE))
                                .addMessageComponent(new MessageComponent(rl.toString(), TextFormatting.YELLOW)));
                    }
                }
                dev.toma.pubgmc.content.GuiMainMenu.createData(map);
                sendStatusUpdate("Finished");
                GuiLoadCommunityContent.this.buttonList.get(0).enabled = true;
                compatMode = false;
            }

            private ResourceLocation[] getGamemodes(String[] array) {
                List<ResourceLocation> list = new ArrayList<>(GameRegistry.REGISTRY.keySet());
                List<ResourceLocation> valid = new ArrayList<>();
                for(String s : array) {
                    ResourceLocation rl = new ResourceLocation(s);
                    if(list.contains(rl)) {
                        valid.add(rl);
                    } else Pubgmc.logger.error("Couldn't find game: {}", rl.toString());
                }
                return valid.toArray(new ResourceLocation[0]);
            }
        }.start();
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if(button.id == 0) {
            this.mc.displayGuiScreen(compatMode ? new GuiMainMenu() : new dev.toma.pubgmc.content.GuiMainMenu());
        } else if(button.id == 1) {
            this.loadCommunityMaps();
            this.buttonList.remove(1);
            this.buttonList.get(0).enabled = false;
            this.addScreenMessage(new Message().addMessageComponent(new MessageComponent("Retrying connection...", TextFormatting.WHITE)));
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        float c = 0.25f;
        ImageUtil.drawShape(0, 0, this.width, this.height, c, c, c);
        for(int i = messages.size() - 1; i >= 0; i--) {
            Message message = messages.get(i);
            if(message == null) continue;
            mc.fontRenderer.drawString(message.toString(), 10, this.height - 20 - i * 12, 0xFFFFFFFF);
        }
        GlStateManager.pushMatrix();
        GlStateManager.scale(1.5, 1.5, 1.5);
        mc.fontRenderer.drawStringWithShadow("PUBGMC Content Loader", 10, 10, 0xFFFFFFFF);
        GlStateManager.popMatrix();
        mc.fontRenderer.drawStringWithShadow("Version: 1.0", 15, 30, 0xFFFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void safeLaunch() {
        compatMode = true;
        Pubgmc.logger.fatal("Exception occurred in custom menu!");
        GuiLoadCommunityContent.this.buttonList.get(0).enabled = true;
    }

    private synchronized void addScreenMessage(final Message message) {
        this.messages.add(message);
    }

    private void sendStatusUpdate(String status) {
        this.addScreenMessage(new Message().addMessageComponent(new MessageComponent("Status: ", TextFormatting.WHITE)).addMessageComponent(new MessageComponent(status, TextFormatting.WHITE, TextFormatting.BOLD)));
    }

    private class Message {

        private List<MessageComponent> list = new ArrayList<>();
        private String converted;

        public Message addMessageComponent(final MessageComponent component) {
            this.list.add(component);
            return this;
        }

        @Override
        public String toString() {
            if(this.converted == null) {
                this.convertMsg();
            }
            return converted;
        }

        private void convertMsg() {
            StringBuilder builder = new StringBuilder();
            Iterator<MessageComponent> iterator = this.list.iterator();
            while (iterator.hasNext()) {
                MessageComponent component = iterator.next();
                boolean flag = iterator.hasNext();
                builder.append(component.getAsString()).append(flag ? " " : "");
            }
            this.converted = builder.toString();
        }
    }

    private class MessageComponent {

        private String msg;
        private TextFormatting[] formats;

        public MessageComponent(String message, TextFormatting... formats) {
            this.msg = message;
            this.formats = formats;
        }

        public String getAsString() {
            StringBuilder builder = new StringBuilder();
            for(TextFormatting formatting : formats) {
                builder.append(formatting.toString());
            }
            builder.append(msg);
            return builder.toString();
        }
    }
}
